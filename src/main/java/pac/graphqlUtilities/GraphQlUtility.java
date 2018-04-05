package pac.graphqlUtilities;

import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import pac.dataFetchers.AllAuthorsDataFetcher;
import pac.dataFetchers.BooksDataFetcher;
import pac.dataFetchers.AuthorDataFetcher;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

import static graphql.GraphQL.newGraphQL;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Component
public class GraphQlUtility {

    @Value("classpath:schemas.graphqls")
    private Resource schemaResource;
    private GraphQL graphQL;
    private AllAuthorsDataFetcher allAuthorsDataFetcher;
    private AuthorDataFetcher authorDataFetcher;
    private BooksDataFetcher booksDataFetcher;

    @Autowired
    GraphQlUtility(AllAuthorsDataFetcher allUsersDataFetcher, AuthorDataFetcher userDataFetcher,
                   BooksDataFetcher booksDataFetcher) throws IOException {
        this.allAuthorsDataFetcher = allUsersDataFetcher;
        this.authorDataFetcher = userDataFetcher;
        this.booksDataFetcher = booksDataFetcher;
    }

    @PostConstruct
    public GraphQL createGraphQlObject() throws IOException {
        File schemas = schemaResource.getFile();
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemas);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        return  newGraphQL(schema).build();
    }

    public RuntimeWiring buildRuntimeWiring(){
        return newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                    .dataFetcher("authors", allAuthorsDataFetcher)
                    .dataFetcher("author", authorDataFetcher))
                .type("Author", typeWiring -> typeWiring
                    .dataFetcher("articles", booksDataFetcher)
                    .dataFetcher("friends", allAuthorsDataFetcher))
                .build();
    }
}


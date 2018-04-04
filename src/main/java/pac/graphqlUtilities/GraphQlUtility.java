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
    private AllAuthorsDataFetcher allUsersDataFetcher;
    private AuthorDataFetcher userDataFetcher;
    private BooksDataFetcher articlesDataFetcher;

    @Autowired
    GraphQlUtility(AllAuthorsDataFetcher allUsersDataFetcher, AuthorDataFetcher userDataFetcher,
                   BooksDataFetcher articlesDataFetcher) throws IOException {
        this.allUsersDataFetcher = allUsersDataFetcher;
        this.userDataFetcher = userDataFetcher;
        this.articlesDataFetcher = articlesDataFetcher;
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
                    .dataFetcher("users", allUsersDataFetcher)
                    .dataFetcher("user", userDataFetcher))
                .type("User", typeWiring -> typeWiring
                    .dataFetcher("articles", articlesDataFetcher)
                    .dataFetcher("friends", allUsersDataFetcher))
                .build();
    }
}


package pac.graphqlUtilities;

import static graphql.GraphQL.newGraphQL;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;
import pac.dataFetchers.AllAuthorsDataFetcher;
import pac.dataFetchers.AuthorDataFetcher;
import pac.dataFetchers.BooksDataFetcher;
import pac.dataFetchers.WriteBookDataFetcher;

@Component
public class GraphQlUtility {

    @Value("classpath:schemas.graphqls")
    private Resource schemaResource;
    private GraphQL graphQL;
    
    @Autowired
    private AllAuthorsDataFetcher allAuthorsDataFetcher;
    @Autowired
    private AuthorDataFetcher authorDataFetcher;
    @Autowired
    private BooksDataFetcher booksDataFetcher;
    @Autowired
    private WriteBookDataFetcher writeBookDataFetcher;


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
                    .dataFetcher("author", authorDataFetcher)
                    .dataFetcher("books", booksDataFetcher)
                    .dataFetcher("writeBook", writeBookDataFetcher)
                    
                )
                .type("Author", typeWiring -> typeWiring
                    .dataFetcher("books", booksDataFetcher))
                
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("writeBook", writeBookDataFetcher))
                
                
//                .type(TypeRuntimeWiring.newTypeWiring("Mutation")
//                        .dataFetcher("writeBook", writeBookDataFetcher)
//                )
                
                .build();
    }
    
//    private RuntimeWiring buildRuntimeWiringTeste() {
//        return RuntimeWiring.newRuntimeWiring()
//                .type(TypeRuntimeWiring.newTypeWiring("Query")
//                        .dataFetcher("numberHolder", MutationWiring.numberHolderFetcher))
//                .type(TypeRuntimeWiring.newTypeWiring("theNumber")
//                        .dataFetcher("theNumber", MutationWiring.theNumberFetcher))
//                .type(TypeRuntimeWiring.newTypeWiring("Mutation")
//                        .dataFetcher("changeTheNumber", MutationWiring.changeTheNumberFetcher)
//                        .dataFetcher("failToChangeTheNumber", MutationWiring.failToChangeTheNumberFetcher)
//                )
//                .build();
//    }
}


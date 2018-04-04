package pac.dataFetchers;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import pac.models.Author;

@Component
public class AuthorDataFetcher implements DataFetcher<Author> {

    

    @Override
    public Author get(DataFetchingEnvironment env) {
//        Map args = env.getArguments();
//        Author user = userService.findOneById(new ObjectId(String.valueOf(args.get("id"))));
        return null;
    }
}

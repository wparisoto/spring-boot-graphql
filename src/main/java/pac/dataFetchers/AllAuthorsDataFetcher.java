package pac.dataFetchers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import pac.models.Author;
import pac.repository.AuthorRepository;
@Component
public class AllAuthorsDataFetcher implements DataFetcher<List<Author>> {

	@Autowired
    private AuthorRepository authorRepository;
   

    @Override
    public List<Author> get(DataFetchingEnvironment env) {
        Author user =  env.getSource();
        
        return authorRepository.findAll();
                
    }
}

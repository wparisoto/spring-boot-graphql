package pac.dataFetchers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.language.Selection;
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
        Author author =  env.getSource();
        
        List<Selection> campos = env.getFields().stream().findFirst().get().getSelectionSet().getSelections();
        
        return authorRepository.findAll();
    }
}

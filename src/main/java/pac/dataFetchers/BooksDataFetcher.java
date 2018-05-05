package pac.dataFetchers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import pac.models.Book;
import pac.repository.BookRepository;

@Component
public class BooksDataFetcher implements DataFetcher<List<Book>>{

	@Autowired
    private BookRepository bookRepository;

   

    @Override
    public List<Book> get(DataFetchingEnvironment env) {
    	
    	return bookRepository.findAll();
    }
}

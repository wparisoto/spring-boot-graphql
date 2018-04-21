package pac.dataFetchers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import pac.models.Book;
import pac.repository.BookRepository;

@Component
public class WhiteBookDataFetcher implements DataFetcher<Book>{

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book get(DataFetchingEnvironment environment) {
	
		
	    Map args = environment.getArguments();
	    Long id = (Long) args.get("id");
		String title = String.valueOf(args.get("title"));
		
		Book book = bookRepository.findOne(id);
		book.setTitle(title);
  
		bookRepository.save(book);
      
      return book;
     // Author user = userService.findOneById(new ObjectId(String.valueOf(args.get("id"))));
      
	}

}

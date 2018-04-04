package pac.repository;
import java.util.List;

import pac.crudDefault.IRepositoryDefault;
import pac.models.Author;
import pac.models.Book;

public interface BookRepository extends IRepositoryDefault<Book>{

    List<Author> findByIdIn(List<String> ids);
}

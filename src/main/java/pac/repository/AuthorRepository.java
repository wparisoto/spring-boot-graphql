package pac.repository;
import java.util.List;

import pac.crudDefault.IRepositoryDefault;
import pac.models.Author;

public interface AuthorRepository extends IRepositoryDefault<Author> {

    List<Author> findByIdIn(List<String> ids);
}

package pac.models;



import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import pac.crudDefault.EntidadePersistente;

@Getter
@Setter
@Entity
@Table(schema = "public")
public class Book implements EntidadePersistente{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String isbn;

    @Column
    private int pageCount;

    @ManyToOne
    @JoinColumn(name = "idAuthor", nullable = false)
    private Author author;

}


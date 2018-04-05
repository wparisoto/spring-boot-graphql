package pac.models;



import java.io.Serializable;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import pac.crudDefault.EntidadePersistente;

@Getter
@Setter
@Entity
@Table(schema = "public")
public class Book implements EntidadePersistente, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -500568335985716874L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name="title")
    private String title;

	@Column(name="isbn")
    private String isbn;

	@Column(name="pagecount")
    private int pageCount;

    @ManyToOne
    @JoinColumn(name = "idauthor", nullable = false)
    private Author author;

}


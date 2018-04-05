package pac.models;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import pac.crudDefault.EntidadePersistente;

@Getter
@Setter
@Entity
@Table(schema = "public")
public class Author implements EntidadePersistente, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9088966920148658863L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

}

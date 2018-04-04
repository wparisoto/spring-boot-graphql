/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pac.crudDefault;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author wagner
 * @param <T>
 */
@NoRepositoryBean
public interface IRepositoryDefault <T extends EntidadePersistente> 
    extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
    
}

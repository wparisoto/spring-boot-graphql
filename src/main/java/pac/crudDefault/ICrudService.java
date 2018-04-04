/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pac.crudDefault;

import java.util.List;

/**
 *
 * @author wagner
 * @param <T>
 * @param <R>
 */
public interface ICrudService <T extends EntidadePersistente, R extends IRepositoryDefault<T>>{
    
    public void salvar(T entidade);
    
    public void excluir(T entidade);
    
    public List<T> listarTodos();
}

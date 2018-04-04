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
public abstract class CrudServiceDefault<T extends EntidadePersistente, R extends IRepositoryDefault<T>>
        implements ICrudService<T, R> {

    private R repositoryDefault;

    private Class<T> entidadeClass;

    public CrudServiceDefault(R repositoryDefault) {
        this.repositoryDefault = repositoryDefault;
    }

    @Override
    public void salvar(T entidade) {

        getRepositoryDefault().save(entidade);

    }
    
    @Override
    public void excluir(T entidade) {
        getRepositoryDefault().delete(entidade.getId());
    }

    @Override
    public List<T> listarTodos(){
        return getRepositoryDefault().findAll();
    }
    
    

    public R getRepositoryDefault() {
        return repositoryDefault;
    }

    public void setRepositoryDefault(R repositoryDefault) {
        this.repositoryDefault = repositoryDefault;
    }

    public Class<T> getEntidadeClass() {
        return entidadeClass;
    }

    public void setEntidadeClass(Class<T> entidadeClass) {
        this.entidadeClass = entidadeClass;
    }

}

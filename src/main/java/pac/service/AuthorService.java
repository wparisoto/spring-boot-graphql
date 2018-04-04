/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pac.crudDefault.CrudServiceDefault;
import pac.models.Author;
import pac.repository.AuthorRepository;

/**
 *
 * @author wagner
 */
@Service
public class AuthorService extends CrudServiceDefault<Author, AuthorRepository>{

    @Autowired
    public AuthorService(AuthorRepository repo) {
        super(repo);
    }

    
}

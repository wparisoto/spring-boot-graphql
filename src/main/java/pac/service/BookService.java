/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pac.crudDefault.CrudServiceDefault;
import pac.models.Book;
import pac.repository.BookRepository;

/**
 *
 * @author wagner
 */
@Service
public class BookService extends CrudServiceDefault<Book, BookRepository>{

    @Autowired
    public BookService(BookRepository repo) {
        super(repo);
    }

    
}

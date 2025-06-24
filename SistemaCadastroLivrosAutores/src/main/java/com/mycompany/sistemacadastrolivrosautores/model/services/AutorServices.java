/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacadastrolivrosautores.model.services;

import com.mycompany.sistemacadastrolivrosautores.exceptions.CadastroException;
import com.mycompany.sistemacadastrolivrosautores.model.dao.AutorDao;
import com.mycompany.sistemacadastrolivrosautores.model.domain.Autor;
import java.util.List;

/**
 *
 * @author Thomaz
 */
public class AutorServices {
    private final AutorDao autorDao = new AutorDao();
    
    public void salvarAutor(Autor autor) throws CadastroException {
        if (autor.getNome() == null || autor.getNome() .isBlank()) {
            throw new CadastroException ("A agência não possui um nome");
        }
        
        if (autor.getNacionalidade()== null || autor.getNacionalidade().isBlank()) {
            throw new CadastroException ("O autor não possui uma nacionalidade");
        }
        if (autor.getDataNascimento()== null ) {
            throw new CadastroException("O autor não possui uma data de nascimento informada");
        }

        autorDao.inserirAutor(autor);

    }
    
    public List<Autor> buscarAutors(){
        
        return autorDao.listarTodasAutores();
        
    }
}

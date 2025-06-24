/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacadastrolivrosautores.model.services;

import com.mycompany.sistemacadastrolivrosautores.exceptions.CadastroException;
import com.mycompany.sistemacadastrolivrosautores.model.dao.LivroDao;
import com.mycompany.sistemacadastrolivrosautores.model.domain.Livro;
import java.util.List;

/**
 *
 * @author Thomaz
 */
public class LivroServices {
    private final LivroDao livroDao = new LivroDao();
    
    public void SalvarLivro(Livro livro) throws CadastroException {
        if (livro.getTitulo() == null || livro.getTitulo().isBlank()) {
            throw new CadastroException ("O livro não possui um título");
        }
        
        if (livro.getGenero()== null || livro.getGenero().isBlank()) {
            throw new CadastroException ("O livro não tem genero");
        }
        
        livroDao.inserirLivro(livro);

    }
    
    public List<Livro> buscarLivros(){
        
        return livroDao.listarTodosLivros();
        
    }
}

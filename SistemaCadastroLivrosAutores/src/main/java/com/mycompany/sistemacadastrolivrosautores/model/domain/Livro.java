/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacadastrolivrosautores.model.domain;

/**
 *
 * @author Thomaz
 */
public class Livro {
    private Long idLivro;
    private String titulo;
    private String genero;
    private Autor autor;
    
    public Livro(){
    }

    public Livro(Long idLivro, String titulo, String genero, Autor autor) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
       
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Livro{" + "idLivro=" + idLivro + ", titulo=" + titulo + ", genero=" + genero + ", autor=" + autor + '}';
    }
    
}

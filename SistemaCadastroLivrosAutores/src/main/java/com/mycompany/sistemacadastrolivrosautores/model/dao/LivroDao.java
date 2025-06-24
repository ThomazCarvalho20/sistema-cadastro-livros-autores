/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacadastrolivrosautores.model.dao;

import com.mycompany.sistemacadastrolivrosautores.configurations.MySQL;
import com.mycompany.sistemacadastrolivrosautores.model.domain.Autor;
import com.mycompany.sistemacadastrolivrosautores.model.domain.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomaz
 */
public class LivroDao {

    public void inserirLivro(Livro livro) {
        String sql = "INSERT INTO LIVROS(titulo, genero, id_autor) VALUES (?, ?, ?)";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getGenero());
            ps.setLong(3, livro.getAutor().getIdAutor());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Livro> listarTodosLivros() {
        String sql = "SELECT * FROM Livros";
        List<Livro> autors = new ArrayList<>();
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                autors.add(construirLivroSql(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autors;
    }

    public Livro buscarLivroPorId(Long id) {
        String sql = "SELECT * FROM Livros where id_autor = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirLivroSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Livro construirLivroSql(ResultSet rs) throws SQLException {
        Livro livro = new Livro();
        livro.setIdLivro(rs.getLong("id_livro"));
        livro.setTitulo(rs.getString("titulo"));
        Autor autor = new AutorDao().buscarAutorPorId(rs.getLong("id_autor"));
        livro.setAutor(autor);
        return livro;
    }

    public static void main(String[] args) {
        var livro = new Livro();
        livro.setTitulo("Senho do Anéis");
        livro.setGenero("Fantazia");
        var autor = new AutorDao().buscarAutorPorId(1l);
        livro.setAutor(autor);

        var livroDao = new LivroDao();
        livroDao.inserirLivro(livro);
        System.out.println("==============================");
        List<Livro> livros = livroDao.listarTodosLivros();
        livros.forEach(ag -> System.out.println("Titulo: " + ag.getTitulo()));

    }
}

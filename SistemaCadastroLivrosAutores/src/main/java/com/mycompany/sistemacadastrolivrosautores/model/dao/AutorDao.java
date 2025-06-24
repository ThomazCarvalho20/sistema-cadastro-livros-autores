/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacadastrolivrosautores.model.dao;

import com.mycompany.sistemacadastrolivrosautores.configurations.MySQL;
import com.mycompany.sistemacadastrolivrosautores.model.domain.Autor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thomaz
 */
public class AutorDao {
    public void inserirAutor(Autor autor) {
        String sql = "INSERT INTO AUTORES(nome, nacionalidade, data_nascimento) VALUES (?, ?, ?)";
        try(Connection con = MySQL.connect(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, autor.getNome());
            ps.setString(2, autor.getNacionalidade());
            ps.setDate(3, Date.valueOf(autor.getDataNascimento()));
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Autor> listarTodasAutores() {
        String sql = "SELECT * FROM Autores";
        List<Autor> autores = new ArrayList<>();
        try(Connection con = MySQL.connect(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                autores.add(construirAutorSql(rs));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autores;
    }
    
    public Autor buscarAutorPorId(Long id) {
        String sql = "SELECT * FROM Autores where id_autor = ?";
        try(Connection con = MySQL.connect(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return construirAutorSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private Autor construirAutorSql(ResultSet rs) throws SQLException {
        Autor autor = new Autor();
        autor.setIdAutor(rs.getLong("id_autor"));
        autor.setNome(rs.getString("nome"));
        autor.setNacionalidade(rs.getString("nacionalidade"));
        autor.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        return autor;
    }
    
    public static void main(String[] args) {
        var autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Caxambu");
        autor.setDataNascimento(LocalDate.now());
        
        var autorDao = new AutorDao();
        autorDao.inserirAutor(autor);
        System.out.println("==============================");
        List<Autor> autores = autorDao.listarTodasAutores();
        autores.forEach(ag -> System.out.println("Codigo: " + ag.getNome()));
        
        autorDao.buscarAutorPorId(1l);

    }
}

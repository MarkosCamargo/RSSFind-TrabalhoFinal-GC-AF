/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Matrix
 */
public class Conection implements ConectionSQLite {

    public Conection() {
        setCreateTables();
    }

    @Override
    public void setUpdate(String script) {

        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate(script);
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        }
    }

    @Override
    public void setInsert(String script) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate(script);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        }
    }

    private void setCreateTables() {
        Statement comando = null;
        try {
            comando = getConection().createStatement();
            String script = "CREATE  TABLE  IF NOT EXISTS \"Termo\" (\"ID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , \"palavra\" VARCHAR); \n"
                    + "CREATE  TABLE  IF NOT EXISTS \"Site\" (\"ID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , \"Url\" VARCHAR); \n"
                    + "CREATE  TABLE  IF NOT EXISTS \"Noticia\" (\"ID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , \"Titulo\" VARCHAR, \"Link\" VARCHAR, \"Noticia\" VARCHAR);";

            comando.executeUpdate(script);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        }

    }

    @Override
    public void setDelete(String script) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate(script);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        }
    }

    private java.sql.Connection getConection() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:DataBase\\RssFind.sqlite");
            return c;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ArrayList<URL> getListaUrl() {
        ArrayList<URL> lista = new ArrayList<>();
        Statement comando;
        try {
            comando = getConection().createStatement();
            ResultSet resultado = comando.executeQuery("select Url from Site");
            while (resultado.next()) {
                String Url = resultado.getString("Url");
                lista.add(new URL(Url));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

}

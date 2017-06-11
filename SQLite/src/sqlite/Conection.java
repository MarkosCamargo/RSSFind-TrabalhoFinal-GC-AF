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
        } finally {
            FecharConexao();
        }
    }

    @Override
    public void setInsert(String script) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate(script);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }
    }

    private void setCreateTables() {
        Statement comando = null;
        try {
            comando = getConection().createStatement();
            String script = "CREATE  TABLE  IF NOT EXISTS \"Termo\" (\"ID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , \"palavra\" VARCHAR); \n"
                    + "CREATE  TABLE  IF NOT EXISTS \"Site\" (\"ID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , \"Url\" VARCHAR); \n"
                    + "CREATE  TABLE  IF NOT EXISTS \"Horario\" (\"ID\" INTEGER PRIMARY KEY  NOT NULL  UNIQUE , \"Horario\" VARCHAR); \n"
                    + "CREATE  TABLE  IF NOT EXISTS \"Email\" (\"ID\" INTEGER PRIMARY KEY  NOT NULL  UNIQUE , \"Email\" VARCHAR NOT NULL , \"senha\" VARCHAR NOT NULL); \n"
                    + "CREATE  TABLE  IF NOT EXISTS \"Noticia\" (\"ID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , \"Titulo\" VARCHAR, \"Link\" VARCHAR); \n";

            comando.executeUpdate(script);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }

    }

    @Override
    public void setDelete(String script) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate(script);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
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
        } finally {
            FecharConexao();
        }

        return lista;
    }

    @Override
    public ArrayList<String> getListaTermos() {
        ArrayList<String> lista = new ArrayList<>();
        Statement comando;
        try {
            comando = getConection().createStatement();
            ResultSet resultado = comando.executeQuery("select ID, palavra from Termo");
            while (resultado.next()) {
                String objeto = resultado.getInt("ID") + "#" + resultado.getString("palavra");

                lista.add(objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            FecharConexao();
        }

        return lista;
    }

    @Override
    public ArrayList<String> getListaHorarios() {
        ArrayList<String> lista = new ArrayList<>();
        Statement comando;
        try {
            comando = getConection().createStatement();
            ResultSet resultado = comando.executeQuery("select ID, Horario from Horario");
            while (resultado.next()) {
                String objetoHorario = resultado.getInt("ID") + "#" + resultado.getString("Horario");
                lista.add(objetoHorario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            FecharConexao();
        }

        return lista;
    }

    @Override
    public String getRegistroString(String script, String coluna) {
        String colunaRetorno = "";
        Statement comando;
        try {
            comando = getConection().createStatement();
            ResultSet resultado = comando.executeQuery(script);
            if (resultado.next()) {
                colunaRetorno = resultado.getString(coluna);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            FecharConexao();
        }

        return colunaRetorno;
    }

    @Override
    public void setInsertUrl(String url) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate("insert into Site(Url) values(\"" + url + "\")");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }
    }

    public boolean FecharConexao() {
        try {
            getConection().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void setInsertTermos(String termo) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate("insert into Termo(palavra) values(\"" + termo + "\")");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }
    }

    @Override
    public void setInsertHorario(String horario) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate("insert into horario(horario) values(\"" + horario + "\")");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }
    }

    @Override
    public void setUpdateUrl(int id, String novaUrl) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate("update site set Url = \"" + novaUrl + "\" where ID = " + id + "");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }

    }

    @Override
    public void setUpdateTermo(int id, String novoTermo) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate("update Termo set palavra = \"" + novoTermo + "\" where ID = " + id + " ");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }
    }

    @Override
    public void setUpdateHorario(int id, String novoHorario) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate("update Horario set Horario = \"" + novoHorario + "\" where ID = " + id + "");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }
    }

    @Override
    public ArrayList<String> getListaUrlString() {
        ArrayList<String> lista = new ArrayList<>();
        Statement comando;
        try {
            comando = getConection().createStatement();
            ResultSet resultado = comando.executeQuery("select ID,Url from Site");
            while (resultado.next()) {
                String objeto = resultado.getInt("ID") + "#" + resultado.getString("Url");
                lista.add(objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            FecharConexao();
        }

        return lista;
    }

    @Override
    public void setDeleteTermos(int id) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate("Delete from Termo where ID = " + id + "");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }
    }

    @Override
    public void setDeleteHorario(int id) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate("Delete from Termo where ID = " + id + "");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }
    }

    @Override
    public void setDeleteUrl(int id) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate("Delete from Site where ID = " + id + "");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }
    }

    @Override
    public void setInsertNoticiaEncontrada(String titulo, String link) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate("insert into Noticia(Titulo, Link) values(\"" + titulo + "\", \"" + link + "\")");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }
    }

    @Override
    public ArrayList<String> getListaNoticiasEncontradas() {
        ArrayList<String> lista = new ArrayList<>();
        Statement comando;
        try {
            comando = getConection().createStatement();
            ResultSet resultado = comando.executeQuery("select ID, Titulo, Link from Noticia");
            while (resultado.next()) {
                String objeto = resultado.getInt("ID") + "#" + resultado.getString("Titulo") + "#" + resultado.getString("Link");
                lista.add(objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            FecharConexao();
        }

        return lista;
    }

    @Override
    public void setDeleteNoticia(int id) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate("Delete from Noticia where ID = " + id + "");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }
    }

    @Override
    public boolean getNoticiaJaInserida(String titulo) {
        boolean noticiaJaInserida = false;

        Statement comando;
        try {
            comando = getConection().createStatement();
            ResultSet resultado = comando.executeQuery("SELECT ID FROM Noticia where Titulo like \"" + titulo + "\"");
            noticiaJaInserida = resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
            noticiaJaInserida = false;
        } finally {
            FecharConexao();
        }

        return noticiaJaInserida;

    }

    @Override
    public void setInsertEmail(String email, String senha) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate("Insert into Email(Email, senha) Values (\"" + email + "\", \"" + senha + "\")");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }
    }

    @Override
    public void setDeleteEmail(int id) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate("Delete from Email where ID = " + id + "");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }
    }

    @Override
    public void setUpdateEmail(int id, String novoEmail, String novaSenha) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate("update Email set Email = \"" + novoEmail + "\" where ID = " + id + "");

            if (!novaSenha.isEmpty()) {
                comando.executeUpdate("update Email set senha = \"" + novaSenha + "\" where ID = " + id + "");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, Conection.class.getName());
        } finally {
            FecharConexao();
        }
    }

    @Override
    public ArrayList<String> getListaEmail() {
        ArrayList<String> lista = new ArrayList<>();
        Statement comando;
        try {
            comando = getConection().createStatement();
            ResultSet resultado = comando.executeQuery("select ID,Email from Email");
            while (resultado.next()) {
                String objeto = resultado.getInt("ID") + "#" + resultado.getString("Email");
                lista.add(objeto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            FecharConexao();
        }

        return lista;
    }

}

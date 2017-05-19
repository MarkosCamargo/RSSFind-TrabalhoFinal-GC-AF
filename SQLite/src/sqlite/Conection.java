/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matrix
 */
public class Conection implements ConectionSQLite {

    @Override
    public void setUpdate(String script) {

        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate(script);
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setInsert(String script) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate(txtParaString("src\\database\\DDL-DML.txt"));
            comando.executeUpdate(script);
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDelete(String script) {
        try {
            Statement comando = getConection().createStatement();
            comando.executeUpdate(script);
        } catch (SQLException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private java.sql.Connection getConection() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src\\database\\RssFind.sqlite");
            return c;
        } catch (Exception e) {
            return null;
        }
    }

    private String txtParaString(String caminho) {
        try {
            File file = new File(caminho);//txt que contém comandos DDL e DML
            //ISO-8859-1 para ler as acentuações corretamente:
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Erro";
    }

}

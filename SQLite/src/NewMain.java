
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sqlite.Conection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matrix
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conection t = new Conection();
        t.setInsert("insert into site(url) values(\"www.valor.com.br/rss\");");
        t.setInsert("insert into site(url) values(\"www.google.com.br\");");

        ArrayList<URL> lista = t.getListaUrl();

        for (URL lista1 : lista) {
            JOptionPane.showMessageDialog(null, lista1);
        }

    }

}

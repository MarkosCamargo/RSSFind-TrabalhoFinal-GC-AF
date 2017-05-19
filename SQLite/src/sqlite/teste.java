/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite;

/**
 *
 * @author Matrix
 */
public class teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConectionSQLite teste = new Conection();
        teste.setInsert("insert into Site(url)values(\"www.google.com.br\");");
    }
    
}

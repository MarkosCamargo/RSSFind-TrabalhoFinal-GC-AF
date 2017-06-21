/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.udesc.ceavi.findnews.agendador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Log {

    private String path;

    public Log() {
        this.path = "log.txt";
    }

    public void logMessage(String message) throws IOException {
        FileWriter filewriter = new FileWriter(path, true);
        BufferedWriter out = new BufferedWriter(filewriter);
        out.write(new Date().toLocaleString()+ " - " + message);
        out.newLine();
        //close buffer writer
        out.close();

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.udesc.ceavi.findnews.agendador;

import java.util.Date;
import java.util.TimerTask;
import br.com.udesc.ceavi.findnews.leitorrss.IRSSImpl;

public class Agendador extends TimerTask {

    Date instanteAtual;

    @Override
    public void run() {
        instanteAtual = new Date();
        System.out.println("Processo Executado no Horario Definido: " + instanteAtual);
        new IRSSImpl().verificaNoticiasManual();
    }

}

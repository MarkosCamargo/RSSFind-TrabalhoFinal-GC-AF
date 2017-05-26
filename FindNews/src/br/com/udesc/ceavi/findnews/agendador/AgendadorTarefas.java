/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.udesc.ceavi.findnews.agendador;

import br.com.udesc.ceavi.findnews.modelo.Horario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

/**
 *
 * @author marcos
 */
public class AgendadorTarefas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Timer timer = new Timer();
        Agendador agendador = new Agendador();
        Date d = new Date();

        //Pegar os horarios do banco de dados
        List<Horario> horarios = new ArrayList<>();
        horarios.add(new Horario(20, 00));

        d.setHours(horarios.get(0).getHora());
        d.setMinutes(horarios.get(0).getMinuto());

        timer.schedule(agendador, d);

        while (true) {
            System.out.println("Aguardando o Horario ...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

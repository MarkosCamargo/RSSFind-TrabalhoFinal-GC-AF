/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.udesc.ceavi.findnews.agendador;

import br.com.udesc.ceavi.findnews.modelo.Horario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import sqlite.Conection;

/**
 *
 * @author marcos
 */
public class IAgendadorImpl implements IAgendador {

    @Override
    public void run() {
        Log l;
        try {
            l = new Log();

            //Pegar os horarios do banco de dados
            System.out.println("Começou a Execuçao");
            l.logMessage("Começou a Execuçao");

            Conection conn = new Conection();
            List<String> horariosBD = conn.getListaHorarios();
            List<Horario> horarios = new ArrayList<>();
            for (String hora : horariosBD) {
                Horario horario = new Horario();
                String[] objeto = hora.split("#");
                horario.setHora(Integer.parseInt(objeto[1].substring(0, 2)));
                horario.setMinuto(Integer.parseInt(objeto[1].substring(3, 5)));
                horarios.add(horario);
            }
            if (horarios.size() == 0) {
                System.out.println("Cadastre um horario");
                l.logMessage("Cadastre um horario");
            }

            for (Horario horario : horarios) {
                Agendador agendador = new Agendador();
                Timer timer = new Timer();
                Date d = new Date();

                d.setHours(horario.getHora());
                d.setMinutes(horario.getMinuto());
                d.setSeconds(0);
                System.out.println("Agendado para Executar: as " + horario.getHora() + ":" + horario.getMinuto());
                l.logMessage("Agendado para Executar: as " + horario.getHora() + ":" + horario.getMinuto());
                timer.schedule(agendador, d);
//            timer.purge();s

            }
        } catch (IOException ex) {
            Logger.getLogger(IAgendadorImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

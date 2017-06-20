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
import sqlite.Conection;

/**
 *
 * @author marcos
 */
public class IAgendadorImpl implements IAgendador {

    @Override
    public void run() {
        //Pegar os horarios do banco de dados
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
        
        for (Horario horario : horarios) {
            Agendador agendador = new Agendador();
            Timer timer = new Timer();
            Date d = new Date();

            d.setHours(horario.getHora());
            d.setMinutes(horario.getMinuto());
            d.setSeconds(0);

            timer.schedule(agendador, d);
//            timer.purge();s

        }
    }

}

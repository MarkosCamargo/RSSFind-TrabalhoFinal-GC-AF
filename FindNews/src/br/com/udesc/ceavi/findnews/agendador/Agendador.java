/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.udesc.ceavi.findnews.agendador;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import br.com.udesc.ceavi.findnews.leitorrss.IRSSImpl;
import br.com.udesc.ceavi.findnews.modelo.Noticia;
import br.com.udesc.ceavi.findnews.leitorrss.ImailImpl;

public class Agendador extends TimerTask {

    Date instanteAtual;

    @Override
    public void run() {
        instanteAtual = new Date();
        System.out.println("Processo Executado no Horario Definido: " + instanteAtual);
        List<Noticia> noticias = new IRSSImpl().verificaNoticiasManual();

        ///Monta e envia o email
        String msg = "Foram encontrados link interessantes\n";
        for (Noticia noticia : noticias) {
            msg = msg + noticia.getTitulo() + "\n" + noticia.getLink();
        }
        new ImailImpl().sendEmail("markos_camargo@hotmail.com", "senhadousuario", "markos_camargo@hotmail.com", msg);
    }

}


import br.com.udesc.ceavi.findnews.leitorrss.IMail;
import br.com.udesc.ceavi.findnews.leitorrss.IRSS;
import br.com.udesc.ceavi.findnews.leitorrss.IRSSImpl;
import br.com.udesc.ceavi.findnews.leitorrss.ImailImpl;
import br.com.udesc.ceavi.findnews.modelo.Noticia;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import sqlite.Conection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marcos
 */
public class Test {

    public static void main(String[] args) {
        //BANCO DE DADOS
//        Conection conn = new Conection();
//        conn.setDelete("drop table Site");
//        conn.setDelete("drop table Termo");
//        conn.setDelete("drop table Noticia");
//        conn.setInsert("insert into site(url) values(\"http://www.valor.com.br/financas/rss\");");
//        ArrayList<URL> lista = conn.getListaUrl();
//        for (URL lista1 : lista) {
//            JOptionPane.showMessageDialog(null, lista1);
//        }

        //TESTE VERIFICA SITES MANUAL E MONTA MSG PARA ENVIAR POR EMAIL CASO ENCONTRE ALGO
        IRSS r = new IRSSImpl();
        String msgSendEmail = "Foram encontrados noticias interessantes\n\n\n";
        List<Noticia> noticiasEncontradas = r.verificaNoticiasManual();
        for (Noticia noticiasEncontrada : noticiasEncontradas) {
            System.out.println("Title: " + noticiasEncontrada.getTitulo() + "\nDesc: " + noticiasEncontrada.getNoticia() + "\n");
            msgSendEmail = msgSendEmail + noticiasEncontrada.getTitulo() + "  -  LINK: " + noticiasEncontrada.getLink() + "\n";
        }

        //TESTE ENVIA EMAIL
        if (noticiasEncontradas.size() > 0) {
            IMail i = new ImailImpl();
            i.sendEmail("markos_camargo@hotmail.com", "suasenha", "markos_camargo@hotmail.com", msgSendEmail);
        }
    }
}

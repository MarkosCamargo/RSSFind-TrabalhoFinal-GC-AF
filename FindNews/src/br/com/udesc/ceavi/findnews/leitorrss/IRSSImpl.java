/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.udesc.ceavi.findnews.leitorrss;

import br.com.udesc.ceavi.findnews.modelo.Noticia;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sqlite.Conection;

/**
 *
 * @author marcos
 */
public class IRSSImpl implements IRSS {

    @Override
    public List<Noticia> verificaNoticiasManual() {

        //pegar os sites do banco de dados
        Conection conn = new Conection();
        List<URL> sites = conn.getListaUrl();

        List<String> palavrasChaves1 = conn.getListaTermos();
        List<String> palavrasChaves = new ArrayList<>();
        for (String palavrasChave : palavrasChaves1) {
            String[] filtro = palavrasChave.split("#");
            palavrasChaves.add(filtro[1]);
        }
//        palavrasChaves.add("dólar");
//        palavrasChaves.add("Dinheiro");
        List<Noticia> noticias = new ArrayList<>();
        for (URL site : sites) {
            Charset inputCharset = Charset.forName("UTF-8");

            HttpURLConnection httpcon = null;
            try {
                httpcon = (HttpURLConnection) site.openConnection();
            } catch (IOException ex) {
                Logger.getLogger(IRSSImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Reading the feed
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = null;
            try {
                feed = input.build(new InputStreamReader(httpcon.getInputStream(), inputCharset));
            } catch (IOException ex) {
                Logger.getLogger(IRSSImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(IRSSImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FeedException ex) {
                Logger.getLogger(IRSSImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            List entries = feed.getEntries();
            Iterator itEntries = entries.iterator();

            while (itEntries.hasNext()) {
                SyndEntry entry = (SyndEntry) itEntries.next();
                for (String chave : palavrasChaves) {
                    if (entry.getTitle().contains(chave) || entry.getDescription().getValue().contains(chave)) {
                        try {
//                            noticias.add(new Noticia(entry.getTitle(), entry.getDescription().getValue(), new URL(entry.getLink())));

                            if (!conn.getNoticiaJaInserida(entry.getTitle())) {//se a noticia e nova ele insere
                                conn.setInsertNoticiaEncontrada(entry.getTitle(), entry.getLink());
                                noticias.add(new Noticia(entry.getTitle(), entry.getDescription().getValue(), new URL(entry.getLink())));
                            }

                        } catch (MalformedURLException ex) {
                            Logger.getLogger(IRSSImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        if (noticias.size() > 0) {
            montaMSGEmail(noticias);
        }
        return noticias;
    }

    public void montaMSGEmail(List<Noticia> noticias) {
//        if (noticias.size() > 0) {
            String msgSendEmail = "";
            //pegar o login do banco de dados
            Conection conn = new Conection();
            List<String> login = conn.getListaEmail();
            String[] filtro = null;
            for (String string : login) {
                filtro = string.split("#");
            }

            for (Noticia noticiasEncontrada : noticias) {
                System.out.println("Title: " + noticiasEncontrada.getTitulo() + "\nDesc: " + noticiasEncontrada.getNoticia() + "\n");
                msgSendEmail = msgSendEmail + noticiasEncontrada.getTitulo() + "  -  LINK: " + noticiasEncontrada.getLink() + "\n";
            }

            IMail i = new ImailImpl();
            i.sendEmail(filtro[1], filtro[2], filtro[1], msgSendEmail);
//        }
    }

}

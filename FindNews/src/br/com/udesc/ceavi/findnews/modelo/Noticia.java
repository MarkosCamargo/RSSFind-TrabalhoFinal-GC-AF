/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.udesc.ceavi.findnews.modelo;

import java.net.URL;

/**
 *
 * @author marcos
 */
public class Noticia {

    private String titulo;
    private String noticia;
    private URL link;

    public Noticia() {
    }

    public Noticia(String titulo, String noticia, URL link) {
        this.titulo = titulo;
        this.noticia = noticia;
        this.link = link;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNoticia() {
        return noticia;
    }

    public void setNoticia(String noticia) {
        this.noticia = noticia;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

}

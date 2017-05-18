/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.udesc.ceavi.findnews.leitorrss;

/**
 *
 * @author marcos
 */
public interface IMail {

    /**
     * Metodo que busca faz o envio de email
     *
     */
    public void sendEmail(String emailTo,String senha, String emailFrom, String msg);

}

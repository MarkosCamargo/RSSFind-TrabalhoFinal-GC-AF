/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CCE User
 */
public class formPrincipal {

    view.formRssFind formPrincipal = null;
    controller.formCadastroSites telaCadastroSites = null;
    controller.formCadastroTermos telaCadastroTermos = null;
    controller.formCadastroHorarios telaCadastroHorarios = null;
    controller.formEditarSites telaEditarSites = null;
    controller.formEditarTermo telaEditarTermos = null;
    controller.formEditarHorario telaEditarHorario = null;
    controller.formEditarNoticias telaEditarNoticias = null;
    controller.formVerificacaoManual telaVerificacaoManual = null;

    public formPrincipal() throws IOException {
        formPrincipal = new view.formRssFind();
        telaCadastroSites = new controller.formCadastroSites();
        telaCadastroTermos = new controller.formCadastroTermos();
        telaCadastroHorarios = new controller.formCadastroHorarios();
        telaEditarSites = new controller.formEditarSites();
        telaEditarTermos = new controller.formEditarTermo();
        telaEditarHorario = new controller.formEditarHorario();
        telaEditarNoticias = new controller.formEditarNoticias();
        telaVerificacaoManual = new controller.formVerificacaoManual();
        ligaEventos();

    }

    public void chamaTelaPrincipal() {
        formPrincipal.setVisible(true);
    }

    private void ligaEventos() {

        formPrincipal.mmVerificacaoManual.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                telaVerificacaoManual.chamaTela();
            }
        });

        formPrincipal.mmEditarNoticias.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    telaEditarNoticias.chamaTelaEditarNoticias();
                } catch (IOException ex) {
                    Logger.getLogger(formPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        formPrincipal.mmEditarHorario.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    telaEditarHorario.chamaTelaEditarHorario();
                } catch (IOException ex) {
                    Logger.getLogger(formPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        formPrincipal.mmEditarTemros.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    telaEditarTermos.chamaTelaEditarTermos();
                } catch (IOException ex) {
                    Logger.getLogger(formPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        formPrincipal.mmEditarSites.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    telaEditarSites.chamaTelaEditarSites();
                } catch (IOException ex) {
                    Logger.getLogger(formPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        formPrincipal.mmCadastroHorario.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                telaCadastroHorarios.chamaTelaCadastroHorarios();
            }
        });

        formPrincipal.mmCadastroSites.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                telaCadastroSites.chamaTelaCadastroSites();
            }
        });

        formPrincipal.mmCadastrarTermos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                telaCadastroTermos.chamaTelaCadastroTermos();
            }
        });

    }

}

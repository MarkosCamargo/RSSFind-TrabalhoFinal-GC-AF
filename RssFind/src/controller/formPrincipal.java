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

    public formPrincipal() {
        formPrincipal = new view.formRssFind();
        telaCadastroSites = new controller.formCadastroSites();
        telaCadastroTermos = new controller.formCadastroTermos();
        telaCadastroHorarios = new controller.formCadastroHorarios();
        telaEditarSites = new controller.formEditarSites();
        ligaEventos();

    }

    public void chamaTelaPrincipal() {
        formPrincipal.setVisible(true);
    }

    private void ligaEventos() {

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

        formPrincipal.mmSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

}

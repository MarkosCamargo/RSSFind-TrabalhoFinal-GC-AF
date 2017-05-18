/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author CCE User
 */
public class formPrincipal {

    view.formRssFind formPrincipal = null;
    controller.formCadastroSites telaCadastroSites = null;
   

    public formPrincipal() {
        formPrincipal = new view.formRssFind();
        telaCadastroSites = new controller.formCadastroSites();
        
        ligaEventos();

    }

    public void chamaTelaPrincipal() {
        formPrincipal.setVisible(true);
    }

    private void ligaEventos() {
        formPrincipal.mmCadastroSites.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                telaCadastroSites.chamaTelaCadastroSites();

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

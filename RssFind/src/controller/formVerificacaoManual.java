/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *
 * @author Matrix
 */
public class formVerificacaoManual {

    view.formVerificacaoManual tela = null;

    public formVerificacaoManual() throws IOException {
        tela = new view.formVerificacaoManual(null, true);

        ligaEventos();
    }

    public void chamaTela() {
        tela.setVisible(true);
    }

    private void sair() {
        tela.setVisible(false);
    }

    private void ligaEventos() throws IOException {

        tela.btnVerificacao.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        tela.btnSair.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });

    }

}

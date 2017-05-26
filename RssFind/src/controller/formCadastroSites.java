/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author CCE User
 */
public class formCadastroSites {

    private view.formCadastroSites telaCadastroSites = null;
    private sqlite.Conection banco = null;

    public formCadastroSites() {
        telaCadastroSites = new view.formCadastroSites(null, true);
        banco = new sqlite.Conection();
        ligaEventos();
    }

    private void limparTela() {
        telaCadastroSites.edUrl.setText("");
        telaCadastroSites.edUrl.requestFocus();
    }

    public void chamaTelaCadastroSites() {
        limparTela();
        telaCadastroSites.setVisible(true);
    }

    private void gravarUrl() {
        if (telaCadastroSites.edUrl.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Obrigatório informar URL");
        } else {
            banco.setInsertUrl(telaCadastroSites.edUrl.getText());
        }
    }

    private void fecharTelaCadastroSites() {
        telaCadastroSites.setVisible(false);
    }

    private void ligaEventos() {

        telaCadastroSites.btnGravar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               gravarUrl();
            }
        });

        telaCadastroSites.btnSair.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fecharTelaCadastroSites();
            }
        });

        telaCadastroSites.btnLimpar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                limparTela();
            }
        });

    }

}

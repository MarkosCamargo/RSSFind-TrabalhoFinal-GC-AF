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
 * @author Matrix
 */
public class formCadastroEmail {
    private view.formCadastroEmail telaCadastroEmail = null;
    private sqlite.Conection banco = null;

    public formCadastroEmail() {
        telaCadastroEmail = new view.formCadastroEmail(null, true);
        banco = new sqlite.Conection();
        ligaEventos();
    }

    private void limparTela() {
        telaCadastroEmail.edemail.setText("");
    
    }

    public void chamaTelaCadastroEmail() {
        limparTela();
        telaCadastroEmail.setVisible(true);
    }

    private void gravarUrl() {
        if (telaCadastroEmail.edemail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Obrigatório informar Email.");
        } else {
            banco.setInsertEmail(telaCadastroEmail.edemail.getText());
            JOptionPane.showMessageDialog(null, "Incluido com sucesso.");
            limparTela();
        }
    }

    private void fecharTelaCadastroEmail() {
        telaCadastroEmail.setVisible(false);
    }

    private void ligaEventos() {

        telaCadastroEmail.btnGravar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gravarUrl();
            }
        });

        telaCadastroEmail.btnSair.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fecharTelaCadastroEmail();
            }
        });

        telaCadastroEmail.btnLimpar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                limparTela();
            }
        });

    }
    
}

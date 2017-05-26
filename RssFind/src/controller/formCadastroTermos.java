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
public class formCadastroTermos {

    private view.formCadastroTermos telaCadastroTermos = null;
    private sqlite.Conection banco = null;

    public formCadastroTermos() {
        telaCadastroTermos = new view.formCadastroTermos(null, true);
        banco = new sqlite.Conection();
        ligaEventos();
    }

    private void limparTela() {
        telaCadastroTermos.edTermo.setText("");
        telaCadastroTermos.edTermo.requestFocus();
    }

    public void chamaTelaCadastroTermos() {
        limparTela();
        telaCadastroTermos.setVisible(true);
    }

    private void gravarUrl() {
        if (telaCadastroTermos.edTermo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Obrigatório informar Termo.");
        } else {
            banco.setInsertUrl(telaCadastroTermos.edTermo.getText());
            JOptionPane.showMessageDialog(null, "Incluido com sucesso.");
            limparTela();
        }
    }

    private void fecharTelaCadastroSites() {
        telaCadastroTermos.setVisible(false);
    }

    private void ligaEventos() {

        telaCadastroTermos.btnGravar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gravarUrl();
            }
        });

        telaCadastroTermos.btnSair.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fecharTelaCadastroSites();
            }
        });

        telaCadastroTermos.btnLimpar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                limparTela();
            }
        });

    }

}

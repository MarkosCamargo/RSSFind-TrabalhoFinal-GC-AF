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
public class formCadastroHorarios {

    private view.formCadastroHorarios telaCadastroHorarios = null;
    private sqlite.Conection banco = null;

    public formCadastroHorarios() {
        telaCadastroHorarios = new view.formCadastroHorarios(null, true);
        banco = new sqlite.Conection();
        ligaEventos();
    }

    private void limparTela() {
        telaCadastroHorarios.edHora.setText("");
        telaCadastroHorarios.edMinutos.setText("");
        telaCadastroHorarios.edHora.requestFocus();
    }

    public void chamaTelaCadastroHorarios() {
        limparTela();
        telaCadastroHorarios.setVisible(true);
    }

    private void gravar() {
        if (telaCadastroHorarios.edHora.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Obrigatório informar Hora.");
            telaCadastroHorarios.edHora.requestFocus();
        } else if (telaCadastroHorarios.edMinutos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Obrigatório informar Minutos.");
            telaCadastroHorarios.edMinutos.requestFocus();
        } else {
            String horario = telaCadastroHorarios.edHora.getText() + "." + telaCadastroHorarios.edMinutos.getText();

            banco.setInsertHorario(horario);
            JOptionPane.showMessageDialog(null, "Incluido com sucesso.");
            limparTela();
        }
    }

    private void fecharTelaCadastroSites() {
        telaCadastroHorarios.setVisible(false);
    }

    private void ligaEventos() {

        telaCadastroHorarios.btnGravar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gravar();
            }
        });

        telaCadastroHorarios.btnSair.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fecharTelaCadastroSites();
            }
        });

        telaCadastroHorarios.btnLimpar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                limparTela();
            }
        });

    }

}

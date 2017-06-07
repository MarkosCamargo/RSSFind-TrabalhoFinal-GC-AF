/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Email;

/**
 *
 * @author Matrix
 */
public class formEditarEmail {

    private view.ModelEmail TableModel = null;
    private view.formEditarEmail tela = null;
    private ArrayList<Email> listaEmail = null;
    private sqlite.Conection banco = null;
    private String emailAtual = "";
    private int idEmail = 0;

    public formEditarEmail() throws IOException {
        tela = new view.formEditarEmail(null, true);
        listaEmail = new ArrayList<Email>();
        TableModel = new view.ModelEmail();
        tela.tableEmail.setModel(TableModel);
        banco = new sqlite.Conection();

        ligaEventos();
    }

    private void mouseCliqueTable() {

        int Posicao = tela.tableEmail.getSelectedRow();
        Email email = TableModel.GetPosition(Posicao);
        idEmail = email.getId();
        emailAtual = email.getEmail();
        tela.edEmail.setText(emailAtual);

    }

    private void sair() {
        tela.setVisible(false);
    }

    private void alterar() throws IOException {
        String novoEmail = tela.edEmail.getText();
        if (novoEmail.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Obrigatório informar a novo Email para alterar.");
        } else if (!novoEmail.equalsIgnoreCase(emailAtual)) {
            banco.setUpdateEmail(idEmail, novoEmail);
            CarregaTableModel();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            limparTela();
        }
    }

    private void excluir() throws IOException {
        if (listaEmail.size() < 1) {
            JOptionPane.showMessageDialog(null, "Nenhum registro gravado para excluir.");
        } else {

            if (tela.edEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Obrigatório Selecionar Email para excluir.");
            } else {
                banco.setDeleteEmail(idEmail);
                CarregaTableModel();
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
                limparTela();
            }
        }
    }

    private void ligaEventos() throws IOException {

        tela.btnExcluir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    excluir();
                } catch (IOException ex) {
                    Logger.getLogger(formEditarSites.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        tela.btnAlterar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    alterar();
                } catch (IOException ex) {
                    Logger.getLogger(formEditarSites.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        tela.btnSair.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });

        tela.tableEmail.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                mouseCliqueTable();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    private void limparTela() {
        tela.edEmail.setText("");
    }

    public void chamaTelaEditarEmail() throws IOException {
        limparTela();
        CarregaTableModel();
        tela.setVisible(true);
    }

    private void CarregaTableModel() throws FileNotFoundException, IOException {
        listaEmail.clear();
        TableModel.Limpar();
        ArrayList<String> listaEmailString = banco.getListaEmail();

        for (int i = 0; i < listaEmailString.size(); i++) {
            Email email = new Email();
            String emailCompleto = listaEmailString.get(i);
            String[] objeto = emailCompleto.split("#");
            email.setId(Integer.parseInt(objeto[0]));
            email.setEmail(objeto[1]);
            listaEmail.add(email);
            TableModel.AddEmail(email);
        }

    }

}

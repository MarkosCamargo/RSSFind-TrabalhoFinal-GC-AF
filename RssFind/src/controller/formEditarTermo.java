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
import model.Termo;

/**
 *
 * @author Matrix
 */
public class formEditarTermo {

    private view.ModelTermos TableModel = null;
    private view.formEditarTermos tela = null;
    private ArrayList<Termo> ListaTermos = null;
    private sqlite.Conection banco = null;
    private String termoAtual = "";
    private int idTermo = 0;

    public formEditarTermo() throws IOException {
        tela = new view.formEditarTermos(null, true);
        ListaTermos = new ArrayList<Termo>();
        TableModel = new view.ModelTermos();
        tela.tableTermos.setModel(TableModel);
        banco = new sqlite.Conection();

        ligaEventos();
    }

    private void mouseCliqueTable() {

        int Posicao = tela.tableTermos.getSelectedRow();
        Termo termo = TableModel.GetPosition(Posicao);
        idTermo = termo.getId();
        termoAtual = termo.getPalavra();
        tela.edTermo.setText(termoAtual);

    }

    private void sair() {
        tela.setVisible(false);
    }

    private void alterar() throws IOException {
        String novoTermo = tela.edTermo.getText();
        if (novoTermo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Obrigatório informar o novo termo para alterar.");
        } else if (!novoTermo.equalsIgnoreCase(termoAtual)) {
            banco.setUpdateTermo(idTermo, novoTermo);
            CarregaTableModel();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            limparTela();
        }
    }

    private void ligaEventos() throws IOException {

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

        tela.tableTermos.addMouseListener(new MouseListener() {

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
        tela.edTermo.setText("");
    }

    public void chamaTelaEditarTermos() throws IOException {
        limparTela();
        CarregaTableModel();
        tela.setVisible(true);
    }

    private void CarregaTableModel() throws FileNotFoundException, IOException {
        ListaTermos.clear();
        TableModel.Limpar();
        ArrayList<String> listaTermoString = banco.getListaTermos();

        for (int i = 0; i < listaTermoString.size(); i++) {
            Termo termo = new Termo();
            String termoCompleto = listaTermoString.get(i);
            String[] objeto = termoCompleto.split("#");
            termo.setId(Integer.parseInt(objeto[0]));
            termo.setPalavra(objeto[1]);
            ListaTermos.add(termo);
            TableModel.AddTermo(termo);
        }

    }

}

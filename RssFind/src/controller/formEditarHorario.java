/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Matrix
 */
public class formEditarHorario {

    private view.ModelHorario TableModel = null;
    private view.formEditarHorario tela = null;
    private ArrayList<String> ListaHorario = null;
    private sqlite.Conection banco = null;

    public formEditarHorario() throws IOException {
        tela = new view.formEditarHorario(null, true);
        ListaHorario = new ArrayList<String>();
        TableModel = new view.ModelHorario();
        tela.tablehorarios.setModel(TableModel);
        banco = new sqlite.Conection();

        ligaEventos();
    }

    private void sair() {
        tela.setVisible(false);
    }

    private void ligaEventos() throws IOException {

        tela.btnSair.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });

    }

    private void limparTela() {
        tela.edHora.setText("");
        tela.edMinutos.setText("");
    }

    public void chamaTelaEditarHorario() throws IOException {
        limparTela();
        CarregaTableModel();
        tela.setVisible(true);
    }

    private void CarregaTableModel() throws FileNotFoundException, IOException {
        ListaHorario.clear();
        TableModel.Limpar();
        ListaHorario = banco.getHorarios();

        for (int i = 0; i < ListaHorario.size(); i++) {
            TableModel.AddHorario(ListaHorario.get(i));
        }

    }

}

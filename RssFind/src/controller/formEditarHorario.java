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
import model.Horario;

/**
 *
 * @author Matrix
 */
public class formEditarHorario {

    private view.ModelHorario TableModel = null;
    private view.formEditarHorario tela = null;
    private ArrayList<Horario> ListaHorario = null;
    private sqlite.Conection banco = null;
    private String horaAtual = "";
    private String minutoAtual = "";
    private int IDHorario = 0;

    public formEditarHorario() throws IOException {
        tela = new view.formEditarHorario(null, true);
        ListaHorario = new ArrayList<Horario>();
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

        tela.btnAlterar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    alterar();
                } catch (IOException ex) {
                    Logger.getLogger(formEditarHorario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        tela.tablehorarios.addMouseListener(new MouseListener() {

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
        tela.edHora.setText("");
        tela.edMinutos.setText("");
    }

    private void alterar() throws IOException {
        String novaHora = tela.edHora.getText();
        String novoMinutos = tela.edMinutos.getText();

        if (novaHora.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Obrigatório informar hora para alterar.");
        } else if (novoMinutos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Obrigatório informar minutos para alterar.");
        } else {
            banco.setUpdateHorario(IDHorario, novaHora + "." + novoMinutos);
            CarregaTableModel();
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            limparTela();
        }
    }

    private void mouseCliqueTable() {

        int Posicao = tela.tablehorarios.getSelectedRow();
        Horario horario = TableModel.GetPosition(Posicao);
        IDHorario = horario.getId();

        String horarioCompleto = horario.getHorario().replace('.', '#');
        String[] horarioDiv = horarioCompleto.split("#");
        horaAtual = horarioDiv[0];
        minutoAtual = horarioDiv[1];

        tela.edHora.setText(horaAtual);
        tela.edMinutos.setText(minutoAtual);

    }

    public void chamaTelaEditarHorario() throws IOException {
        limparTela();
        CarregaTableModel();
        tela.setVisible(true);
    }

    private void CarregaTableModel() throws FileNotFoundException, IOException {
        ListaHorario.clear();
        TableModel.Limpar();
        ArrayList<String> listaHorariosString = banco.getHorarios();

        for (int i = 0; i < listaHorariosString.size(); i++) {
            Horario horario = new Horario();
            String horarioCompleto = listaHorariosString.get(i);
            String[] objeto = horarioCompleto.split("#");
            horario.setId(Integer.parseInt(objeto[0]));
            horario.setHorario(objeto[1]);
            ListaHorario.add(horario);
            TableModel.AddHorario(horario);
        }

    }

}

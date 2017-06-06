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
import model.Noticia;

/**
 *
 * @author Matrix
 */
public class formEditarNoticias {

    private view.ModelNoticias TableModel = null;
    private view.formEditarNoticiasEncontradas tela = null;
    private ArrayList<Noticia> listaNoticias = null;
    private sqlite.Conection banco = null;
    private int idNoticia = 0;

    public formEditarNoticias() throws IOException {
        tela = new view.formEditarNoticiasEncontradas(null, true);
        listaNoticias = new ArrayList<Noticia>();
        TableModel = new view.ModelNoticias();
        tela.tableNoticias.setModel(TableModel);
        banco = new sqlite.Conection();

        ligaEventos();
    }

    private void mouseCliqueTable() {

        int Posicao = tela.tableNoticias.getSelectedRow();
        Noticia not = TableModel.GetPosition(Posicao);
        idNoticia = not.getId();

    }

    private void sair() {
        tela.setVisible(false);
    }

    private void excluir() throws IOException {
        if (listaNoticias.size() < 1) {
            JOptionPane.showMessageDialog(null, "Nenhum registro gravado para excluir.");
        } else if (idNoticia == 0) {
            JOptionPane.showMessageDialog(null, "Necessário selecionar uma notícia para excluir");
        } else {

            if ((JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?")) == 0) {
                banco.setDeleteNoticia(idNoticia);
                CarregaTableModel();
                idNoticia = 0;
                JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
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
                    Logger.getLogger(formEditarTermo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        tela.btnSair.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });

        tela.tableNoticias.addMouseListener(new MouseListener() {

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

    public void chamaTelaEditarNoticias() throws IOException {
        CarregaTableModel();
        tela.setVisible(true);
    }

    private void CarregaTableModel() throws FileNotFoundException, IOException {
        listaNoticias.clear();
        TableModel.Limpar();
        ArrayList<String> listaString = banco.getListaNoticiasEncontradas();

        for (int i = 0; i < listaString.size(); i++) {
            Noticia obj = new Noticia();
            String objCompleto = listaString.get(i);
            String[] objeto = objCompleto.split("#");
            obj.setId(Integer.parseInt(objeto[0]));
            obj.setTitulo(objeto[1]);
            obj.setLink(objeto[2]);
            listaNoticias.add(obj);
            TableModel.AddNoticia(obj);
        }

    }

}

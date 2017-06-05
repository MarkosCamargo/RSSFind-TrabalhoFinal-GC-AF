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

    private void ligaEventos() throws IOException {


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

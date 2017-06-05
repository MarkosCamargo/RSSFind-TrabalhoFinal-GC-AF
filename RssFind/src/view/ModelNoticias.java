/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Noticia;

/**
 *
 * @author Matrix
 */
public class ModelNoticias extends AbstractTableModel {

    private ArrayList<Noticia> listaNoticias = null;

    public ModelNoticias() {
        listaNoticias = new ArrayList<Noticia>();

    }

    public void AddNoticia(Noticia Objeto) {
        listaNoticias.add(Objeto);
        fireTableRowsInserted(listaNoticias.size() - 1, listaNoticias.size() - 1);
    }

    public void Limpar() {
        listaNoticias.clear();
    }

    public Noticia GetPosition(int posicao) {
        return listaNoticias.get(posicao);

    }

    public void Excluir(int Posicao) {
        listaNoticias.remove(Posicao);
        fireTableRowsDeleted(Posicao, Posicao);
    }

    @Override
    public int getRowCount() {
        return listaNoticias.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Noticia obj = listaNoticias.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj.getTitulo();
            case 1:
                return obj.getLink();
        }

        return null;

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Titulo";
            case 1:
                return "Link";
        }

        return "";
    }

}

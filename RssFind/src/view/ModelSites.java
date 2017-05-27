/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Matrix
 */
public class ModelSites extends AbstractTableModel {

    private ArrayList<String> listaSites = null;

    public ModelSites() {
        listaSites = new ArrayList<String>();

    }

    public void AddSite(String Objeto) {
        listaSites.add(Objeto);
        fireTableRowsInserted(listaSites.size() - 1, listaSites.size() - 1);
    }

    public void Limpar() {
        listaSites.clear();
    }

    public String GetPosition(int posicao) {
        return listaSites.get(posicao);

    }

    public void Excluir(int Posicao) {
        listaSites.remove(Posicao);
        fireTableRowsDeleted(Posicao, Posicao);
    }

    @Override
    public int getRowCount() {
        return listaSites.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String site = listaSites.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return site;
        }

        return null;

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Site";
        }

        return "";
    }

}

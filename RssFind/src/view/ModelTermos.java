/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Termo;

/**
 *
 * @author Matrix
 */
public class ModelTermos extends AbstractTableModel {

    private ArrayList<Termo> listaTermos = null;

    public ModelTermos() {
        listaTermos = new ArrayList<Termo>();

    }

    public void AddTermo(Termo Objeto) {
        listaTermos.add(Objeto);
        fireTableRowsInserted(listaTermos.size() - 1, listaTermos.size() - 1);
    }

    public void Limpar() {
        listaTermos.clear();
    }

    public Termo GetPosition(int posicao) {
        return listaTermos.get(posicao);

    }

    public void Excluir(int Posicao) {
        listaTermos.remove(Posicao);
        fireTableRowsDeleted(Posicao, Posicao);
    }

    @Override
    public int getRowCount() {
        return listaTermos.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Termo termo = listaTermos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return termo.getPalavra();
        }

        return null;

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Termo";
        }

        return "";
    }

}

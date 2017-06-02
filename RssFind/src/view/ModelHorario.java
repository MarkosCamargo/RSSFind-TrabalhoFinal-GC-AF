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
public class ModelHorario extends AbstractTableModel{
    
     private ArrayList<String> listaHorario = null;

    public ModelHorario() {
        listaHorario = new ArrayList<String>();

    }

    public void AddHorario(String Objeto) {
        listaHorario.add(Objeto);
        fireTableRowsInserted(listaHorario.size() - 1, listaHorario.size() - 1);
    }

    public void Limpar() {
        listaHorario.clear();
    }

    public String GetPosition(int posicao) {
        return listaHorario.get(posicao);

    }

    public void Excluir(int Posicao) {
        listaHorario.remove(Posicao);
        fireTableRowsDeleted(Posicao, Posicao);
    }

    @Override
    public int getRowCount() {
        return listaHorario.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String Horario = listaHorario.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return Horario;
        }

        return null;

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Horário";
        }

        return "";
    }
    
}

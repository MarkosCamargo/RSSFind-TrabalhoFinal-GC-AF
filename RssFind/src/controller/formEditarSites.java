/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Matrix
 */
public class formEditarSites {

    private view.ModelSites TableModel = null;
    private view.formEditarSites tela = null;
    private ArrayList<String> ListaSites = null;

    public formEditarSites() {
        tela = new view.formEditarSites(null, true);
        ListaSites = new ArrayList<String>();
        TableModel = new view.ModelSites();
        tela.tableSites.setModel(TableModel);
    }

    public void chamaTelaEditarSites() throws IOException {
        CarregaTableModel();
        tela.setVisible(true);
    }

    private void CarregaTableModel() throws FileNotFoundException, IOException {
        TableModel.Limpar();
//       ListaSites = GerenciadorEspecie.CarregaLista();

        ListaSites.add("Google.com.br");
        ListaSites.add("beeg.com.br");
        ListaSites.add("xvideos.com.br");
        for (int i = 0; i < ListaSites.size(); i++) {
            TableModel.AddSite(ListaSites.get(i));
        }

    }

}

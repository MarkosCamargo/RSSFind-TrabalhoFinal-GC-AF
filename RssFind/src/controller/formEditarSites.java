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
import model.Site;

/**
 *
 * @author Matrix
 */
public class formEditarSites {

    private view.ModelSites TableModel = null;
    private view.formEditarSites tela = null;
    private ArrayList<Site> ListaSites = null;
    private sqlite.Conection banco = null;
    private String urlAtual = "";
    private int idSite= 0;

    public formEditarSites() throws IOException {
        tela = new view.formEditarSites(null, true);
        ListaSites = new ArrayList<Site>();
        TableModel = new view.ModelSites();
        tela.tableSites.setModel(TableModel);
        banco = new sqlite.Conection();

        ligaEventos();
    }

    private void mouseCliqueTable() {

        int Posicao = tela.tableSites.getSelectedRow();
        Site url = TableModel.GetPosition(Posicao);
        idSite = url.getId();
        urlAtual = url.getUrl();
        tela.edUrl.setText(urlAtual);

    }

    private void sair() {
        tela.setVisible(false);
    }

    private void alterar() throws IOException {
        String novaUrl = tela.edUrl.getText();
        if (novaUrl.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Obrigatório informar a nova URL para alterar.");
        } else if (!novaUrl.equalsIgnoreCase(urlAtual)) {
            banco.setUpdateUrl(idSite, novaUrl);
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

        tela.tableSites.addMouseListener(new MouseListener() {

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
        tela.edUrl.setText("");
    }

    public void chamaTelaEditarSites() throws IOException {
        limparTela();
        CarregaTableModel();
        tela.setVisible(true);
    }

    private void CarregaTableModel() throws FileNotFoundException, IOException {
        ListaSites.clear();
        TableModel.Limpar();
        ArrayList<String> listaSiteString = banco.getListaUrlString();

        for (int i = 0; i < listaSiteString.size(); i++) {
            Site site = new Site();
            String siteCompleto = listaSiteString.get(i);
            String[] objeto = siteCompleto.split("#");
            site.setId(Integer.parseInt(objeto[0]));
            site.setUrl(objeto[1]);
            ListaSites.add(site);
            TableModel.AddSite(site);
        }

    }

}

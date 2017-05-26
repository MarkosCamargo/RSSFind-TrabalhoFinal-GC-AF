/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author CCE User
 */
public class formCadastroSites {

    private view.formCadastroSites telaCadastroSites = null;

    public formCadastroSites() {
        telaCadastroSites = new view.formCadastroSites(null, true);
    }

    public void chamaTelaCadastroSites() {
        telaCadastroSites.setVisible(true);
    }

}

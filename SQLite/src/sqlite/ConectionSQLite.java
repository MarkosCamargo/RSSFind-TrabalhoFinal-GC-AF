/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite;

import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Matrix
 */
public interface ConectionSQLite {
    
    public void setUpdate(String script);
    public void setInsert(String script);
    public void setDelete(String script);    
    
    public void setDeleteTermos(int id);
    public void setDeleteHorario(int id);
    public void setDeleteUrl(int id);
    public void setDeleteNoticia(int id);    
    public void setDeleteEmail(int id);    
    
    public void setUpdateUrl(int id, String novaUrl);
    public void setUpdateTermo(int id, String novoTermo);
    public void setUpdateHorario(int id, String novoHorario);
    public void setUpdateEmail(int id, String novoEmail);  
    
    public void setInsertUrl(String url);
    public void setInsertHorario(String horario);
    public void setInsertTermos(String termo);
    public void setInsertNoticiaEncontrada(String titulo, String link);  
    public void setInsertEmail(String email);
    
    public ArrayList<URL> getListaUrl();
    public ArrayList<String> getListaUrlString();
    public ArrayList<String> getListaTermos();
    public ArrayList<String> getListaHorarios();
    public ArrayList<String> getListaNoticiasEncontradas();
    public String getRegistroString(String script, String coluna);
    public boolean getNoticiaJaInserida(String titulo);
    public ArrayList<String> getListaEmail();
    
    
}

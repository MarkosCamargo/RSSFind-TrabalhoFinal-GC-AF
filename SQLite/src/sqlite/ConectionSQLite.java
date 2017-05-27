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
    public void setInsertUrl(String Url);
    public void setInsertHorario(String horario);
    public void setInsertTermos(String Termo);
    public ArrayList<URL> getListaUrl();
    public ArrayList<String> getTermos();
    public ArrayList<String> getHorarios();
    public String getRegistroString(String Script, String coluna);
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite;

/**
 *
 * @author Matrix
 */
public interface ConectionSQLite {
    
    public void setUpdate(String script);
    public void setInsert(String script);
    public void setDelete(String script);
    
}

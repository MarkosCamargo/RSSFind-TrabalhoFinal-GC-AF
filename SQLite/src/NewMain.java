
import java.time.LocalTime;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matrix
 */
public class NewMain {

    public static void main(String[] args) {        
        LocalTime time = LocalTime.now();
        JOptionPane.showMessageDialog(null, time.getHour());
        JOptionPane.showMessageDialog(null, time.getMinute());

    }

}

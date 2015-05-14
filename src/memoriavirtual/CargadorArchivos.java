/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Jespi_000
 */
public class CargadorArchivos {
    public static File Cargar(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int option = chooser.showOpenDialog(new JFrame());
        if ((option == JFileChooser.APPROVE_OPTION) && (chooser.getSelectedFile().getName().endsWith(".txt"))) {
           return chooser.getSelectedFile();
        }
        else{
            return null;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;


/**
 *
 * @author Jespi_000
 */
public class LeedorArchivos {
    public static List<String> Leer(String nombre_archivo){
        try {
            BufferedReader br = new BufferedReader(new FileReader(nombre_archivo+".txt"));
            List<String> sb = new LinkedList();
            String line = br.readLine();

            while (line != null) {
                sb.add(line);
                line = br.readLine();
            }
            return sb;
        } 
        catch (Exception e) {
            return null;
        }
    }
}

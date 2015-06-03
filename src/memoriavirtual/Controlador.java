/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Josue
 */
public class Controlador {
    //Clase que controla la ejecución de referencias, manda a llamar métodos de las políticas Fetch, Placement, Replacement, Cleaning Demand
    
    public static void ejecutarReferencias(){
        //Ejecutar cada referencia en la lista: Main.lineas_referencias_en_cuestion
            for(Referencia r : Main.lineas_referencias_en_cuestion){
                //Si memoria Fisica es nula --> Fetch
                //Si memoria Fisica no llena --> Placement
                //Si memoria Fisica llena o scope local *fixed--> Replacement.AsignarPagina(r);
                //ACTUALIZAR Main.mFisica, Logs y Tabla de Referencias
            }
    }
    
    public static void CleaningDemand(Frame f){ //llamarlo antes de hacer el replacement
        f.modificado = false;
        JOptionPane.showMessageDialog(new JFrame(), "Frame "+f.identificador+" del proceso: "+f.contenido.nombre+", fue limpiado debido a un reemplazo (Cleaning Demand).");
    }
}

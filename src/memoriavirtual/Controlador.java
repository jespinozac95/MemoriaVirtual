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
    public static boolean memoriaFisicaLlena(){
        boolean llena = true;
        for (Frame f : Main.memoria_fisica){
            if (!f.esta_ocupado)
                llena = false;
        }
        return llena;
    }
    public static boolean procesoLleno(String nombre_proceso){
        boolean lleno = true;
        for (Frame f : Main.memoria_fisica){
            try{
                if (f.proceso_reserva.equals(nombre_proceso)){
                    if (!f.esta_ocupado)
                        lleno = false;
                }
            }
            catch (Exception e){}
            try{
                if (f.contenido.nombre.equals(nombre_proceso)){
                    if (!f.esta_ocupado)
                        lleno = false;
                }
            }
            catch (Exception e){}
        }
        return lleno;
    }
    public static int indiceProceso(int id_proceso){
        int indice = 0;
        for (Proceso p : Main.lista_Procesos){
            if (p.identificador == id_proceso)
                return indice;
            indice++;
        }
        return indice;
    }
    public static void ejecutarReferencias(){
        //Ejecutar cada referencia en la lista: Main.lineas_referencias_en_cuestion
        //Si es la primera vez (Memoria Fisica nula) --> Working set
        if (Main.memoria_fisica.isEmpty()){
            ResidentSet rs = new ResidentSet();
            //rs.ResidentSetFijo(Main.tamaño_fijo);
            rs.ResidentSetFijo();     

            if (!Main.fetch_demand){
                FetchPolicy fp = new FetchPolicy();
                fp.prepaging();
            }
        }
        for(Referencia r : Main.lineas_referencias_en_cuestion){
            Proceso p = Main.lista_Procesos.get(indiceProceso(r.id_proceso));
            //Si memoria Fisica no llena --> Placement
            if (!((procesoLleno(p.nombre)) || (memoriaFisicaLlena()))){
                JOptionPane.showMessageDialog(new JFrame(), "Placement");
            }
            //Si memoria Fisica llena o scope local *fixed--> Replacement.AsignarPagina(r);
            else{
                JOptionPane.showMessageDialog(new JFrame(), "Replacement");
            }
        }
    }
    
    public static void CleaningDemand(Frame f){ //llamarlo antes de hacer el replacement
        f.modificado = false;
        JOptionPane.showMessageDialog(new JFrame(), "Frame "+f.identificador+" del proceso: "+f.contenido.nombre+", fue limpiado debido a un reemplazo (Cleaning Demand).");
    }
}

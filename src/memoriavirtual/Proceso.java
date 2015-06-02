/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

import java.util.List;

/**
 *
 * @author Adrian
 */
public class Proceso {

    public int identificador;
    public String nombre;
    public int prioridad;
    public int tamano;
    public int cantPartes;
    public boolean esta_bloqueado;
    public int[][] UMF = new int[cantPartes][3]; //ubicacion_memoriaF
    public int[][] UMV = new int[cantPartes][3]; //ubicacion_memoriaV
    public int num_pags;

    public Proceso(int identificador, String nombre, int tamano, boolean bit_bloqueo) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.tamano = tamano;
        this.esta_bloqueado = bit_bloqueo;
        //this.identificador = lista_Procesos.length();
    }
    public Proceso(int identificador, String nombre, int tamano, boolean bit_bloqueo, int prioridad) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.tamano = tamano;
        this.esta_bloqueado = bit_bloqueo;
        this.prioridad = prioridad;
        //this.identificador = lista_Procesos.length();
    }
    public static boolean yaEsProceso(int id, String nombre){
        //System.out.println("    Entré a yaEsProceso con: id = "+id+", nombre = "+nombre);
        if (Main.lista_Procesos.isEmpty()){
            //System.out.println("    es vacía, return false");
            return false;
        }
        //System.out.println("    No es vacía");
        for (Proceso p : Main.lista_Procesos){
            //System.out.println("    al if");
            if ((p.identificador == id) || (p.nombre.equals(nombre))){
                //System.out.println("    yaEsProceso");
                return true;
            }
        }
        //System.out.println("    NO yaEsProceso");
        return false;
    }
    
    public static Proceso SeleccionarMayorPrioridad(List<Proceso> lista_Procesos) {
        Proceso p = null;
        for (Proceso q : lista_Procesos){
            if (p == null){
                p = q;
            }
            else if (p.prioridad < q.prioridad){
                p = q;
            }
        }
        return p;
    }
}

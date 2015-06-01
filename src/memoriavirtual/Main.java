/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Jespi_000
 */
public class Main {
    //Atributos obtenidos de los archivos .txt
    public static List<String> lineas_archivos_procesos = new LinkedList<String>();
    public static List<String> lineas_archivos_referencias = new LinkedList<String>();
    public static List<Referencia> lineas_referencias_en_cuestion = new LinkedList<Referencia>();
    public static List<Proceso> lista_Procesos = new LinkedList<Proceso>();
    public static List<Frame> memoria_fisica = new LinkedList<Frame>(); //max: 16
    public static List<Frame> memoria_virtual = new LinkedList<Frame>(); //max: 128
    public static List<Log> log = new LinkedList<Log>();
    
    //Atributos obtenidos de la ventana de configuración
    public static boolean fetch_demand = false; //true si es demand, false si es prepaging
    public static boolean placement_first_available = false; //true si es first available, false si es next availablew
    public static String replacement_policy; //LRU,FIFO,MRU o Clock
    public static boolean resident_set_management_fixed = false; //true si es fixed, false si es variable
        //FIXED
    public static int tamaño_fijo; 
        //VARIABLE
    public static int tamaño_inicial;
    public static int tamaño_maximo;
    public static int crecimiento_por_reemplazo;
    
    public static boolean replacement_scope_global = false; //true si es global, false si es local
    public static boolean cleaning_demand = false; //true si es demand, false si es precleaning
    public static int grado_de_multiprogramacion;
    public static boolean seleccion_de_procesos_FIFO = false; //true si es FIFO, false si es Prioridad
    
    //Otros atributos (tamaño de memorias y # de referencias por iteracion)
    public static int tamaño_memoria_fisica; //1-16
    public static int bits_de_referencias; //24-27
    public static int numero_referencias_por_iteracion; //menor al # de líneas totales del archivo de referencias
    
    public static int tamaño_memoria_virtual; //16-128
    public static Map ListaDeAtributos; //Lista con todos los atributos
    
    public static void main(String[] args) {
        VentanaInicio ventana_inicio = new VentanaInicio();
        ventana_inicio.setVisible(true);
    }
    
}

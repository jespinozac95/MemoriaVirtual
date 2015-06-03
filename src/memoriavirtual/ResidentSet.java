/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

/**
 *
 * @author Mauricio Gamboa C
 */
public class ResidentSet {
    
    public void ResidentSetFijo(int tamano_fijo) {  
        //Validar que no se sobrepase la cantidad de frames en memoria física
        int contador=0; 
        int cantidad_maxima_frames = tamano_fijo;
        String nombre_proceso = Main.memoria_virtual.get(0).contenido.nombre; 
        //System.out.println("Nombre Primer Proceso: "+nombre_proceso);
        int ubicacion_fisica=0; 
        for (int i = 0; i < Main.memoria_virtual.size(); i++ ) {
            // Initialize each object
            while (Main.memoria_fisica.size()<Main.tamaño_memoria_fisica){
            if ((contador < cantidad_maxima_frames) && (Main.memoria_virtual.get(i).contenido.nombre.equalsIgnoreCase(nombre_proceso))){
                //System.out.println("Primer If, Nombre proceso: "+nombre_proceso+" - Contador: "+contador);
                Frame espacio_reservado = new Frame(true,nombre_proceso);
                Main.memoria_fisica.add(ubicacion_fisica,espacio_reservado); 
                ubicacion_fisica++; 
                contador++; 
            }
            else if ((contador < cantidad_maxima_frames) & (!(Main.memoria_virtual.get(i).contenido.nombre.equalsIgnoreCase(nombre_proceso)))){ 
                Frame espacio_reservado = new Frame(true,nombre_proceso);
                Main.memoria_fisica.add(ubicacion_fisica,espacio_reservado); 
                contador++; 
                ubicacion_fisica++; 
                
            }
            else{
                if (!(Main.memoria_virtual.get(i).contenido.nombre.equalsIgnoreCase(nombre_proceso))){
                    nombre_proceso=Main.memoria_virtual.get(i).contenido.nombre; 
                    Frame espacio_reservado = new Frame(true,nombre_proceso);
                    Main.memoria_fisica.add(ubicacion_fisica,espacio_reservado);
                    contador=1; 
                    ubicacion_fisica++; 
                }
            }
        }
        }
    }
  
    
    
}

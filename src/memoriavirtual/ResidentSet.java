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
    
    
//    public void ResidentSetFijo() {
//        //Proceso p = new Proceso("P1",5, 5);
//        //Frame j = new Frame(5,p ,25);
//        //Frame[][] frame1[0][1] = j;  
//        int columnas_memoria_virtual = 5; 
//        int filas_memoria_virtual =5; 
//        int contador=0; 
//        int cantidad_maxima_frames = Main.tamaño_fijo;
//        for (int i = 0; i < columnas_memoria_virtual; i ++ ) {
//            for (int j = 0; j < filas_memoria_virtual; j ++ ) {
//                String nombre_proceso = Main.memoria_vitual[i][j].contenido.nombre; 
//                // Initialize each object
//                int columnas_memoria_fisica = 5; 
//                int filas_memoria_fisica =5; 
//                for (int k = 0; k < columnas_memoria_fisica; k ++ ) {
//                    for (int l = 0; l < filas_memoria_fisica; l ++ ) {
//                        if ((contador < cantidad_maxima_frames) & (Main.memoria_vitual[i][j].contenido.nombre.equalsIgnoreCase(nombre_proceso))){
//                            Main.memoria_fisica[k][l]=Main.memoria_vitual[i][j]; 
//                            contador++; 
//                        }
//                        else if ((contador < cantidad_maxima_frames) & (!(Main.memoria_vitual[i][j].contenido.nombre.equalsIgnoreCase(nombre_proceso)))){
//                            // no hace nada y el for se brinca la posición 
//                        }
//                        else{
//                            if (!(Main.memoria_vitual[i][j].contenido.nombre.equalsIgnoreCase(nombre_proceso))){
//                                nombre_proceso=Main.memoria_vitual[i][j].contenido.nombre; 
//                                contador=0; 
//                            }
//                             
//                        }
//                    }
//                }
//            }
//        }
//    
//    
//    }
  
public void ResidentSetFijo(int tamano_fijo) {  
        //Validar que no se sobrepase la cantidad de frames en memoria física
        int contador=0; 
        int cantidad_maxima_frames = tamano_fijo;
//        String nombre_proceso = Main.memoria_virtual.get(0).contenido.nombre; 
//        //System.out.println("Nombre Primer Proceso: "+nombre_proceso);
//        int ubicacion_fisica=0; 
//        for (int i = 0; i < Main.memoria_virtual.size(); i++ ) {
//            // Initialize each object
//            if ((contador < cantidad_maxima_frames) && (Main.memoria_virtual.get(i).contenido.nombre.equalsIgnoreCase(nombre_proceso))){
//                //System.out.println("Primer If, Nombre proceso: "+nombre_proceso+" - Contador: "+contador);
//                Main.memoria_fisica.add(ubicacion_fisica,Main.memoria_virtual.get(i)); 
//                //System.out.println("Primer If, Cambio Memoria Fisica, Proceso Actual: "+nombre_proceso+" - Nombre Cambiado: "+Main.memoria_fisica.get(ubicacion_fisica).contenido.nombre);
//                ubicacion_fisica++; 
//                contador++; 
//            }
//            else if ((contador < cantidad_maxima_frames) & (!(Main.memoria_virtual.get(i).contenido.nombre.equalsIgnoreCase(nombre_proceso)))){ 
//                Main.memoria_fisica.add(ubicacion_fisica,null); 
//                contador++; 
//                ubicacion_fisica++; 
//                //System.out.println("Segundo If, Nombre proceso espacio reservado: "+nombre_proceso+" - Contador: "+contador);   
//            }
//            else{
//                if (!(Main.memoria_virtual.get(i).contenido.nombre.equalsIgnoreCase(nombre_proceso))){
//                    nombre_proceso=Main.memoria_virtual.get(i).contenido.nombre; 
//                    Main.memoria_fisica.add(ubicacion_fisica,Main.memoria_virtual.get(i));
//                    contador=1; 
//                    ubicacion_fisica++; 
//                }
//            }              
//        }
    }
    
    
    
}

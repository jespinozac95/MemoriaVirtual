/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

import static memoriavirtual.Controlador.getIndiceFrameVirtual;

/**
 *
 * @author Mauricio Gamboa C
 */
public class ResidentSet {
    
    public void ResidentSetFijo(){ //cubre caso inicial de ambos: tamaño fijo y variable
        for (Proceso p : Main.lista_Procesos){
            System.out.println("*****RS Fijo, proeso: "+p.nombre);
            int i = 0;
            if (Main.resident_set_management_fixed){
                while(i < Main.tamaño_fijo){
                    System.out.println("*****tamano fijo");
                    if (Main.memoria_fisica.size()<Main.tamaño_memoria_fisica){
                        Frame f = new Frame(true,p.nombre);
                        Main.memoria_fisica.add(f);
                    }
                    i++;
                }
            }
            else{
                System.out.println(p.nombre+", Main.tamano inicial = "+Main.tamaño_inicial);
                while(i < Main.tamaño_inicial){
                    System.out.println("*****tamano variable");
                    if (Main.memoria_fisica.size()<Main.tamaño_memoria_fisica){
                        Frame f = new Frame(true,p.nombre);
                        Main.memoria_fisica.add(f);
                        System.out.println("        Frame creado en reserva para: "+f.proceso_reserva);
                    }
                    i++;
                }
            }
        }
        Main.puntero_memoria_fisica=Main.memoria_fisica.size()-1; 
    }

    
    //Si hay espacio de sobra le doy, en primera instancia vacios totalmente
    //En segunda a los que estan reservados pero no ocupados ni bloqueados > LLamo a place(First y Next). 
    //Si no hay campo,sobre los no ocupados por el mismo si es local llamando a core
    //Sino lo hace reemplazo global llamando a global
    
    public void ResidentSetVariableIncrementar(String nombre_proceso, Frame f, boolean modificado){
        int cantidad_maxima =Main.tamaño_maximo; //Cambiarlo por la variable global
        int crecimiento=Main.crecimiento_por_reemplazo; // crecimiento igualarlo a la variable global. 
        int contador=0;
        //int tamano_fisica=16; //igualarlo al tamaño 
        while (crecimiento!=0){
            if (HayEspacio()){
                //Agreguelo al final  
                if (PuedeIncrementarse(nombre_proceso)){
                    Frame espacio_reservado = new Frame(true,nombre_proceso);
                    Main.memoria_fisica.add(espacio_reservado);
                    crecimiento--; 
                    Main.puntero_memoria_fisica=Main.memoria_fisica.size()-1; 
                }
                //alcanzó el máximo, no puede crecer más
                else{           
                    Main.global_convertido_fijo=true; 
                    // Busca el proceso buscaProceso(String nombreProceso) para el reemplazo
                    //llamar a reemplazo
                    Controlador.Reemplazo(buscaProceso(nombre_proceso), f, modificado);
                }
            }
            else if (Hay_desocupados()){
                // si no hay desocupados desbloqueados, hace reemplazo 
                if (Main.placement_first_available==true){
                    
                    if ((FirstAvailable())!=-1){
                        int fa = FirstAvailable();
                        Main.memoria_fisica.get(fa).proceso_reserva= nombre_proceso; 
                    //posiblemente fetch 
                        FetchPolicy fp = new FetchPolicy();
                        fp.fetch(Controlador.getIndiceFrameVirtual(f),fa, modificado);
                    }
                    else{
                    //llamar a reemplazo g o l
                        Controlador.Reemplazo(buscaProceso(nombre_proceso), f, modificado);
                    }
                    
                }
                else{
                    if ((NextAvailable())!=-1){
                        int na = NextAvailable();
                    Main.memoria_fisica.get(NextAvailable()).proceso_reserva= nombre_proceso; 
                    Main.puntero_memoria_fisica=na; 
                    //fetch
                        FetchPolicy fp = new FetchPolicy();
                        fp.fetch(Controlador.getIndiceFrameVirtual(f),na, modificado);
                    }
                    else{
                    //llamar a reemplazo
                        Controlador.Reemplazo(buscaProceso(nombre_proceso), f, modificado);
                    }
                }
                
            }
            // No hay espacios en ningun lado
            else {
                //reemplazo alcances globales o fijos 
                //buscaProceso(nombre_proceso)      
                Controlador.Reemplazo(buscaProceso(nombre_proceso), f, modificado);
            }
            
        }

    }
           
    
    public int NextAvailable(){
        int posicionActual = Main.puntero_memoria_fisica+1;       
        while(posicionActual<Main.puntero_memoria_fisica){
            if ((Main.memoria_fisica.get(posicionActual).esta_reservado) && (Main.memoria_fisica.get(posicionActual).proceso_reserva.equals(Main.procesoManejado))){
                return posicionActual;
            }
            else if (posicionActual==(Main.memoria_fisica.size())-1){
                posicionActual = 0;
                if (Main.memoria_fisica.get(posicionActual).contenido.esta_bloqueado==false){
                    return posicionActual;
                }
                else {
                    posicionActual++;
                }
            }
            else{
                if (Main.memoria_fisica.get(posicionActual).contenido.esta_bloqueado==false){
                    return posicionActual;
                }
                else {
                    posicionActual++;
                }
            }        
        }
        return -1;
    }
    
    public Proceso buscaProceso(String nombreProceso){
        for (int i=0;i<Main.memoria_virtual.size();i++){
            if (nombreProceso.equals(Main.lista_Procesos.get(i).nombre)){
                return Main.memoria_virtual.get(i).contenido;
            }            
        } 
        return null;
    }
    
    public boolean Hay_desocupados(){
        for (int i = 0; i < Main.memoria_fisica.size(); i++ ) {
                    if ((Main.memoria_fisica.get(i).esta_ocupado)==false){ //Pinky al colocarlo poner el ocupado en verdadero
                        return true;  
                    }
        }
        return false; 
    }
    
    public boolean PuedeIncrementarse(String nombre_proceso){
        if (Contar_Reservados(nombre_proceso)<Main.tamaño_maximo){
            return true; 
        }
        else{
            return false; 
        }
    }
    
    public boolean HayEspacio(){
        if (Main.memoria_fisica.size()< Main.tamaño_memoria_fisica){
            return true; 
        }
        else{
            return false; 
        }
    }
    
    
    public int FirstAvailable(){
        for (int i = 0; i < Main.memoria_fisica.size(); i++ ) {
                    try{
                            if ((Main.memoria_fisica.get(i).contenido.equals(null)) || ((Main.memoria_fisica.get(i).contenido.esta_bloqueado)==false)){ //Pinky al colocarlo poner el ocupado en verdadero
                                System.out.println("*-*-*-*No está ocupado posicion: "+i+", esta bloqueado = "+Main.memoria_fisica.get(i).contenido.esta_bloqueado);
                                return i; 
                        
                    }
                    }
                    catch(Exception e){
                        if ((Main.memoria_fisica.get(i).esta_reservado) && (Main.memoria_fisica.get(i).proceso_reserva.equals(Main.procesoManejado))){
                            System.out.println("*-*-*-*está reservado posicion: "+i);
                            return i;
                        }
                    }
                }
        return -1; 
        
    }
    
    public int Contar_Reservados (String nombre_proceso){
        int contador = 0;
        for (int i = 0; i < Main.memoria_fisica.size(); i++ ) {
            if (Main.memoria_fisica.get(i).proceso_reserva.equalsIgnoreCase(nombre_proceso)){
                contador++;
            }
        }
        return contador;
    }
    
    public void imprimir_frames(){
        for (int i = 0; i < Main.memoria_fisica.size(); i++ ) {
            System.out.println(Main.memoria_fisica.get(i).proceso_reserva);
            
        }
    }
}

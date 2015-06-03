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
        for (int i = 0; i < Main.lista_Procesos.size() * Main.tamaño_fijo; i++ ) {
            // Initialize each object
            if (Main.memoria_fisica.size()<Main.tamaño_memoria_fisica){
            if ((contador < cantidad_maxima_frames) && (Main.lista_Procesos.get(i/Main.tamaño_fijo).nombre.equalsIgnoreCase(nombre_proceso))){
                //System.out.println("Primer If, Nombre proceso: "+nombre_proceso+" - Contador: "+contador);
                Frame espacio_reservado = new Frame(true,nombre_proceso);
                Main.memoria_fisica.add(ubicacion_fisica,espacio_reservado); 
                ubicacion_fisica++; 
                contador++; 
            }
            else if ((contador < cantidad_maxima_frames) & (!(Main.lista_Procesos.get(i/Main.tamaño_fijo).nombre.equalsIgnoreCase(nombre_proceso)))){ 
                Frame espacio_reservado = new Frame(true,nombre_proceso);
                Main.memoria_fisica.add(ubicacion_fisica,espacio_reservado); 
                contador++; 
                ubicacion_fisica++; 
                
            }
            else{
                if (!(Main.lista_Procesos.get(i/Main.tamaño_fijo).nombre.equalsIgnoreCase(nombre_proceso))){
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
    
    public void ResidentSetFijo2(){ //cubre caso inicial de ambos: tamaño fijo y variable
        for (Proceso p : Main.lista_Procesos){
            int i = 0;
            try{
                while(i < Main.tamaño_fijo){
                    if (Main.memoria_fisica.size()<Main.tamaño_memoria_fisica){
                        Frame f = new Frame(true,p.nombre);
                        Main.memoria_fisica.add(f);
                    }
                    i++;
                }
            }
            catch(Exception e){
                while(i < Main.tamaño_inicial){
                    if (Main.memoria_fisica.size()<Main.tamaño_memoria_fisica){
                        Frame f = new Frame(true,p.nombre);
                        Main.memoria_fisica.add(f);
                    }
                i++;
                }
            }
        }
    }
    
    //Si hay espacio de sobra le doy, en primera instancia vacios totalmente
    //En segunda a los que estan reservados pero no ocupados ni bloqueados > LLamo a place(First y Next). 
    //Si no hay campo,sobre los no ocupados por el mismo si es local llamando a core
    //Sino lo hace reemplazo global llamando a global
    
    public void ResidentSetVariableIncrementar(String nombre_proceso){
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
                }
                //alcanzó el máximo, no puede crecer más
                else{           
                    Main.global_convertido_fijo=true; 
                    //llamar a reemplazo
                }
            }
            else if (Hay_desocupados()){
                // si no hay desocupados desbloqueados, hace reemplazo 
                if (Main.placement_first_available==true){
                    
                }
                else{
                    if ((NextAvailable())!=-1){
                    Main.memoria_fisica.get(NextAvailable()).proceso_reserva= nombre_proceso; 
                    }
                    else{
                    //llamar a reemplazo
                    }
                }
                
            }
            // No hay espacios en ningun lado
            else {
                //reemplazo
                        
            }
            
        }
            
        
    }
           
    
    public int NextAvailable(){
        int posicionActual = Main.puntero_memoria_fisica+1;       
        while(posicionActual!=Main.puntero_memoria_fisica){
            if (posicionActual==(Main.memoria_fisica.size())-1){
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
    
    
    public void PrimeroDisponible(String nombre_proceso){
        for (int i = 0; i < Main.memoria_fisica.size(); i++ ) {
                    if ((Main.memoria_fisica.get(i).esta_ocupado)==false){ //Pinky al colocarlo poner el ocupado en verdadero
                        //&(!(Main.memoria_fisica.get(i).contenido.esta_bloqueado))
                        System.out.println("Yeahh");
                        System.out.println("Yeah "+Main.memoria_fisica.get(i));
                        Main.memoria_fisica.get(i).proceso_reserva= nombre_proceso;  
                        break; 
                    }
                }
        
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

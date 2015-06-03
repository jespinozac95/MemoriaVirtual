/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Andres
 */
public class MRU {
    public int CompararMRU(Proceso proceso){    //AGARRO EL NANOTIME MAYOR.       
        if ((Main.replacement_scope_global==true)&&(Main.global_convertido_fijo==false)){  //no se va a utilizar el proceso recibido.
            int posicionActual=0;
            Frame actual = Main.memoria_fisica.get(0);            
            int n=1;
            while (n<Main.memoria_fisica.size()){
                System.out.println("Entro al while");
                System.out.println("n="+n);
                System.out.println("TS de actual: "+actual.TS_ultima_referencia);
                System.out.println("TS de get(n): "+Main.memoria_fisica.get(n).TS_ultima_referencia);
                if ((Main.memoria_fisica.get(n).identificador==actual.identificador)&&(Main.memoria_fisica.get(n).contenido.identificador!=actual.contenido.identificador)){
                    System.out.println("Ya está en física");
                    posicionActual = -1;
                    break;                    
                }
                if (esApto(Main.memoria_fisica.get(n).contenido)==false){
                    n++;
                }
                if(Main.memoria_fisica.get(n).esta_reservado==true){
                    System.out.println("Está reservado");
                    if (Main.memoria_fisica.get(n).contenido.esta_bloqueado){
                        n++;
                    }
                    if (esApto(Main.memoria_fisica.get(n).contenido)==false){
                        n++;
                    }
                    else{
                        System.out.println("Está reservado pero no bloqueado");
                        if (actual.TS_ultima_referencia<Main.memoria_fisica.get(n).TS_ultima_referencia){
                            actual = Main.memoria_fisica.get(n);                             
                            posicionActual = n;
                            System.out.println("Posicion actual en el else-if cuando actual es más viejo que el puntero "+posicionActual);
                            n++;
                            
                        }
                        else if (actual.TS_ultima_referencia>Main.memoria_fisica.get(n).TS_ultima_referencia){
                            //Significa que actual ha sido más recientemente utilizado por lo que se mantiene como actual.
                            n++;
                        }
                    }
                }                
                else if (actual.TS_ultima_referencia>Main.memoria_fisica.get(n).TS_ultima_referencia){   
                    System.out.println("No hay nada reservado y Actual está después del frame");
                    n++;
                }
                else if (actual.TS_ultima_referencia<Main.memoria_fisica.get(n).TS_ultima_referencia){
                    actual = Main.memoria_fisica.get(n);
                    posicionActual = n;
                    System.out.println("Posicion actual en el else if cuando actual es más viejo que el puntero "+posicionActual);
                    n++;                    
                }           
            }
           System.out.println("posicionActual  "+posicionActual); 
           return posicionActual;
        }    
        
        else {
            int posicionActual = 0;
            int id_proceso = proceso.identificador;
            Frame actual = Main.memoria_fisica.get(0);
            int n=1;
            if(Main.memoria_fisica.size()==0){
                posicionActual = -1;
            }
            else{
                while (n<Main.memoria_fisica.size()){
                    if(proceso.identificador==Main.memoria_fisica.get(n).identificador){
                        if ((Main.memoria_fisica.get(n).identificador==actual.identificador)&&(Main.memoria_fisica.get(n).contenido.identificador!=actual.contenido.identificador)){
                            break;
                        }
                        if (esApto(Main.memoria_fisica.get(n).contenido)==false){
                            n++;
                        }
                        if(Main.memoria_fisica.get(n).esta_reservado==true){
                                if (esApto(Main.memoria_fisica.get(n).contenido)==false){
                                    n++;
                                }
                                if (actual.TS_ultima_referencia>Main.memoria_fisica.get(n).TS_ultima_referencia){
                                    n++;
                                }
                                else if (actual.TS_ultima_referencia<Main.memoria_fisica.get(n).TS_ultima_referencia){
                                    actual = Main.memoria_fisica.get(n);
                                    posicionActual = n;
                                    n++;                            
                                }
                            }
                        
                        else if (actual.TS_ultima_referencia>Main.memoria_fisica.get(n).TS_ultima_referencia){                    
                            n++;
                        }
                        else if (actual.TS_ultima_referencia<Main.memoria_fisica.get(n).TS_ultima_referencia){
                            actual = Main.memoria_fisica.get(n);
                            posicionActual = n;
                            n++;                    
                        }                
                    }
                    else{n++;}
                }
            }
	    Main.global_convertido_fijo=false;
            System.out.println("posicionActual  "+posicionActual);
            return posicionActual;
        }                
    }
    public boolean esApto(Proceso p){
        //Main.tamaño_inicial    
        int contador=0;
        for (int i=0;i<Main.memoria_fisica.size();i++){            
            if (Main.memoria_fisica.get(i).contenido.nombre.equals(p.nombre)){
                contador++;
            }           
        }
        if (contador <=Main.tamaño_inicial){
            return false;
        }
        else{return true;}
    }
    
    public List<Frame> obtenerLocales(int idProceso){
        List<Frame> listaLocal = new LinkedList<Frame>();
        int i=0;
        while (i<Main.memoria_fisica.size()){
            if (Main.memoria_fisica.get(i).contenido.identificador==idProceso){
                listaLocal.add(Main.memoria_fisica.get(i));
                i++;
            }
            else{i++;}
        }
        return listaLocal;
    }
}

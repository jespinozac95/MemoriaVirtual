/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;





/**
 *
 * @author Andres
 */
public class FIFO {       
    public int CompararFIFO(Proceso proceso){        
        if ((Main.replacement_scope_global==true)&&(Main.global_convertido_fijo==false)){  //no se va a utilizar el proceso recibido.
            Frame actual = Main.memoria_fisica.get(0);
            int posicionActual = 0;
            int n=1;
            while(n<Main.memoria_fisica.size()){
                if ((Main.memoria_fisica.get(n).identificador==actual.identificador)&&(Main.memoria_fisica.get(n).contenido.identificador!=actual.contenido.identificador)){
                    break;
                }
                if(Main.memoria_fisica.get(n).esta_reservado==true){
                    if ((Main.memoria_fisica.get(n).identificador==actual.identificador)&&(Main.memoria_fisica.get(n).contenido.identificador!=actual.contenido.identificador)){
                        n++;
                    }
                    else{
                        if (actual.TS1<Main.memoria_fisica.get(n).TS1){
                            n++;
                        }
                        else if (actual.TS1>Main.memoria_fisica.get(n).TS1){
                            actual = Main.memoria_fisica.get(n);
                            posicionActual = n;
                            n++;                            
                        }
                    }
                }                
                else if (actual.TS1<Main.memoria_fisica.get(n).TS1){
                    n++;
                }
                else if (actual.TS1>Main.memoria_fisica.get(n).TS1){
                    actual = Main.memoria_fisica.get(n);
                    posicionActual = n;
                    n++;                    
                }
            }
           System.out.println("posicionActual: "+posicionActual);
           return posicionActual;
        }    
        
        else {
            //local
            int posicionActual = 0;
            int id_proceso = proceso.identificador;            
            Frame actual = Main.memoria_fisica.get(0);
            int n=1;
            while(n<Main.memoria_fisica.size()){
                if(proceso.identificador==Main.memoria_fisica.get(n).contenido.identificador){
                    if ((Main.memoria_fisica.get(n).identificador==actual.identificador)&&(Main.memoria_fisica.get(n).contenido.identificador!=actual.contenido.identificador)){
                        break;
                    }
                    if(Main.memoria_fisica.get(n).esta_reservado==true){                        
                            if (actual.TS1<Main.memoria_fisica.get(n).TS1){
                                n++;
                            }
                            else if (actual.TS1>Main.memoria_fisica.get(n).TS1){
                                actual = Main.memoria_fisica.get(n);
                                posicionActual = n;
                                n++;                            
                            }
                        }
                    }
                    else if (actual.TS1<Main.memoria_fisica.get(n).TS1){        
                        n++;
                    }
                    else if (actual.TS1>Main.memoria_fisica.get(n).TS1){
                        actual = Main.memoria_fisica.get(n);
                        posicionActual = n;
                        n++;                    
                    }                
                
                else{n++;}
            }
	    Main.global_convertido_fijo=false;
            System.out.println("posicionActual: "+posicionActual);
            return posicionActual;
        }                
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

    
    
    public Proceso buscaProceso(String nombreProceso){
        for (int i=0;i<Main.memoria_virtual.size();i++){
            if (nombreProceso.equals(Main.lista_Procesos.get(i).nombre)){
                return Main.memoria_virtual.get(i).contenido;
            }            
        } 
        return null;
    }
    
    
}



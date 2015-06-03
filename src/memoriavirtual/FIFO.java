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
                    posicionActual = -1;
                    break;
                }
                if (esApto(Main.memoria_fisica.get(n).contenido)==false){
                        n++;
                    }
                if(Main.memoria_fisica.get(n).esta_reservado==true){
                    if ((Main.memoria_fisica.get(n).identificador==actual.identificador)&&(Main.memoria_fisica.get(n).contenido.identificador!=actual.contenido.identificador)){
                        n++;
                    }
                    if (esApto(Main.memoria_fisica.get(n).contenido)==false){
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
            int posicionActual = 0;
            int id_proceso = proceso.identificador;            
            Frame actual = Main.memoria_fisica.get(0);
            int n=1;
            while(n<Main.memoria_fisica.size()){
                if(proceso.nombre.equals(Main.memoria_fisica.get(n).contenido.nombre)){
                    if ((Main.memoria_fisica.get(n).identificador==actual.identificador)&&(Main.memoria_fisica.get(n).contenido.identificador!=actual.contenido.identificador)){
                        posicionActual = -1;
                        break;
                    }
                    if (esApto(Main.memoria_fisica.get(n).contenido)==false){
                        n++;
                    }
                    if(Main.memoria_fisica.get(n).esta_reservado==true){
                        if (Main.memoria_fisica.get(n).contenido.esta_bloqueado){
                            n++;
                        } //M�s viejo menor
                        if (esApto(Main.memoria_fisica.get(n).contenido)==false){
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
                else{n++;}
            }
            System.out.println("posicionActual: "+posicionActual);
            return posicionActual;
        }                
    }
    public boolean esApto(Proceso p){
        //Main.tama�o_inicial    
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



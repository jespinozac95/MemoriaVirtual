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
public class LRU {
    public int CompararLRU(Proceso proceso){        
        if ((Main.replacement_scope_global==true)&&(Main.global_convertido_fijo==false)){  //no se va a utilizar el proceso recibido.
            Frame actual = Main.memoria_fisica.get(0);
            int posicionActual = 0;
            int n = 1;
            while(n<Main.memoria_fisica.size()){
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
                    }
                    if (esApto(Main.memoria_fisica.get(n).contenido)==false){
                        n++;
                    }
                    else{
                        if (actual.TS_ultima_referencia>Main.memoria_fisica.get(n).TS_ultima_referencia){
                            //Actual se ha utilizado más recientemente por lo que actual debe cambiar a get(n)
                            actual = Main.memoria_fisica.get(n);
                            n++;
                            posicionActual = n;
                        }
                        else if (actual.TS_ultima_referencia<Main.memoria_fisica.get(n).TS_ultima_referencia){
                            n++;
                        }
                    }
                }                
                else if (actual.TS_ultima_referencia<Main.memoria_fisica.get(n).TS_ultima_referencia){   
                    n++;
                }
                else if (actual.TS_ultima_referencia>Main.memoria_fisica.get(n).TS_ultima_referencia){
                    actual = Main.memoria_fisica.get(n);
                    posicionActual = n;
                    n++;                    
                }
            }
           Main.global_convertido_fijo=false;
           System.out.println("posicionActual: "+posicionActual);
           return posicionActual;
        }    
        
        else { //Aqui es local
            int posicionActual = 0;                        
            Frame actual = Main.memoria_fisica.get(0);
            int n = 1;
            while(n<Main.memoria_fisica.size()){
                //System.out.println();
                if (proceso.nombre.equals(Main.memoria_fisica.get(n).contenido.nombre)){
                    if ((Main.memoria_fisica.get(n).identificador==actual.identificador)&&(Main.memoria_fisica.get(n).contenido.identificador!=actual.contenido.identificador)){
                        posicionActual = -1;
                        break;
                    }
                    if (esApto(Main.memoria_fisica.get(n).contenido)==false){
                        n++;
                    }
                    if(Main.memoria_fisica.get(n).esta_reservado==true){                        
                            if (actual.TS_ultima_referencia>Main.memoria_fisica.get(n).TS_ultima_referencia){
                                actual = Main.memoria_fisica.get(n);
                                n++;
                                posicionActual = n;
                            }
                            else if (actual.TS_ultima_referencia<Main.memoria_fisica.get(n).TS_ultima_referencia){
                                n++;
                            }
                        }
                    }
                    else if (actual.TS_ultima_referencia<Main.memoria_fisica.get(n).TS_ultima_referencia){ 
                        n++;
                    }
                    else if (actual.TS_ultima_referencia>Main.memoria_fisica.get(n).TS_ultima_referencia){
                        actual = Main.memoria_fisica.get(n);
                        n++;
                        posicionActual = n;
                    }
                }
            
            System.out.println("posicionActual: "+posicionActual);
            return posicionActual;
        } //termina while           
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

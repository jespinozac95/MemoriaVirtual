/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

import java.util.List;

/**
 *
 * @author Andres
 */
public class Clock {
    
    public int CompararClock(Proceso proceso){        
        boolean hayReemplazo=false;
        int posicionActual=Main.puntero_clock;
        if ((Main.replacement_scope_global==true)&&(Main.global_convertido_fijo==false)){  //no se va a utilizar el proceso recibido.
            while (hayReemplazo==false){
                if(posicionActual==(Main.memoria_fisica.size())-1){
                    posicionActual=Main.puntero_clock=0;
                    if ((Main.memoria_fisica.get(posicionActual).bitReloj==0)&&(Main.memoria_fisica.get(posicionActual).contenido.esta_bloqueado==false)){
                        Main.memoria_fisica.get(posicionActual).bitReloj=1;
                        Main.puntero_clock++;
                        hayReemplazo=true;
                    }
                    else if ((Main.memoria_fisica.get(posicionActual).bitReloj==1)&&(Main.memoria_fisica.get(posicionActual).contenido.esta_bloqueado==false)){
                        Main.memoria_fisica.get(posicionActual).bitReloj=0;
                        posicionActual++;
                    }
                }
                else{
                    if ((Main.memoria_fisica.get(posicionActual).bitReloj==0)&&(Main.memoria_fisica.get(posicionActual).contenido.esta_bloqueado==false)){
                        Main.memoria_fisica.get(posicionActual).bitReloj=1;
                        Main.puntero_clock++;
                        hayReemplazo=true;
                    }
                    else if ((Main.memoria_fisica.get(posicionActual).bitReloj==1)&&(Main.memoria_fisica.get(posicionActual).contenido.esta_bloqueado==false)){
                        Main.memoria_fisica.get(posicionActual).bitReloj=0;
                        posicionActual++;
                    }
                }  
            }
        
        System.out.println("posicionActual: "+posicionActual);
        return posicionActual;
        }
        else{
            while (hayReemplazo==false){
                if(posicionActual==(Main.memoria_fisica.size())-1){
                    posicionActual=Main.puntero_clock=0;
                    if(proceso.identificador==Main.memoria_fisica.get(posicionActual).contenido.identificador){
                        if (Main.memoria_fisica.get(posicionActual).bitReloj==0){
                            Main.memoria_fisica.get(posicionActual).bitReloj=1;
                            Main.puntero_clock++;
                            hayReemplazo=true;
                        }
                        else if (Main.memoria_fisica.get(posicionActual).bitReloj==1){
                            Main.memoria_fisica.get(posicionActual).bitReloj=0;
                            posicionActual++;
                        }
                    }
                    else{posicionActual++;}
                }
                else{
                    if(proceso.identificador==Main.memoria_fisica.get(posicionActual).contenido.identificador){
                        if (Main.memoria_fisica.get(posicionActual).bitReloj==0){
                            Main.memoria_fisica.get(posicionActual).bitReloj=1;
                            Main.puntero_clock++;
                            hayReemplazo=true;
                        }
                        else if (Main.memoria_fisica.get(posicionActual).bitReloj==1){
                            Main.memoria_fisica.get(posicionActual).bitReloj=0;
                            posicionActual++;
                        }
                    }
                }  
            }  
	Main.global_convertido_fijo=false;
        System.out.println("posicionActual: "+posicionActual);
        return posicionActual;      
        }
    }
}


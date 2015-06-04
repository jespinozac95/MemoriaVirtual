/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;


/**
 *
 * @author Andres
 */
public class Clock {
    
    public int CompararClock(Proceso proceso){
        System.out.println("Entre a hacer clock");
        boolean hayReemplazo=false;
        int posicionActual=Main.puntero_clock;
        if ((Main.replacement_scope_global==true)&&(Main.global_convertido_fijo==false)){  //no se va a utilizar el proceso recibido.
            while (hayReemplazo==false){
                if(posicionActual==(Main.memoria_fisica.size())-1){
                    posicionActual=Main.puntero_clock=0;
                    if (esApto(Main.memoria_fisica.get(posicionActual).contenido)==false){
                        posicionActual++;
                    }
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
            System.out.println("Entre a hacer clock local");
            while (hayReemplazo==false){
                System.out.println("Entre a while");
                if(posicionActual==(Main.memoria_fisica.size())-1){
                    System.out.println("posicionActual y el tamano dela vara son iguales");
                    posicionActual=Main.puntero_clock=0;
                    if ((Main.memoria_fisica.get(posicionActual).esta_reservado)||(esApto(Main.memoria_fisica.get(posicionActual).contenido)==false)){                                                
                        posicionActual++;
                        System.out.println("posicionActal: "+posicionActual);
                    }
                    if((!Main.memoria_fisica.get(posicionActual).esta_reservado)&&(proceso.identificador==Main.memoria_fisica.get(posicionActual).contenido.identificador)){
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
                    if (!Main.memoria_fisica.get(posicionActual).esta_reservado){
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
                        else {posicionActual++;}
                    }
                    else{
                        posicionActual++;
                    }
                }  
            }  
	Main.global_convertido_fijo=false;
        System.out.println("posicionActual: "+posicionActual);
        return posicionActual;      
        }
    }
    public boolean esApto(Proceso p){
        //Main.tama�o_inicial    
        int contador=0;
        for (int i=0;i<Main.memoria_fisica.size();i++){   
            if (!Main.memoria_fisica.get(i).esta_reservado){
            if (Main.memoria_fisica.get(i).contenido.nombre.equals(p.nombre)){
                contador++;
            }           
            }
            else{
                if (Main.memoria_fisica.get(i).proceso_reserva.equals(p.nombre)){
                contador++;
            }   
            }
        }
        if (contador <=Main.tamaño_inicial){
            return false;
        }
        else{return true;}
    }
}


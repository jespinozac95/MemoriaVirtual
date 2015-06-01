/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

import java.util.Date;

/**
 *
 * @author Josue
 */
public class Log {
    //# de referencia, proceso, frame en cuestion, reemplazo, TS
    public int numero_referencia;
    public String proceso;
    public int frame_en_cuestion; //identificador del frame
    public boolean reemplazo;
    public Date ts;
    
    public Log(int numero, String proceso, int frame, boolean reemplazo, Date ts){
        this.numero_referencia = numero;
        this.proceso = proceso;
        this.frame_en_cuestion = frame;
        this.reemplazo = reemplazo;
        this.ts = ts;
    }
}

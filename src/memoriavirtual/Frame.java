/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

import java.util.Date;

/**
 *
 * @author Adrian
 * 
 */


public class Frame {
    
    public int identificador;
    public Proceso contenido;
    public boolean esta_reservado=false;
    public String proceso_reserva;
    public int tamano;
    public int[][] localizacion = new int[1][3]; //ubicacion_memoriaF
    public long TS1;
    public boolean modificado;
//    public Date TS_ultima_referencia;
    public long TS_ultima_referencia;
    public int bitReloj=1;
    public long tiempo;
    
    public Frame(Proceso contenido){ //Para las páginas de memoria virtual
        //this.identificador = ultimo_id++;
        this.contenido = contenido;
    }
    
    public Frame(int identificador, Proceso contenido, int tamano) {
        this.identificador = identificador;
        this.contenido = contenido;
        this.tamano = tamano;
    }

    public Frame(int identificador, Proceso contenido, int tamano, long TS1, long TS2) {
        this.identificador = identificador;
        this.contenido = contenido;
        this.tamano = tamano;
        this.TS1 = TS1; //=DateTime.parse("04/02/2011 20:27:05",DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));;
        this.TS_ultima_referencia = TS2; //=DateTime.parse("04/02/2011 20:27:05",DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));;
    }
    
    public Frame (boolean reservado, String proceso_reserva){
        this.esta_reservado = reservado;
        this.proceso_reserva = proceso_reserva;
    }
    public Date ObtenerTiempoActual(){
        java.util.Date date= new java.util.Date();
        return date;
    }
    
}
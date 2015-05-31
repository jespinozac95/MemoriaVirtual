/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

/**
 *
 * @author Josue
 */
class Referencia {
    public int id_proceso;
    public int tamano;
    public char tipo_ejecucion; //w o r
    
    public Referencia(int id, int tamano, char tipo_ejecucion){
        this.id_proceso = id;
        this.tamano = tamano;
        this.tipo_ejecucion = tipo_ejecucion;
    }
}

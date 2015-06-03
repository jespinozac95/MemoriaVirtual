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
public class FetchPolicy {
    public void prepaging(){ //cargar las primeras páginas de cada proceso
        int contadorFisica = 0;
        int contadorVirtual = 0;
        while ((contadorFisica < Main.memoria_fisica.size()) && (contadorVirtual < Main.memoria_virtual.size())){
            if (Main.memoria_fisica.get(contadorFisica).proceso_reserva.equals(Main.memoria_virtual.get(contadorVirtual).contenido.nombre)){
                //System.out.println("Consiguió el frame necesario para memoria fisica ["+contadorFisica+"] en memoria virtual ["+contadorVirtual+"].");
                Frame f =  Main.memoria_virtual.get(contadorVirtual);
                f.TS1 = System.nanoTime();
                Main.memoria_fisica.set(contadorFisica,f);
                contadorFisica ++;
                contadorVirtual ++;
            }
            else{
                //Proceso tiene reservadas más páginas en memoria física que las que tiene en memoria virtual
                String nombre_proceso = Main.memoria_fisica.get(contadorFisica).proceso_reserva;
                Proceso elegido = null;
                for (Proceso p : Main.lista_Procesos){
                    if (p.nombre.equals(nombre_proceso)){
                        elegido = p;
                    }
                }
                try{
                    if (elegido.num_pags < Main.tamaño_fijo){ //Mem Virtual < Mem física --> dejar reservado
                        contadorFisica ++;
                    }
                }
                catch (Exception e){}
                try{
                    if (elegido.num_pags > Main.tamaño_fijo){ //Mem Virtual > Mem física
                        contadorVirtual ++;
                    }
                }
                catch (Exception e){}
            }
        }
    }
    public void fetch(int posicionVirtual, int posicionFisica, boolean modificacion){
        if (posicionFisica != -1){
            Main.memoria_fisica.set(posicionFisica, Main.memoria_virtual.get(posicionVirtual));
            Main.memoria_fisica.get(posicionFisica).esta_ocupado = true;
            Main.memoria_fisica.get(posicionFisica).TS_ultima_referencia = System.nanoTime();
            if (modificacion)
                Main.memoria_fisica.get(posicionFisica).modificado = true;
        }
    }
}

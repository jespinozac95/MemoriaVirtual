/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Josue
 */
public class Controlador {
    //Clase que controla la ejecución de referencias, manda a llamar métodos de las políticas Fetch, Placement, Replacement, Cleaning Demand
    
    public static boolean todosMemoriaFisicaReservados(){
        for (Frame f : Main.memoria_fisica){
            if (!f.esta_reservado)
                System.out.println("memoriafisica con un no reservado en: "+f.identificador);
                return false;
        }
        System.out.println("memoriafisica llena de reservados");
        return true;
    }
    public static boolean memoriaFisicaLlena(){
        boolean llena = true;
        if (Main.memoria_fisica.isEmpty()){
            return false;
        }
        if (todosMemoriaFisicaReservados())
            return false;
        for (Frame f : Main.memoria_fisica){
            System.out.println("f.identificador = "+f.identificador);
            if (!f.esta_ocupado){
                llena = false;
                return llena;
            }    
        }
        return llena;
    }
    public static boolean procesoLleno(String nombre_proceso){
        boolean lleno = true;
        for (Frame f : Main.memoria_fisica){
            try{
                if (f.proceso_reserva.equals(nombre_proceso)){
                    if (!f.esta_ocupado)
                        lleno = false;
                }
            }
            catch (Exception e){}
            try{
                if (f.contenido.nombre.equals(nombre_proceso)){
                    if (!f.esta_ocupado)
                        lleno = false;
                }
            }
            catch (Exception e){}
        }
        return lleno;
    }
    public static int indiceProceso(int id_proceso){
        int indice = 0;
        for (Proceso p : Main.lista_Procesos){
            if (p.identificador == id_proceso)
                return indice;
            indice++;
        }
        return indice;
    }
    public static void ejecutarReferencias(){
        //Ejecutar cada referencia en la lista: Main.lineas_referencias_en_cuestion
        //Si es la primera vez (Memoria Fisica nula) --> Working set
        if (Main.memoria_fisica.isEmpty()){
            ResidentSet rs = new ResidentSet();
            //rs.ResidentSetFijo(Main.tamaño_fijo);
            rs.ResidentSetFijo();     
            //System.out.println("Se realizó el resident set fijo");
            Log l = new Log("Carga del Resident Set",new Date());
            Main.log.add(l);
            if (!Main.fetch_demand){
                FetchPolicy fp = new FetchPolicy();
                fp.prepaging();
                l = new Log("Fetch Policy cargada",new Date());
                Main.log.add(l);
                System.out.println("FETCH POLICY REALIZADA");
                //System.out.println("Se realizó fetch prepaging");
            }
        }
        for(Referencia r : Main.lineas_referencias_en_cuestion){
            Proceso p = Main.lista_Procesos.get(indiceProceso(r.id_proceso));
            //System.out.println("referencia # "+r.id_proceso);
            //Si memoria Fisica no llena --> Placement
            
            if (r.tamano > p.tamano){
                JOptionPane.showMessageDialog(new JFrame(), "La referencia leída especifica un tamaño mayor al del proceso que utiliza.");
            }
            else{
                //---------------------------------------------------------------------------------------------------------
                if (!(Main.resident_set_management_fixed) && (!memoriaFisicaLlena())){//placement manual
                    FetchPolicy fpi = new FetchPolicy();
                    ResidentSet rset= new ResidentSet();
                    //if ya está, no le haga nada
                    if (yaEstaEnFisica(frameNecesitado(r.tamano,p))){
                        System.out.println("No hay necesidad de hacer placement ni replacement.");
                        if (r.tipo_ejecucion=='w'){
                            Main.memoria_fisica.get(indiceEnFisica(frameNecesitado(r.tamano,p))).modificado = true;
                            Log l = new Log(r.id_proceso,p.nombre+"-Referencia",indiceEnFisica(frameNecesitado(r.tamano,p)),false,new Date());
                            Main.log.add(l);
                        }
                        else{
                            Main.memoria_fisica.get(indiceEnFisica(frameNecesitado(r.tamano,p))).modificado = false;
                            Log l = new Log(r.id_proceso,p.nombre+"-Referencia",indiceEnFisica(frameNecesitado(r.tamano,p)),false,new Date());
                            Main.log.add(l);
                        }
                    }
                    else if (Main.placement_first_available){//Placement first avaiable
                        System.out.println("frame necesitado = "+frameNecesitado(r.tamano,p).identificador+" del proceso: "+p.nombre+". rset.FirstAvailable() = "+rset.FirstAvailable());
                        Main.procesoManejado = p.nombre;
                        if (rset.FirstAvailable() == -1){
                            //Hacer reemplazo
                            if (r.tipo_ejecucion=='w'){
                                Controlador.Reemplazo(p, frameNecesitado(r.tamano,p), true);
                                Log l = new Log(r.id_proceso,p.nombre+"-Reemplazo",indiceEnFisica(frameNecesitado(r.tamano,p)),true,new Date());
                                    Main.log.add(l);
                                System.out.println("Replacement con write.");
                            }
                                else{
                                Controlador.Reemplazo(p, frameNecesitado(r.tamano,p), false);
                                Log l = new Log(r.id_proceso,p.nombre+"-Reemplazo",indiceEnFisica(frameNecesitado(r.tamano,p)),true,new Date());
                                    Main.log.add(l);
                                System.out.println("Replacement sin write.");
                            }
                        }
                        else if (r.tipo_ejecucion=='w'){
                            //Manejo del -1 que tira el rset.FA
                            Main.procesoManejado = p.nombre;
                            fpi.fetch(getIndiceFrameVirtual(frameNecesitado(r.tamano,p)),rset.FirstAvailable(), true);
                            Log l = new Log(r.id_proceso,p.nombre+"-Placement",indiceEnFisica(frameNecesitado(r.tamano,p)),false,new Date());
                            Main.log.add(l);
                            System.out.println("Placement con write.");
                        }
                        else{
                            Main.procesoManejado = p.nombre;
                            fpi.fetch(getIndiceFrameVirtual(frameNecesitado(r.tamano,p)),rset.FirstAvailable(), false);
                            Log l = new Log(r.id_proceso,p.nombre+"-Placement",indiceEnFisica(frameNecesitado(r.tamano,p)),false,new Date());
                            Main.log.add(l);
                            System.out.println("Placement sin write.");
                        }
                    }
                    else{//Placement next avaiable
                        System.out.println("frame necesitado = "+frameNecesitado(r.tamano,p).identificador+" del proceso: "+p.nombre+". rset.FirstAvailable() = "+rset.FirstAvailable());
                        Main.procesoManejado = p.nombre;
                        if (rset.NextAvailable() == -1){
                            //HACER REEMPLAZO
                            if (r.tipo_ejecucion=='w'){
                                Controlador.Reemplazo(p, frameNecesitado(r.tamano,p), true);
                                Log l = new Log(r.id_proceso,p.nombre+"-Reemplazo",indiceEnFisica(frameNecesitado(r.tamano,p)),true,new Date());
                                    Main.log.add(l);
                                System.out.println("Replacement con write.");
                            }
                                else{
                                Controlador.Reemplazo(p, frameNecesitado(r.tamano,p), false);
                                Log l = new Log(r.id_proceso,p.nombre+"-Reemplazo",indiceEnFisica(frameNecesitado(r.tamano,p)),true,new Date());
                                    Main.log.add(l);
                                System.out.println("Replacement sin write.");
                            }
                        }
                        else if (r.tipo_ejecucion=='w'){
                            Main.procesoManejado = p.nombre;
                            fpi.fetch(getIndiceFrameVirtual(frameNecesitado(r.tamano,p)),rset.NextAvailable(),true);
                            Log l = new Log(r.id_proceso,p.nombre+"-PlacementNextAv",indiceEnFisica(frameNecesitado(r.tamano,p)),false,new Date());
                            Main.log.add(l);
                            System.out.println("Placement con write.");
                        }
                        else{
                            Main.procesoManejado = p.nombre;
                            fpi.fetch(getIndiceFrameVirtual(frameNecesitado(r.tamano,p)),rset.NextAvailable(),false);
                            Log l = new Log(r.id_proceso,p.nombre+"-PlacementNextAv",indiceEnFisica(frameNecesitado(r.tamano,p)),false,new Date());
                            Main.log.add(l);
                            System.out.println("Placement con write.");
                        }
                    }
                }
                //---------------------------------------------------------------------------------------------------------
                else if ((Main.resident_set_management_fixed) && (!memoriaFisicaLlena())){//placement manual
                    FetchPolicy fpi = new FetchPolicy();
                    ResidentSet rset= new ResidentSet();
                    //if ya está, no le haga nada
                    if (yaEstaEnFisica(frameNecesitado(r.tamano,p))){
                        System.out.println("No hay necesidad de hacer placement ni replacement.");
                        if (r.tipo_ejecucion=='w'){
                            Main.memoria_fisica.get(indiceEnFisica(frameNecesitado(r.tamano,p))).modificado = true;
                            Log l = new Log(r.id_proceso,p.nombre+"-Referencia",indiceEnFisica(frameNecesitado(r.tamano,p)),false,new Date());
                            Main.log.add(l);
                        }
                        else{
                            Main.memoria_fisica.get(indiceEnFisica(frameNecesitado(r.tamano,p))).modificado = false;
                            Log l = new Log(r.id_proceso,p.nombre+"-Referencia",indiceEnFisica(frameNecesitado(r.tamano,p)),false,new Date());
                            Main.log.add(l);
                        }
                    }
                    else if (Main.placement_first_available){//Placement first avaiable
                        Main.procesoManejado = p.nombre;
                        if (rset.FirstAvailable() == -1){
                            //HACER REEMPLAZO
                            if (r.tipo_ejecucion=='w'){
                                Controlador.Reemplazo(p, frameNecesitado(r.tamano,p), true);
                                Log l = new Log(r.id_proceso,p.nombre+"-Reemplazo",indiceEnFisica(frameNecesitado(r.tamano,p)),true,new Date());
                                    Main.log.add(l);
                                System.out.println("Replacement con write.");
                            }
                                else{
                                Controlador.Reemplazo(p, frameNecesitado(r.tamano,p), false);
                                Log l = new Log(r.id_proceso,p.nombre+"-Reemplazo",indiceEnFisica(frameNecesitado(r.tamano,p)),true,new Date());
                                    Main.log.add(l);
                                System.out.println("Replacement sin write.");
                            }
                        }
                        else if (r.tipo_ejecucion=='w'){
                            Main.procesoManejado = p.nombre;
                            System.out.println("frame necesitado = "+frameNecesitado(r.tamano,p).identificador+" del proceso: "+p.nombre+". rset.FirstAvailable() = "+rset.FirstAvailable());
                        fpi.fetch(getIndiceFrameVirtual(frameNecesitado(r.tamano,p)),rset.FirstAvailable(), true);
                            Log l = new Log(r.id_proceso,p.nombre+"-Placement",indiceEnFisica(frameNecesitado(r.tamano,p)),false,new Date());
                            Main.log.add(l);
                            System.out.println("Placement con write.");
                        }
                        else{
                            Main.procesoManejado = p.nombre;
                            System.out.println("frame necesitado = "+frameNecesitado(r.tamano,p).identificador+" del proceso: "+p.nombre+". rset.FirstAvailable() = "+rset.FirstAvailable());
                            fpi.fetch(getIndiceFrameVirtual(frameNecesitado(r.tamano,p)),rset.FirstAvailable(), false);
                            Log l = new Log(r.id_proceso,p.nombre+"-Placement",indiceEnFisica(frameNecesitado(r.tamano,p)),false,new Date());
                            Main.log.add(l);
                            System.out.println("Placement sin write.");
                        }
                    }
                    else{//Placement next avaiable
                        Main.procesoManejado = p.nombre;
                        if (rset.NextAvailable() == -1){
                            //HACER REEMPLAZO
                            if (r.tipo_ejecucion=='w'){
                                Controlador.Reemplazo(p, frameNecesitado(r.tamano,p), true);
                                Log l = new Log(r.id_proceso,p.nombre+"-Reemplazo",indiceEnFisica(frameNecesitado(r.tamano,p)),true,new Date());
                                    Main.log.add(l);
                                System.out.println("Replacement con write.");
                            }
                                else{
                                Controlador.Reemplazo(p, frameNecesitado(r.tamano,p), false);
                                Log l = new Log(r.id_proceso,p.nombre+"-Reemplazo",indiceEnFisica(frameNecesitado(r.tamano,p)),true,new Date());
                                    Main.log.add(l);
                                System.out.println("Replacement sin write.");
                            }
                        }
                        else if (r.tipo_ejecucion=='w'){
                            Main.procesoManejado = p.nombre;
                            System.out.println("frame necesitado = "+frameNecesitado(r.tamano,p).identificador+" del proceso: "+p.nombre+". rset.FirstAvailable() = "+rset.FirstAvailable());
                            fpi.fetch(getIndiceFrameVirtual(frameNecesitado(r.tamano,p)),rset.NextAvailable(),true);
                            Log l = new Log(r.id_proceso,p.nombre+"-PlacementNextAv",indiceEnFisica(frameNecesitado(r.tamano,p)),false,new Date());
                            Main.log.add(l);
                            System.out.println("Placement con write.");
                        }
                        else{
                            Main.procesoManejado = p.nombre;
                            System.out.println("frame necesitado = "+frameNecesitado(r.tamano,p).identificador+" del proceso: "+p.nombre+". rset.FirstAvailable() = "+rset.FirstAvailable());
                            fpi.fetch(getIndiceFrameVirtual(frameNecesitado(r.tamano,p)),rset.NextAvailable(),false);
                            Log l = new Log(r.id_proceso,p.nombre+"-PlacementNextAv",indiceEnFisica(frameNecesitado(r.tamano,p)),false,new Date());
                            Main.log.add(l);
                            System.out.println("Placement con write.");
                        }
                    }
                }
                //---------------------------------------------------------------------------------------------------------
                else if ((Main.resident_set_management_fixed) && (memoriaFisicaLlena())){//reemplazo manual
                    if (r.tipo_ejecucion=='w'){
                        Controlador.Reemplazo(p, frameNecesitado(r.tamano,p), true);
                        Log l = new Log(r.id_proceso,p.nombre+"-Reemplazo",indiceEnFisica(frameNecesitado(r.tamano,p)),true,new Date());
                            Main.log.add(l);
                        System.out.println("Replacement con write.");
                    }
                        else{
                        Controlador.Reemplazo(p, frameNecesitado(r.tamano,p), false);
                        Log l = new Log(r.id_proceso,p.nombre+"-Reemplazo",indiceEnFisica(frameNecesitado(r.tamano,p)),true,new Date());
                            Main.log.add(l);
                        System.out.println("Replacement sin write.");
                    }
                }
                //---------------------------------------------------------------------------------------------------------
                else{//RS set variable
                    System.out.println("------------MemFisica llena ="+memoriaFisicaLlena());
                    ResidentSet rset = new ResidentSet();
                    if (r.tipo_ejecucion=='w'){
                        rset.ResidentSetVariableIncrementar(p.nombre,frameNecesitado(r.tamano,p),true);
                        Log l = new Log(r.id_proceso,p.nombre+"-Reemplazo(Variable)*(No reemplazo siempre)",indiceEnFisica(frameNecesitado(r.tamano,p)),true,new Date());
                            Main.log.add(l);
                            System.out.println("Resident set con write.*(No reemplazo siempre)");
                        }
                        else{
                        System.out.println("Resident set sin write.*(No reemplazo siempre)");
                        rset.ResidentSetVariableIncrementar(p.nombre,frameNecesitado(r.tamano,p),false);
                        Log l = new Log(r.id_proceso,p.nombre+"-Reemplazo(Variable)*(No reemplazo siempre)",indiceEnFisica(frameNecesitado(r.tamano,p)),true,new Date());
                            Main.log.add(l);
                        }
                        
                }
            }
            /*if (!((procesoLleno(p.nombre)) || (memoriaFisicaLlena()))){
                
                //JOptionPane.showMessageDialog(new JFrame(), "Placement");
            }
            //Si memoria Fisica llena o scope local *fixed--> Replacement.AsignarPagina(r);
            else{
                //JOptionPane.showMessageDialog(new JFrame(), "Replacement");
            }*/
        }
    }
    public static Frame frameNecesitado (int tamano, Proceso p){
        int id_pag = (tamano/1048576)+1;
        for (Frame f : Main.memoria_virtual){
            if ((f.contenido.equals(p)) && (f.identificador == id_pag))
                return f;
        }
        return null;
    }
    public static int indiceReemplazo(Proceso p){
        int indice = 0;
        switch (Main.replacement_policy){
            case "LRU":
                LRU l = new LRU();
                indice = l.CompararLRU(p);
                break;
            case "FIFO":
                FIFO f = new FIFO();
                indice = f.CompararFIFO(p);
                break;
            case "MRU":
                MRU m = new MRU();
                indice = m.CompararMRU(p);
                break;
            case "Clock":
                Clock c = new Clock();
                indice = c.CompararClock(p);
                break;
        }
        return indice;
    }
    public static void Reemplazo(Proceso p, Frame necesitadoVirtual, boolean modificado){
        int indice = indiceReemplazo(p);
        //Preguntar si es cleaning demand
        Frame f = Main.memoria_fisica.get(indice);
        if (Main.cleaning_demand)
            CleaningDemand(f);
        FetchPolicy fetch = new FetchPolicy();
        fetch.fetch(getIndiceFrameVirtual(f), indice, modificado);
    }
    public static int getIndiceFrameVirtual(Frame f){
        int indice = 0;
        for (Frame e : Main.memoria_virtual){
            if (e.equals(f))
                return indice;
            indice++;
        }
        return indice;
    }
    public static void CleaningDemand(Frame f){ //llamarlo antes de hacer el replacement
        f.modificado = false;
        JOptionPane.showMessageDialog(new JFrame(), "Frame "+f.identificador+" del proceso: "+f.contenido.nombre+", fue limpiado debido a un reemplazo (Cleaning Demand).");
    }

    public static boolean yaEstaEnFisica(Frame frameNecesitado) {
        for (Frame f : Main.memoria_fisica){
            try{
                if (f.contenido.nombre.equals(frameNecesitado.contenido.nombre) && (f.identificador == frameNecesitado.identificador)){
                    return true;
                }
            }
            catch(Exception e){}
        }
        return false;
    }
    public static int indiceEnFisica(Frame frameNecesitado) {
        int indice = 0;
        for (Frame f : Main.memoria_fisica){
            try{
                if (f.contenido.nombre.equals(frameNecesitado.contenido.nombre) && (f.identificador == frameNecesitado.identificador)){
                    return indice;
                }
            }
            catch(Exception e){}
            indice++;
        }
        return indice;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Jespi_000
 */
public class FuncionamientoGUI extends javax.swing.JFrame {

    /**
     * Creates new form FuncionamientoGUI
     */
    public FuncionamientoGUI() {
        initComponents();
        CargarTablaReferencias();
        CargarTablaLog();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TablaDeReferencias = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        NextButton = new javax.swing.JButton();
        ResetButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaDeLog = new javax.swing.JTable();
        menu1 = new javax.swing.JMenuBar();
        Ayuda1 = new javax.swing.JMenu();
        General1 = new javax.swing.JMenuItem();
        General2 = new javax.swing.JMenuItem();
        Creditos = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Muestreo General");

        TablaDeReferencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Frame", "Proceso", "Página", "Bit de Bloqueo", "Dirty Bit (Modificación)", "TS de Creación", "TS de Modificación"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaDeReferencias);

        jLabel1.setText("Log de Actividad");

        NextButton.setText("Siguiente");
        NextButton.setToolTipText("Continuar leyendo y ejecutando las siguientes referencias.");
        NextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextButtonActionPerformed(evt);
            }
        });

        ResetButton.setText("Reset");
        ResetButton.setToolTipText("Volver a configurar el sistema desde la inserción de los archivos de texto.");
        ResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Tabla de Referencias");

        TablaDeLog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "# de Referencia", "Proceso", "Frame en Cuestión", "Reemplazo", "Time Stamp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TablaDeLog);

        Ayuda1.setText("Ayuda");
        Ayuda1.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                Ayuda1MenuSelected(evt);
            }
        });
        Ayuda1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ayuda1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Ayuda1MousePressed(evt);
            }
        });
        Ayuda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ayuda1ActionPerformed(evt);
            }
        });

        General1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        General1.setText("Ayuda General");
        General1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                General1ActionPerformed(evt);
            }
        });
        Ayuda1.add(General1);

        General2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        General2.setText("Configuraciones Actuales");
        General2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                General2ActionPerformed(evt);
            }
        });
        Ayuda1.add(General2);

        menu1.add(Ayuda1);

        Creditos.setText("Créditos");
        Creditos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CreditosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CreditosMousePressed(evt);
            }
        });
        Creditos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreditosActionPerformed(evt);
            }
        });
        Creditos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CreditosKeyPressed(evt);
            }
        });
        menu1.add(Creditos);

        setJMenuBar(menu1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ResetButton)
                        .addGap(39, 39, 39)
                        .addComponent(NextButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 514, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(176, 176, 176))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextButton)
                    .addComponent(ResetButton))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetButtonActionPerformed
        this.setVisible(false);
        this.dispose();
        Main.cleaning_demand = true; //para evitar que se siga haciendo precleaning
        Main.log.add(new Log(-1,"RESET",-1,false, new Date()));
        VentanaInicio vi = new VentanaInicio();
        vi.setVisible(true);
        Main.mFisica.dispose();
        Main.mVirtual.dispose();
        Main.mFisica = null;
        Main.mVirtual = null;
    }//GEN-LAST:event_ResetButtonActionPerformed

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButtonActionPerformed
        Log l = new Log("Siguientes referencias",new Date());
        Main.log.add(l);
        Main.lineas_referencias_en_cuestion = new LinkedList<Referencia>();
        if (Main.lineas_archivos_referencias.isEmpty()){
            JOptionPane.showMessageDialog(new JFrame(), "Ya se cargaron todas las referencias del archivo especificado.");
        }
        else{
            //System.out.println("Referencias totales = "+Main.lineas_archivos_referencias.size());
            for (int indice = 0; indice < Main.numero_referencias_por_iteracion; indice ++){
                try{
                    //Obtener y seccionar Referencia
                    String[] referenciaSeccionada = Main.lineas_archivos_referencias.get(indice).split(",");

                    //Validar Referencia
                    if (ValidarReferencia(referenciaSeccionada)){
                        //Agregar Referencia válida a la lista en Main.lineas_referencias_en_cuestion
                        Referencia r = new Referencia(Integer.parseInt(referenciaSeccionada[0]),Integer.parseInt(referenciaSeccionada[1]),referenciaSeccionada[2].charAt(0));
                        Main.lineas_referencias_en_cuestion.add(r);

                    }
                }
                catch(Exception e){
                    
                }
            }
            //Quitar la referencia de la lista de referencias
            //System.out.println("Referencias totales después de agregaciónes = "+Main.lineas_archivos_referencias.size());
            for (int i = 0; i < Main.numero_referencias_por_iteracion; i++){
                try{
                    Main.lineas_archivos_referencias.remove(Main.lineas_archivos_referencias.get(0));
                }
                catch(Exception e){
                    
                }
            }
            //System.out.println("Referencias totales despues de quitarlas = "+Main.lineas_archivos_referencias.size());
            
            Controlador.ejecutarReferencias();
            
            JOptionPane.showMessageDialog(new JFrame(), "Referencias cargadas con éxito.");

            if ((!Main.cleaning_demand)&&(!Main.precleaningActivo)){ //Precleaning --> thread cada 15segs
                Timer timer = new Timer();
                timer.schedule(new PreCleaning(), 0, 15000);
                Main.precleaningActivo = true;
            }
            
            //ACTUALIZAR Main.mFisica, Logs y Tabla de Referencias
            Main.mFisica.dispose();
            Main.mFisica = new MapMemoriaFisica();
            Main.mFisica.setVisible(true);
            this.CargarTablaLog();
            this.CargarTablaReferencias();
        }
    }//GEN-LAST:event_NextButtonActionPerformed
    
    class PreCleaning extends TimerTask{

        @Override
        public void run() {
            if (!Main.cleaning_demand){
                for (Frame f : Main.memoria_fisica){
                    if (f.modificado){
                        f.modificado = false;
                    }
                }
                JOptionPane.showMessageDialog(new JFrame(), "Precleaning activo ha limpiado los frames de la memoria física.");
                Main.log.add(new Log(-1,"PreCleaning",-1,false, new Date()));
                CargarTablaReferencias();
                CargarTablaLog();
                MapMemoriaFisica mapActualizado = new MapMemoriaFisica();
                if (Main.mFisica != null){
                    Main.mFisica.dispose();
                    Main.mFisica = mapActualizado;
                    Main.mFisica.setVisible(true);
                }
                else{
                    Main.mFisica = mapActualizado;
                    Main.mFisica.setVisible(true);
                }
            }
        }
        
    }
    
    private void General1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_General1ActionPerformed
        JOptionPane.showMessageDialog(new JFrame(),"Esta es la ventana del Muestreo General.\nAquí puede observar cuatro elementos en tres ventanas:"
                + "\n\n   - Ventana Muestreo General: Contiene dos elementos.\n"
                + "         * Tabla de Referencias: es un espejo del estado de la memoria virtual en ejecución (muestra varias columnas referentes a cada frame disponible de la memoria física).\n"
                + "         * Log de Actividad: muestra cada operación de Placement, Reemplazo y Cleaning realizada al cargar las "+Main.numero_referencias_por_iteracion+" referencias por iteración.\n\n"
                + "   - Ventana Map Memoria Física: muestra un gráfico de la memoria física al momento.\n     Cada cuadro corresponde a un frame. \n     Los cuadros negros son frames vacíos o asignados a páginas de procesos que no están en modificación (dirty) o bloqueados.\n"
                + "     Los cuadros rojos son frames que corresponden a páginas en modificación (dirty) de procesos.\n     Los cuadros azules corresponden a páginas de procesos bloqueados.\n     Los cuadros verdes corresponden a páginas reservadas para los procesos."
                + "\n\n   - Ventana Map Memoria Virtual: muestra la distribución de las páginas de los procesos cargados en la memoria virtual.","Ayuda - Muestreo General",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_General1ActionPerformed

    private void Ayuda1MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_Ayuda1MenuSelected

    }//GEN-LAST:event_Ayuda1MenuSelected

    private void Ayuda1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ayuda1MouseClicked

    }//GEN-LAST:event_Ayuda1MouseClicked

    private void Ayuda1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ayuda1MousePressed

    }//GEN-LAST:event_Ayuda1MousePressed

    private void Ayuda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ayuda1ActionPerformed

    }//GEN-LAST:event_Ayuda1ActionPerformed

    private void CreditosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreditosMouseClicked
        JOptionPane.showMessageDialog(new JFrame(),"El presente programa fue diseñado y desarrollado para el curso Infraestructura Tecnológica I, I semestre 2015.\n\nLos estudiantes responsables son:\n- Adrián Siles Masís\n- Mauricio Gamboa Cubero\n- Andrés Pacheco Quesada\n- Josué Espinoza Castro","Créditos",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_CreditosMouseClicked

    private void CreditosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreditosMousePressed
        JOptionPane.showMessageDialog(new JFrame(),"El presente programa fue diseñado y desarrollado para el curso Infraestructura Tecnológica I, I semestre 2015.\n\nLos estudiantes responsables son:\n- Adrián Siles Masís\n- Mauricio Gamboa Cubero\n- Andrés Pacheco Quesada\n- Josué Espinoza Castro","Créditos",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_CreditosMousePressed

    private void CreditosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreditosActionPerformed

    }//GEN-LAST:event_CreditosActionPerformed

    private void CreditosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CreditosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CreditosKeyPressed

    private void General2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_General2ActionPerformed
        String configuraciones = "";
        configuraciones += "Fetch Policy: ";
        if (Main.fetch_demand)
            configuraciones += "Demand";
        else
            configuraciones += "Prepaging";
        configuraciones += "\nPlacement Policy: ";
        if (Main.placement_first_available)
            configuraciones += "First Available";
        else
            configuraciones += "Next Available";
        configuraciones += "\nReplacement Policy: "+Main.replacement_policy;
        configuraciones += "\nWorking Set: "; //Fijo o Variable
        if (Main.resident_set_management_fixed){
            configuraciones += "Fijo";
            configuraciones += "\n    - Tamaño: "+Main.tamaño_fijo;
        }
        else{
            configuraciones += "Variable";
            configuraciones += "\n    - Tamaño Inicial: "+Main.tamaño_inicial;
            configuraciones += "\n    - Tamaño Máximo: "+Main.tamaño_maximo;
            configuraciones += "\n    - Crecimiento: "+Main.crecimiento_por_reemplazo;
        }
        configuraciones += "\nReplacement Scope: ";
        if (Main.replacement_scope_global)
            configuraciones += "Global";
        else
            configuraciones += "Local";
        configuraciones += "\nCleaning Policy: ";
        if (Main.cleaning_demand)
            configuraciones += "Demand";
        else
            configuraciones += "Precleaning";
        configuraciones += "\nGrado de Multiprogramación: "+Main.grado_de_multiprogramacion;
        configuraciones += "\nPolítica de Selección de Procesos: ";
        if (Main.seleccion_de_procesos_FIFO)
            configuraciones += "FIFO";
        else
            configuraciones += "Prioridad";
        configuraciones += "\nTamaño de la Memoria Física: "+Main.tamaño_memoria_fisica;
        configuraciones += "\nTamaño de la Memoria Virtual: "+Main.tamaño_memoria_virtual;
        configuraciones += "\nCantidad de Referencias por Iteración: "+Main.numero_referencias_por_iteracion;
        
        JOptionPane.showMessageDialog(new JFrame(), configuraciones, "Ayuda - Configuraciones Actuales",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_General2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FuncionamientoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FuncionamientoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FuncionamientoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FuncionamientoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //Prueba para Tabla Referencias
        //--Crear Frames e insertarlos en el Main.memoriafisica
        /*for (int i = 0; i<5; i++){
            //Crear procesos int identificador, String nombre, int tamano, boolean bit_bloqueo
            Proceso p = new Proceso(i,"Proceso #"+i,i*1000,false);
            Frame f = new Frame(i,p,i*1100,new Date(),new Date());
            Main.memoria_fisica.add(f);
        }*/
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FuncionamientoGUI().setVisible(true);
                //new MapMemoriaVirtual().setVisible(true);
                //new MapMemoriaFisica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Ayuda1;
    private javax.swing.JMenu Creditos;
    private javax.swing.JMenuItem General1;
    private javax.swing.JMenuItem General2;
    private javax.swing.JButton NextButton;
    private javax.swing.JButton ResetButton;
    private javax.swing.JTable TablaDeLog;
    private javax.swing.JTable TablaDeReferencias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuBar menu1;
    // End of variables declaration//GEN-END:variables

    private boolean ValidarReferencia(String[] referenciaSeccionada) {
        try{
            int id = Integer.parseInt(referenciaSeccionada[0]);
            int tamano = Integer.parseInt(referenciaSeccionada[1]);
            if ((referenciaSeccionada[2].equals("w")) || (referenciaSeccionada[2].equals("r"))){
                if (Main.lista_Procesos.stream().anyMatch((a) -> ((a.identificador == id) && (a.tamano >= tamano)))) {
                    return true;
                }
                return false;
            }
            return true;
        }
        catch(Exception e){
            String referencia = "";
            for (String s : referenciaSeccionada){
                referencia = referencia.concat(s);
            }
            VentanaConfiguracion.MensajeError("La referencia que contiene los siguientes datos:\n\n     "+referencia+"\n\nNo presenta un formato válido.", "Referencia Inválida");
            return false;
        }
    }

    private void CargarTablaReferencias() {
        System.out.println("Entré a cargar referencias.");
        Object [][] infoTablaReferencias = new Object [Main.tamaño_memoria_fisica][8];
        try{for (int i = 0; i < Main.memoria_fisica.size(); i++){
            //System.out.println("i = "+i);
            infoTablaReferencias[i][0] = i;
            infoTablaReferencias[i][1]= Main.memoria_fisica.get(i).contenido.nombre;
            infoTablaReferencias[i][2] = Main.memoria_fisica.get(i).identificador;
            infoTablaReferencias[i][3] = Main.memoria_fisica.get(i).contenido.esta_bloqueado;
            infoTablaReferencias[i][4] = Main.memoria_fisica.get(i).modificado;
            infoTablaReferencias[i][5] = Main.memoria_fisica.get(i).esta_reservado;
            infoTablaReferencias[i][6] = Main.memoria_fisica.get(i).TS1;
            infoTablaReferencias[i][7] = Main.memoria_fisica.get(i).TS_ultima_referencia;
        }
        String[] columnas = new String[]{"Frame","Proceso","Página","Bit de Bloqueo","Dirty Bit (Modificación)","Reservado","TS de Creación","TS de Modificación"};
        
        TablaDeReferencias.setModel(new javax.swing.table.DefaultTableModel(infoTablaReferencias,columnas){});
        }
        catch(Exception e){
        }
    }

    private void CargarTablaLog() {
        Object [][] infoTablaLog = new Object [Main.log.size()][5];
        try{for (int i = 0; i < Main.log.size(); i++){
            infoTablaLog[i][0] = Main.log.get(i).numero_referencia;
            infoTablaLog[i][1]= Main.log.get(i).proceso;
            infoTablaLog[i][2] = Main.log.get(i).frame_en_cuestion;
            infoTablaLog[i][3] = Main.log.get(i).reemplazo;
            infoTablaLog[i][4] = Main.log.get(i).ts;
        }
        String[] columnas = new String[]{"# de Referencia","Proceso","Frame en Cuestión","Reemplazo","Time Stamp"};
        
        TablaDeLog.setModel(new javax.swing.table.DefaultTableModel(infoTablaLog,columnas){});
        }
        catch(Exception e){
        }
    }
}

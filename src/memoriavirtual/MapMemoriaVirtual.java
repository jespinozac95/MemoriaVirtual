/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriavirtual;

/**
 *
 * @author Jespi_000
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class MapMemoriaVirtual extends JFrame {
   public MapMemoriaVirtual() {
      super("Map Memoria Virtual");
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.setResizable(true);
      SquaresVirtual squares = new SquaresVirtual();
      JScrollPane pane = new JScrollPane(squares,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      this.setPreferredSize(new Dimension(350,350));
      this.setMinimumSize(new Dimension(350,350));
      pane.setWheelScrollingEnabled(true);
      this.setContentPane(pane);
      
      int plano_x = 5;
      int plano_y = 0;
      for (int i = 0; i < Main.tamaño_memoria_virtual; i++) { //Procesos p: Main.MemoriaVirtual
        if (i%4==0){
            plano_x=5;
            plano_y += 100;
            squares.addSquare(plano_x, plano_y, 100, 100);
        }
        else{
            plano_x += 100;
            squares.addSquare(plano_x, plano_y, 100, 100);
        }
      }
      pack();
      setLocationRelativeTo(null);
      setVisible(true);

   }

   public static void main(String[] args) {
      new MapMemoriaVirtual();
   }

}

class SquaresVirtual extends JPanel {
   private List<Rectangle> squares = new ArrayList<Rectangle>();

   public void addSquare(int x, int y, int width, int height) {
      Rectangle rect = new Rectangle(x, y, width, height);
      squares.add(rect);
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(410,510);
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      int indice = 0;
      for (Rectangle rect : squares) {
          g2.setColor(Color.BLACK);
          g2.draw(rect);
          if (indice < Main.memoria_virtual.size()){
              //System.out.println("indice = "+indice+", memoria_virtual.size() = "+Main.memoria_virtual.size());
              g2.drawString(Main.memoria_virtual.get(indice).contenido.nombre,rect.x+30,rect.y+40); //Proceso.nombre
              g2.drawString("Pág #"+Integer.toString(Main.memoria_virtual.get(indice).pag),rect.x+30,rect.y+60); //Pag del proceso
              indice ++;
          }
      }
   }

}

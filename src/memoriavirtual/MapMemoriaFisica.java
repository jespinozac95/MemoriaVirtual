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

public class MapMemoriaFisica extends JFrame {
   public MapMemoriaFisica() {
      super("Map Memoria Fisica");
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.setResizable(false);
      Squares squares = new Squares();
      JScrollPane pane = new JScrollPane(squares,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      
      this.setMinimumSize(new Dimension(350,350));
      pane.setWheelScrollingEnabled(true);
      pane.setViewportView(squares);
      this.setContentPane(pane);
      //this.getContentPane().add(pane);
      
      int plano_x = 5;
      int plano_y = 0;
      for (int i = 0; i < Main.tamaño_memoria_fisica; i++) { //i < Main.tamaño_memoria_fisica
        if (i%4==0){
            plano_x=5;
            plano_y += 100;
            Color c = Color.BLACK;
            if (!Main.memoria_fisica.isEmpty()){
                // if modificado == 1 Color.Red
                try{if (Main.memoria_fisica.get(i).modificado)
                    c = Color.RED;
                // if bloqueado == 1 Color.Blue
                if (Main.memoria_fisica.get(i).contenido.esta_bloqueado)
                    c = Color.BLUE;
                // if esta_reservado == 1 Color.Green
                if (Main.memoria_fisica.get(i).esta_reservado)
                    c = Color.GREEN;
                }
                catch(Exception e){}
            }
            squares.addSquare(plano_x, plano_y, 100, 100,c);
        }
        else{
            plano_x += 100;
            Color c = Color.BLACK;
            if (!Main.memoria_fisica.isEmpty()){
                try{
                // if modificado == 1 Color.Red
                if (Main.memoria_fisica.get(i).modificado)
                    c = Color.RED;
                // if bloqueado == 1 Color.Blue
                if (Main.memoria_fisica.get(i).contenido.esta_bloqueado)
                    c = Color.BLUE;
                // if esta_reservado == 1 Color.Green
                if (Main.memoria_fisica.get(i).esta_reservado)
                    c = Color.GREEN;
                }
                catch(Exception e){}
            }     
            squares.addSquare(plano_x, plano_y, 100, 100,c);
        }
      }

      pack();
      setLocationRelativeTo(null);
      setVisible(true);

   }

   public static void main(String[] args) {
      new MapMemoriaFisica();
   }

}

class Cuadrado extends Rectangle{
    Color color;
    Cuadrado(int x, int y, int width, int height,Color c){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = c;
    }
}

class Squares extends JPanel implements Scrollable{
   private List<Cuadrado> squares = new ArrayList<Cuadrado>();

   public void addSquare(int x, int y, int width, int height, Color c) {
      Cuadrado rect = new Cuadrado(x, y, width, height,c);
      squares.add(rect);
   }

   @Override
   public Dimension getPreferredSize() {
      if (Main.tamaño_memoria_fisica % 4 == 0)
          return new Dimension(410,((Main.tamaño_memoria_fisica/4)*100)+110); //Main.tamaño_memoria_fisica
      else
          return new Dimension(410,(((Main.tamaño_memoria_fisica/4)+1)*100)+110); //Main.tamaño_memoria_fisica
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      int indice = 0;
      for (Cuadrado rect : squares) {
          g2.setColor(rect.color);
          g2.draw(rect);
          if (indice < Main.memoria_fisica.size()){
              //System.out.println("indice = "+indice+", memoria_virtual.size() = "+Main.memoria_virtual.size());
              try{
                g2.drawString(Main.memoria_fisica.get(indice).contenido.nombre,rect.x+30,rect.y+40); //Proceso.nombre
                g2.drawString("Pág #"+Integer.toString(Main.memoria_fisica.get(indice).identificador),rect.x+30,rect.y+60); //Pag del proceso
              }
              catch (Exception e){
                  try{
                      g2.drawString(Main.memoria_fisica.get(indice).proceso_reserva,rect.x+30,rect.y+40); //Proceso.nombre
                  }
                  catch (Exception i){}
              }
              indice ++;
          }
      }
   }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return new Dimension(410,510);
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 50;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 35;
    }
}

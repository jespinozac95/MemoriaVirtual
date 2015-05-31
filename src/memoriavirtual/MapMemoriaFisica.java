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
      new MapMemoriaFisica();
   }

}

class Squares extends JPanel implements Scrollable{
   private List<Rectangle> squares = new ArrayList<Rectangle>();

   public void addSquare(int x, int y, int width, int height) {
      Rectangle rect = new Rectangle(x, y, width, height);
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
      for (Rectangle rect : squares) {
          g2.setColor(Color.BLACK);
          g2.draw(rect);
          //g2.drawString("Proceso",rect.x+30,rect.y+40); //Proceso.nombre
          //g2.drawString("Pag #",rect.x+30,rect.y+60); //Pag del proceso
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

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
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Squares squares = new Squares();
      getContentPane().add(squares);
      int plano_x = 0;
      int plano_y = 0;
      for (int i = 0; i < 15; i++) {
        if (i%5==0){
            plano_x=0;
            plano_y += 50;
            squares.addSquare(plano_x, plano_y, 50, 50);
        }
        else{
            plano_x += 50;
            squares.addSquare(plano_x, plano_y, 50, 50);
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

class Squares extends JPanel {
   private static final int PREF_W = 500;
   private static final int PREF_H = PREF_W;
   private List<Rectangle> squares = new ArrayList<Rectangle>();

   public void addSquare(int x, int y, int width, int height) {
      Rectangle rect = new Rectangle(x, y, width, height);
      squares.add(rect);
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      for (Rectangle rect : squares) {
          g.setColor(Color.BLACK);
          g2.draw(rect);
      }
   }

}

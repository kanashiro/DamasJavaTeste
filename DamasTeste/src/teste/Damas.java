/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Panel;

/**
 *
 * @author WillianKanashiro
 */


 public class Damas extends Applet

{

tabuleiro t;

interface_tabuleiro i;

int x,y;

Button b1;

Button b2;

Button b3,b4,b5;

Panel p;

int resposta=0;

public void init() { 
  
 

if (t==null) t=new tabuleiro();

setLayout(new BorderLayout()); 
  
 

if (p==null) p = new Panel();

p.setLayout(new FlowLayout(FlowLayout.LEFT)); 
  
  
  
  
  
 

if (b1==null) b1=new Button("Jogar c/ brancas");

if (b2==null) b2=new Button("Jogar c/ pretas");

if (b3==null) b3=new Button("1");

if (b4==null) b4=new Button("2");

if (b5==null) b5=new Button("3"); 
  
 

p.add( b1);

p.add( b2);

p.add(b3);p.add(b4);p.add(b5); 
  
  
  
 

add("North",p);

if (i==null) i=new interface_tabuleiro(this,t,-1);

i.acabou=0;

i.computador=10;

i.level=5; 
  
 

add("Center",i); 
  
 

}

public boolean action(Event evt, Object arg) {

if ("1".equals(arg)) {i.level=5;i.repaint(); return true;}

if ("2".equals(arg)) {i.level=6;i.repaint(); return true;}

if ("3".equals(arg)) {i.level=7;i.repaint(); return true;}

if ("Jogar c/ brancas".equals(arg)) i.recomeca(1);

else i.recomeca(-1);

i.repaint();

return true;

}

} 
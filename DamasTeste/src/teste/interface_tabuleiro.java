/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.util.Vector;

/**
 *
 * @author WillianKanashiro
 */
public class interface_tabuleiro extends Canvas {

int level;

Damas controller;

tabuleiro tab,tab_visual;

int jogador,njog;

Vector escolha_jogada;

Lista_jogadas admissiveis;

int menor;

int inter;

int cursorx,cursory;

int acabou; 

public int computador; 
  
 

public void recomeca(int jog) {

njog=1;

jogador=-1;

computador=jog;

acabou=0;

tab=new tabuleiro();

tab_visual=new tabuleiro();

tab.copia(tab_visual);

cursorx=-1;cursory=-1;

inter=1; 
  
 

admissiveis=new Lista_jogadas();

tab.Lista_admissiveis(tab,jogador,admissiveis);

escolha_jogada=new Vector();

repaint();

} 
  
 

public interface_tabuleiro(Damas controller,tabuleiro tab,int jogador) {

super();

int i,j;

Vector rui;

cursorx=-1;cursory=-1;

njog=1;

inter=1;

this.controller=controller;

this.tab=tab;

this.jogador=jogador;

tab_visual=new tabuleiro();

tab.copia(tab_visual);

admissiveis=new Lista_jogadas();

tab.Lista_admissiveis(tab,jogador,admissiveis);

escolha_jogada=new Vector();

} 
  
 

public boolean mouseDown(Event evt,int x,int y) {

Vector v1;

if (computador==10) return false;

if (jogador==computador) return false;

if (acabou>0) return false;

if (admissiveis.movimentos.size()==0) {

acabou=computador;

repaint();

return false;

}

int resul,wx,wy,m=menor/9;

wx=x/m;

wy=y/m;

if ((wx+wy)%2==1) return true;

if (wx>7 || wy>7 ||wx<0 ||wy<0) return true;

if (escolha_jogada.size()==0 && tab.matriz[wy][wx]*jogador<=0) return false;

escolha_jogada.addElement(new Par(wy,wx));

/* 0 se não , 1 se sim incompleta,2 se sim completa */

tab.copia(tab_visual);

resul=verifica_jogada(escolha_jogada,tab_visual);

if (resul==0) {cursorx=-1;cursory=-1;escolha_jogada.removeAllElements();repaint();return false;}

cursorx=wx;

cursory=wy; 
  
 

if (resul==1) {repaint();return false;}

cursorx=-1;

repaint(); 
  
 

tab_visual.copia(tab);

escolha_jogada.removeAllElements(); 
  
 

jogador=-jogador;

njog++;

repaint(); 
  
 

return true;

} 
  
 

public int verifica_jogada(Vector escolha,tabuleiro tabuleiro1) {

int ex,ey,vx,vy,i,j;

Vector v1;

if (escolha.size()==1) {

/* só indicou a peça a mover */ 
  
  
  
 

for(i=0;i<admissiveis.movimentos.size();i++)

{ 
  
 

v1=(Vector)admissiveis.movimentos.elementAt(i);

ex= ((Par)escolha.elementAt(0)).x;

ey= ((Par)escolha.elementAt(0)).y;

vx= ((Par)v1.elementAt(0)).x;

vy= ((Par)v1.elementAt(0)).y;

if (ex==vx && ey==vy) return 1;

}

return 0; } 
  
  
  
 

for(i=0;i<admissiveis.movimentos.size();i++)

{

v1=(Vector)admissiveis.movimentos.elementAt(i);

if (v1.size()<escolha.size()) continue;

for(j=0;j<escolha.size();j++) {

ex= ((Par)escolha.elementAt(j)).x;

ey= ((Par)escolha.elementAt(j)).y;

vx= ((Par)v1.elementAt(j)).x;

vy= ((Par)v1.elementAt(j)).y;

if (ex!=vx || ey!=vy) break;

}

if (j==escolha.size()) {

aplica(escolha,tabuleiro1); 
  
 

if (v1.size()==escolha.size()) return 2;

return 1;

} 
  
 

} /* fim do for */ 
  
 

return 0;

} 
  
 

public void aplica(Vector movimentos,tabuleiro tabuleiro1) {

int i,cx,cy,ix,iy;

int x1,y1,x2,y2,guarda;

if (movimentos.size()==0) return;

x1=((Par)movimentos.elementAt(0)).x;

y1=((Par)movimentos.elementAt(0)).y;

guarda=tabuleiro1.matriz[x1][y1]; 
  
 

for(i=0;i<movimentos.size()-1;i++) {

x1=((Par)movimentos.elementAt(i)).x;

y1=((Par)movimentos.elementAt(i)).y;

x2=((Par)movimentos.elementAt(i+1)).x;

y2=((Par)movimentos.elementAt(i+1)).y;

if (x2>x1) ix=1; else ix=-1;

if (y2>y1) iy=1; else iy=-1; 
  
 

for(cx=x1,cy=y1;cx!=x2;cx+=ix,cy+=iy)

tabuleiro1.matriz[cx][cy]=0; 
  
 

tabuleiro1.matriz[x2][y2]=guarda; 
  
 

}

x2=((Par)movimentos.elementAt(movimentos.size()-1)).x;

y2=((Par)movimentos.elementAt(movimentos.size()-1)).y; 
  
  
  
 

/* promove peça se chegar ao fim */

if (Math.abs(tabuleiro1.matriz[x2][y2])==2) return;

if (guarda>0 && x2==7) tabuleiro1.matriz[x2][y2]*=2;

if (guarda<0 && x2==0) tabuleiro1.matriz[x2][y2]*=2; 
  
 

} 
  
  
  
  
  
  
  
  
  
 

public void paint (Graphics g) {

int i,j,m;

if (computador==10) return;

if (size().width<size().height) menor=size().width; else menor=size().height;

m=menor/9;

if (acabou!=0) {

g.setColor(Color.white);

g.fillRect(0,0,size().width,size().height);

g.setColor(Color.black);

if (acabou==1)

if (computador==1) g.drawString("Ganhei o Jogo!!!! :-)",4*m,4*m);

else g.drawString("Perdi o Jogo!!!! :-(",4*m,4*m); 
  
 

if (acabou==-1)

if (computador==-1) g.drawString("Ganhei o Jogo!!!! :-)",4*m,4*m);

else g.drawString("Perdi o Jogo!!!! :-(",4*m,4*m);

computador=10;

return; 
  
 

} 
  
 

for(i=0;i<8;i++)

for(j=0;j<8;j++)

{ if ((i+j)%2==0) {g.setColor(Color.gray);g.fillRect(i*m,j*m,m+1,m+1);}

else {g.setColor(Color.gray);g.drawRect(i*m,j*m,m,m);}

if (tab_visual.matriz[j][i]==1) drawPeca(i,j,1,g);

if (tab_visual.matriz[j][i]==-1) drawPeca(i,j,0,g);

if (tab_visual.matriz[j][i]==2) drawDama(i,j,1,g);

if (tab_visual.matriz[j][i]==-2) drawDama(i,j,0,g);

} 
  
 

drawPeca(2,8,(jogador>0)?1:0,g);

g.setColor(Color.blue);

g.drawString(" Jogada "+njog+" Nível="+(level-4),5*m,9*m);

g.setColor(Color.black);

if (cursorx>=0 && cursory>=0) {

int a,b;

g.setColor(Color.red);

a=cursorx*m;

b=cursory*m;

g.drawLine(a+m/3,b+m/3,a+2*m/3,b+2*m/3);

g.drawLine(a+2*m/3,b+m/3,a+m/3,b+2*m/3);

g.setColor(Color.black);

} 
  
 

if (jogador==computador) {

tab.Lista_admissiveis(tab,jogador,admissiveis);

if (admissiveis.movimentos.size()==0) {

acabou=-computador;

repaint();

return;

} 
  
 

tab.jogar_bem(tab,level,tab_visual,jogador);

tab_visual.copia(tab);

njog++;

jogador=-jogador;

tab.Lista_admissiveis(tab,jogador,admissiveis); 
  
 

repaint(); 
  
 

}

} 
  
  
  
 

public void drawPeca(int x,int y,int cor,Graphics g) {

// cor=0 branca; cor=1 preta 
  
 

int pe,pe1x,pe1y;

int m=menor/9; 
  
 

pe=(8*m)/10;pe1x=pe;pe1y=(60*pe)/100;

if (cor==0) g.setColor(Color.white); else g.setColor(Color.black);

g.fillOval(x*m+m/2-pe1x/2,y*m+m/2-pe1y/2,pe1x,pe1y);

if (cor==0) g.setColor(Color.black); else g.setColor(Color.white);

// g.drawOval(x*menor+menor/2-pe1x/2,y*menor+menor/2-pe1y/2,pe1x,pe1y);

g.drawArc(x*m+m/2-pe1x/2,y*m+m/2-pe1y/2,pe1x,pe1y,0,-180);

g.drawArc(x*m+m/2-pe1x/2,y*m+m/2-pe1y/2+1,pe1x,pe1y,0,-180); 
  
  
  
  
  
 

}

public void drawDama(int x,int y,int cor,Graphics g) {

// cor=0 branca; cor=1 preta

int pe,pe1x,pe1y;

int m=menor/9; 
  
 

pe=(8*m)/10;pe1x=pe;pe1y=(60*pe)/100;pe=pe/5;

if (cor==0) g.setColor(Color.white); else g.setColor(Color.black);

g.fillOval(x*m+m/2-pe1x/2,pe+y*m+m/2-pe1y/2,pe1x,pe1y);

if (cor==0) g.setColor(Color.black); else g.setColor(Color.white);

// g.drawOval(x*m+m/2-pe1x/2,pe+y*m+m/2-pe1y/2,pe1x,pe1y);

g.drawArc(x*m+m/2-pe1x/2,pe+y*m+m/2-pe1y/2,pe1x,pe1y,0,-180);

g.drawArc(x*m+m/2-pe1x/2,pe+y*m+m/2-pe1y/2+1,pe1x,pe1y,0,-180); 
  
 

if (cor==0) g.setColor(Color.white); else g.setColor(Color.black);

g.fillOval(x*m+m/2-pe1x/2,y*m+m/2-pe1y/2,pe1x,pe1y);

if (cor==0) g.setColor(Color.black); else g.setColor(Color.white);

// g.drawOval(x*m+m/2-pe1x/2,y*m+m/2-pe1y/2,pe1x,pe1y);

g.drawArc(x*m+m/2-pe1x/2,y*m+m/2-pe1y/2,pe1x,pe1y,0,-180);

g.drawArc(x*m+m/2-pe1x/2,y*m+m/2-pe1y/2+1,pe1x,pe1y,0,-180); 
  
  
  
 

}

} 
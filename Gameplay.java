/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brick_breaker;

//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.*;
//import java.awt.Graphics2D;
import java.awt.*;


public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;
    private int totalbricks = 21;
    private Timer time;
    private int delay = 8;
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapGenerator map ;

    Gameplay() {
        map = new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay, this);
        time.start();
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        
        map.draw((Graphics2D)g);

        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        
        g.setColor(Color.red);
        g.setFont(new Font("Serif",Font.BOLD, 20));
        g.drawString(""+score, 590, 30);
        

        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);
        
        if(totalbricks <=0){
             play = false;
            ballXdir = 0;
            ballYdir = 0 ;
            g.setColor(Color.red);
            g.setFont(new Font("Serif",Font.BOLD, 30));
            g.drawString(" you won ", 260, 300);
            
            g.setFont(new Font("Serif",Font.BOLD, 30));
            g.drawString(" Press Enter to Restart ", 230, 350);
        }
        
        if(ballposY> 570){
            play = false;
            ballXdir = 0;
            ballYdir = 0 ;
            g.setColor(Color.red);
            g.setFont(new Font("Serif",Font.BOLD, 30));
            g.drawString(" Game Over ,score : "+score, 190, 300);
            
            g.setFont(new Font("Serif",Font.BOLD, 30));
            g.drawString(" Press Enter to Restart ", 230, 350);
        }
        
        g.dispose();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }
        
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                play = true ;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalbricks = 21;
                map = new MapGenerator(3,7);
                repaint();
            }
        }
    }

    public void moveRight() {
        play = true;
        playerX += 20;
    }

    public void moveLeft() {
        play = true;
        playerX -= 20;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        time.start();
        if (play) {
            if(new Rectangle(ballposX, ballposY, 20,20).intersects(new Rectangle(playerX,550,100,8))){
                ballYdir = -ballYdir;
            }
            A:for(int i = 0 ; i<map.map.length;i++){
                for(int j =0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int brickX = j*map.brickwidth+80;
                        int brickY = i*map.brickheight+50;
                        int brickwidth = map.brickwidth;
                        int brickheight = map.brickheight;
                        
                        Rectangle rect = new Rectangle(brickX, brickY,brickwidth,brickheight );
                        Rectangle ballrect = new Rectangle (ballposX, ballposY, 20,20);
                        Rectangle brickrect = rect ;
                        if(ballrect.intersects(brickrect)){
                            map.setBrickValue(0,i,j);
                            totalbricks--;
                            score+=5 ;
                            
                            
                            if(ballposX +19 <= brickrect.x || ballposY + 1>=brickrect.x+brickrect.width){
                                ballXdir= -ballXdir;
                            }else{
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                                
                    }
                }
            }
            ballposX += ballXdir;
            ballposY += ballYdir;
            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX > 670) {
                ballXdir = -ballXdir;
            }
        }
        repaint();
    }

}

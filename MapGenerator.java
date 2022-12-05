/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brick_breaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.*;

public class MapGenerator {
    public int map [][];
    public int brickwidth;
    public int brickheight;
    public MapGenerator(int rows, int col){
        map = new int[rows][col];
        for(int i = 0;i<map.length;i++ ){
            for(int j =0 ; j<map[0].length;j++){
                map[i][j]=1;
            }
        }
        brickwidth = 540/col;
        brickheight = 150/rows;
        
    }
    public void draw(Graphics2D g){
         for(int i = 0;i<map.length;i++ ){
            for(int j =0 ; j<map[0].length;j++){
            if(map[i][j]>0){
                g.setColor(Color.white);
                g.fillRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
                
                g.setStroke(new BasicStroke(3));
                g.setColor(Color.black);
                g.drawRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);                
            }
            }
        }
    }
    public void setBrickValue(int value , int rows, int col){
        map[rows][col]= value;
    }
}

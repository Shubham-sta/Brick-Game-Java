
//import java.swing.*;
package com.mycompany.brick_breaker;
import javax.swing.*;
public class Brick_Breaker {
    
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();
        obj.setBounds(10,10,700,600);
        obj.setTitle("Breakout Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
}

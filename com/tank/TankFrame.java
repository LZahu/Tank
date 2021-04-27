package com.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// 设计一个窗口
public class TankFrame extends Frame {
    private Tank myTank;

    // 构造器
    public TankFrame(){
        this.setTitle("tank war");
        this.setLocation(200, 100);
        this.setSize(800, 600);
        this.addKeyListener(new TankKeyListener());

        myTank = new Tank(100, 100, Direction.D);
    }

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
    }

    // 内部类（键盘监听类）
    // 从KeyAdapter类继承，比实现KeyListener接口要简单
    // 因为KeyAdapter类已经实现了KeyListener接口，并含有空方法
    private class TankKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }
}

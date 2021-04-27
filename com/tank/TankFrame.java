package com.tank;

import java.awt.*;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

// 设计一个窗口
public class TankFrame extends Frame {
    public static final TankFrame INSTANCE = new TankFrame();
    private Tank myTank;
    private Tank enemy;
    private List<Bullet> bullets;
    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    // 存储在内存里的图片
    Image offScreenImage = null;

    // 构造器
    private TankFrame(){
        this.setTitle("tank war");
        this.setLocation(200, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.addKeyListener(new TankKeyListener());

        myTank = new Tank(100, 100, Direction.D, Group.GOOD);
        enemy = new Tank(200, 150, Direction.L, Group.BAD);
        bullets = new ArrayList<>();
    }

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        enemy.paint(g);
        for (Bullet bullet : bullets){
            bullet.paint(g);
        }
    }

    public void add(Bullet bullet) {
        this.bullets.add(bullet);
    }

    // 内部类（键盘监听类）
    // 从KeyAdapter类继承，比实现KeyListener接口要简单，因为KeyAdapter类已经实现了KeyListener接口，并含有空方法
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

    // 防止闪烁
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}

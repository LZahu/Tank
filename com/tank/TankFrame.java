package com.tank;

import java.awt.*;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

// 设计一个窗口
public class TankFrame extends Frame {
    public static final TankFrame INSTANCE = new TankFrame();
    private Player player;
    private List<Enemy> enemies;
    private List<Bullet> bullets;
    private List<Explode> explodes;
    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    // 存储在内存里的图片
    Image offScreenImage = null;

    // 构造器
    private TankFrame(){
        this.setTitle("tank war");
        this.setLocation(200, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.addKeyListener(new TankKeyListener());
        initGameObjects();
    }

    private void initGameObjects() {
        player = new Player(100, 100, Direction.D);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        explodes = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            enemies.add(new Enemy(100 + 50 * i, 200, Direction.D));
        }
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullets: " + bullets.size(), 10, 50);
        g.drawString("enemies:" + enemies.size(), 10, 100);
        g.drawString("explodes:" + explodes.size(), 10, 150);
        g.setColor(c);

        // 画玩家坦克
        player.paint(g);
        // 画敌方坦克
        for (int i = 0; i < enemies.size(); i++){
            if (!enemies.get(i).isLive()){
                enemies.remove(i);
            }
            else {
                enemies.get(i).paint(g);
            }
        }
        // 画子弹
        for (int i = 0; i < bullets.size(); i++){
            for (Enemy enemy : enemies){
                bullets.get(i).collidesWithTank(enemy);
            }

            // 子弹超过边界，或者子弹和坦克相撞，则从list中删除
            if (!bullets.get(i).isLive()){
                bullets.remove(i);
            }
            else {
                bullets.get(i).paint(g);
            }
        }
        // 画爆炸效果
        for (int i = 0; i < explodes.size(); i++){
            if (!explodes.get(i).isLive()){
                explodes.remove(i);
            }
            else {
                explodes.get(i).paint(g);
            }
        }
    }

    public void add(Bullet bullet) {
        this.bullets.add(bullet);
    }

    public void add(Explode explode){
        this.explodes.add(explode);
    }

    // 内部类（键盘监听类）
    // 从KeyAdapter类继承，比实现KeyListener接口要简单，因为KeyAdapter类已经实现了KeyListener接口，并含有空方法
    private class TankKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
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

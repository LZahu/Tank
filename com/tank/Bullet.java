package com.tank;

import java.awt.*;
import java.util.concurrent.TimeUnit;

// 子弹类
public class Bullet {
    // 位置
    private int x, y;
    // 方向
    private Direction direction;
    // 速度
    public static final int SPEED = 6;
    // 子弹属性
    private Group group;

    public Bullet(int x, int y, Direction direction, Group group) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
    }

    public void paint(Graphics g){
        switch (direction) {
            case L -> g.drawImage(ResourceMgr.bulletL, x, y, null);
            case U -> g.drawImage(ResourceMgr.bulletU, x, y, null);
            case R -> g.drawImage(ResourceMgr.bulletR, x, y, null);
            case D -> g.drawImage(ResourceMgr.bulletD, x, y, null);
        }

        move();
    }

    private void move() {
        switch (direction) {
            case L -> x -= SPEED;
            case U -> y -= SPEED;
            case R -> x += SPEED;
            case D -> y += SPEED;
        }

//        try {
//            TimeUnit.MILLISECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}

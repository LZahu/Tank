package com.tank;

import java.awt.*;
import java.awt.event.KeyEvent;

// 坦克类
public class Tank {
    // 坐标
    private int x, y;
    // 方向
    private Direction direction;
    private boolean bL, bU, bR, bD;
    // 坦克分类
    private Group group;
    // 速度
    public static final int SPEED = 7;
    // 判断坦克是否移动
    private boolean moving = false;

    public Tank(int x, int y, Direction direction, Group group) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
    }

    // 刷新坦克
    public void paint(Graphics g){
        // 自己坦克
        if (group == Group.GOOD) {
            switch (direction) {
                case L -> g.drawImage(ResourceMgr.goodTankL, x, y, null);
                case U -> g.drawImage(ResourceMgr.goodTankU, x, y, null);
                case R -> g.drawImage(ResourceMgr.goodTankR, x, y, null);
                case D -> g.drawImage(ResourceMgr.goodTankD, x, y, null);
            }
        }
        // 敌方坦克
        if (group == Group.BAD) {
            switch (direction) {
                case L -> g.drawImage(ResourceMgr.badTankL, x, y, null);
                case U -> g.drawImage(ResourceMgr.badTankU, x, y, null);
                case R -> g.drawImage(ResourceMgr.badTankR, x, y, null);
                case D -> g.drawImage(ResourceMgr.badTankD, x, y, null);
            }
        }
    }

    // 按键按下
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT -> {
                x -= SPEED;
                bL = true;
                direction = Direction.L;
            }
            case KeyEvent.VK_UP -> {
                y -= SPEED;
                bU = true;
                direction = Direction.U;
            }
            case KeyEvent.VK_RIGHT -> {
                x += SPEED;
                bR = true;
                direction = Direction.R;
            }
            case KeyEvent.VK_DOWN -> {
                y += SPEED;
                bD = true;
                direction = Direction.D;
            }
        }
        setMainDirection();
    }

    // 按键释放
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT -> bL = false;
            case KeyEvent.VK_UP -> bU = false;
            case KeyEvent.VK_RIGHT -> bR = false;
            case KeyEvent.VK_DOWN -> bD = false;
            case KeyEvent.VK_CONTROL -> fire();
        }
        setMainDirection();
    }

    // 发射子弹
    private void fire() {
        TankFrame.INSTANCE.add(new Bullet(x, y, direction, group));
    }

    // 设置玩家坦克的方向
    private void setMainDirection() {
        // 如果所有按键释放，坦克应当停止
        if (!bL && !bU && !bR && !bD){
            moving = false;
        }
        // 如果有任一按键按下，坦克应当移动
        else {
            moving = true;
            if (bL && !bU && !bR && !bD){
                direction = Direction.L;
            }
            if (!bL && bU && !bR && !bD){
                direction = Direction.U;
            }
            if (!bL && !bU && bR && !bD){
                direction = Direction.R;
            }
            if (!bL && !bU && !bR && bD){
                direction = Direction.D;
            }
        }
    }

    // 移动坦克
    private void move() {
        switch (direction) {
            case L -> x -= SPEED;
            case U -> y -= SPEED;
            case R -> x += SPEED;
            case D -> y += SPEED;
        }
    }

}

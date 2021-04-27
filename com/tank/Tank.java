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
    // 速度
    public static final int SPEED = 5;
    // 判断坦克是否移动
    private boolean moving = false;

    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    // 刷新坦克
    public void paint(Graphics g){
        g.fillRect(x, y, 50, 50);
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
            case KeyEvent.VK_LEFT -> {
                bL = false;
            }
            case KeyEvent.VK_UP -> {
                bU = false;
            }
            case KeyEvent.VK_RIGHT -> {
                bR = false;
            }
            case KeyEvent.VK_DOWN -> {
                bD = false;
            }
        }
        setMainDirection();
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

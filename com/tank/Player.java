package com.tank;

import java.awt.*;
import java.awt.event.KeyEvent;

// 坦克类
public class Player extends Tank {
    private boolean bL, bU, bR, bD;

    public Player(int x, int y, Direction direction) {
        super(x, y, direction, Group.GOOD);
    }

    // 刷新坦克
    @Override
    public void paint(Graphics g){
        if (!isLive()){
            return;
        }

        switch (getDirection()) {
            case L -> g.drawImage(ResourceMgr.goodTankL, getX(), getY(), null);
            case U -> g.drawImage(ResourceMgr.goodTankU, getX(), getY(), null);
            case R -> g.drawImage(ResourceMgr.goodTankR, getX(), getY(), null);
            case D -> g.drawImage(ResourceMgr.goodTankD, getX(), getY(), null);
        }
    }

    // 按键按下
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT -> {
                setX(getX() - SPEED);
                bL = true;
                setDirection(Direction.L);
            }
            case KeyEvent.VK_UP -> {
                setY(getY() - SPEED);
                bU = true;
                setDirection(Direction.U);
            }
            case KeyEvent.VK_RIGHT -> {
                setX(getX() + SPEED);
                bR = true;
                setDirection(Direction.R);
            }
            case KeyEvent.VK_DOWN -> {
                setY(getY() + SPEED);
                bD = true;
                setDirection(Direction.D);
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

    // 设置玩家坦克的方向
    private void setMainDirection() {
        // 如果所有按键释放，坦克应当停止
        if (!bL && !bU && !bR && !bD){
            this.setMoving(false);
        }
        // 如果有任一按键按下，坦克应当移动
        else {
            setMoving(true);
            if (bL && !bU && !bR && !bD){
                setDirection(Direction.L);
            }
            if (!bL && bU && !bR && !bD){
                setDirection(Direction.U);
            }
            if (!bL && !bU && bR && !bD){
                setDirection(Direction.R);
            }
            if (!bL && !bU && !bR && bD){
                setDirection(Direction.D);
            }
        }
    }
}

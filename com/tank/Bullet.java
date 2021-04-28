package com.tank;

import java.awt.*;

// 子弹类
public class Bullet {
    // 位置
    private int x, y;
    // 方向
    private Direction direction;
    // 速度
    public static final int SPEED = 25;
    // 子弹属性
    private Group group;
    // 子弹状态，是否还在窗口内
    private boolean live = true;

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

        boundsCheck();
    }

    // 检测子弹是否打中坦克
    public void collidesWithTank(Tank tank) {
        if (!isLive() || !tank.isLive() || group == tank.getGroup()){
            return;
        }

        Rectangle rect = new Rectangle(x, y, ResourceMgr.bulletU.getWidth(), ResourceMgr.bulletU.getHeight());
        Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(), ResourceMgr.goodTankU.getWidth(), ResourceMgr.goodTankU.getHeight());

        if (rect.intersects(rectTank)){
            this.die();
            tank.die();
        }
    }

    private void die() {
        this.setLive(false);
    }

    // 边界检查
    private void boundsCheck() {
        if (x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            die();
        }
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}

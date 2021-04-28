package com.tank;

import java.awt.*;

public class Tank {
    // 坐标
    private int x, y;
    // 方向
    private Direction direction;
    // 坦克分类
    private Group group;
    // 速度
    public static final int SPEED = 7;
    // 判断坦克是否移动
    private boolean moving = false;
    // 判断坦克是否还活着
    private boolean live = true;
    // 上一次所在位置
    private int oldX, oldY;
    // 坦克的宽度和高度
    private final int width, height;

    public Tank(int x, int y, Direction direction, Group group) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        this.oldX = x;
        this.oldY = y;
        width = ResourceMgr.goodTankU.getWidth();
        height = ResourceMgr.goodTankU.getHeight();
    }

    // 发射子弹
    public void fire() {
        int bX = x + ResourceMgr.goodTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int bY = y + ResourceMgr.goodTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        TankFrame.INSTANCE.add(new Bullet(bX, bY, direction, group));
    }

    // 刷新坦克
    public void paint(Graphics g){}

    // 移动坦克
    public void move() {
        if (!moving){
            return;
        }

        // 判断是否出界，并记录上一次的位置
        boundsCheck();
        oldX = x;
        oldY = y;

        switch (direction) {
            case L -> x -= SPEED;
            case U -> y -= SPEED;
            case R -> x += SPEED;
            case D -> y += SPEED;
        }
    }

    // 边界检测
    public void boundsCheck() {
        if (x < 0 || y < 30 || x + width > TankFrame.GAME_WIDTH || y + height > TankFrame.GAME_HEIGHT){
            back();
        }
    }

    // 坦克重置为上一次的位置
    public void back() {
        x = oldX;
        y = oldY;
    }

    // 坦克被打中，死了
    public void die() {
        this.setLive(false);
        TankFrame.INSTANCE.add(new Explode(x, y));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
}

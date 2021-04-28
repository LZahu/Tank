package com.tank;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

// 坦克类
public class Enemy extends Tank {
    private final Random random = new Random();

    public Enemy(int x, int y, Direction direction) {
        super(x, y, direction, Group.BAD);
        setMoving(true);
    }

    // 刷新坦克
    @Override
    public void paint(Graphics g){
        if (!isLive()){
            return;
        }

        switch (getDirection()) {
            case L -> g.drawImage(ResourceMgr.badTankL, getX(), getY(), null);
            case U -> g.drawImage(ResourceMgr.badTankU, getX(), getY(), null);
            case R -> g.drawImage(ResourceMgr.badTankR, getX(), getY(), null);
            case D -> g.drawImage(ResourceMgr.badTankD, getX(), getY(), null);
        }

        move();
    }

    @Override
    public void move() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.move();
        randomDir();
        if (random.nextInt(100) > 95) {
            fire();
        }
    }

    // 设置随机的方向
    private void randomDir() {
        if (random.nextInt(100) > 95) {
            setDirection(Direction.randomDir());
        }
    }
}

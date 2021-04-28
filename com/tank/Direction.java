package com.tank;

import java.util.Random;

// 方向类
public enum Direction {
    L, U, R, D;

    private static final Random random = new Random();

    // 获得随即方向
    public static Direction randomDir(){
        return values()[random.nextInt(values().length)];
    }
}

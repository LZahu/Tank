package com.tank;

public class Main {
    public static void main(String[] args) {
        TankFrame tankFrame = TankFrame.INSTANCE;
        tankFrame.setVisible(true);

        while (true){
            tankFrame.repaint();
        }
    }
}

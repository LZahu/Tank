package com.tank;

public class Main {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
        tankFrame.setVisible(true);

        while (true){
//            try {
//                TimeUnit.MILLISECONDS.sleep(250);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            tankFrame.repaint();
        }
    }
}
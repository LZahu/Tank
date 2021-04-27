package com.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtil {
    // 旋转图片
    public static BufferedImage rotateImage(final BufferedImage tankL, final int degree) {
        int w = tankL.getWidth();
        int h = tankL.getHeight();
        int type = tankL.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2D;
        (graphics2D = (img = new BufferedImage(w, h, type)).createGraphics())
                .setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2D.drawImage(tankL, 0, 0, null);
        graphics2D.dispose();
        return img;
    }
}

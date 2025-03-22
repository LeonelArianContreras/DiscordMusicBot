package dev.discordMusicBot.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class AvatarService {

    public BufferedImage getCircularAvatarImage(String avatarUrl) throws IOException {
        BufferedImage source = ImageIO.read(new URL(avatarUrl));
        int size = Math.min(source.getWidth(), source.getHeight());
        BufferedImage circleImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = circleImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setClip(new Ellipse2D.Float(0, 0, size, size));
        g2d.drawImage(source, 0, 0, size, size, null);
        g2d.dispose();

        return circleImage;
    }
}
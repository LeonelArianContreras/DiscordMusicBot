package dev.discordMusicBot.service;

import dev.discordMusicBot.service.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.utils.FileUpload;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class LoveService {

    private final ImageService imageService = new ImageService("love");
    private final AvatarService avatarData = new AvatarService();

    public InputStream createLoveImage(String firstAvatarUrl, String secondAvatarUrl) throws IOException {

        BufferedImage backgroundImage = imageService.getImage();
        BufferedImage firstAvatarImage = avatarData.getCircularAvatarImage(firstAvatarUrl);
        BufferedImage secondAvatarImage = avatarData.getCircularAvatarImage(secondAvatarUrl);

        int width = backgroundImage.getWidth();
        int height = backgroundImage.getHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        composeFinalImage(width, height, graphics, backgroundImage, firstAvatarImage, secondAvatarImage);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    private void composeFinalImage(int width, int height, Graphics2D graphics, BufferedImage backgroundImage, BufferedImage firstAvatarImage, BufferedImage secondAvatarImage) {
        int avatarSize = 150;
        graphics.drawImage(backgroundImage, 0, 0, null); // Draw background
        drawRect(graphics);
        drawAvatars(graphics, firstAvatarImage, secondAvatarImage, avatarSize);
        drawEdges(graphics, avatarSize);
        drawPercentage(graphics, (float) width);
        graphics.dispose(); // Free graphics
    }

    private void drawRect(Graphics2D graphics) {
        graphics.setColor(new Color(50, 50, 50, 150));
        graphics.fillRoundRect(120, 100, 560, 200, 50, 50);
    }

    private void drawEdges(Graphics2D graphics, int avatarSize) {
        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(1)); // image edge thickness
        graphics.drawOval(150, 120, avatarSize, avatarSize); // edge
        graphics.drawOval(500, 120, avatarSize, avatarSize); // edge
    }

    private void drawAvatars(Graphics2D graphics, BufferedImage firstUserImage, BufferedImage secondUserImage, int avatarSize) {
        graphics.drawImage(firstUserImage.getScaledInstance(avatarSize, avatarSize, Image.SCALE_DEFAULT), 150, 120, null);
        graphics.drawImage(secondUserImage.getScaledInstance(avatarSize, avatarSize, Image.SCALE_DEFAULT), 500, 120, null);
    }

    private void drawPercentage(Graphics2D graphics, Float width) {
        graphics.setFont(new Font("Rale way", Font.BOLD, 50));
        graphics.setColor(Color.WHITE);
        String lovePercentage = new Random().nextInt(101) + "%";
        graphics.drawString(lovePercentage, width / 2 - 40, 200);
    }

}
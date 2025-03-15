package dev.discordMusicBot.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.utils.FileUpload;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;


public class Love implements Command {

    List<Member> taggedMembers = new ArrayList<Member>();

    public void execute(MessageReceivedEvent event, String[] args) {

        if(args.length < 2 || args.length > 3) {
            event.getChannel().sendMessage("Usage: `!love <player> ?<player>?").queue();
            return;
        }

        taggedMembers = event.getMessage().getMentions().getMembers();
        String firstAvatarUrl, secondAvatarUrl;

        if(taggedMembers.size() == 2) {
            firstAvatarUrl = getAvatarMentioned(0);
            secondAvatarUrl = getAvatarMentioned(1);
        } else {
            firstAvatarUrl = event.getAuthor().getEffectiveAvatarUrl();
            secondAvatarUrl = getAvatarMentioned(0);
        }

        this.createImage(firstAvatarUrl, secondAvatarUrl, event);

    }

    public String getAvatarMentioned(int index) {
        return taggedMembers.get(index).getEffectiveAvatarUrl();
    }

    private void createImage(String firstAvatarUrl, String secondAvatarUrl, MessageReceivedEvent event) {
        try {
            File loveFolder = new File("src/main/resources/pictures/love/");
            if (!loveFolder.exists() || loveFolder.listFiles() == null) {
                System.err.println("Love images folder not found: " + loveFolder.getAbsolutePath());
                return;
            }

            File[] picsLove = loveFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
            if (picsLove == null || picsLove.length == 0) {
                System.err.println("No love images found in: " + loveFolder.getAbsolutePath());
                return;
            }

            BufferedImage backgroundImage = ImageIO.read(picsLove[new Random().nextInt(picsLove.length)]);
            BufferedImage firstUserImage = cropToCircle(ImageIO.read(new URL(firstAvatarUrl)));
            BufferedImage secondUserImage = cropToCircle(ImageIO.read(new URL(secondAvatarUrl)));

            int width = backgroundImage.getWidth();
            int height = backgroundImage.getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();

            // Draw background
            graphics.drawImage(backgroundImage, 0, 0, null);

            // Scale and draw avatars
            int avatarSize = 150;
            graphics.drawImage(firstUserImage.getScaledInstance(avatarSize, avatarSize, Image.SCALE_SMOOTH), 150, 120, null);
            graphics.drawImage(secondUserImage.getScaledInstance(avatarSize, avatarSize, Image.SCALE_SMOOTH), 500, 120, null);

            // Rect
            graphics.setColor(new Color(50, 50, 50, 150));
            graphics.fillRoundRect(120, 100, 560, 200, 50, 50);

            // Edge of the avatars
            graphics.setColor(Color.WHITE);
            graphics.setStroke(new BasicStroke(1)); // Grosor del borde
            graphics.drawOval(150, 120, avatarSize, avatarSize); // Borde del primer avatar
            graphics.drawOval(500, 120, avatarSize, avatarSize); // Borde del segundo avatar

            // Draw percentage
            graphics.setFont(new Font("Rale way", Font.BOLD, 50));
            graphics.setColor(Color.WHITE);
            String lovePercentage = new Random().nextInt(101) + "%";
            graphics.drawString(lovePercentage, width / 2 - 40, 200);

            graphics.dispose(); // Free

            // Convert image to InputStream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            InputStream imageStream = new ByteArrayInputStream(baos.toByteArray());

            // Send image
            event.getChannel().sendFiles(FileUpload.fromData(imageStream, "love.png")).queue();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static BufferedImage cropToCircle(BufferedImage source) {
        int size = Math.min(source.getWidth(), source.getHeight());
        BufferedImage circleImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = circleImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setClip(new Ellipse2D.Float(0, 0, size, size));
        g2d.drawImage(source, 0, 0, size, size, null);
        g2d.dispose();

        return circleImage;
    }
}
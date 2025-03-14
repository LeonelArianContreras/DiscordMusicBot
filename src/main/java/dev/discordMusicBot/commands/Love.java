package dev.discordMusicBot.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Love implements Command {

    public void execute(MessageReceivedEvent event, String[] args) {

        if(args.length < 2 || args.length > 3) {
            event.getChannel().sendMessage("Usage: `!love <player> ?<player>?").queue();
            return;
        }

        if(args.length == 2) {

        }

        int width = 600, height = 250;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        BufferedImage backgroundImage = ImageIO.read();
        BufferedImage firstUserImage = ImageIO.read();
        BufferedImage secondUserImage = ImageIO.read();
    }
    // ToDo: Reduce code repetition !!!
    public void createImage() {
        int width = 600, height = 250;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        BufferedImage backgroundImage = ImageIO.read();
        BufferedImage firstUserImage = ImageIO.read();
        BufferedImage secondUserImage = ImageIO.read();
    }

}
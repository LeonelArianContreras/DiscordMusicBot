package dev.discordMusicBot.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Love implements Command {

    List<Member> taggedMembers = new ArrayList<Member>();

    public void execute(MessageReceivedEvent event, String[] args) {

        if(args.length < 2 || args.length > 3) {
            event.getChannel().sendMessage("Usage: `!love <player> ?<player>?").queue();
            return;
        }

        if(args.length == 2) {

        }

        taggedMembers = event.getMessage().getMentions().getMembers();
        String firstAvatarUrl = this.getFirstAvatarMentioned(event);
        String secondAvatarUrl = this.getAvatarMentioned(0);

    }

    public String getFirstAvatarMentioned(MessageReceivedEvent event) {
        if(taggedMembers.size() == 1) {
            return event.getAuthor().getEffectiveAvatarUrl();
        }
        return this.getAvatarMentioned(0);
    }

    public String getAvatarMentioned(int index) {
        return taggedMembers.get(index).getEffectiveAvatarUrl();
    }

    public void createImage(String firstAvatarUrl, String secondAvatarUrl) {
        int width = 600, height = 250;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        File picsLoveFolder = new File("src/main/resources/images/love/");

        try {

            BufferedImage backgroundImage = ImageIO.read(); // ToDo
            BufferedImage firstUserImage = ImageIO.read(new URL(firstAvatarUrl));
            BufferedImage secondUserImage = ImageIO.read(new URL(secondAvatarUrl));

        } catch(IOException exception) {
            exception.printStackTrace(); // Show where the error occurred
        }
    }

}
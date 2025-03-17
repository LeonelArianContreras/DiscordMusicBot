package dev.discordMusicBot.commands;

import dev.discordMusicBot.service.ImageUtils;
import dev.discordMusicBot.service.LeoEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.io.File;
import java.util.Random;

public class GifCommand implements Command {

    private final Random random = new Random();
    private final String command;

    public GifCommand(String command) {
        this.command = command;
    }

    public void execute(LeoEvent event) {

        if(event.getSizeOfMessage() < 2) {
            event.sendBasicMessage("Usage: `!" + command + " <someone>");
            return;
        }
        ImageUtils imageUtils = new ImageUtils("gifs/" + command, ".gif");
        File randomGif = imageUtils.getFile();
        Member huggedMember = event.getMentionedMember(0);

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(event.getAuthor().getEffectiveName() + " has given a " + command + " to " + huggedMember.getEffectiveName());
        eb.setColor(0xFF69B4); // Pink colour
        event.sendEmbedWithFile(eb, randomGif);
    }
}

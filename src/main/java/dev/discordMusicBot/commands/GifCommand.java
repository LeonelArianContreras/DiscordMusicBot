package dev.discordMusicBot.commands;

import dev.discordMusicBot.service.ImageUtils;
import dev.discordMusicBot.service.LeoEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.io.File;
import java.util.Random;

import static dev.discordMusicBot.service.EmbedUtils.*;

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
        String huggedMember = event.getEffectiveNameOfMentionedMember(0);

        EmbedBuilder eb = getEmbedGif(event.getAuthorEffectiveName(), huggedMember, command);
        event.sendEmbedWithFile(eb, randomGif);
    }
}

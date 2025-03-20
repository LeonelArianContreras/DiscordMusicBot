package dev.discordMusicBot.commands;

import dev.discordMusicBot.service.*;
import dev.discordMusicBot.utils.LeoEvent;

import java.io.IOException;
import java.io.InputStream;

public class Love implements Command {

    private final LoveService loveService;

    public Love(LoveService loveService) {
        this.loveService = loveService;
    }

    public void execute(LeoEvent event) {
        event.argumentsVerification("love", "<player> ?<player>?", 2, 3);

        String oneAvatarUrl = event.getAvatarUrlOfMentionedMember(0);
        String otherAvatarUrl = event.getAnotherAvatarUrl();
        try {
            InputStream imageStream = loveService.createLoveImage(oneAvatarUrl, otherAvatarUrl);
            event.sendFile(imageStream, "love.png");

        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }



}
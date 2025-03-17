package dev.discordMusicBot.commands;

import dev.discordMusicBot.service.*;

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
        String otherAvatarUrl = getAnotherAvatarUrl(event);
        try {
            InputStream imageStream = loveService.createLoveImage(oneAvatarUrl, otherAvatarUrl);
            event.sendFile(imageStream, "love.png");

        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    private String getAnotherAvatarUrl(LeoEvent event) {
        if(event.getSizeOfMessage() == 2)
            return event.getAuthorEffectiveAvatarUrl();
        return event.getAvatarUrlOfMentionedMember(1);
    }

}
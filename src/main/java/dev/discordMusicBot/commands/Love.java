package dev.discordMusicBot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import dev.discordMusicBot.service.*;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.IOException;
import java.io.InputStream;

public class Love implements Command {

    private final LoveService loveService;


    public Love(LoveService loveService) {
        this.loveService = loveService;
    }

    public void execute(MessageReceivedEvent event, String[] args) {
        if(args.length < 2 || args.length > 3) {
            event.getChannel().sendMessage("Usage: `!love <player> ?<player>?").queue();
            return;
        }
        String oneAvatarUrl = event.getMessage().getMentions().getMembers().get(0).getEffectiveAvatarUrl();
        String otherAvatarUrl = getAnotherAvatarUrl(event, args);

        try {
            InputStream imageStream = loveService.createLoveImage(oneAvatarUrl, otherAvatarUrl);
            event.getChannel().sendFiles(FileUpload.fromData(imageStream, "love.png")).queue();

        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    private String getAnotherAvatarUrl(MessageReceivedEvent event, String[] args) {
        if(args.length == 2)
            return event.getAuthor().getEffectiveAvatarUrl();
        return event.getMessage().getMentions().getMembers().get(1).getEffectiveAvatarUrl();
    }

}
package dev.discordMusicBot.commands.infoCommand;

import dev.discordMusicBot.commands.Command;
import dev.discordMusicBot.service.LeoEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;
import java.util.function.Function;

public abstract class InfoCommand implements Command {

    protected abstract Function<Member, EmbedBuilder> embedBuilderFunction();

    public void execute(LeoEvent event) {
        if(event.getSizeOfMessage() < 2) {
            event.sendMessageAsEmbed(embedBuilderFunction().apply(event.getAuthor()));
            return;
        }

        List<Member> taggedUsers = event.getMentionedMembers();
        List<EmbedBuilder> embedData = taggedUsers.stream().map(embedBuilderFunction()).toList();
        embedData.forEach(event::sendMessageAsEmbed);
    }
}
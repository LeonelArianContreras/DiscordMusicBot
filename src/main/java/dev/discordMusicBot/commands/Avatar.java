package dev.discordMusicBot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Avatar implements Command {

    public void execute(MessageReceivedEvent event, String[] args) {
        String avatarUrl = event.getAuthor().getAvatarUrl() + "?size=1024";
        EmbedBuilder embed = new EmbedBuilder(); // Embed lets us beautify the bot interactions
        embed.setTitle("Avatar de: " + event.getAuthor().getEffectiveName());
        embed.setImage(avatarUrl);
        embed.setColor(0x1DA1F2);

        event.getChannel().sendMessageEmbeds(embed.build()).queue();
    }
}
package dev.discordMusicBot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.util.function.Function;

public class Avatar extends InfoCommand {

    @Override
    protected Function<Member, EmbedBuilder> embedBuilderFunction() {
        return Avatar :: getEmbedAvatar;
    }

    public static EmbedBuilder getEmbedAvatar(Member member) {
        String avatarUrl = member.getUser().getAvatarUrl() + "?size=1024";

        EmbedBuilder embed = new EmbedBuilder(); // Embed lets us beautify the bot interactions
        embed.setTitle("Avatar de: " + member.getEffectiveName());
        embed.setImage(avatarUrl);
        embed.setColor(0x1DA1F2);

        return embed;
    }
}
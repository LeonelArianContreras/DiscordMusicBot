package dev.discordMusicBot.commands.infoCommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.util.function.Function;
import dev.discordMusicBot.service.EmbedUtils;

public class Avatar extends InfoCommand {

    @Override
    protected Function<Member, EmbedBuilder> embedBuilderFunction() {
        return EmbedUtils :: getEmbedAvatar;
    }
}
package dev.discordMusicBot.commands.infoCommand;

import dev.discordMusicBot.utils.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.util.function.Function;

public class UserInfo extends InfoCommand {

    @Override
    protected Function<Member, EmbedBuilder> embedBuilderFunction() {
        return EmbedUtils:: getUserData;
    }

}
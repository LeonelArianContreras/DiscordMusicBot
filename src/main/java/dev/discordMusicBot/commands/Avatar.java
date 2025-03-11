package dev.discordMusicBot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.stream.Collectors;

public class Avatar implements Command {

    public void execute(MessageReceivedEvent event, String[] args) {
        if(args.length < 2) {
            event.getChannel().sendMessageEmbeds(this.getEmbedAvatar(event.getMember()).build()).queue();
            return;
        }
        List<Member> taggedMembers = event.getMessage().getMentions().getMembers();
        List<EmbedBuilder> avatars = taggedMembers.stream().map(Avatar::getEmbedAvatar).collect(Collectors.toList());
        avatars.forEach(avatar -> event.getChannel().sendMessageEmbeds(avatar.build()).queue());
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
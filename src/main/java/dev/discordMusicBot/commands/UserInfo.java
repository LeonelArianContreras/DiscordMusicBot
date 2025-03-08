package dev.discordMusicBot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class UserInfo implements Command {

    public void execute(MessageReceivedEvent event, String[] args) {
        Member user = event.getMember();
        EmbedBuilder eb = new EmbedBuilder();

        String username = event.getAuthor().getName();
        String nickname = event.getAuthor().getEffectiveName();
        String avatar = event.getAuthor().getAvatarUrl() + "?size=1024";
        String userid = event.getAuthor().getId();
        OffsetDateTime joinDate = user.getTimeJoined();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Guild guild = event.getGuild();
        
        eb.setTitle("User Info");
        eb.setThumbnail(avatar);
        eb.addField("Nickname", nickname, false);
        eb.addField("Username", username, false);
        eb.addField("UserID", userid, false);
        eb.addField("Date Joined", joinDate.format(formatter), false);
        eb.addField("Roles", user.getRoles().toString(), false);
        eb.setColor(0x1DA1F2);

        event.getChannel().sendMessageEmbeds(eb.build()).queue();
    }
}
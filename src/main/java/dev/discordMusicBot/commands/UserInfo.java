package dev.discordMusicBot.commands;

import dev.discordMusicBot.service.LeoEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import net.dv8tion.jda.api.entities.Role;

import java.util.function.Function;
import java.util.stream.Collectors;

public class UserInfo extends InfoCommand {

    @Override
    protected Function<Member, EmbedBuilder> embedBuilderFunction() {
        return UserInfo :: getUserData;
    }

    public static EmbedBuilder getUserData(Member member) {
        String username = member.getUser().getName();
        String nickname = member.getEffectiveName();
        String avatar = member.getUser().getAvatarUrl() + "?size=1024";
        String userId = member.getId();
        OffsetDateTime joinDate = member.getTimeJoined();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String roles;

        if(member.getRoles().isEmpty()) {
            roles = "None";

        } roles = member.getRoles().stream().map(Role :: getName).collect(Collectors.joining(", "));


        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("User Info");
        eb.setThumbnail(avatar);
        eb.addField("Nickname", nickname, false);
        eb.addField("Username", username, false);
        eb.addField("UserID", userId, false);
        eb.addField("Date Joined", joinDate.format(formatter), false);
        eb.addField("Roles", roles, false);
        eb.setColor(0x1DA1F2);

        return eb;
    }
}
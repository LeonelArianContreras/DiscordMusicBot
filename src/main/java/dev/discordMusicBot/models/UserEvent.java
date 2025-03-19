package dev.discordMusicBot.models;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class UserEvent {
    private final String username;
    private final String nickname;
    private final String avatarUrl;
    private final String userId;
    private final String joinDate;
    private final String roles;

    public UserEvent(Member member) {
        this.username = member.getUser().getName();
        this.nickname = member.getEffectiveName();
        this.avatarUrl = member.getUser().getAvatarUrl() + "?size=1024";
        this.userId = member.getId();

        OffsetDateTime joinDate = member.getTimeJoined();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.joinDate = joinDate.format(formatter);

        List<Role> roleList = member.getRoles();
        this.roles = roleList.isEmpty() ? "None" : roleList.stream()
                .map(Role::getName)
                .collect(Collectors.joining(", "));
    }

    // Getters
    public String getUsername() { return username; }
    public String getNickname() { return nickname; }
    public String getAvatarUrl() { return avatarUrl; }
    public String getUserId() { return userId; }
    public String getJoinDate() { return joinDate; }
    public String getRoles() { return roles; }
}
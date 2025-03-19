package dev.discordMusicBot.models;

public class UserDB {
    private String username;
    private final String discord_id;
    private String password;
    private final int user_id;

    public UserDB(String username, String discord_id, String password, int user_id) {
        this.username = username;
        this.discord_id = discord_id;
        this.password = password;
        this.user_id = user_id;
    }

    // Getters
    public String getUsername() { return username; }
    public String getDiscordID() { return discord_id; }
    public String getPassword() { return password; }
    public int getUserID() { return user_id; }

    //Setters
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

}
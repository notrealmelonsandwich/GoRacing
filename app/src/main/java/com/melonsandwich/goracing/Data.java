package com.melonsandwich.goracing;

public class Data {
    private static String playerName = "Player";

    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String playerName) {
        Data.playerName = playerName;
    }
}

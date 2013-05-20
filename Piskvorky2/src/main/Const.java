package main;

import java.awt.Image;

public class Const {

    public static final String LOCAL_GAME = "Local Game";
    public static final String LAN_GAME = "LAN Game";
    public static final String NEW_GAME = "New Game";
    public static final String SAVE_GAME = "Save Game";
    public static final String LOAD_GAME = "Load Game";
    public static final String EXIT = "Exit";
    
    public static final int HUMAN = 111;
    public static final int UI = 112;
    public static final int LAN = 114;
    
    public static final int PORT = 9903;
    
    private static String iconType = null;
    private static Image circle = null;
    private static Image cross = null;

    public static Image getCross() {
        return cross;
    }

    public static Image getCircle() {
        return circle;
    }

    public static void setCircle(Image circle) {
        Const.circle = circle;
    }

    public static void setCross(Image cross) {
        Const.cross = cross;
    }        

    public static String getIconType() {
        return iconType;
    }

    public static void setIconType(String iconType) {
        Const.iconType = iconType;
    }
}

package clientModel;

import android.graphics.Color;

/**
 * Created by tyler on 3/20/2017.
 * Our color values
 */

public enum MyColor {
    MAGENTA(Color.MAGENTA),
    RED(Color.RED),
    BLUE(Color.BLUE),
    CYAN(Color.CYAN),
    GREEN(Color.GREEN),
    GRAY(Color.GRAY),
    YELLOW(Color.YELLOW),
    DARKGREY(Color.DKGRAY),
    LIGHTGRAY(Color.LTGRAY),
    WILD(Color.TRANSPARENT),
    WHITE(Color.WHITE),
    ORANGE(Color.parseColor("#FFA500")),
    PURPLE(Color.parseColor("#551a8b")),
    BLACK(Color.BLACK);


    private int color;
    private final int orangeColor = Color.parseColor("#FFA500");
    private final int purpleColor = Color.parseColor("#551a8b");

    MyColor(int c) {
        color = c;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {

        switch (color){
            case Color.WHITE:
                return "White";
            case Color.BLACK:
                return "Black";
            case Color.BLUE:
                return "Blue";
            case Color.CYAN:
                return "Cyan";
            case Color.DKGRAY:
                return "Dark Gray";
            case Color.GRAY:
                return "Gray";
            case Color.GREEN:
                return "Green";
            case Color.LTGRAY:
                return "Light Gray";
            case Color.MAGENTA:
                return "Magenta";
            case Color.RED:
                return "Red";
            case Color.YELLOW:
                return "Yellow";
            case Color.TRANSPARENT:
                return "Wild";
        }
        if(color == orangeColor){
            return "Orange";
        }
        else if(color == purpleColor){
            return "Purple";
        }
        return "Unknown Color";
    }
    /**Used for the spinners to go from string to color*/
    public static MyColor unToString(String colorString){
        switch (colorString) {
            case "Black":
                return MyColor.BLACK;
            case "Blue":
                return MyColor.BLUE;
            case "Cyan":
                return MyColor.CYAN;
            case "Dark Gray":
                return MyColor.DARKGREY;
            case "Gray":
                return MyColor.GRAY;
            case "Green":
                return MyColor.GREEN;
            case "LightGray":
                return MyColor.LIGHTGRAY;
            case "Magenta":
                return MyColor.MAGENTA;
            case "Red":
                return MyColor.RED;
            case "Yellow":
                return MyColor.YELLOW;
            case "Wild":
                return MyColor.WILD;
            case "White":
                return MyColor.WHITE;
            case "Orange":
                return MyColor.ORANGE;
            case "Purple":
                return MyColor.PURPLE;
        }
        return null;
    }
}

package modeling;


import java.awt.Color;

/**
 * Created by kwankyuk on 10/7/17.
 */

public enum ColorEnum {

    WHITE(Color.WHITE.getRGB()),
    RED(Color.RED.getRGB()),
    BLUE(Color.BLUE.getRGB()),
    YELLOW(Color.YELLOW.getRGB()),
    GREEN(Color.GREEN.getRGB()),
    BLACK(Color.BLACK.getRGB());


    private int color;

    ColorEnum (int c) {
        color = c;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}

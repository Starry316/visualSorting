package Entity;

import java.awt.*;

/**
 * Created by Starry on 2018/3/9.
 */
public class Stick {
    private int height,width,value,x,y;
    private int color=BLACK;
    public static final int YELLOW=1;
    public static final int RED=2;
    public static final int BLUE=3;
    public  static final int GREEN=4;
    public static final int BLACK=5;
    public static final int GERY=6;
    public Stick (){

    }
    public  Stick (int x, int y ,int width ,int height,int value){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.value =value;

    }
    public void paintStick(Graphics2D g){
        Stroke s = g.getStroke();
        switch (color) {
            case YELLOW:
                g.setColor(Color.yellow);
                break;
            case RED:
                g.setColor(Color.red);
                break;
            case BLUE:
                g.setColor(Color.cyan);
                break;
            case GREEN:
                g.setColor(Color.GREEN);
                break;
            case BLACK:
                g.setColor(Color.BLACK);
                break;
            case GERY:
                g.setColor(Color.GRAY);
                break;
        }
        g.fillRect(x,y,width,height);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Font font1 = new Font("微软雅黑", Font.BOLD, 20);
        g.setFont(font1);
        g.setColor(Color.BLACK);
        g.drawString(value+"",x+25,y+30);
        g.setStroke(s);
    }

    public void setColor(int color) {
        this.color = color;
    }
}

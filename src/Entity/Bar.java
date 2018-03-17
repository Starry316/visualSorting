package Entity;

import java.awt.*;

/**
 * Created by Starry on 2018/3/5.
 */
public class Bar { //该类用于冒泡和归并排序中的数据展示
    private int x,y,width,height,value;//分别代表x，y坐标，宽，长和代表的值
    private int color;//代表柱状图的颜色
    public static final int YELLOW=1; //预先设置的常量，用于绘制时的颜色判断
    public static final int RED=2;
    public static final int BLUE=3;
    public static final int GREEN=4;
    public Bar(){}
    public Bar(int x,int y,int width,int height,int value){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.value=value;
        this.color=YELLOW; //默认为黄色
    }
    public void paintBar(Graphics2D g){
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
            }
            g.fillRect(x,y,width,height);
            g.setStroke(s);

            g.setColor(Color.BLACK);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            Font font1 = new Font("Times New Roman", Font.PLAIN, 15);
            g.setFont(font1);
            g.drawString(value+"",x+width/2-7,y+height+20);
    } //绘制方法
    public void move(int moveX,int moveY,int time){
        int disX = (moveX-this.x)/time;
        int disY = (moveY-this.y)/time;
        int xc = Math.abs(moveX-this.x-disX*time);
        int yc = Math.abs(moveY-this.y-disY*time);
        for(int i=0;i<time;i++){
            if(xc-->0){
                if (x<moveX)x++;
                else x--;
            }
            if(yc-->0){
                if (y<moveY)y++;
                else y--;
            }
            x+=disX;
            y+=disY;
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }//移动方法

    public void setHeight(int height){
        this.height=height;
    }
    public void setColor(int color){
        this.color=color;
    }

    public int getHeight() {
        return height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }
}

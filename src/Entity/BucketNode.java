package Entity; /**
 * Created by Starry on 2018/3/7.
 */
import java.awt.*;

/**
 * Created by Starry on 2018/3/5.
 */
public class BucketNode { //该类用于箱子排序的数据展示
    private int x,y;//代表坐标
    private int colorInner=WHITE;//节点内部的颜色
    private int colorOut=BLACK;//节点外部的颜色
    private int colorV=BLACK;//节点的值的颜色
    private int value; //代表值
    static final int YELLOW=1;//预先设置的常量，用于绘制时的颜色判断
    static final int RED=2;
    static final int BLUE=3;
    static final int GREEN=4;
    static final int BLACK=5;
    static final int GLASSGREEN=6;
    static final int YELLOW2=7;
    static final int WHITE=8;
    public BucketNode(){}
    public BucketNode(int x,int y,int value){
        this.x=x;
        this.value=value;
        this.y=y;
    }
    public void paintNode(Graphics2D g){
        Stroke s = g.getStroke();
        switch (colorOut) {
            case YELLOW:
                g.setColor(Color.yellow);
                break;
            case RED:
                g.setColor(Color.red);
                break;
            case BLUE:
                g.setColor(Color.cyan);
                break;
            case BLACK:
                g.setColor(Color.BLACK);
                break;
            case GREEN:
                g.setColor(Color.GREEN);
                break;
            case GLASSGREEN:
                g.setColor(new Color(82,188,105));
                break;
            case YELLOW2:
                g.setColor(new Color(254,197,21));
                break;
            case WHITE:
                g.setColor(Color.white);
                break;
        }
        g.setStroke(s);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRect(x,y,70,50);

        switch (colorInner) {
            case YELLOW:
                g.setColor(Color.yellow);
                break;
            case RED:
                g.setColor(Color.red);
                break;
            case BLUE:
                g.setColor(Color.cyan);
                break;
            case BLACK:
                g.setColor(Color.BLACK);
                break;
            case GREEN:
                g.setColor(Color.GREEN);
                break;
            case GLASSGREEN:
                g.setColor(new Color(82,188,105));
                break;
            case YELLOW2:
                g.setColor(new Color(254,197,21));
                break;
            case WHITE:
                g.setColor(Color.white);
                break;
        }
        g.setStroke(s);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRect(x+3,y+3,64,44);

        switch (colorV) {
            case YELLOW:
                g.setColor(Color.yellow);
                break;
            case RED:
                g.setColor(Color.red);
                break;
            case BLUE:
                g.setColor(Color.cyan);
                break;
            case BLACK:
                g.setColor(Color.BLACK);
                break;
            case GREEN:
                g.setColor(Color.GREEN);
                break;
            case GLASSGREEN:
                g.setColor(new Color(82,188,105));
                break;
            case YELLOW2:
                g.setColor(new Color(254,197,21));
                break;
            case WHITE:
                g.setColor(Color.white);
                break;
        }
        g.setStroke(s);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Font font1 = new Font("微软雅黑", Font.PLAIN, 18);
        g.setFont(font1);
        if(value==10)g.drawString(value+"",x+24,y+32);
        else g.drawString(value+"",x+27,y+32);
    }
    public void move(int moveX,int moveY,int time){ //从当前位置使用time时间，移动到moveX,moveY
        int disX = (moveX-this.x)/time; //距离除以时间，即每个时间单位所移动的距离
        int disY = (moveY-this.y)/time; //同上
        /**
         *  由于坐标和时间均为int类型，用除法运算后会有一部分距离被舍去
         *  若不处理这部分距离，会导致图形在移动结束时瞬移一段距离
         *  故使用下面的xc,yc变量记录被舍去的数值
         */
        int xc = Math.abs(moveX-this.x-disX*time); //记录x方向被舍去的数值
        int yc = Math.abs(moveY-this.y-disY*time); //记录y方向被舍去的数值
        for(int i=0;i<time;i++){ //每次循环改写一次坐标，并使线程休眠10ms
            /*
             在xc--大于0的情况下，使图形在x方向移动1。
             即把xc这段被舍去的距离分解成xc次距离为1的移动 ，这样可以保证图形的平滑移动
             */
            if(xc-->0){
                if (x<moveX)x++; //判断是加还是减
                else x--;
            }
            if(yc-->0){ //y方向的移动
                if (y<moveY)y++;
                else y--;
            }
            x+=disX;//加上每个时间单位移动的距离，不需考虑正负
            y+=disY;
            try {
                Thread.sleep(10); //线程休眠10ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.x=moveX;
        this.y=moveY;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setColorInner(int colorInner) {
        this.colorInner = colorInner;
    }

    public void setColorV(int colorV) {
        this.colorV = colorV;
    }

    public void setColorOut(int colorOut) {
        this.colorOut = colorOut;
    }

//    //public void setFill(boolean fill) {
//        isFill = fill;
//    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
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

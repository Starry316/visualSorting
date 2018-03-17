import Entity.BucketNode;
import Entity.Code;
import Entity.Stick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Starry on 2018/3/5.
 */
public class BucketSort extends JPanel {
    private int height = 0;
    private int width = 0;
    private int x_loacte = 0;
    private int y_loacte = 0;
    private static int N =10;
    private static int speed = 400;
    private static int compareCount = 0;
    private static int moveCount = 0;
    private static boolean stopFlag =false;
    private static boolean pause =false;
    private static boolean resetFlag =false;
    private Main mainBoard = null;
    private static boolean canGo=false;
    private static  int a[]=new int [N];
    private static int a2[]=new int [N];
    private static int boxCount[] =new int[11];
    private JButton start;
    private JButton quick;
    private JButton slow;
    private JButton back;
    private JButton pauseButton;
    private JButton resetButton;
    private BucketNode[] bucketNodes=new BucketNode[N];
    private BucketNode[][]box = new BucketNode[11][N];
    private Code codes[]=new Code[5];
    private Stick sticks[]=new Stick[10];
    private Code title;
    private Code hint;
    public BucketSort(int width, int height, Main mainBoard) {
        this.mainBoard = mainBoard;
        this.height = height;
        this.width = width;
        setLayout(null);
        setBounds(x_loacte, y_loacte, width, height);
        setBackground(Color.white);
        setVisible(false);
        setFocusable(true);
        codes[0]=new Code(1100,600,490,30,"for ( i = 0; i < N; i++)");
        codes[1]=new Code(1100,630,490,30,"         put a[i] into bucket");
        codes[2]=new Code(1100,660,490,30,"for ( j = 0; j < bucket.length; j++)");
        codes[3]=new Code(1100,690,490,30,"         while( bucket is not empty)");
        codes[4]=new Code(1100,720,490,30,"             restore element to array");
        back = new JButton("返回");
        back.setBounds(400,825,80,40);
        back.setContentAreaFilled(false);
        back.setForeground(Color.white);
        this.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopFlag=true;

                mainBoard.changeToControll();

            }
        });
        quick =new JButton("加速");
        quick.setBounds(600,825,80,40);
        quick.setContentAreaFilled(false);
        quick.setForeground(Color.white);
        this.add(quick);
        quick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(speed>50){
                    speed-=50;
                }
                System.out.println("quick now speed : "+speed);
            }
        });
        slow =new JButton("减速");
        slow .setBounds(500,825,80,40);
        slow .setContentAreaFilled(false);
        slow.setForeground(Color.white);
        this.add( slow );
        slow .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(speed<600){
                    speed+=50;
                }
                System.out.println("slow now speed : "+speed);
            }
        });
        pauseButton = new JButton("暂停");
        pauseButton.setBounds(300,825,80,40);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setForeground(Color.white);
        this.add(pauseButton);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pause){
                    pause=false;
                    pauseButton.setText("暂停");
                }
                else {
                    pause=true;
                    pauseButton.setText("开始");
                }
            }
        });
        start = new JButton("排序");
        start.setBounds(200,825,80,40);
        start.setContentAreaFilled(false);
        start.setForeground(Color.white);
        this.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canGo=true;
                System.out.println(canGo);
                moveCount=0;
                compareCount=0;
            }
        });
        resetButton = new JButton("重置");
        resetButton.setBounds(700,825,80,40);
        resetButton.setContentAreaFilled(false);
        resetButton.setForeground(Color.white);
        this.add(resetButton);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFlag=true;


            }
        });

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gg = (Graphics2D) g;
        for(BucketNode i:bucketNodes){
            i.paintNode(gg);
        }
        for(Code i:codes){
            i.paintBar(gg);
        }
        Stroke s = gg.getStroke();
        gg.setColor(new Color(254,197,21));
        gg.fillRect(1100,535,490,60);
        gg.setStroke(s);

        gg.setColor(Color.BLACK);
        gg.fillRect(0,820,1600,80);
        gg.setStroke(s);
        for(int i=0;i<10;i++){
            sticks[i].paintStick(gg);
        }
        hint.paintBar(gg);
        title.paintBar(gg);
        gg.setColor(Color.white);
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Font font1 = new Font("微软雅黑",Font.BOLD, 18);
        gg.setFont(font1);
        gg.drawString("比较次数 :                     "+compareCount,1135,555);
        gg.drawString("移动次数 :                     "+moveCount,1135,585);
        gg.drawString("速度 : "+(70-speed/10),850,850);
        gg.setStroke(s);

    }
    public  void swap(int a [],int j,int i){
        int tmp = a[i];
        a[i]=a[j];
        a[j]=tmp;
    }
    public void Init() {
        boxCount=new int[11];
        for(int i=0;i<N;i++) {
            a[i] = (int) (Math.random() * 10)+1;
            a2[i]=a[i];
            bucketNodes[i] = new BucketNode(100+i*100,250,a[i]);
        }

        title= new Code(1100,470,490,30,"箱子排序");
        title.setColor(Code.BLUE);
        hint= new Code(1100,500,490,30,"");
        hint.setColor(Code.BLUE);
        for(int i=0;i<10;i++){
            sticks[i]=new Stick(100+i*80,700,70,3,i+1);
        }
        compareCount=0;
        moveCount=0;
        new paintThread().start();
    }
    public void sort(){
        System.out.println("sort start");
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (stopFlag){
                stopFlag=false;
                canGo = false;
                break;
            }
            if (canGo) {
                hint.setStr("排序开始");
                codes[0].setColor(Code.BLACK);
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i = 0 ;i<N;i++){
                    codes[0].setColor(Code.GLASSGREEN);
                    codes[1].setColor(Code.BLACK);
                    hint.setStr("将元素放入与其值相对应的箱子中");
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pause();
                    if (stopFlag){
                        canGo = false;
                        break;
                    }
                    if(resetFlag)resetInit();
                    box[a[i]][boxCount[a[i]]]=bucketNodes[i];
                    bucketNodes[i].move(20+a[i]*80,700-55*(++boxCount[a[i]]),speed/10);
                    moveCount++;
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pause();
                    if (stopFlag){
                        canGo = false;
                        break;
                    }
                    if(resetFlag)resetInit();
                    codes[1].setColor(Code.GLASSGREEN);
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pause();
                    if (stopFlag){
                        canGo = false;
                        break;
                    }
                    if(resetFlag)resetInit();
                }
                int countNodes=0;
                hint.setStr("按箱子顺序依次取出元素，放回原数组");
                for(int i=1;i<=10;i++){
                    sticks[i-1].setColor(Stick.GERY);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pause();
                    if (stopFlag){
                        canGo = false;
                        break;
                    }
                    if(resetFlag)resetInit();
                    while (boxCount[i]>0){
                        codes[4].setColor(Code.BLACK);
                        box[i][--boxCount[i]].move(100+(countNodes)*100,250,speed/10);
                        moveCount++;
                        countNodes++;
                        codes[4].setColor(Code.GLASSGREEN);
                        pause();
                        if (stopFlag){
                            canGo = false;
                            break;
                        }
                        if(resetFlag)resetInit();
                    }
                    sticks[i-1].setColor(Stick.BLACK);
                }


                hint.setStr("排序结束，整个过程比较次数为 0");
                canGo = false;
            }
            if (resetFlag){
                resetInit();
            }

        }
        System.out.println("sort end");
    }


    private void resetInit(){
        resetFlag=false;
        canGo = false;
        compareCount=0;
        moveCount=0;
        boxCount=new int[11];
        hint.setStr("");
        for(int i=0;i<N;i++) {
            a[i] = a2[i];
            bucketNodes[i] = new BucketNode(100+i*100,250,a[i]);
        }
        for (int i=0;i<10;i++)sticks[i].setColor(Stick.BLACK);

    }
    private void pause(){
        while(pause){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private class paintThread extends Thread{
        @Override
        public void run() {
            while(true){
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}

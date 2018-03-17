import Entity.Bar;
import Entity.Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Starry on 2018/3/5.
 */
public class BubbleSort extends JPanel {
    private int height = 0;
    private int width = 0;
    private int x_loacte = 0;
    private int y_loacte = 0;
    private static int N = 15;
    private static int speed = 300;
    private static int compareCount = 0;
    private static int moveCount = 0;
    private static boolean stopFlag =false;
    private static boolean pause =false;
    private static boolean resetFlag =false;
    private static boolean canGo=false;
    private static  int a[]=new int [N];
    private static int a2[]=new int [N];
    private Main mainBoard = null;
    private JButton start;
    private JButton quick;
    private JButton slow;
    private JButton back;
    private JButton pauseButton;
    private JButton resetButton;
    private Bar arr[]=new Bar[N];
    private Code codes[]=new Code[5];
    private Code title;
    private Code hint ;
    public BubbleSort(int width, int height, Main mainBoard) {
        this.mainBoard = mainBoard;
        this.height = height;
        this.width = width;
        setLayout(null);
        setBounds(x_loacte, y_loacte, width, height);
        setBackground(Color.white);
        setVisible(false);
        setFocusable(true);
        codes[0]=new Code(1100,650,490,30,"for (  i = n-1; i >= 0;i--)");
        codes[1]=new Code(1100,680,490,30,"         for ( j = 0; j < i-1;j++){");
        codes[2]=new Code(1100,710,490,30,"             if (a[j] > a[j+1])");
        codes[3]=new Code(1100,740,490,30,"                 swap(a, j, j+1) ;");
        codes[4]=new Code(1100,770,490,30,"         }");
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
        for(Bar i:arr){
            i.paintBar(gg);
        }
        for(Code i:codes){
            i.paintBar(gg);
        }
        hint.paintBar(gg);
        title.paintBar(gg);
        Stroke s = gg.getStroke();
        gg.setColor(new Color(254,197,21));
        gg.fillRect(1100,585,490,60);
        gg.setStroke(s);

        gg.setColor(Color.BLACK);
        gg.fillRect(0,820,1600,80);
        gg.setStroke(s);

        gg.setColor(Color.white);
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Font font1 = new Font("微软雅黑", Font.BOLD, 18);
        gg.setFont(font1);
        gg.drawString("比较次数 :                     "+compareCount,1135,605);
        gg.drawString("移动次数 :                     "+moveCount,1135,635);
        gg.drawString("速度 : "+(70-speed/10),850,850);
        gg.setStroke(s);

    }
    public  void swap(int a [],int j,int i){
        int tmp = a[i];
        a[i]=a[j];
        a[j]=tmp;
    }
    public void Init() {
        title= new Code(1100,520,490,30,"冒泡排序");
        title.setColor(Code.BLUE);
        hint= new Code(1100,550,490,30,"");
        hint.setColor(Code.BLUE);
        for(int i=0;i<N;i++) {
            a[i] = (int) (Math.random() * 100)+1;
            a2[i]=a[i];
            arr[i]=new Bar(60*i+150,600-a[i]*3,50,a[i]*3,a[i]);
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
            //检测返回flag
            if (stopFlag){
                stopFlag=false; //重置返回标志
                canGo = false; //重置开始标志
                break;
            }
            if (canGo) {    //canGo为true，开始排序
                    hint.setStr("排序开始");//改变提示行显示的文字
                    for (int i = N; i > 0; i--) {
                        //检测各个标志
                        if (stopFlag){
                            break;
                        }
                        if (resetFlag){
                            break;
                        }
                        if(i==1)arr[0].setColor(Bar.GREEN);
                        for (int j = 0; j < i - 1; j++) {
                            //检测各个标志
                            if (stopFlag){
                                break;
                            }
                            if (resetFlag){
                                break;
                            }
                            arr[j].setColor(Bar.BLUE);
                            arr[j + 1].setColor(Bar.BLUE);
                            codes[2].setColor(Code.BLACK);
                            hint.setStr("比较相邻元素大小");
                            //设置暂停点
                            if(pause){
                                while (pause){
                                    try {
                                        Thread.sleep(300);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    if(stopFlag||resetFlag)break;
                                }
                            }
                            try {
                                Thread.sleep(speed);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            codes[2].setColor(Code.GLASSGREEN);
                            compareCount++;

                            if (a[j + 1] < a[j]) { //交换元素
                                hint.setStr("交换相邻元素");
                                swap(a, j + 1, j);
                                moveCount+=3;
                                codes[3].setColor(Code.BLACK);
                                Bar tmp = arr[j];
                                arr[j] = arr[j + 1];
                                arr[j + 1] = tmp;
                                //移动元素
                                for (int x = 0; x < 30; x++) {
                                    arr[j + 1].setX(arr[j + 1].getX() + 2);
                                    arr[j].setX(arr[j].getX() - 2);
                                    try {
                                        Thread.sleep(8);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if (resetFlag){
                                break;
                            }
                            if (j + 1 == i - 1) {
                                arr[j + 1].setColor(Bar.GREEN);
                            }
                            else {
                                arr[j + 1].setColor(Bar.YELLOW);

                            }
                            arr[j].setColor(Bar.YELLOW);
                            codes[3].setColor(Code.GLASSGREEN);
                            //调试输出
                            for (int k : a)
                                System.out.print(k + " ");
                            System.out.println();
                        }
                    }
                    if (stopFlag){
                        stopFlag=false;
                        canGo = false;
                        break;
                    }
                    if (resetFlag){
                        resetFlag=false;
                        canGo = false;
                        compareCount=0;
                        moveCount=0;
                        hint.setStr("");
                        for(int i=0;i<N;i++) {
                            a[i] = a2[i];
                            arr[i]=new Bar(60*i+150,600-a[i]*3,50,a[i]*3,a[i]);
                        }

                    }
                    canGo = false;
                    hint.setStr("排序结束");
            }
            if (resetFlag){ //初始化
                resetFlag=false;
                canGo = false;
                compareCount=0;
                moveCount=0;
                hint.setStr("");
                for(int i=0;i<N;i++) {
                    a[i] = a2[i];
                    arr[i]=new Bar(60*i+150,600-a[i]*3,50,a[i]*3,a[i]);
                }

            }

        }

        System.out.println("sort end");
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

import Entity.Bar;
import Entity.Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Starry on 2018/3/5.
 */
public class MergeSort extends JPanel {
    private int height = 0;
    private int width = 0;
    private int x_loacte = 0;
    private int y_loacte = 0;
    private static int N = 15;
    private static int speed = 100;
    private Main mainBoard = null;
    private static boolean canGo=false;
    public static boolean sorted=false;
    public static boolean stopFlag =false;
    private static boolean pauseFlag=false;
    private static boolean resetFlag =false;
    private static  int a[]=new int [N];
    private static int a2[]=new int [N];
    private static int compareCount=0;
    private static int moveCount=0;
    private static Bar[] tempBar = new Bar[N];
    private JButton start;
    private JButton quick;
    private JButton slow;
    private JButton back;
    private JButton pauseButton;
    private JButton resetButton;
    private Code codes[]=new Code[7];
    private Code title;
    private Code hint;
    private Bar arr[]=new Bar[N];
    public MergeSort(int width, int height, Main mainBoard) {
        this.mainBoard = mainBoard;
        this.height = height;
        this.width = width;
        setLayout(null);
        setBounds(x_loacte, y_loacte, width, height);
        setBackground(Color.white);
        setVisible(false);
        setFocusable(true);
        codes[0]=new Code(1100,600,490,30,"split each element into parts of size 1");
        codes[1]=new Code(1100,630,490,30,"merge two adjancent parts");
        codes[2]=new Code(1100,660,490,30,"     for (i = leftPartStart to rightPartLast)");
        codes[3]=new Code(1100,690,490,30,"         if (leftPartHead <= rightPartHead)");
        codes[4]=new Code(1100,720,490,30,"             copy(leftPartHeadValue);");
        codes[5]=new Code(1100,750,490,30,"         else copy(rightPartHead);");
        codes[6]=new Code(1100,780,490,30,"     copy elements back to original array");

        quick =new JButton("加速");
        quick.setBounds(600,825,80,40);
        quick.setContentAreaFilled(false);
        quick.setForeground(Color.white);

        this.add(quick);
        quick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(speed>25){
                    speed-=25;
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
                if(speed<300){
                    speed+=25;
                }
                System.out.println("slow now speed : "+speed);
            }
        });
        start = new JButton("排序");
        start.setBounds(200,825,80,40);

        //start.setBackground(Color.BLACK);
        start.setForeground(Color.white);
        start.setContentAreaFilled(false);
        this.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canGo=true;
                System.out.println(canGo);
                compareCount=0;
                moveCount=0;
            }
        });


        back = new JButton("返回");
        back.setBounds(400,825,80,40);
        back.setContentAreaFilled(false);
        //setBorderPainted(false);
        back.setForeground(Color.white);
        this.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopFlag=true;
                mainBoard.changeToControll();

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
                if(pauseFlag){
                    pauseFlag=false;
                    pauseButton.setText("暂停");
                }
                else {
                    pauseFlag=true;
                    pauseButton.setText("开始");
                }
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
        title.paintBar(gg);
        hint.paintBar(gg);
        for(Bar i:tempBar){
            if (i!=null){
            i.paintBar(gg);
            }
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

        gg.setColor(Color.white);
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Font font1 = new Font("微软雅黑", Font.BOLD, 18);
        gg.setFont(font1);
        gg.drawString("比较次数 :                     "+compareCount,1135,555);
        gg.drawString("移动次数 :                     "+moveCount,1135,585);
        gg.drawString("速度 : "+(40-speed/10),850,850);
        gg.setStroke(s);

    }
    public  void swap(int a [],int j,int i){
        int tmp = a[i];
        a[i]=a[j];
        a[j]=tmp;
    }
    public void Init() {
        title= new Code(1100,470,490,30,"归并排序");
        title.setColor(Code.BLUE);
        hint= new Code(1100,500,490,30,"");
        hint.setColor(Code.BLUE);
        stopFlag=false;
        for(int i=0;i<N;i++) {
            a[i] = (int) (Math.random() * 100)+1;
            a2[i]=a[i];
            arr[i]=new Bar(60*i+150,350-a[i]*3,50,a[i]*3,a[i]);
        }
        compareCount=0;
        moveCount=0;
        tempBar =new Bar[N];
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
            if(stopFlag){
                canGo=false;
                break;
            }
            if(resetFlag){
                canGo=false;
                hint.setStr("");
                for(int i=0;i<N;i++){
                    a[i]=a2[i];
                    arr[i]=new Bar(60*i+150,350-a[i]*3,60,a[i]*3,a[i]);
                    moveCount=0;
                    compareCount=0;
                }

                tempBar =new Bar[N];
                resetFlag=false;
                System.out.println("if end");
            }
            if (canGo) {
               sort(a,0,N-1);
               if(resetFlag){
                   canGo=false;
                   hint.setStr("");
                   for(int i=0;i<N;i++){
                       a[i]=a2[i];
                       arr[i]=new Bar(60*i+150,350-a[i]*3,50,a[i]*3,a[i]);
                       moveCount=0;
                       compareCount=0;
                   }
                   tempBar =new Bar[N];
                   resetFlag=false;
                   System.out.println("if end");
               }
               else if(stopFlag){
                   canGo=false;
                   break;
               }
               else {
                   canGo=false;
                   hint.setStr("排序完成");
                   sorted = true;
               }
            }
        }
        System.out.println("sort end");
    }

    public void sort(int[] a,int low,int high){
        codes[1].setColor(Code.BLACK);
        hint.setStr("递归地划分区域");
        int mid = (low+high)/2;
        if(stopFlag||resetFlag)return ;
        if(pauseFlag){
            while (pauseFlag)
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(low<high){
            if(pauseFlag){
                while (pauseFlag)
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
            if(stopFlag||resetFlag)return ;
            sort(a,low,mid);
            if(pauseFlag){
                while (pauseFlag)
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
            if(stopFlag||resetFlag)return ;

            sort(a,mid+1,high);
            if(pauseFlag){
                while (pauseFlag)
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
            if(stopFlag||resetFlag)return ;
            //左右归并
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            codes[1].setColor(Code.GLASSGREEN);
            merge(a,low,mid,high);

        }

    }

    public void merge(int[] a, int low, int mid, int high) {
        hint.setStr("将两个区域合并为一个有序区域");
        codes[2].setColor(Code.BLACK);
        int[] temp = new int[high-low+1];

        int i= low;
        int j = mid+1;
        int k=0;
        for(int index =low;index<=high;index++){
            arr[index].setColor(Bar.BLUE);
        }

        try {
            Thread.sleep(5*speed);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(pauseFlag){
            while (pauseFlag)
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        if(stopFlag||resetFlag)return ;
        // 把较小的数先移到新数组中
        codes[2].setColor(Code.GLASSGREEN);
        while(i<=mid && j<=high){
            if(pauseFlag){
                while (pauseFlag)
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
            if(stopFlag||resetFlag)return ;
            codes[3].setColor(Code.BLACK);
            if(a[i]<a[j]){
                compareCount++;
                tempBar[k]=arr[i];
                temp[k++] = a[i];
              //  arr[i].setColor(Entity.Bar.GREEN);
                try {
                    Thread.sleep(2*speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                codes[4].setColor(Code.BLACK);
                arr[i].move(60*(k-1)+150,arr[i].getY()+400,15);
                arr[i].setColor(Bar.GREEN);
                moveCount++;
//                arr[i].setX(60*k+350);
//                arr[i].setY(arr[i].getY()+200);
                i++;
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                codes[4].setColor(Code.GLASSGREEN);
                codes[3].setColor(Code.GLASSGREEN);
            }else{
                compareCount++;
                if(pauseFlag){
                    while (pauseFlag)
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
                tempBar[k]=arr[j];
                temp[k++] = a[j];
//                arr[j].setColor(Entity.Bar.GREEN);
                try {
                    Thread.sleep(2*speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                arr[j].move(60*(k-1)+150,arr[j].getY()+400,15);
                arr[j].setColor(Bar.GREEN);
                moveCount++;

                j++;
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // 把左边剩余的数移入数组
        codes[4].setColor(Code.BLACK);
        while(i<=mid){
            if(pauseFlag){
                while (pauseFlag)
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
            if(stopFlag||resetFlag)return ;
            tempBar[k]=arr[i];
            temp[k++] = a[i];

            //arr[i].setColor(Entity.Bar.GREEN);
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arr[i].move(60*(k-1)+150,arr[i].getY()+400,15);
            arr[i].setColor(Bar.GREEN);
            moveCount++;
//            arr[i].setX(60*k+350);
//            arr[i].setY(arr[i].getY()+200);
            i++;
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        codes[4].setColor(Code.GLASSGREEN);
        // 把右边边剩余的数移入数组
        codes[5].setColor(Code.BLACK);
        while(j<=high){
            if(pauseFlag){
                while (pauseFlag)
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
            if(stopFlag||resetFlag)return ;
            tempBar[k]=arr[j];
            temp[k++] = a[j];
//            arr[j].setColor(Entity.Bar.GREEN);
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arr[j].move(60*(k-1)+150,arr[j].getY()+400,15);
            arr[j].setColor(Bar.GREEN);
            moveCount++;
            j++;
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        codes[5].setColor(Code.GLASSGREEN);
        codes[3].setColor(Code.GLASSGREEN);
        // 把新数组中的数覆盖nums数组
        hint.setStr("将临时数组的元素依次放回原数组");
        codes[6].setColor(Code.BLACK);
        for(int x=0;x<temp.length;x++){
            if(pauseFlag){
                while (pauseFlag)
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
            if(stopFlag||resetFlag)return ;
            try {
                Thread.sleep(2*speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            tempBar[x].setColor(Entity.Bar.YELLOW);
            tempBar[x].move(60*(x+low)+150,tempBar[x].getY()-400,15);
            //tempBar[x].setColor(Entity.Bar.YELLOW);
            a[x+low] = temp[x];
            moveCount++;

            try {
                Thread.sleep(2*speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arr[x+low]= tempBar[x];
            //arr[x+low].move(60*(x+low)+350,arr[x+low].getY()-200,15);

//            arr[x+low].setX(60*(x+low)+350);
//            arr[x+low].setY(arr[x+low].getY()-200);
        }
        codes[6].setColor(Code.GLASSGREEN);
        codes[2].setColor(Code.GLASSGREEN);
    }

    private class paintThread extends Thread{
        @Override
        public void run() {
            while(true){
                repaint();
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
    public boolean isSorted(){
        return sorted;
    }
}

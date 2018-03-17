import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Starry on 2018/3/5.
 */
public class Controll extends JPanel {
    private int height = 0;
    private int width = 0;
    private int x_loacte = 0;
    private int y_loacte = 0;
    private Main mainBoard = null;
    private static JButton bubbleSortButton;
    private static JButton mergeSortButton;
    private static JButton heapSortButton;
    private static JButton bucketSortButton;
    public Controll(int width, int height, Main mainBoard) {
        this.mainBoard = mainBoard;
        this.height = height;
        this.width = width;
        setLayout(null);
        setBounds(x_loacte, y_loacte, width, height);
        setBackground(Color.white);
        setVisible(false);
        setFocusable(true);

       // bubbleSortButton = new JButton(new ImageIcon("./src/Image/bubble.gif"))
        bubbleSortButton = new JButton(new ImageIcon(getClass().getResource("/Image/bubble.gif")));
        bubbleSortButton.setBounds(350,120,400,300);
     //   bubbleSortButton.setIcon(new ImageIcon("bubble.jpg"));
      //  bubbleSortButton.setContentAreaFilled(false);
        this.add(bubbleSortButton);
        bubbleSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               mainBoard.changeToBubble();
            }
        });

        mergeSortButton = new JButton(new ImageIcon(getClass().getResource("/Image/merge.gif")));
        mergeSortButton.setBounds(850,480,400,300);
        mergeSortButton.setContentAreaFilled(true);
        this.add(mergeSortButton);
        mergeSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((MergeSort)mainBoard.getMergeSort()).sorted=false;
                ((MergeSort)mainBoard.getMergeSort()).stopFlag=false;
                mainBoard.changeToMerge();
            }
        });
        bucketSortButton = new JButton(new ImageIcon(getClass().getResource("/Image/bucket.gif")));
        bucketSortButton.setBounds(850,120,400,300);
        bucketSortButton.setContentAreaFilled(true);
        this.add(bucketSortButton);
        bucketSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainBoard.changeToBucket();
            }
        });
        heapSortButton = new JButton(new ImageIcon(getClass().getResource("/Image/heap1.gif")));
        heapSortButton.setBounds(350,480,400,300);
        heapSortButton.setContentAreaFilled(false);
        this.add(heapSortButton);
        heapSortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainBoard.changeToHeap();
            }
        });




    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gg = (Graphics2D) g;

    }

    public void Init() {
        new paintThread().start();
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

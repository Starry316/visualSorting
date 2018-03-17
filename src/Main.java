import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame{
    private int height,width;
    private static JComponent nowSorting;
    private static JComponent bubbleSort;
    private static JComponent mergeSort;
    private static JComponent controll;
    private static HeapSort heapSort;
    private static BucketSort bucketSort;

    Main(int height, int width) {
        this.height = height;
        this.width = width;
        setTitle("数据结构课设");
        //得到屏幕大小
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screensize.width;
        int screenHeight = screensize.height;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(screenWidth / 2 - width / 2, screenHeight / 2 - height / 2, width, height);
        setLayout(null);

        controll =new Controll(width,height,this);
        getContentPane().add(controll);
        controll.setVisible(true);
        nowSorting=controll;
        bucketSort = new BucketSort(width,height,this);
        getContentPane().add(bucketSort);
        bucketSort.setVisible(false);
        bubbleSort =new BubbleSort(width,height,this);
        getContentPane().add(bubbleSort);
        bubbleSort.setVisible(false);
        mergeSort =new MergeSort(width,height,this);
        getContentPane().add(mergeSort);
        mergeSort.setVisible(false);
        heapSort =new HeapSort(width,height,this);
        getContentPane().add(heapSort);
        heapSort.setVisible(false);

        setVisible(true);
        //((BubbleSort)bubbleSort).Init();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("鼠标位置: x "+e.getX() + " , y " + e.getY());
            }
        });
        while (true){
            if(nowSorting==mergeSort){

                controll.setVisible(false);
                if (!((MergeSort)mergeSort).isSorted()){
                    mergeSort =new MergeSort(width,height,this);
                    getContentPane().add(mergeSort);
                ((MergeSort)mergeSort).Init();
                mergeSort.setVisible(true);
                ((MergeSort)mergeSort).sort();
                }

              //  break;
            }
            if (nowSorting == bucketSort){
                controll.setVisible(false);
                bucketSort.Init();
                bucketSort.setVisible(true);
                bucketSort.sort();
            }
            if (nowSorting==bubbleSort){
                controll.setVisible(false);

                ((BubbleSort)bubbleSort).Init(); bubbleSort.setVisible(true);

                ((BubbleSort)bubbleSort).sort();

              //  break;
            }
            if(nowSorting==heapSort){
                controll.setVisible(false);
                heapSort.setVisible(true);
                heapSort.Init();
                heapSort.sort();
            }
            if (nowSorting==controll){
                bubbleSort.setVisible(false);
                mergeSort.setVisible(false);
                heapSort.setVisible(false);
                bucketSort.setVisible(false);
                controll.setVisible(true);

                //  break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void changeToHeap(){
        nowSorting=heapSort;
    }
    public void changeToBubble(){

       nowSorting=bubbleSort;

    }
    public void changeToBucket(){
        nowSorting = bucketSort;
    }
    public void changeToMerge(){

        nowSorting=mergeSort;

    }
    public void changeToControll(){

        nowSorting=controll;

    }
    public static void main(String[] args) {

        new Main(900, 1600);
    }

    public static JComponent getBubbleSort() {
        return bubbleSort;
    }

    public static JComponent getMergeSort() {
        return mergeSort;
    }

    public static HeapSort getHeapSort() {
        return heapSort;
    }

    public static BucketSort getBucketSort() {
        return bucketSort;
    }
}

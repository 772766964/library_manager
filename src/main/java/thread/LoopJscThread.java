package thread;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * @ClassName TipsThread
 * @Description TODO
 * @Author 1
 * @Date 2020/12/29
 **/
public class LoopJscThread extends Thread {
    private JScrollPane jScrollPane;
    private int maxLength;
    private int len;

    public void setjScrollPane(JScrollPane jScrollPane){
        this.jScrollPane = jScrollPane;
    }

    @Override
    public void run() {
        maxLength = jScrollPane.getHorizontalScrollBar().getMaximum();
        len = 0;
        while(true){
            jScrollPane.getHorizontalScrollBar().setValue(len);
            //System.out.println("正在运行");
            //System.out.println(maxLength+","+len);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.err.println("轮播logo异常");
            }
            len+=1;
            if(len>=(maxLength)){
                len=0;
            }
        }
    }
}

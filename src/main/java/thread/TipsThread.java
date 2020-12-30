package thread;

import javax.swing.*;

/**
 * @ClassName TipsThread
 * @Description TODO
 * @Author 1
 * @Date 2020/12/29
 **/
public class TipsThread extends Thread {
    String message;
    public void setMessage(String message){
        this. message = message;
    }

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        JOptionPane.showMessageDialog(null,message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //JOptionPane.s
    }
}

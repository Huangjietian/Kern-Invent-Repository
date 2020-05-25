package cn.kerninventor.tools.poibox.opensource.data.tabulation.writer.chain;

/**
 * @author Kern
 * @date 2020/5/22 16:40
 * @description
 */
public class WriteThread extends Thread {

    public WriteThread(Runnable target) {
        super(target);
    }

    @Override
    public synchronized void start() {
        super.start();
    }
}

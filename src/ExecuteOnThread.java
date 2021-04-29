import java.io.IOException;

public class ExecuteOnThread implements Runnable{
    private Thread thread;
    private String threadName;
    MonitorFunctionality _monitor;

    public ExecuteOnThread(String name, MonitorFunctionality monitor) {
        threadName = name;
        _monitor = monitor;
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    @Override
    public void run() {
        try {
            //Put your MonitorClient functionality in here!
            _monitor.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
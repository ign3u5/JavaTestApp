import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.*;

public class MonitorForm implements ActionListener {
    JLabel lTest = new JLabel();
    private MonitorFunctionality _monitor;

    public void Render(String username) {
        lTest.setText(String.format("Hello %s!", username));
        JFrame fMain = new JFrame("Example - Main");
        JButton btnTest = new JButton("Click me!");

        lTest.setBounds(50, 20, 200, 25);
        btnTest.setBounds(50, 50, 200, 25);

        btnTest.addActionListener(this);

        fMain.add(lTest);
        fMain.add(btnTest);

        fMain.setLayout(null);
        fMain.setSize(500, 300);  
        fMain.setLocationRelativeTo(null);  
        fMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        fMain.setVisible(true);  
    }

    public void setMonitor(MonitorFunctionality monitor) {
        monitor.setLabelForData(lTest);
        _monitor = monitor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (_monitor != null) {
            var task = new ExecuteOnThread("monitor", _monitor);
            task.start();
        }
    }
}

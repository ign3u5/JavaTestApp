import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.*;

public class LoginForm implements ActionListener {
    JFrame fMain = new JFrame("Example - Login");
    JButton btnLogin = new JButton("Login");
    private MonitorForm _monitorForm;
    JTextField tfUsername = new JTextField();
    
    public void Render(MonitorForm monitorForm) {
        _monitorForm = monitorForm;
        JLabel lUsername = new JLabel("Username: ");
        JLabel lPassword = new JLabel("Password: ");
        JPasswordField pfPassword = new JPasswordField();

        lUsername.setBounds(50, 20, 200, 25);
        lPassword.setBounds(50, 70, 200, 25);
        tfUsername.setBounds(50, 40, 200, 25);
        pfPassword.setBounds(50, 90, 200, 25);
        btnLogin.setBounds(50, 150, 200, 20);
        
        fMain.add(lUsername);
        fMain.add(lPassword);
        fMain.add(tfUsername);
        fMain.add(pfPassword);
        fMain.add(btnLogin);
        
        btnLogin.addActionListener(this);
        fMain.setLayout(null);
        fMain.setSize(500, 300);  
        fMain.setLocationRelativeTo(null);  
        fMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        fMain.setVisible(true);  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            _monitorForm.Render(tfUsername.getText());
            fMain.dispose();
        }        
    }
}

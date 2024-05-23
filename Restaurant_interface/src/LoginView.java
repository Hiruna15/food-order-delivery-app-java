import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView {

    JButton loginBtn;
    JTextField usernameTxtField;
    JTextField passwordTxtField;
    JFrame loginFrame;

    public LoginView(){
        loginFrame = new JFrame();
        loginFrame.setSize(457, 441);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);

        JPanel loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(457, 441));
        loginPanel.setBackground(new Color(0x353535));
        loginPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        loginFrame.add(loginPanel, BorderLayout.CENTER);

        JPanel logoPanel = new JPanel();
        logoPanel.setPreferredSize(new Dimension(457, 100));
        logoPanel.setBackground(new Color(0x353535));
        logoPanel.setLayout(null);
        loginPanel.add(logoPanel);

        JLabel logoLabel = new JLabel("LOGO");
        logoLabel.setFont(new Font(null, Font.BOLD, 30));
        logoLabel.setForeground(Color.white);
        logoLabel.setBounds(180, 25, 200,50);
        logoPanel.add(logoLabel);

        JPanel usernamePanel = new JPanel();
        usernamePanel.setPreferredSize(new Dimension(400, 80));
        usernamePanel.setBackground(new Color(0x353535));
        usernamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        loginPanel.add(usernamePanel);

        JLabel usernameLabel = new JLabel("USERNAME");
        usernameLabel.setForeground(Color.white);
        usernamePanel.add(usernameLabel);

        usernameTxtField = new JTextField("hirunadilmith15@gmail.com"); ////
        usernameTxtField.setPreferredSize(new Dimension(400, 40));
        usernamePanel.add(usernameTxtField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setPreferredSize(new Dimension(400, 100));
        passwordPanel.setBackground(new Color(0x353535));
        passwordPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        loginPanel.add(passwordPanel);

        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setForeground(Color.white);
        passwordPanel.add(passwordLabel);

        passwordTxtField = new JTextField("gutdAyaQ"); ////
        passwordTxtField.setPreferredSize(new Dimension(400, 40));
        passwordPanel.add(passwordTxtField);

        loginBtn = new JButton("LOG IN");
        loginBtn.setForeground(Color.white);
        loginBtn.setPreferredSize(new Dimension(400, 40));
        loginBtn.setBackground(new Color(0x047716));
        loginPanel.add(loginBtn);

        loginFrame.setVisible(true);
    }

    public void addLoginButtonListner(ActionListener listener){
        loginBtn.addActionListener(listener);
    }

    public String[] getUsernamePassword(){
        String[] userLog = {usernameTxtField.getText(), passwordTxtField.getText()};
        return userLog;
    }

    public void displayInvalidLogMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Invalid log", JOptionPane.ERROR_MESSAGE);
    }

    public void closeLoginFrame(){
        loginFrame.dispose();
    }
}


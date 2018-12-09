package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame
{
    private JPanel pnl_main;
    private JLabel lbl_username;
    private JTextField tf_username;
    private JLabel lbl_password;
    private JPasswordField pf_password;
    private JButton btn_register;
    private JButton btn_login;

    public LoginForm()
    {
        initializeComponents();
        startListeners();
    }

    private void startListeners()
    {
        btn_register.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                tf_username.setText("Hello World");
            }
        });
    }

    private void initializeComponents()
    {
        add(pnl_main);

        setTitle("Login/Register");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

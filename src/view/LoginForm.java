package view;

import controller.LoginController;
import org.apache.commons.lang3.StringUtils;

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
    private LoginController controller;

    public LoginForm()
    {
        controller = new LoginController(this);
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
                controller.handleRegistrationButtonPress(
                        tf_username.getText(), new String(pf_password.getPassword()));
            }
        });

        btn_login.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                controller.handleLoginButtonPress(
                        tf_username.getText(), new String(pf_password.getPassword()));
            }
        });
    }

    private void initializeComponents()
    {
        add(pnl_main);

        setTitle("Login/Register");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

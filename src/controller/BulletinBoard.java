package controller;

import view.LoginForm;

import javax.swing.*;

/**
 * @Author Luke McCann - U1364096
 *
 * Entry point of BulletinBoard application.
 */
public class BulletinBoard
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
            }
        });
    }
}

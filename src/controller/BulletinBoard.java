package controller;

import view.LoginForm;

import javax.swing.*;

public class BulletinBoard
{
    public static void main(String[] args) throws InterruptedException
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                LoginForm form = new LoginForm();
                form.setVisible(true);
            }
        });
    }
}

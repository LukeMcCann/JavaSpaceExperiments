package controller;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import model.LMUserEntry;
import org.apache.commons.lang3.StringUtils;
import net.jini.core.transaction.TransactionException;
import utility.UserUtils;
import utility.security.CipherUtils;
import view.LoginForm;
import view.TopicForm;

/**
 * @Author Luke McCann
 *
 * Controller class for Registration and Login logic.
 */
public class LoginController
{
    private LoginForm loginForm;
    private static final UserUtils userUtils = UserUtils.getUserUtils();

    public LoginController(LoginForm loginForm) { this.loginForm = loginForm; }

    public void handleRegistrationButtonPress(String username, String password)
    {
        try
        {
            if (StringUtils.isEmpty(username) ||
                    StringUtils.isEmpty(password) || username.length() > 20)
            {
                JOptionPane.showMessageDialog(loginForm,
                        "Please enter a valid Username and Password.");
            }
            else if (password.length() < 6 ||
                    password.length() > 25)
            {
                JOptionPane.showMessageDialog(loginForm,
                        "Invalid Password! Please try again.");
            }
            else
            {
                String conf_password = confirmPassword();

                if (password.equals(conf_password))
                {
                    LMUserEntry user = new LMUserEntry();

                    user.salt = CipherUtils.getSalt(30);
                    user.password = password;
                    user.userName = username;
                    user.id = UUID.randomUUID();

                    userUtils.createUser(user);
                    JOptionPane.showMessageDialog(loginForm, "Account created! Please login.");
                }
                else
                {
                    JOptionPane.showMessageDialog(loginForm,
                            "Passwords did not match.");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(loginForm,
                    "Account creation unsuccessful.");
        }
    }

    public void handleLoginButtonPress(String username, String password)
    {
        if (StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password))
        {
            JOptionPane.showMessageDialog(loginForm,
                    "Please enter a valid username and password.");
        }
        else
        {
            LMUserEntry template = new LMUserEntry();
            template.userName = username;

            LMUserEntry disc_user =
                    userUtils.getUserByName(template.userName);

            if (disc_user == null)
            {
                JOptionPane.showMessageDialog(loginForm,
                        "Invalid username.  Please try again.");
            }
            else
            {
                try
                {
                    boolean validPassword = CipherUtils.verifyPassword(
                            password, disc_user.password, disc_user.salt);

                    if (validPassword)
                    {

                        loginForm.setVisible(false);
                        loginForm.dispose();

                        TopicForm topicForm = new TopicForm();
                        topicForm.setVisible(true);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(loginForm,
                                "Invalid password.  Please try again.");
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(loginForm,
                            "Something went wrong in the Space.");
                }
            }
        }
    }

    private String confirmPassword()
    {
        JPanel pnl_confirmPassword = new JPanel();
        JLabel lbl_confirmPassword = new JLabel("Confirm Password: ");
        JPasswordField passwordConfirmationField = new JPasswordField(20);

        pnl_confirmPassword.add(lbl_confirmPassword);
        pnl_confirmPassword.add(passwordConfirmationField);

        String[] options = new String[] { "Confirm" };

        JOptionPane.showOptionDialog(null,
                pnl_confirmPassword, "Confirm Password", JOptionPane.NO_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        return new String(passwordConfirmationField.getPassword());
    }
}

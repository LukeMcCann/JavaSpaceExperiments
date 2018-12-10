package utility;

import java.rmi.RemoteException;

import javax.naming.directory.InvalidAttributeValueException;

import model.LMUserEntry;
import utility.helper.TransactionHelper;
import org.apache.commons.lang3.StringUtils;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.lease.Lease;
import net.jini.core.transaction.Transaction;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace05;

public class UserUtils {

    private static int ONE_SECOND = 1000;
    private static int ONE_MINUTE = ONE_SECOND*60;
    private static int ONE_HOUR = ONE_MINUTE*60;
    private static int ONE_DAY = ONE_HOUR*24;
    private static int ONE_WEEK = ONE_DAY*7;

    private static final JavaSpace05 space = SpaceUtils.getSpace();
    private static UserUtils UserUtils;

    // Singleton
    private UserUtils() {}

    public Lease createUser(LMUserEntry user)
    {
        Lease lease = null;
        isValid(user);
        try
        {
            Transaction transaction =
                    TransactionHelper.getTransaction(3000);

            if (getUserByName(
                    user.userName, transaction) == null)
            {
                lease = space.write(user,
                        transaction, ONE_WEEK);

                transaction.commit();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return lease;
    }

    public Lease renewLease(LMUserEntry user)
    {
        Lease lease = null;

        try
        {
            if (isValid(user))
            {
                Transaction transaction = TransactionHelper.getTransaction(3000);
                LMUserEntry disc_user = getUserByName(user.userName, transaction);

                if (disc_user != null)
                {
                    try
                    {
                        space.takeIfExists(disc_user, transaction, 3000);
                        lease = space.write(user, transaction, ONE_WEEK);
                        transaction.commit();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return lease;
    }

    public LMUserEntry getUserByName(String userName, Transaction transaction)
    {
        LMUserEntry template = new LMUserEntry();
        template.userName = userName;
        LMUserEntry disc_user = null;

        try
        {
            disc_user = (LMUserEntry) space.readIfExists(
                    template, transaction, 3000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return disc_user;
    }

    public LMUserEntry getUserByName(String userName) { return getUserByName(userName, null); }

    private boolean isValid(LMUserEntry user)
    {
        if (!StringUtils.isEmpty(user.userName) && !StringUtils.isEmpty(user.password)
                && !StringUtils.isEmpty(user.id.toString()))
        {
            return true;
        }
        return false;
    }

    public static UserUtils getUserUtils()
    {
        if (UserUtils == null)
        {
            UserUtils = new UserUtils();
        }
        return UserUtils;
    }

}

package utilities;

import model.User;
import net.jini.core.lease.Lease;
import net.jini.core.transaction.Transaction;
import net.jini.space.JavaSpace05;

public class UserUtils
{
    private static int THREE_SECONDS = (3 * 1000);
    private static int THREE_MINUTES = THREE_SECONDS * 60;

    private static final JavaSpace05 spae = SpaceUtils.getSpace();
    private static UserUtils userUtils;

    public static UserUtils getUserUtils()
    {
        if(getUserUtils() == null)
        {
            userUtils = new UserUtils();
        }
        return userUtils;
    }

    public void createUser(User user)
    {
        Lease lease = null;

    }
}

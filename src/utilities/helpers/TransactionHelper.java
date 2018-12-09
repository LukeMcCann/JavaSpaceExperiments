package utilities.helpers;

import net.jini.core.transaction.Transaction;
import net.jini.core.transaction.TransactionFactory;
import net.jini.core.transaction.server.TransactionManager;
import utilities.SpaceUtils;

public class TransactionHelper
{
    public static final int DEFAULT_LEASE = 3000;

    private TransactionHelper() {}

    public static Transaction getTransaction() { return getTransaction(DEFAULT_LEASE);}

    public static Transaction getTransaction(int timeout)
    {
        Transaction transaction = null;

        try
        {
            TransactionManager tm = SpaceUtils.getManager();
            Transaction.Created createdTransaction;
            createdTransaction = TransactionFactory.create(tm, timeout);
            transaction = createdTransaction.transaction;
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e);
            System.err.println("Failed to create transaction!");
            e.printStackTrace();
        }
        return transaction;
    }
}

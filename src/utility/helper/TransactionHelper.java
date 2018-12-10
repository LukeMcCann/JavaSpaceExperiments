package utility.helper;

import java.rmi.RemoteException;

import net.jini.core.lease.LeaseDeniedException;
import net.jini.core.transaction.Transaction;
import net.jini.core.transaction.Transaction.Created;
import net.jini.core.transaction.TransactionFactory;
import net.jini.core.transaction.server.TransactionManager;
import utility.SpaceUtils;

public class TransactionHelper
{
    private TransactionHelper() {}

    public static Transaction getTransaction(int timeout)
    {
        Transaction transaction = null;

        try
        {
            TransactionManager transactionManager = SpaceUtils.getManager();
            Created createdTransaction;
            createdTransaction = TransactionFactory.create(transactionManager, timeout);
            transaction = createdTransaction.transaction;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println("Failed to create transaction.");
        }

        return transaction;
    }
}

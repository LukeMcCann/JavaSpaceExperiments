package model;

import java.util.UUID;

import net.jini.core.entry.Entry;

/**
 * @Author Luke McCann
 *
 */
@SuppressWarnings("serial")
public class LMUserEntry implements Entry
{
    public String userName;
    public String password; // The hashed password
    public UUID id;
    public String salt;

    public LMUserEntry() {}

    public LMUserEntry(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
        this.id = UUID.randomUUID();
    }

    /**
     * Checks if two users are the same.
     */
    public boolean equals(Object user) {
        if (user != null && user instanceof LMUserEntry)
        {
            LMUserEntry otherUser = (LMUserEntry) user;

            if (otherUser.id.equals(this.id))
            {
                return true;
            }
        }
        return false;
    }
}

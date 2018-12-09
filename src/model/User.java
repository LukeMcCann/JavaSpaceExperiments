package model;

import net.jini.core.entry.Entry;

import java.util.UUID;

public class User implements Entry
{
    public UUID id;
    public String username;
    public String password; // hash of password

    public User() {}

    public User(String username, String password)
    {
        this.id = UUID.randomUUID();
        this.password = password;
        this.username = username;
    }
}

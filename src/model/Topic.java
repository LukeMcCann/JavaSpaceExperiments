package model;

import net.jini.core.entry.Entry;

import java.util.UUID;

public class Topic implements Entry
{
    public UUID id;
    public String title;
    public User owner;

    public Topic() {}

    public Topic(String title, User owner)
    {
        this.id = UUID.randomUUID();
        this.title = title;
        this.owner = owner;
    }
}

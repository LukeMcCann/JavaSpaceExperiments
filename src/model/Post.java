package model;

import java.util.Date;
import java.util.UUID;

public class Post
{
    public UUID id;
    public Topic topic;
    public Date sent;
    public User from;
    public User to;
    public String content;

    public Post() {}

    public Post(Topic topic) { this.topic = topic; }

    public Post(Date sent, Topic topic, User from, User to, UUID id, String content)
    {
        this.sent = sent;
        this.topic = topic;
        this.from = from;
        this.to = to;
        this.id = id;
        this.content = content;
    }
}

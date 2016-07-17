package com.justin.twitch.follows;


public class Follows
{
private String notifications;

private _links _links;

private String created_at;

private User user;

public String getNotifications ()
{
return notifications;
}

public void setNotifications (String notifications)
{
this.notifications = notifications;
}

public _links get_links ()
{
return _links;
}

public void set_links (_links _links)
{
this._links = _links;
}

public String getCreated_at ()
{
return created_at;
}

public void setCreated_at (String created_at)
{
this.created_at = created_at;
}

public User getUser ()
{
return user;
}

public void setUser (User user)
{
this.user = user;
}

@Override
public String toString()
{
return "ClassPojo [notifications = "+notifications+", _links = "+_links+", created_at = "+created_at+", user = "+user+"]";
}
}


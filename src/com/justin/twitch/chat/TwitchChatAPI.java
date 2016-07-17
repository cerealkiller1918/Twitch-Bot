package com.justin.twitch.chat;


public class TwitchChatAPI
{
private String chatter_count;

private String _links;

private Chatter chatters;

public String getChatter_count ()
{
return chatter_count;
}

public void setChatter_count (String chatter_count)
{
this.chatter_count = chatter_count;
}

public String get_links ()
{
return _links;
}

public void set_links (String _links)
{
this._links = _links;
}

public Chatter getChatters ()
{
return chatters;
}

public void setChatters (Chatter chatters)
{
this.chatters = chatters;
}

@Override
public String toString()
{
return "ClassPojo [chatter_count = "+chatter_count+", _links = "+_links+", chatters = "+chatters+"]";
}
}
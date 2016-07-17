package com.justin.twitch.follows;

public class TwitchFollowsAPI
{
    private Follows[] follows;

    private _links _links;

    private String _total;

    private String _cursor;

    public Follows[] getFollows ()
    {
        return follows;
    }

    public void setFollows (Follows[] follows)
    {
        this.follows = follows;
    }

    public _links get_links ()
    {
        return _links;
    }

    public void set_links (_links _links)
    {
        this._links = _links;
    }

    public String get_total ()
    {
        return _total;
    }

    public void set_total (String _total)
    {
        this._total = _total;
    }

    public String get_cursor ()
    {
        return _cursor;
    }

    public void set_cursor (String _cursor)
    {
        this._cursor = _cursor;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [follows = "+follows+", _links = "+_links+", _total = "+_total+", _cursor = "+_cursor+"]";
    }
}
			
			

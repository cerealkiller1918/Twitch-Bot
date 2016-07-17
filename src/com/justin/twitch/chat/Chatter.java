package com.justin.twitch.chat;

public class Chatter {
	
	 private String[] staff;

	    private String[] viewers;

	    private String[] global_mods;

	    private String[] moderators;

	    private String[] admins;

	    public String[] getStaff ()
	    {
	        return staff;
	    }

	    public void setStaff (String[] staff)
	    {
	        this.staff = staff;
	    }

	    public String[] getViewers ()
	    {
	        return viewers;
	    }

	    public void setViewers (String[] viewers)
	    {
	        this.viewers = viewers;
	    }

	    public String[] getGlobal_mods ()
	    {
	        return global_mods;
	    }

	    public void setGlobal_mods (String[] global_mods)
	    {
	        this.global_mods = global_mods;
	    }

	    public String[] getModerators ()
	    {
	        return moderators;
	    }

	    public void setModerators (String[] moderators)
	    {
	        this.moderators = moderators;
	    }

	    public String[] getAdmins ()
	    {
	        return admins;
	    }

	    public void setAdmins (String[] admins)
	    {
	        this.admins = admins;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [staff = "+staff+", viewers = "+viewers+", global_mods = "+global_mods+", moderators = "+moderators+", admins = "+admins+"]";
	    }

}

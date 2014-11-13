package com.diamed.DiaMed;

public class DataObject {
	
	public String getUser() {
		return username;
	}

	public void setUser(String user) {
		this.username = user;
	}

	
	public String getpID() {
		return pID;
	}

	public void setpID(String pID) {
		this.pID = pID;
	}
	
	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getSugar_Level() {
		return sugar_Level;
	}

	public void setSugar_Level(String sugar_Level) {
		this.sugar_Level = sugar_Level;
	}

	
	private String pID;
	private String Date;
	private String Time;
	private String activity;
	private String sugar_Level;
	private String username;
	
	public DataObject(String user, String pID, String Date, String Time,
			String activity, String sugar_Level) {
		this.username = user;
		this.pID = pID;
		this.Date = Date;
		this.Time = Time;
		this.activity = activity;
		this.sugar_Level = sugar_Level;
	}

	
}

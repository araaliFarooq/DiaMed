package com.diamed.DiaMed;

public class DataObjectHbP {

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

	
	public String getSystolic() {
		return systolic;
	}

	public void setSystolic(String systolic) {
		this.systolic = systolic;
	}
	
	public String getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(String diastolic) {
		this.diastolic = diastolic;
	}

	
	private String pID;
	private String Date;
	private String Time;
	private String activity;
	private String systolic;
	private String diastolic;
	private String username;
	

	
	public DataObjectHbP(String user, String pID, String Date, String Time,
			String activity, String systolic, String diastolic) {
		this.username = user;
		this.pID = pID;
		this.Date = Date;
		this.Time = Time;
		this.activity = activity;
		this.systolic = systolic;
		this.diastolic = diastolic;
	}

	
}

package com.diamed.DiaMed;

public class DataObjectFile {

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

	
	public String getMedication() {
		return Medication;
	}

	public void setMedication(String Medication) {
		this.Medication = Medication;
	}

	public String getPrescription() {
		return Prescription;
	}

	public void setPrescription(String Prescription) {
		this.Prescription = Prescription;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String StartDate) {
		this.StartDate = StartDate;
	}

	public String getDisease() {
		return StartDate;
	}

	public void setDisease(String Disease) {
		this.Disease = Disease;
	}
	
	private String pID;
	private String Date;
	private String username;
	private String Medication;
	private String Prescription;
	private String duration;
	private String StartDate;
	private String Disease;

	public DataObjectFile(String user, String pID, String Date, String disease, String medication,
			String prescription, String duration, String startDate) {
		this.username = user;
		this.pID = pID;
		this.Date = Date;
		this.Disease = disease;
		this.Medication = medication;
		this.Prescription = prescription;
		this.duration = duration;
		this.StartDate = startDate;
	}
	
}

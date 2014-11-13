package com.diamed.DiaMed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManipulator {
	private static final String DATABASE_NAME = "mydatabase.db";
	private static final int DATABASE_VERSION = 1;
	static final String TABLE_NAME = "myFile";
	static final String TABLE_NAME2 = "login";
	static final String TABLE_NAME3 = "diabetes";
	static final String TABLE_NAME4 = "pressure";
	static final String TABLE_NAME5 = "doctorsregister";

	private static Context context;
	static SQLiteDatabase db;
	@SuppressWarnings("unused")
	private static final String PIN = "Pin";
	public final String ID = "id";

	private SQLiteStatement insertStmt, insertStmt2, insertStmt3, insertStmt4,
			insertStmt5;

	private static final String INSERT = "insert into "
			+ TABLE_NAME
			+ " (Date,Disease,Medication,Prescription,Duration,StartDate) values (?,?,?,?,?,?)";
	private static final String INSERT2 = "insert into " + TABLE_NAME2
			+ " (Pin) values (?)";
	private static final String INSERT3 = "insert into " + TABLE_NAME3
			+ " (Date,Time,Activity,Sugar_Level) values (?,?,?,?)";
	private static final String INSERT4 = "insert into " + TABLE_NAME4
			+ " (Date,Time,Activity,Systolic,Diastolic) values (?,?,?,?,?)";
	private static final String INSERT5 = "insert into "
			+ TABLE_NAME5
			+ " (pName,pUsername,docName,docContact, docAddress) values (?,?,?,?,?)";

	private static String Sugar_Level = "Sugar_Level";
	private static String Systolic = "Systolic";
	private static String Diastolic = "Diastolic";
	private static String Disease = "Disease";
	private static String Medication = "Medication";
	private static String Prescription = "Prescription";
	private static String Duration = "Duration";
	private static String startDate = "startDate";
	private static String pName = "pNAme";
	private static String pUsername = "pUsername";
	private static String docName = "docName";
	
	private static String docContact = "docContact";
	private static String docAddress = "docAddress";

	public DatabaseManipulator(Context context) {
		DatabaseManipulator.context = context;
		OpenHelper openHelper = new OpenHelper(DatabaseManipulator.context);
		DatabaseManipulator.db = openHelper.getWritableDatabase();
		try {
			this.insertStmt = DatabaseManipulator.db.compileStatement(INSERT);
			this.insertStmt2 = DatabaseManipulator.db.compileStatement(INSERT2);
			this.insertStmt3 = DatabaseManipulator.db.compileStatement(INSERT3);
			this.insertStmt4 = DatabaseManipulator.db.compileStatement(INSERT4);
			this.insertStmt5 = DatabaseManipulator.db.compileStatement(INSERT5);
		} catch (Exception e) {
			Log.i("DatabaseManipulator", "" + e);
		}
	}

	// insert into newtable
	public long insert(String Date, String Disease, String Medication,
			String Prescription, String Duration, String StartDate) {

		this.insertStmt.bindString(1, Date);
		this.insertStmt.bindString(2, Disease);

		this.insertStmt.bindString(3, Medication);
		this.insertStmt.bindString(4, Prescription);
		this.insertStmt.bindString(5, Duration);
		this.insertStmt.bindString(6, StartDate);
		return this.insertStmt.executeInsert();
	}

	// insert into login
	public long insert2(String Pin) {

		this.insertStmt2.bindString(1, Pin);

		return this.insertStmt2.executeInsert();
	}

	// insert into daibetes
	public long insert3(String Date, String Time, String Activity,
			String Sugar_Level) {

		this.insertStmt3.bindString(1, Date);
		this.insertStmt3.bindString(2, Time);
		this.insertStmt3.bindString(3, Activity);
		this.insertStmt3.bindString(4, Sugar_Level);

		return this.insertStmt3.executeInsert();
	}

	// insert into pressure
	public long insert4(String Date, String Time, String Activty,
			String Systolic, String Diastolic) {

		this.insertStmt4.bindString(1, Date);
		this.insertStmt4.bindString(2, Time);
		this.insertStmt4.bindString(3, Activty);
		this.insertStmt4.bindString(4, Systolic);
		this.insertStmt4.bindString(5, Diastolic);

		return this.insertStmt4.executeInsert();
	}

	public long insert5(String pName, String pUsername, String docName,
			 String docContact, String docAdress) {

		this.insertStmt5.bindString(1, pName);
		this.insertStmt5.bindString(2, pUsername);

		this.insertStmt5.bindString(3, docName);
	
		this.insertStmt5.bindString(4, docContact);
		this.insertStmt5.bindString(5, docAdress);
		return this.insertStmt5.executeInsert();
	}

	// delete doctors details.
	public void deleteDocDetails() {
		db.delete(TABLE_NAME5, null, null);
	}

	// update doctors details
	public boolean updateDocRegister(double rowID, String pName1,
			String pUsername1, String docName1,
			String docContact1, String docAdress1) {
		ContentValues args = new ContentValues();

		args.put(pName, pName1);
		args.put(pUsername, pUsername1);
		args.put(docName, docName1);
		args.put(docContact, docContact1);
		args.put(docAddress, docAdress1);
		// continue

		return DatabaseManipulator.db.update(TABLE_NAME5, args, ID + "="
				+ rowID, null) > 0;
	}

	// select from doctors table
	public List<String[]> selectDocDetails() {

		List<String[]> list3 = new ArrayList<String[]>();
		Cursor cursor2 = db.query(TABLE_NAME5, new String[] { "id", "pName",
				"pUsername", "docName", "docContact", "docAddress" }, null,
				null, null, null, "id asc");

		int x = 0;
		if (cursor2.moveToFirst()) {
			do {

				String[] b1 = new String[] { cursor2.getString(0),
						cursor2.getString(1), cursor2.getString(2),
						cursor2.getString(3), cursor2.getString(4),
						cursor2.getString(5)};

				list3.add(b1);

				x = x + 1;
			} while (cursor2.moveToNext());
		}
		if (cursor2 != null && !cursor2.isClosed()) {

			cursor2.close();
		}
		cursor2.close();

		return list3;
	}
//counting doc table rows
	public int rowCount(){
		
		final String tableCount = "select count(*) from doctorsregister";

		int count = (int) DatabaseUtils.longForQuery(db, tableCount, null);
		return count;	
	}
	
	public void deleteAll() {
		db.delete(TABLE_NAME, null, null);
	}

	// edit diabetes values
	public boolean updateDiabetes(double rowID, String sugarLevel) {
		ContentValues args = new ContentValues();

		args.put(Sugar_Level, sugarLevel);

		return DatabaseManipulator.db.update(TABLE_NAME3, args, ID + "="
				+ rowID, null) > 0;
	}

	// edit pressure values

	public boolean updatePressure(double rowID, String systolic,
			String diastolic) {
		ContentValues args = new ContentValues();

		args.put(Systolic, systolic);
		args.put(Diastolic, diastolic);

		return DatabaseManipulator.db.update(TABLE_NAME4, args, ID + "="
				+ rowID, null) > 0;
	}

	// select from newtable
	public List<String[]> selectAll() {

		List<String[]> list = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_NAME, new String[] { "id", "Date",
				"Disease", "Medication", "Prescription", "Duration",
				"StartDate" }, null, null, null, null, "Disease asc");

		int x = 0;
		if (cursor.moveToFirst()) {
			do {
				String[] b1 = new String[] { cursor.getString(0),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4),
						cursor.getString(5), cursor.getString(6) };

				list.add(b1);

				x++;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		cursor.close();

		return list;
	}

	// select values for the pressure chart...
	public List<String[]> PresssureChartSelect() {

		List<String[]> list2 = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_NAME4, new String[] { "Date",
				"Systolic", "Diastolic" }, null, null, null, null, "Pin asc");

		int x = 0;
		if (cursor.moveToFirst()) {
			do {
				String[] b1 = new String[] { cursor.getString(0),
						cursor.getString(1) };

				list2.add(b1);

				x = x + 1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		cursor.close();

		return list2;
	}

	// select from daibetes
	public List<String[]> selectAll3() {

		List<String[]> list3 = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_NAME3, new String[] { "id", "Date",
				"Time", "Activity", "Sugar_Level" }, null, null,
				null, null, "id asc");

		int x = 0;
		if (cursor.moveToFirst()) {
			do {
				String[] b1 = new String[] { cursor.getString(0),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4)};

				list3.add(b1);

				x = x + 1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		cursor.close();

		return list3;
	}


	// select from pressure
	public List<String[]> selectAll4() {

		/*String selectQuery = "SELECT AVG(systolic) as systolic, AVG(diastolic) as diastolic FROM " + TABLE_NAME4 + " GROUP BY Date";*/
		
		List<String[]> list4 = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_NAME4, new String[] { "id", "Date",
				"Time", "Activity", "Systolic", "Diastolic" }, null, null,
				null, null, "id asc");

		int x = 0;
		if (cursor.moveToFirst()) {
			do {
				String[] b1 = new String[] { cursor.getString(0),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4),
						cursor.getString(5) };

				list4.add(b1);

				x = x + 1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		cursor.close();

		return list4;
	}
	
	//selecting pressure values for plotting
	public List<String[]> SelectPressureChartValue() {

String selectQuery = "SELECT AVG(systolic) as systolic, AVG(diastolic) as diastolic FROM " + TABLE_NAME4 + " GROUP BY Date";
		
		List<String[]> list4 = new ArrayList<String[]>();
		Cursor cursor = db.rawQuery(selectQuery, null);
		int x = 0;
		if (cursor.moveToFirst()) {
			do {
				String[] b1 = new String[] { cursor.getString(0),
						cursor.getString(1)};

				list4.add(b1);

				x = x + 1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		cursor.close();

		return list4;
	}

	// fetch id from newtable
	public int fetchId() {
		Cursor dbCursor;
		int pinN = 0;
		// try {
		dbCursor = db.query(true, TABLE_NAME, new String[] { "id", "Date",
				"Disease", "Medication", "Prescription" }, null, null, null,
				null, null, null);

		if ((dbCursor != null) && (dbCursor.getCount() > 0)) {
			dbCursor.moveToFirst();
			pinN = dbCursor.getInt(dbCursor.getColumnIndex("id"));
		}
		/*
		 * } catch (SQLiteException e) { pinN = null; }
		 */
		return pinN;
	}

	// select sugar level for editing

	public List<String[]> selectSugarLevel(int row) {

		List<String[]> list3 = new ArrayList<String[]>();
		Cursor cursor2 = db.query(TABLE_NAME3, new String[] { "id", "Date",
				"Time", "Activity", "Sugar_Level" }, ID + "=" + row, null,
				null, null, null);

		int x = 0;
		if (cursor2.moveToFirst()) {
			do {

				String[] b1 = new String[] { cursor2.getString(0),
						cursor2.getString(1), cursor2.getString(2),
						cursor2.getString(3), cursor2.getString(4) };

				list3.add(b1);

				x = x + 1;
			} while (cursor2.moveToNext());
		}
		if (cursor2 != null && !cursor2.isClosed()) {

			cursor2.close();
		}
		cursor2.close();

		return list3;
	}

	// select pressure values for editng
	public List<String[]> selectPressureValues(int rowNum) {

		List<String[]> list4 = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_NAME4, new String[] { "id", "Date",
				"Time", "Activity", "Systolic", "Diastolic" }, ID + "="
				+ rowNum, null, null, null, null);

		int x = 0;
		if (cursor.moveToFirst()) {
			do {
				String[] b1 = new String[] { cursor.getString(0),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4),
						cursor.getString(5) };

				list4.add(b1);

				x = x + 1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		cursor.close();

		return list4;
	}

	// select values from the new table fro edititng

	public List<String[]> selectFile(int row) {

		List<String[]> list = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_NAME, new String[] { "id", "Date",
				"Disease", "Medication", "Prescription" }, ID + "=" + row,
				null, null, null, null);

		int x = 0;
		if (cursor.moveToFirst()) {
			do {
				String[] b1 = new String[] { cursor.getString(0),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4) };

				list.add(b1);

				x++;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		cursor.close();

		return list;
	}

	// update new table
	public boolean updateFile(double rowID, String disease, String medication,
			String prescription, String duration, String startdate) {
		ContentValues args = new ContentValues();

		args.put(Disease, disease);
		args.put(Medication, medication);
		args.put(Prescription, prescription);
		args.put(Duration, duration);
		args.put(startDate, startdate);

		return DatabaseManipulator.db.update(TABLE_NAME, args,
				ID + "=" + rowID, null) > 0;
	}

	public double fetchSystolic() {
		double pinN3 = 0.0;
		// @SuppressWarnings("unused")
		Cursor dbCursor;
		dbCursor = db.rawQuery("select Systolic from pressure ORDER BY Date ",
				null);
		dbCursor.moveToFirst();
		while (dbCursor.isAfterLast() != true) {
			pinN3 = dbCursor.getDouble(dbCursor.getColumnIndex("Systolic"));
		}
		return pinN3;

	}

	// delete row from newtable.
	public boolean deleteItem(int x) {

		return db.delete(TABLE_NAME, ID + "=" + x, null) > 0;

	}

	// delete row from daibetes
	public boolean deleteItem3(int d) {

		return db.delete(TABLE_NAME3, ID + "=" + d, null) > 0;

	}

	// delete row from pressure
	public boolean deleteItem4(int p) {

		return db.delete(TABLE_NAME4, ID + "=" + p, null) > 0;

	}

	// delete allfrom newtable.
	public boolean deleteData() {

		return db.delete(TABLE_NAME, null, null) > 0;

	}

	// delete all from daibetes
	public boolean deleteData3() {

		return db.delete(TABLE_NAME3, null, null) > 0;

	}

	// delete all from pressure
	public boolean deleteData4() {

		return db.delete(TABLE_NAME4, null, null) > 0;

	}

	// delete newtable
	public void delete(int rowId) {
		db.delete(TABLE_NAME, null, null);

	}

	// delete login table
	public void delete1(int rowId) {
		db.delete(TABLE_NAME, null, null);

	}

	// delete diabetes table
	public void delete2(int rowId) {
		db.delete(TABLE_NAME, null, null);

	}

	// delete pressure table
	public void delete4(int rowId) {
		db.delete(TABLE_NAME, null, null);

	}

	private static class OpenHelper extends SQLiteOpenHelper {

		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			db.execSQL("CREATE TABLE "
					+ TABLE_NAME
					+ " (id INTEGER PRIMARY KEY, Date TEXT, Disease TEXT, Medication TEXT, Prescription TEXT, Duration TEXT, StartDate TEXT)");
			db.execSQL("CREATE TABLE " + TABLE_NAME2
					+ " (id INTEGER PRIMARY KEY, Pin TEXT)");
			db.execSQL("CREATE TABLE "
					+ TABLE_NAME3
					+ " (id INTEGER PRIMARY KEY, Date TEXT,Time TEXT, Activity TEXT, Sugar_Level TEXT)");
			db.execSQL("CREATE TABLE "
					+ TABLE_NAME4
					+ " (id INTEGER PRIMARY KEY,Date TEXT,Time TEXT, Activity TEXT, Systolic TEXT,Diastolic TEXT)");
			db.execSQL("CREATE TABLE "
					+ TABLE_NAME5
					+ " (id INTEGER PRIMARY KEY, pName TEXT, pUsername TEXT, docName TEXT, docContact TEXT, docAddress TEXT)");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME5);
			onCreate(db);
		}
	}

}

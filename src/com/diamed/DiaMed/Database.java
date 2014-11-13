package com.diamed.DiaMed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class Database{
	// list column_name variables in table Login
	//private static final String L_ID= "LoginId";
	private static final String PIN ="pin";
	private static final String KEY_ROWID ="LoginId";
	// list column_name variables in table Records
	
	private static final String R_ID= "RecordId";
	
	private static final String Cat= "Category";
	
	private static final String BG = "BG";
	
	
	
	
	// table login
	
	private static final String DATABASE_NAME= "Login";
	private static final String TABLE = "Records";
	
	private Context context;
	private SQLiteDatabase dbase;
	private DbHelp dbHelper;
	
	
	// constructor for Database
	public Database(Context context){
		this.context= context;
		
	}
	public Database open() throws SQLiteException{
		
		dbHelper = new DbHelp(context);
		dbase = dbHelper.getWritableDatabase();
		//dbHelper.close();
		return this;

	}
	public void close(){
		dbHelper.close();
		
	}
	

	
  public long addPIN(String pin){
		
		ContentValues setUpValues = new ContentValues();
		setUpValues.put(PIN, pin);

		return dbase.insert(DATABASE_NAME, null, setUpValues);
	}
	
	public boolean updateItem( String pin){
		
		ContentValues updateValues = createContentValues( pin);
		return dbase.update(DATABASE_NAME, updateValues,PIN +"='"+pin+"'",null)> 0;
	}
	
	public String fetchPin(){
		Cursor dbCursor;
		String pinN=null;
		//try {
			dbCursor = dbase.query(true, DATABASE_NAME, new String[]{KEY_ROWID,PIN},null,null,null,null,null,null);
			
			
			if((dbCursor!=null)&&(dbCursor.getCount()>0)){
				dbCursor.moveToFirst();
				pinN = dbCursor.getString(dbCursor.getColumnIndex(PIN));
			}
			dbCursor.close();
		/*} catch (SQLiteException e) {
			pinN = null;
		}*/
		return pinN;
	}
	public boolean delete(){
		 
	 	return dbase.delete(DATABASE_NAME, null, null)>0;
	 
	 }
	
	
	
	public ContentValues createContentValues(String pin2){
		
		ContentValues values =new ContentValues();
		values.put(PIN,pin2);
		return values;
	}
	
 public long addData (String cat, float glucose , float weight , int DateOfTest, String time , String meds){
		
		ContentValues setValues = new ContentValues();
		setValues.put(Cat,cat);
		setValues.put(BG,glucose);
		
		
			return dbase.insertOrThrow(TABLE, null, setValues);
		
	}
 
 
 public ContentValues contentValues(String category, float glucose, float weight,int dateOfTest, String time, String meds){
		
		ContentValues values =new ContentValues();
		values.put(Cat,category);
		values.put(BG,glucose);
		
		
		return values;
	}
	
 
 
 
 public String fetchAllitem(){
		Cursor dbCursor;
		String allData = "";
		try {
			dbCursor = dbase.query(TABLE,new String[]{R_ID , Cat, BG},null, null, null, null, null);
			
			int recordsId=dbCursor.getColumnIndex(R_ID);
		     int category = dbCursor.getColumnIndex(Cat);
			 int glucose =dbCursor.getColumnIndex(BG);
			 
			 
			if((dbCursor!=null)&&(dbCursor.getCount()>0)){
			for(dbCursor.moveToFirst();!dbCursor.isAfterLast();dbCursor.moveToNext()){
				
			allData = allData+ ""+ dbCursor.getString(recordsId)+"/t/t"+ dbCursor.getString(category)+"/t/t"+dbCursor.getString(glucose);
				
			}
			} }catch (SQLiteException e) {
				allData= "";
		}
		return allData;
	}
 
 
	public class DbHelp extends SQLiteOpenHelper{
		
		
		
		
		//NAME OF DATABASE
		public final static String DATABASE_NAME = "DiaLog";
		// DATABASE VERSION
		public final static int DATABASE_VERSION = 1;
		// Creating Statement for table
		//public final String DATABASE_CREATE = "CREATE TABLE Login(LoginId integer AUTO_INCREMENT NOT NULL,pin varchar(5) PRIMARY KEY NOT NULL); ";
		
		
		public DbHelp(Context context) {
			super(context,DATABASE_NAME,null,DATABASE_VERSION);
			
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase dbase) {
			// TODO Auto-generated method stub
			
			dbase.execSQL("CREATE TABLE Login(LoginId integer AUTO_INCREMENT," +
					"pin varchar(15) PRIMARY KEY);");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int newV, int oldV) {
			db.execSQL("DROP TABLE IF EXISTS Login");
			
			onCreate(db);
			
		}

		
		@Override
		public synchronized SQLiteDatabase getReadableDatabase() {
			// TODO Auto-generated method stub
			return super.getReadableDatabase();
		}
		
		
		
		
	}
}
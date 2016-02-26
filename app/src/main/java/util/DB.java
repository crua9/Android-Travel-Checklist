package util;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
	private static String DBName = "TaskList";
	private static int DBVersion = 2;
	private static Context context;

	public static final String ID = "id";
	public static final String created = "created";
	private static final String TIMEZONE = "localtime";
	public static final String isSync = "issync";

	// ------------------------TASKS-------------
	public static final String TABLE_Tasks = "Tasks";
	public static final String Tasks_Name = "name";
	public static final String Tasks_place = "place";

	String CREATE_Tasks = "CREATE TABLE IF NOT EXISTS " + TABLE_Tasks + " ( "
			+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + Tasks_Name
			+ " VARCHARE(50) , " + Tasks_place + " VARCHARE(50) , " + isSync
			+ " INTEGER , " + created + " DATETIME DEFAULT (DATETIME('now','"
			+ TIMEZONE + "')) )";
	// ---------------------------END------------------

	// -----------------------PERSON--------------------
	public static final String TABLE_Person = "Person";
	public static final String Person_Name = "PersonName";
	public static final String Person_TaskID = "PersonTask";

	String CREATE_Person = "CREATE TABLE IF NOT EXISTS " + TABLE_Person + " ( "
			+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + Person_Name
			+ " VARCHARE(50) , " + isSync + " INTEGER , " + Person_TaskID
			+ " INTEGER , " + created + " DATETIME DEFAULT (DATETIME('now','"
			+ TIMEZONE + "')) )";

	// ---------------------END---------------------

	// ------------------------Perons-TASKS-------------
	public static final String TABLE_PersonTask = "PersonTask";
	public static final String PersonTask_TaskID = "taskID";
	public static final String PersonTask_PersonID = "personID";
	public static final String PersonTask_Detail = "taskDetail";

	String CREATE_PersonTask = "CREATE TABLE IF NOT EXISTS " + TABLE_PersonTask
			+ " ( " + ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
			+ PersonTask_TaskID + " INTEGER , " + PersonTask_PersonID
			+ " INTEGER , " + PersonTask_Detail + " VARCHARE(50) , " + isSync
			+ " INTEGER , " + created + " DATETIME DEFAULT (DATETIME('now','"
			+ TIMEZONE + "')) )";

	// ---------------------------END------------------

	// 1 , fishing;
	// 2 , swiming;
	// -------------------------Category-------------
	public static final String TABLE_Category = "Category";
	public static final String Category_Name = "name";

	String CREATE_Category = "CREATE TABLE IF NOT EXISTS " + TABLE_Category
			+ " ( " + ID + " INTEGER PRIMARY KEY  , " + Category_Name
			+ " VARCHARE(50) , " + isSync + " INTEGER , " + created
			+ " DATETIME DEFAULT (DATETIME('now','" + TIMEZONE + "')) )";

	// ---------------------------END------------------

	// 1,1,fishing rod
	// 2,1,fish food.
	// -------------------------CategoryITEMS-------------
	public static final String TABLE_CategoryITEMs = "CategoryItems";
	public static final String CategoryItems_Name = "name";
	public static final String CategoryItems_ID = "itemID";

	String CREATE_CategoryItems = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_CategoryITEMs + " ( " + ID + " INTEGER PRIMARY KEY  , "
			+ CategoryItems_Name + " VARCHARE(50) , " + CategoryItems_ID
			+ " INTEGER , " + isSync + " INTEGER , " + created
			+ " DATETIME DEFAULT (DATETIME('now','" + TIMEZONE + "')) )";

	// ---------------------------END------------------

	public DB(Context _context) {
		super(_context, DBName, null, DBVersion);
		context = _context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_Category);
		db.execSQL(CREATE_CategoryItems);
		db.execSQL(CREATE_Person);
		db.execSQL(CREATE_PersonTask);
		db.execSQL(CREATE_Tasks);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Tasks);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Category);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CategoryITEMs);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_Person);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PersonTask);

	}

	public int RAW(int CID) {
		SQLiteDatabase db = this.getWritableDatabase();
		String Query = "SELECT * FROM " + TABLE_Tasks + " WHERE " + ID + " = "
				+ CID;
		Cursor c = db.rawQuery(Query, null);
		if (c != null)
			c.moveToFirst();
		String unit = "";
		unit = c.getString(c.getColumnIndex(Tasks_Name));

		return Integer.valueOf(unit);
	}

	public ArrayList<Tasks> getTask() {

		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<Tasks> myList;
		myList = new ArrayList<Tasks>();
		Tasks myTasks;

		String Query = "SELECT * FROM " + TABLE_Tasks;
		Cursor c = db.rawQuery(Query, null);
		if (c != null) {
			if (c.moveToFirst()) {
				while (!c.isAfterLast()) {
					myTasks = new Tasks();
					myTasks.setId(c.getInt(c.getColumnIndex(ID)));
					myTasks.setName(c.getString(c.getColumnIndex(Tasks_Name)));
					myList.add(myTasks);
					c.moveToNext();
				}
			}
		}
		return myList;
	}

	public ArrayList<Category> getCategory() {

		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<Category> myList;
		myList = new ArrayList<Category>();
		Category myCategory;

		String Query = "SELECT * FROM " + TABLE_Category;
		Cursor c = db.rawQuery(Query, null);
		if (c != null) {
			if (c.moveToFirst()) {
				while (!c.isAfterLast()) {
					myCategory = new Category();
					myCategory.setId(c.getInt(c.getColumnIndex(ID)));
					myCategory.setName(c.getString(c
							.getColumnIndex(Category_Name)));
					myList.add(myCategory);
					c.moveToNext();
				}
			}
		}
		return myList;
	}

	public ArrayList<Persons> getPersons(int TaskID) {

		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<Persons> myList;
		myList = new ArrayList<Persons>();
		Persons myPersons;

		String Query = "SELECT * FROM " + TABLE_Person + " WHERE "
				+ Person_TaskID + " = " + TaskID;
		Cursor c = db.rawQuery(Query, null);
		if (c != null) {
			if (c.moveToFirst()) {
				while (!c.isAfterLast()) {
					myPersons = new Persons();
					myPersons.setId(c.getInt(c.getColumnIndex(ID)));
					myPersons.setPersonName(c.getString(c
							.getColumnIndex(Person_Name)));
					myList.add(myPersons);
					c.moveToNext();
				}
			}
		}
		return myList;
	}

	public ArrayList<PersonTasks> getPersonsTask(int TaskID, int PID) {

		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<PersonTasks> myList;
		myList = new ArrayList<PersonTasks>();
		PersonTasks myPersonTasks;

		String Query = "SELECT * FROM " + TABLE_PersonTask + " WHERE "
				+ PersonTask_TaskID + " = " + TaskID + " AND "
				+ PersonTask_PersonID + " = " + PID;
		Cursor c = db.rawQuery(Query, null);
		if (c != null) {
			if (c.moveToFirst()) {
				while (!c.isAfterLast()) {
					myPersonTasks = new PersonTasks();
					myPersonTasks.setId(c.getInt(c.getColumnIndex(ID)));
					myPersonTasks.setTaskDetail(c.getString(c
							.getColumnIndex(PersonTask_Detail)));
					myList.add(myPersonTasks);
					c.moveToNext();
				}
			}
		}
		return myList;
	}

	public ArrayList<String> getCategoryItems(int _categoryID) {

		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<String> myList;
		myList = new ArrayList<String>();
		String Query = "SELECT * FROM " + TABLE_CategoryITEMs + " WHERE "
				+ CategoryItems_ID + " = " + _categoryID;
		Cursor c = db.rawQuery(Query, null);
		if (c != null) {
			if (c.moveToFirst()) {
				while (!c.isAfterLast()) {
					myList.add(c.getString(c.getColumnIndex(CategoryItems_Name)));
					c.moveToNext();
				}
			}
		}
		return myList;
	}

	public int UPDATE(int CID, String _TaskName) {
		int response = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Tasks_Name, _TaskName);
		long id = db.update(TABLE_Tasks, values, ID + " = " + CID, null);
		if (id > 0) {
			response = (int) id;
		}
		return response;
	}

	public int INSERT_Task(String _TaskName, String _TaskPlace) {
		int response = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(Tasks_Name, _TaskName);
		values.put(Tasks_place, _TaskPlace);

		long id = db.insert(TABLE_Tasks, null, values);
		if (id > 0) {
			response = (int) id;
		}
		return response;
	}

	public int INSERT_People(int _TID, String _Name) {
		int response = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(Person_Name, _Name);
		values.put(Person_TaskID, _TID);

		long id = db.insert(TABLE_Person, null, values);
		if (id > 0) {
			response = (int) id;
		}
		return response;
	}

	public Boolean INSERT_PersonTask(int _TID, int _PID, ArrayList<String> _task) {
		int response = 0;
		boolean result = false;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		long insertId = 0;
		for (int i = 0; i < _task.size(); i++) {
			if (insertId != -1) {
				values = new ContentValues();
				values.put(PersonTask_TaskID, _TID);
				values.put(PersonTask_PersonID, _PID);
				values.put(PersonTask_Detail, _task.get(i).toString());
				insertId = db.insert(TABLE_PersonTask, null, values);
			} else {
				break;
			}
		}

		if (insertId > 1) {
			result = true;
		}

		return result;
	}

	public Boolean INSERT_PersonTask(int _TID, int _PID, String _task) {

		boolean result = false;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		long insertId = 0;

		values = new ContentValues();
		values.put(PersonTask_TaskID, _TID);
		values.put(PersonTask_PersonID, _PID);
		values.put(PersonTask_Detail, _task);
		insertId = db.insert(TABLE_PersonTask, null, values);

		if (insertId > 1) {
			result = true;
		}

		return result;
	}

	public int INSERT(int CID, String _TaskName) {
		int response = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ID, CID);
		values.put(Tasks_Name, _TaskName);

		long id = db.insert(TABLE_Tasks, null, values);
		if (id > 0) {
			response = (int) id;
		}
		return response;
	}

	public void DeleteTask(int TID) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_Tasks, ID + " = " + TID, null);

	}

	
	public void DeletePersonTaskItem(int TID) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_PersonTask, ID + " = " + TID, null);

	}
	// c.getString(c.getColumnIndex(Tasks_Name));

	public boolean ifRecordExist(int _idx) {
		if (_idx > 0) {
			String Query = "SELECT * FROM " + TABLE_Tasks + " WHERE " + ID
					+ " = " + _idx;
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor c = db.rawQuery(Query, null);

			if (c.getCount() > 0) {
				return true;
			}
		}
		return false;
	}

}

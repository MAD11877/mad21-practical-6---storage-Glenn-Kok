package sg.edu.np.madpractical;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class UserDBHandler extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "userDB.db";
    private static final int DATABASE_VERSION = 1;
    public static final String USER_TABLE = "User";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_FOLLOW = "Follow";

    public UserDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "+USER_TABLE+
                " ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME+" TEXT, "+
                COLUMN_DESCRIPTION+" TEXT, "+
                COLUMN_FOLLOW+" INTEGER DEFAULT 0)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USER_TABLE);
        onCreate(db);
    }

    public void addUser(User u){
        ContentValues values = new ContentValues();
//        values.put(COLUMN_ID, u.getId());
        values.put(COLUMN_NAME, u.getName());
        values.put(COLUMN_DESCRIPTION, u.getDescription());
        values.put(COLUMN_FOLLOW, u.isFollow());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USER_TABLE,null, values);
        db.close();
    }

    public ArrayList<User> getUsers(){
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM "+USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            int id = Integer.parseInt(cursor.getString(0));
            String name = cursor.getString(1);
            Log.v("db", name);

            String des = cursor.getString(2);
            boolean follow = cursor.getInt(3)==1;
            User user = new User(id, name, des, follow);
            userList.add(user);
        }
        cursor.close();
        db.close();
        return userList;
    }

    public void dropTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+USER_TABLE);
    }

    public User findUser(int id){
        String query = "SELECT * FROM "+USER_TABLE+" WHERE "+COLUMN_ID+" = "+"\""+id+"\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user;
        if (cursor.moveToFirst()){
            user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)==1);
        }
        else{
            user = null;
        }
        cursor.close();
        db.close();
        return user;
    }

    public void updateUser(User u){
        ContentValues values = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        values.put(COLUMN_ID, u.getId());
        values.put(COLUMN_NAME, u.getName());
        values.put(COLUMN_DESCRIPTION, u.getDescription());
        values.put(COLUMN_FOLLOW, u.isFollow());
        db.update(USER_TABLE, values, COLUMN_ID+" =?", new String[] {String.valueOf(u.getId())});
    }

}
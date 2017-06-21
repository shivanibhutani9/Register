package project.register.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import project.register.data.DataContract.dataEntry;

/**
 * Created by shivani on 21/6/17.
 */

public class dataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "data.db";

    private static final int VERSION = 1;

    public dataHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_TABLE = "CREATE TABLE "  + dataEntry.TABLE_NAME + " (" +
                dataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                dataEntry.COLUMN_FNAME + " TEXT NOT NULL, " +
                dataEntry.COLUMN_LNAME    + " TEXT NOT NULL, " +
                dataEntry.COLUMN_AGE + " TEXT NOT NULL, " +
                dataEntry.COLUMN_DOB+" TEXT NOT NULL, "+ dataEntry.COLUMN_EMAIL+ " TEXT NOT NULL);" ;

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dataEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}

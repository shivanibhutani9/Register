package project.register;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import project.register.data.DataContract.*;
import project.register.data.dataHelper;

public class MainActivity extends AppCompatActivity {
    private TextView error;
    private Button Register;
    private EditText firstName,lastName,age,dateofbirth,email_id;
   private static SQLiteDatabase mydb;
    private String fName,lName,mail,Age,dob;
    private dataHelper mydbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Register=(Button)findViewById(R.id.button);
        firstName=(EditText)findViewById(R.id.fName);
        lastName=(EditText)findViewById(R.id.lName);
        age=(EditText)findViewById(R.id.age);
        dateofbirth=(EditText)findViewById(R.id.dateofbirth);
        email_id=(EditText)findViewById(R.id.mail_id);
        error=(TextView)findViewById(R.id.error);


        mydbhelper =new dataHelper(this);
        mydb=mydbhelper.getWritableDatabase();


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b;

                b=validate();
                if(b==true) {
                    error.setVisibility(View.INVISIBLE);
                    writeData();
                    Toast.makeText(getBaseContext(),"Registered!",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    boolean validate()
    {   fName=firstName.getText().toString();
        lName=lastName.getText().toString();
        Age=age.getText().toString();
        dob=dateofbirth.getText().toString();
        mail=email_id.getText().toString();
        if(fName.isEmpty()||lName.isEmpty()||Age.isEmpty()||dob.isEmpty()||mail.isEmpty())
       {    error.setText(R.string.blankField);
           error.setVisibility(View.VISIBLE);
           return false;
       }
       if(!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
           error.setVisibility(View.VISIBLE);

           error.setText(R.string.invalidMail);

            return false;
       }
       return true;
    }
    boolean writeData()
    {
        ContentValues cv=new ContentValues();

        cv.put(dataEntry.COLUMN_FNAME,fName);
        cv.put(dataEntry.COLUMN_LNAME,lName);
        cv.put(dataEntry.COLUMN_AGE,Age);
        cv.put(dataEntry.COLUMN_DOB,dob);
        cv.put(dataEntry.COLUMN_EMAIL,mail);
        return mydb.insert(dataEntry.TABLE_NAME,null,cv)>0;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reg:
                Toast.makeText(this,"Registered Information",Toast.LENGTH_SHORT).show();
                display();
                return true;
            case R.id.regNew:
                firstName.setText(null);
                email_id.setText(null);
                dateofbirth.setText(null);
                age.setText(null);
                lastName.setText(null);
                error.setVisibility(View.INVISIBLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    static Cursor getAllData() {

        return mydb.query(
                dataEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    void display()
    {
        Cursor cursor=getAllData();
        String data =null;

        StringBuilder b=new StringBuilder("");
        if (cursor.moveToFirst()) {
            do {
                // get the data into array, or class variable
                b.append("Name  "+ cursor.getString(cursor.getColumnIndex(dataEntry.COLUMN_FNAME))+" "
                        + cursor.getString(cursor.getColumnIndex(dataEntry.COLUMN_LNAME))+"\nAge  "
                +cursor.getString(cursor.getColumnIndex(dataEntry.COLUMN_AGE))+"\nDate Of Birth  "
                +cursor.getString(cursor.getColumnIndex(dataEntry.COLUMN_DOB))+"\nE-mail  "
                +cursor.getString(cursor.getColumnIndex(dataEntry.COLUMN_EMAIL))+"\n\n");

            } while (cursor.moveToNext());
        }
        cursor.close();
        data=b.toString();
        //firstName.setText(data);
        Intent i=new Intent();
        i.putExtra("data",data);
        i.setClass(this,Display.class);
        startActivity(i);
    }
}

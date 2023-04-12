package com.example.loginpagewithsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText user_first_name, user_last_name;
    private Button signUpBtn;
    private TextView userCnt;

    DBHelper dbhelper  = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_first_name = findViewById(R.id.userFirstName);
        user_last_name = findViewById(R.id.LastName);
        userCnt = findViewById(R.id.usrCnt);
        signUpBtn = findViewById(R.id.signUpBtn);
        userCnt.setText(readUserCnt());

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_first_name.getText() != null && user_last_name.getText() !=null){
                    addTableEntry(user_first_name.getText().toString(),user_last_name.getText().toString());
                    userCnt.setText(readUserCnt());
                }
            }
        });




    }

    private void addTableEntry(String firstname, String lastname){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(DataBaseSchema.TableDB.COLUMN_FIRSTNAME,firstname);
        values.put(DataBaseSchema.TableDB.COLUMN_LASTNAME,lastname);

        // Check if the entry already exists in the database
        String selection = DataBaseSchema.TableDB.COLUMN_FIRSTNAME + "=? AND " + DataBaseSchema.TableDB.COLUMN_LASTNAME + "=?";
        String[] selectionArgs = { firstname, lastname };
        Cursor cursor = db.query(
                DataBaseSchema.TableDB.TABLE_NAME, null, selection, selectionArgs, null, null, null);

        // If the entry doesn't exist, insert it
        if(cursor.getCount() == 0) {
            long rowId = db.insert(DataBaseSchema.TableDB.TABLE_NAME,null,values);
            Log.i("ID", String.valueOf(rowId));
        }
        cursor.close();
    }

    private String readUserCnt(){

        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String countQuery = "SELECT  * FROM " + DataBaseSchema.TableDB.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return "user count : " + count;
    }





}
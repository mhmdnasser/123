package com.example.nasser.testlist;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText txt_note;
    TextView view_note;
    Button btn2;
    CheckBox btn1;
    Button btn_note;
    DatabaseHandler db;
    public String[] show = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        CustomeAdaptor customeAdaptor = new CustomeAdaptor();
        btn_note = (Button) findViewById(R.id.btn_note);
        btn2 = (Button) findViewById(R.id.btn2);
        listView.setAdapter(customeAdaptor);
        db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting ..");

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });





    }

    public void SendNote(View v) {
        txt_note = (EditText) findViewById(R.id.txt_note);
        view_note = (TextView) findViewById(R.id.view_note);
        btn1 = (CheckBox) findViewById(R.id.btn1);
       // btn2 = (Button) findViewById(R.id.btn2);
        Toast.makeText(getApplicationContext(), "note is taken ,  choose any checkbox"
                , Toast.LENGTH_LONG).show();
        btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String str = txt_note.getText().toString();
                view_note.setText(str);
               // btn2.setText("saved data");

            }
        });


    }
    public class CustomeAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return show.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.customlayout, null);
            TextView textView = (TextView) view.findViewById(R.id.view_note);
            CheckBox btn1 = (CheckBox) view.findViewById(R.id.btn1);
            Button btn2 = (Button) view.findViewById(R.id.btn2);


            return view;
        }

    }
    public void AddData(String newEntry) {

        boolean insertData = db.addData(newEntry);

        if(insertData==true){
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_LONG).show();
        }
    }

}


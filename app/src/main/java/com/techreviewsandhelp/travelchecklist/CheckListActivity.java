package com.techreviewsandhelp.travelchecklist;

/**
 * Created by crua9 on 2/26/2016.
 */
import java.util.ArrayList;

import util.AdapterAddPerson;
import util.DB;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class CheckListActivity extends Activity {
    String[] whereTOgo = { " Please Select Option", " Business Tour", " Beach",
            " Hicking", " Fishing", " Long Drive", " Deep Sea Dive" };
    Spinner mySpinner;
    Button myImageView, addPerson;
    EditText ET_personName, ET_TripName;
    ListView LV_personName;
    AdapterAddPerson adapter;
    ArrayList<String> person;
    DB db;
    int responce;
    Intent next;
    int OptionSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#f9833f")));

        mySpinner = (Spinner) findViewById(R.id.spinner1);
        mySpinner.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, whereTOgo));
        myImageView = (Button) findViewById(R.id.IV_GoBTN);
        ET_TripName = (EditText) findViewById(R.id.ET_TripName);
        ET_personName = (EditText) findViewById(R.id.ET_personName);
        addPerson = (Button) findViewById(R.id.BTN_addPerson);
        person = new ArrayList<String>();
        LV_personName = (ListView) findViewById(R.id.LV_personList);
        adapter = new AdapterAddPerson(CheckListActivity.this,
                R.layout.item_person_names, person);
        LV_personName.setAdapter(adapter);
        myImageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (ET_TripName.getText().toString().length() > 0) {
                    if (person.size() > 0) {
                        db = new DB(CheckListActivity.this);
                        responce = db.INSERT_Task(ET_TripName.getText()
                                .toString(), mySpinner.getSelectedItem()
                                .toString());
                        if (responce > 0) {
                            for (String myitem : person) {
                                db.INSERT_People(responce, myitem.toString());
                            }

                            next = new Intent(CheckListActivity.this,
                                    ResultCheckListActivity.class);
                            next.putExtra("ItemID", OptionSelection);
                            next.putExtra("TID", responce);
                            startActivity(next);
                            finish();

                        }
                    }
                    {
                        ET_personName.setError("Add Person ");
                    }
                } else {
                    ET_TripName.setError("Please Enter Trip Name");
                }
            }
        });

        addPerson.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (ET_personName.getText().toString().length() > 0) {
                    AddPerson();
                    ET_personName.setText("");
                } else {
                    ET_personName.setError("Enter Valid name");
                }
            }
        });

        mySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                OptionSelection = arg2;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                OptionSelection = 0;
            }
        });

    }

    public void AddPerson() {

        person.add(ET_personName.getText().toString());

        adapter.notifyDataSetChanged();
    }

}

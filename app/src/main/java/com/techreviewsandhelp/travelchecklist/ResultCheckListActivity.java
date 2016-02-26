package com.techreviewsandhelp.travelchecklist;

/**
 * Created by crua9 on 2/26/2016.
 */
import java.util.ArrayList;

import util.AdapterCheckList;
import util.DB;
import util.Persons;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class ResultCheckListActivity extends Activity {
    ListView myListView;
    EditText addNewItem, addItenQt;
    Button addNewListItem, cancel, finish;
    ImageView IV_AddFromList;
    ArrayList<String> myItemList;
    AdapterCheckList adapter;
    int TID;
    ArrayList<Persons> person;
    DB db;
    int OptionSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_result);
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#f9833f")));
        myListView = (ListView) findViewById(R.id.listView1);
        addNewItem = (EditText) findViewById(R.id.editText1);
        addNewListItem = (Button) findViewById(R.id.button1);
        finish = (Button) findViewById(R.id.BTN_finish);
        IV_AddFromList = (ImageView) findViewById(R.id.IV_AddFromList);
        Intent myIntent = getIntent();
        OptionSelection = myIntent.getIntExtra("ItemID", 0);
        TID = myIntent.getIntExtra("TID", 0);
        person = new ArrayList<Persons>();
        db = new DB(ResultCheckListActivity.this);
        person = db.getPersons(TID);

        InitITems();
        adapter = new AdapterCheckList(ResultCheckListActivity.this,
                R.layout.item_resultlist, myItemList);
        myListView.setAdapter(adapter);

        addNewListItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (addNewItem.getText().toString().length() > 0) {
                    AddItem();
                } else {
                    addNewItem.setError("Please Enter Something!");
                }
            }

        });
        IV_AddFromList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent Next = new Intent(ResultCheckListActivity.this,
                        CategoryListItems_Activity.class);
                startActivityForResult(Next, 11);

            }
        });

        finish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Boolean resultBol = false;
                for (Persons myperson : person) {
                    resultBol = db.INSERT_PersonTask(TID, myperson.getId(),
                            myItemList);
                }
                if (resultBol) {
                    Toast.makeText(ResultCheckListActivity.this,
                            "Task List Completed", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ResultCheckListActivity.this,
                            "Some Error occor Please Try Again!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 11) {
            String myStringData = data.getStringExtra("Item");
            myItemList.add(myStringData);
            adapter.notifyDataSetChanged();
        }
    }

    public void InitITems() {

        if (OptionSelection == 0) {
            myItemList = new ArrayList<String>();
        }

        if (OptionSelection == 1) {
            // Business Tour
            myItemList = new ArrayList<String>();
            myItemList.add(" Sleepwear");
            myItemList.add(" Street clothes");
            myItemList.add(" Bathroom items");
            myItemList.add(" Documents");
        }

        if (OptionSelection == 2) {
            // Beach
            myItemList = new ArrayList<String>();
            myItemList.add(" Beach Towel");
            myItemList.add(" Swimming gear");
            myItemList.add(" Sunblock");
            myItemList.add(" Water bottles");
            myItemList.add(" Fishing Rods");
        }
        if (OptionSelection == 3) {
            // Hiking
            myItemList = new ArrayList<String>();
            myItemList.add(" Hiking shoes");
            myItemList.add(" Water bottles");
            myItemList.add(" Fully charged phone");
            myItemList.add(" Batteries for the phone");

        }
        if (OptionSelection == 4) {
            // Fishing
            myItemList = new ArrayList<String>();
            myItemList.add(" Fishing gear");
            myItemList.add(" Drinking water");
            myItemList.add(" Food");

        }
        if (OptionSelection == 5) {
            // Long Drive
            myItemList = new ArrayList<String>();
            myItemList.add(" Drinking water");
            myItemList.add(" Fully charged phone");
            myItemList.add(" Wallet with your ID");

        }
        if (OptionSelection == 6) {
            // Deep Sea Diving
            myItemList = new ArrayList<String>();
            myItemList.add(" Diving gear");
        }

    }

    public void AddItem() {

        myItemList.add(addNewItem.getText().toString());

        adapter.notifyDataSetChanged();
        addNewItem.setText("");
    }
}

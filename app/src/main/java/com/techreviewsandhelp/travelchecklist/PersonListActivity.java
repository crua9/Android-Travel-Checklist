package com.techreviewsandhelp.travelchecklist;

/**
 * Created by crua9 on 2/26/2016.
 */
import java.util.ArrayList;

import util.AdapterAddPerson;
import util.AdapterPerson;
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
import android.widget.AdapterView;
import android.widget.ShareActionProvider;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class PersonListActivity extends Activity {

    ListView LV_personName;
    AdapterPerson adapter;
    ArrayList<Persons> person;
    DB db;
    int getID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_task_list);
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#f9833f")));

        Intent getData = getIntent();
        getID = getData.getIntExtra("ID", 0);
        LV_personName = (ListView) findViewById(R.id.LV_PTList);
        person = new ArrayList<Persons>();
        db = new DB(PersonListActivity.this);
        person = db.getPersons(getID);
        adapter = new AdapterPerson(PersonListActivity.this,
                R.layout.item_person_names, person);
        LV_personName.setAdapter(adapter);

        LV_personName.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent next = new Intent(PersonListActivity.this,
                        IndividualPersonListActivity.class);
                next.putExtra("TID", getID);
                next.putExtra("PID", person.get(arg2).getId());
                startActivity(next);
            }
        });
    }

}

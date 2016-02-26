package com.techreviewsandhelp.travelchecklist;

import java.util.ArrayList;

import util.AdapterHome;
import util.HomeItem;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {
    GridView myGridView;
    ArrayList<HomeItem> itemsName;
    HomeItem myItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#f9833f")));

        myGridView = (GridView) findViewById(R.id.gridView1);

        itemsName = new ArrayList<HomeItem>();
        myItem = new HomeItem();
        myItem.setTitle("History");
        myItem.setIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.history));
        itemsName.add(myItem);

        myItem = new HomeItem();
        myItem.setTitle("Create List");
        myItem.setIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.create));
        itemsName.add(myItem);

        AdapterHome adapter = new AdapterHome(getApplicationContext(),
                itemsName);
        myGridView.setAdapter(adapter);

        myGridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                if (arg2 == 0) {
                    Toast.makeText(MainActivity.this, "View History",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,
                            HistoryActivity.class));
                }
                if (arg2 == 1) {

                    Toast.makeText(MainActivity.this, "Create Check List",
                            Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,
                            CheckListActivity.class));

                }

            }
        });

    }

}


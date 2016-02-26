package com.techreviewsandhelp.travelchecklist;

/**
 * Created by crua9 on 2/26/2016.
 */
import java.util.ArrayList;

import util.AdapterTastHistory;
import util.DB;
import util.Tasks;
import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class HistoryActivity extends Activity {
    ListView LV_HistoryList;
    DB db;
    AdapterTastHistory adapter;
    ArrayList<Tasks> mylist, mySlist;
    int IserachOpen = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#f9833f")));
        LV_HistoryList = (ListView) findViewById(R.id.LV_HistoryList);
        PopulateList();

        LV_HistoryList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent next = new Intent(HistoryActivity.this,
                        PersonListActivity.class);
                if (IserachOpen == 0) {
                    next.putExtra("ID", mylist.get(arg2).getId());
                }
                if (IserachOpen == 1) {
                    next.putExtra("ID", mySlist.get(arg2).getId());
                }
                startActivity(next);
            }
        });

    }

    private void PopulateList() {
        mylist = new ArrayList<Tasks>();
        db = new DB(HistoryActivity.this);
        mylist.addAll(db.getTask());
        adapter = new AdapterTastHistory(HistoryActivity.this,
                R.layout.item_task_history, mylist);
        LV_HistoryList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history, menu);
        // Associate searchable configuration with the SearchView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

            SearchView search = (SearchView) menu.findItem(R.id.search)
                    .getActionView();

            search.setSearchableInfo(manager
                    .getSearchableInfo(getComponentName()));

            search.setOnCloseListener(new OnCloseListener() {

                @Override
                public boolean onClose() {
                    // TODO Auto-generated method stub
                    IserachOpen = 0;
                    adapter = new AdapterTastHistory(HistoryActivity.this,
                            R.layout.item_task_history, mylist);
                    LV_HistoryList.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    return false;
                }
            });

            search.setOnQueryTextListener(new OnQueryTextListener() {

                @Override
                public boolean onQueryTextChange(String query) {

                    // Text filter here..
                    serachFilter(query);
                    adapter.notifyDataSetChanged();
                    return true;

                }

                @Override
                public boolean onQueryTextSubmit(String arg0) {
                    // TODO Auto-generated method stub
                    return false;
                }

            });

        }
        return true;
    }

    private void serachFilter(String input) {
        IserachOpen = 1;
        mySlist = new ArrayList<Tasks>();
        for (Tasks myTasks : mylist) {
            String checkMessage = "";
            checkMessage = myTasks.getName().toLowerCase();
            if (checkMessage != null && checkMessage.length() > 0) {
                if (checkMessage.contains(input.toLowerCase())) {
                    mySlist.add(myTasks);
                }
            }
        }
        adapter = new AdapterTastHistory(HistoryActivity.this,
                R.layout.item_task_history, mySlist);
        LV_HistoryList.setAdapter(adapter);

    }
}

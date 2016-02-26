package com.techreviewsandhelp.travelchecklist;

/**
 * Created by crua9 on 2/26/2016.
 */
import java.util.ArrayList;

import util.AdapterCheckList;
import util.AdapterPersonTaskList;
import util.DB;
import util.PersonTasks;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ShareActionProvider;

public class IndividualPersonListActivity extends Activity {
    AdapterPersonTaskList adapter;
    ListView myListView;
    ArrayList<PersonTasks> myItemList;
    DB db;
    Button addNewListItem;
    EditText addNewItem;
    ImageView IV_AddFromList;
    ShareActionProvider mShareActionProvider;
    int PersonID, TaskID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_person_list);
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#f9833f")));
        addNewListItem = (Button) findViewById(R.id.button1);
        addNewItem = (EditText) findViewById(R.id.editText1);
        IV_AddFromList = (ImageView) findViewById(R.id.IV_AddFromList);
        myListView = (ListView) findViewById(R.id.listView1);
        myItemList = new ArrayList<PersonTasks>();
        db = new DB(IndividualPersonListActivity.this);

        Intent dataIntent = getIntent();
        PersonID = dataIntent.getIntExtra("PID", 0);
        TaskID = dataIntent.getIntExtra("TID", 0);
        AddItem();
        addNewListItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (addNewItem.getText().toString().length() > 0) {
                    // add into database here...
                    db.INSERT_PersonTask(TaskID, PersonID, addNewItem.getText()
                            .toString());
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
                Intent Next = new Intent(IndividualPersonListActivity.this,
                        CategoryListItems_Activity.class);
                startActivityForResult(Next, 11);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 11) {
            String myStringData = data.getStringExtra("Item");
            // myItemList.add(myStringData);
            // insert into Databata here...
            db.INSERT_PersonTask(TaskID, PersonID, myStringData);
            AddItem();
        }
    }

    public void AddItem() {
        myItemList = db.getPersonsTask(TaskID, PersonID);
        adapter = new AdapterPersonTaskList(IndividualPersonListActivity.this,
                R.layout.item_resultlist, myItemList);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        addNewItem.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.individual_person_list, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) item.getActionProvider();
        // Create the share Intent
        String playStoreLink = "\n";
        // String playStoreLink =
        // "https://play.google.com/store/apps/details?id="
        // + getPackageName();

        for (PersonTasks Ptask : myItemList) {
            playStoreLink += Ptask.getTaskDetail().trim() + "\n";
        }
        String yourShareText = "Task List " + "\n" + playStoreLink;
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain").setText(yourShareText).getIntent();
        // Set the share Intent
        mShareActionProvider.setShareIntent(shareIntent);
        // Return true to display menu
        return true;
    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

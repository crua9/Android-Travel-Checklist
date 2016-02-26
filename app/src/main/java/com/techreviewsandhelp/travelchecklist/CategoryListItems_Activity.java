package com.techreviewsandhelp.travelchecklist;

/**
 * Created by crua9 on 2/26/2016.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import util.CustomExpandableListAdapter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class CategoryListItems_Activity extends Activity {
    CustomExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list_items);
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color
                .parseColor("#f9833f")));
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new CustomExpandableListAdapter(this, listDataHeader,
                listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                // Toast.makeText(getApplicationContext(),
                // listDataHeader.get(groupPosition) + " Expanded",
                // Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                // Toast.makeText(getApplicationContext(),
                // listDataHeader.get(groupPosition) + " Collapsed",
                // Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                // Toast.makeText(
                // getApplicationContext(),
                // listDataHeader.get(groupPosition)
                // + " : "
                // + listDataChild.get(
                // listDataHeader.get(groupPosition)).get(
                // childPosition), Toast.LENGTH_SHORT)
                // .show();

                Intent dataIntent = new Intent();

                String data = listDataChild.get(
                        listDataHeader.get(groupPosition)).get(childPosition);

                dataIntent.putExtra("Item", data);
                setResult(11, dataIntent);
                finish();
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();

    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Cloths");
        listDataHeader.add("Tools");
        listDataHeader.add("Booking");

        // Adding child data
        List<String> Cloths = new ArrayList<String>();
        Cloths.add("Silk Shit");
        Cloths.add("Godfather Coat");
        Cloths.add("Cow Boy jacket");
        Cloths.add("Polo T-shirt");
        Cloths.add("Ugly Jeans");
        Cloths.add("Dark Jeans");
        Cloths.add("Underwears");

        List<String> Tools = new ArrayList<String>();
        Tools.add("Tester");
        Tools.add("Betteries");
        Tools.add("Charger");
        Tools.add("UPS plugs");
        Tools.add("Universal cheager");
        Tools.add("Torch");

        List<String> Booking = new ArrayList<String>();
        Booking.add("Hotel Booking");
        Booking.add("Hotel Reservation");
        Booking.add("Air Ticket");
        Booking.add("Air Ticket Reservation");
        Booking.add("Train Ticket");
        Booking.add("Train Ticket Reservation");

        listDataChild.put(listDataHeader.get(0), Cloths); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Tools);
        listDataChild.put(listDataHeader.get(2), Booking);
    }
}

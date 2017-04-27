package com.bizhawkz.add2cart;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;

import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class Main5Activity extends AppCompatActivity {
    ListView listView;
    String[] arrayS = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p"};
    String[] arrayS2 = new String[]{"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1", "i1", "j1", "k1", "l1", "m1", "n1", "o1", "p1"};
    int pageCount = 1;
    View footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        listView = (ListView) findViewById(R.id.load);

        final ArrayList<String> list = new ArrayList<String>();


        for (int i = 0; i < arrayS.length; i++) {
            list.add(arrayS[i]);
        }

        // Add footer view
        footer = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.progress, null, false);
        listView.addFooterView(footer);

        final ArrayAdapter<String> ad = new ArrayAdapter(Main5Activity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(ad);


        // Implementing scroll refresh
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override
            public void onScroll(AbsListView absListView, int firstItem, int visibleItemCount, final int totalItems) {
                Log.e("Get position", "--firstItem:" + firstItem + "  visibleItemCount:" + visibleItemCount + "  totalItems:" + totalItems + "  pageCount:" + pageCount);
                int total = firstItem + visibleItemCount;


                // Total array list i have so it
                if (pageCount < 2) {

                    if (total == totalItems) {

                        // Execute some code after 15 seconds have passed
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                for (int i = 0; i < arrayS2.length; i++) {
                                    list.add(arrayS2[i]);
                                }
                                ad.notifyDataSetChanged();
                                listView.setAdapter(ad);
                                listView.setSelection(totalItems);
                                pageCount++;
                            }
                        }, 15000);
                    }
                } else {
                    Log.e("hide footer", "footer hide");
                    listView.removeFooterView(footer);
                }
            }
        });

    }
}

package com.bizhawkz.add2cart;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddLike extends AppCompatActivity {
    ListView list;
    CustomAdapter2 adapter;
    public  AddLike CustomListView = null;
    final ListModel sched = new ListModel();
    String fname;
    public ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_like);
        CustomListView = this;

        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        setListData();

        Resources res =getResources();
        list= ( ListView )findViewById( R.id.list );  // List defined in XML ( See Below )

        /**************** Create Custom Adapter *********/
        adapter=new CustomAdapter2( CustomListView, CustomListViewValuesArr,res );
        list.setAdapter( adapter );
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListModel tempValues = ( ListModel ) CustomListViewValuesArr.get(position);
                tempValues.setIsRowSelected(true);
                adapter.notifyDataSetChanged();
                if (tempValues.getIsRowSelected()) {

                    fname = String.valueOf(tempValues.getName());
                    System.out.println(fname);
                    //Intent it = new Intent(BabyCare.this,Ordernow.class);
                  //  Bundle b = new Bundle();
                  //  b.putString("medicine", fname);
                   // it.putExtras(b);
                  //  startActivity(it);

                }
            }
        });
    }

    private void setListData() {
        for (int i = 0; i < 11; i++) {



            /******* Firstly take data in model object ******/
            sched.setCompanyName("Company "+i);
            sched.setImage("image"+i);
            sched.setUrl("http:\\www."+i+".com");

            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add( sched );
        }
    }

    public void onItemClick(int mPosition) {
        ListModel tempValues = ( ListModel ) CustomListViewValuesArr.get(mPosition);
        tempValues.setIsRowSelected(true);
        adapter.notifyDataSetChanged();
        if (tempValues.getIsRowSelected()) {
            tempValues.getCompanyName();
            fname = tempValues.getName();
            System.out.println(fname);
        }

        // SHOW ALERT

        Toast.makeText(CustomListView,""+tempValues.getCompanyName()+"Image:"+tempValues.getImage()+"Url:"+tempValues.getUrl(),
        Toast.LENGTH_LONG).show();

    }
}

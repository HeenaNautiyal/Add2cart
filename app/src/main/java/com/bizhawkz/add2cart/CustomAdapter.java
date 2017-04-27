package com.bizhawkz.add2cart;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Heena on 4/1/2017.
 */
public class CustomAdapter extends BaseAdapter {

    private static final String TAG = CustomAdapter.class.getSimpleName();
    ArrayList<DataModel> listArray;

    public CustomAdapter() {
        listArray = new ArrayList<DataModel>(5);
        listArray.add(new DataModel("name1", 5, 1.8, "Java"));
        listArray.add(new DataModel("name2", 10, 2.8, "Python"));
        listArray.add(new DataModel("name3", 15, 3.8, "Django"));
        listArray.add(new DataModel("name4", 20, 4.8, "Groovy"));
        listArray.add(new DataModel("name5", 25, 5.8, "Maven"));
    }


    @Override
    public int getCount() {
        return listArray.size();    // total number of elements in the list
    }

    @Override
    public Object getItem(int i) {
        return listArray.get(i);    // single item in the list
    }

    @Override
    public long getItemId(int i) {
        return i;                   // index number
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.single_list_item, parent, false);
        }

        final DataModel dataModel = listArray.get(index);

        TextView textView = (TextView) view.findViewById(R.id.tv_string_data);
        textView.setText(dataModel.getName());

        Button button = (Button) view.findViewById(R.id.btn_number_data);
        button.setText("" + dataModel.getAnInt());

        textView = (TextView) view.findViewById(R.id.tv_double_data);
        textView.setText("" + dataModel.getaDouble());

        // button click listener
        // this chunk of code will run, if user click the button
        // because, we set the click listener on the button only

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "string: " + dataModel.getName());
                Log.d(TAG, "int: " + dataModel.getAnInt());
                Log.d(TAG, "double: " + dataModel.getaDouble());
                Log.d(TAG, "otherData: " + dataModel.getOtherData());

                Toast.makeText(parent.getContext(), "button clicked: " + dataModel.getAnInt(), Toast.LENGTH_SHORT).show();
            }
        });


        // if you commented out the above chunk of code and
        // activate this chunk of code
        // then if user click on any view inside the list view (except button)
        // this chunk of code will execute
        // because we set the click listener on the whole view


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "string: " + dataModel.getName());
                Log.d(TAG, "int: " + dataModel.getAnInt());
                Log.d(TAG, "double: " + dataModel.getaDouble());
                Log.d(TAG, "otherData: " + dataModel.getOtherData());

                Toast.makeText(parent.getContext(), "view clicked: " + dataModel.getOtherData(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

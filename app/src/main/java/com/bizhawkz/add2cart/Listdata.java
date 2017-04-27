package com.bizhawkz.add2cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Listdata extends AppCompatActivity {
    private MyCustomAdapter dataAdapter = null;
    private View currentView = null;
Button btn;
    private static final int COUNTRY_EDIT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);
        displayListView();

    }

    private void displayListView() {

        //Array list of countries
        ArrayList<Country> countryList = new ArrayList<Country>();
        Country country = new Country("AFG","Afghanistan","Asia",
                "Southern and Central Asia", 101010);
        countryList.add(country);
        country = new Country("ALB","Albania","Europe","Southern Europe", 202020);
        countryList.add(country);
        country = new Country("DZA","Algeria","Africa","Northern Africa", 303030);
        countryList.add(country);
        country = new Country("ASM","American Samoa","Oceania","Polynesia", 404040);
        countryList.add(country);
        country = new Country("AND","Andorra","Europe","Southern Europe", 505050);
        countryList.add(country);
        country = new Country("AGO","Angola","Africa","Central Africa", 606060);
        countryList.add(country);
        country = new Country("AIA","Anguilla","North America","Caribbean", 707070);
        countryList.add(country);

        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,
                R.layout.row_data, countryList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                currentView = view;

                //get reference to the country Object
                Country country = (Country) view.getTag();
                Toast.makeText(getApplicationContext(),
                        country.getCode(), Toast.LENGTH_SHORT).show();

                Intent countryEdit = new Intent(Listdata.this,CountryEdit.class);
                Bundle b = new Bundle();
                //pass the country object as a parcel
                b.putParcelable("country", country);
                countryEdit.putExtras(b);
                startActivityForResult(countryEdit, COUNTRY_EDIT);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        switch(requestCode) {

            case COUNTRY_EDIT:
                if (resultCode == RESULT_OK) {

                    //read the bundle and get the country object
                    Bundle bundle = data.getExtras();
                    Country country = bundle.getParcelable("country");

                    //update the country object in the ArrayAdapter
                    int listPosition = country.getListPosition();
                    dataAdapter.setCountry(country, listPosition);

                    //update the country name in the ListView
                    currentView.setTag(country);
                    TextView name = (TextView) currentView.findViewById(R.id.name);
                    name.setText(country.getName());

                }
                break;

        }

    }

    private class MyCustomAdapter extends ArrayAdapter<Country> {

        private ArrayList<Country> countryList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Country> countryList) {

            super(context, textViewResourceId, countryList);
            this.countryList = new ArrayList<Country>();
            this.countryList.addAll(countryList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView code = null;
            TextView name = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.row_data, null);

                code  = (TextView) convertView.findViewById(R.id.code);
                name = (TextView) convertView.findViewById(R.id.name);
            }

            Country country = countryList.get(position);
            country.setListPosition(position);
            code.setText(country.getCode());
            name.setText(country.getName());
            convertView.setTag(country);

            return convertView;
        }

        public void setCountry(Country country, int position){
            this.countryList.set(position, country);
        }

    }

}

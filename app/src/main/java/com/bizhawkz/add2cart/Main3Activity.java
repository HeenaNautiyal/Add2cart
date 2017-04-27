package com.bizhawkz.add2cart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView output = (TextView) findViewById(R.id.textView1);

        String strJson="[{\"medicine_name\":\"CRO Health Miralax\",\"medicine_id\":\"19774\",\"Chem_name\":\"vikramjeet@outsourcingservicesusa.com\",\"quantity\":\"1\"},{\"medicine_name\":\"Dynarex-Alcohol\",\"medicine_id\":\"19775\",\"Chem_name\":\"vikramjeet@outsourcingservicesusa.com\",\"quantity\":\"1\"}]";
                //"{ \"Employee\" :[{\"id\":\"101\",\"name\":\"Sonoo Jaiswal\",\"salary\":\"50000\"},{\"id\":\"102\",\"name\":\"Vimal Jaiswal\",\"salary\":\"60000\"}] }";

        String data = "";
        try {
            // Create the root JSONObject from the JSON string.
            //JSONObject jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = new JSONArray(strJson);

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = Integer.parseInt(jsonObject.optString("medicine_id").toString());
                String name = jsonObject.optString("medicine_name").toString();
                //float salary = Float.parseFloat(jsonObject.optString("Chem_name").toString());

                data += "Node"+i+" : \n id= "+ id +" \n Name= "+ name +"  ";
            }
            output.setText(data);
        } catch (JSONException e) {e.printStackTrace();}
    }

}

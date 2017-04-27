package com.bizhawkz.add2cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewActivity extends AppCompatActivity {
Button btnnext,btncon;
    ArrayList<Actors> actorsList;
    String address,spouse;
    ActorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Intent intent = getIntent();
        btncon=(Button)findViewById(R.id.btn_continue);
        btnnext=(Button)findViewById(R.id.btn_back);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it= new Intent(NewActivity.this,Carting.class);
                startActivity(it);
            }
        });

        btncon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* JSONArray jsonArray = new JSONArray();
                ArrayList<Actors> countryList = adapter.actorList;

                for (int i = 0; i < countryList.size(); i++) {
                    Actors actors = countryList.get(i);

                    if (actors.isSelected()) {
                        address = actors.getName();
                        spouse=actors.getSpouse();

                        JSONObject student1 = new JSONObject();
                        try {
                            student1.put("medicine_name", address);
                            student1.put("Spouse",spouse);


                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        jsonArray.put(student1);

                    }
                }
            }*/
                OnBackPressed();
            }
        });
        String jsonArray = intent.getStringExtra("jsonArray");
        try {
            JSONArray array = new JSONArray(jsonArray);
            for (int i=0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                String abc=jsonObject.getString("medicine_name");
                Log.e("name===========>",abc);

            }
          //  System.out.println(array.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void OnBackPressed() {
        super.onBackPressed();
    }
}

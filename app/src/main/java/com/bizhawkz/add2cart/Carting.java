package com.bizhawkz.add2cart;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Carting extends AppCompatActivity {
    ArrayList<Actors> actorsList;
    private TextView mCounter;
    Button btn;
    private int count=0;
    String address,spouse;
    ActorAdapter3 adapter;
    EditText editsearch;
    String TAG="Carting";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carting);
        btn=(Button)findViewById(R.id.btn_continue);
        actorsList = new ArrayList<Actors>();
        editsearch = (EditText) findViewById(R.id.search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"-----------------------------");
                for(Actors actors:actorsList){
                    if(actors.getIsRowSelected()){
                        Log.e(TAG,"selected name: "+actors.getName());
                    }
                }
            }
        });
        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                String text = editsearch.getText().toString();
                if (text.length() > 0) {
                    String text2 = text.substring(0, 1).toUpperCase();
                    text2.equalsIgnoreCase(text2);
                   // adapter.filter(text2);
                } else {
                    //adapter.clearData();
                    new JSONAsyncTask().execute("http://d2dmedicine.com/aPPmob_lie/insertdata.php?caseid=27&catid=27");
                }
            }
        });
        mCounter=(TextView)findViewById(R.id.badge_notification_2);

        ListView listview = (ListView)findViewById(R.id.list);
        adapter = new ActorAdapter3(getApplicationContext(), R.layout.row, actorsList);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        new JSONAsyncTask().execute("http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id) {
                Actors actors=actorsList.get(position);
                actors.setIsRowSelected(true);
                actorsList.set(position,actors);
                adapter.updateAdapter(actorsList);
               // adapter.updateAdapter(actorsList);

             /*   JSONArray jsonArray = new JSONArray();
                ArrayList<Actors> countryList = adapter.actorList;
                for (int i = 0; i < countryList.size(); i++) {
                    Actors actors1 = countryList.get(i);
                    if (actors1.isSelected()) {
                        address = actors1.getName();
                        spouse=actors1.getSpouse();
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
                System.out.println("jsonString: "+jsonArray);
                Intent intent = new Intent(Carting.this, NewActivity.class);
                intent.putExtra("jsonArray", jsonArray.toString());
                startActivity(intent);
                if (mCounter!=null) {
                    count++;
                    mCounter.setText(Integer.toString(count));
                }
            }
        });*/
            }
        });
    }


    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Carting.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {

                //------------------>>
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("actors");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);

                        Actors actor = new Actors();

                        actor.setName(object.getString("name"));
                        actor.setDescription(object.getString("description"));
                        actor.setDob(object.getString("dob"));
                        actor.setCountry(object.getString("country"));
                        actor.setHeight(object.getString("height"));
                        actor.setSpouse(object.getString("spouse"));
                        actor.setChildren(object.getString("children"));
                        actor.setImage(object.getString("image"));

                        actorsList.add(actor);
                    }
                    return true;
                }

                //------------------>>

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            adapter.notifyDataSetChanged();

            if(result == false)
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }

    }
   /* int listSize = actorsList.size();

    for (int i = 0; i<listSize; i++){
        Log.i("Member name: ", String.valueOf(actorsList.get(i)));
    }*/
}

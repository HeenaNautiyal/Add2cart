package com.bizhawkz.add2cart;

import android.app.ProgressDialog;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class AddressPage extends AppCompatActivity implements View.OnClickListener{
    Button btnsubmit;
    TextView btnsaved;
    EditText edcity,edstate,edpincode,edaddress,edmewmail,ednumber;
    String city,state,pincode,address,newmail,number,address1,state1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_page);
        edcity=(EditText)findViewById(R.id.ed_city);
        edstate=(EditText)findViewById(R.id.ed_state);
        edpincode=(EditText)findViewById(R.id.ed_pincode);
        edaddress=(EditText)findViewById(R.id.ed_address);
        edmewmail=(EditText)findViewById(R.id.ed_mail2);
        ednumber=(EditText)findViewById(R.id.ed_numn);

        btnsaved=(TextView)findViewById(R.id.svadd);
        btnsubmit=(Button)findViewById(R.id.btn_continue);

        btnsaved.setOnClickListener(this);
        btnsubmit.setOnClickListener(this);

    }

    private class Logmem extends AsyncTask<String, Void, Boolean> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(AddressPage.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);

                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("result");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);
                        address1 = object.getString("address");
                        city = object.getString("city");
                        state1 = object.getString("state");
                        pincode = object.getString("pincode");
                    }
                    return true;
                }

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            Log.e("saved Address",address1);

        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnsaved) {
            savedaddress();
        }
        if (v == btnsubmit) {
            submitaddress();
        }
    }

    private void submitaddress() {
        address=edaddress.getText().toString();
        Log.e("Submit Address",address);

    }

    private void savedaddress() {
        new Logmem().execute("http://d2dmedicine.com/aPPmob_lie/insertdata.php?caseid=19&email=heenacse@yahoo.in");
       // Toast.makeText(getApplicationContext(),address1.toString(),Toast.LENGTH_LONG).show();
//        Log.e("saved Address",address1);
    }
}

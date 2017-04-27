package com.bizhawkz.add2cart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CountryEdit extends Activity implements View.OnClickListener {

    private Country country;
    private TextView code;
    private EditText name, region, continent, population;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_edit);

        Bundle bundle = this.getIntent().getExtras();
        country = bundle.getParcelable("country");

        Button save = (Button) findViewById(R.id.save);
        Button cancel = (Button) findViewById(R.id.cancel);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);

        code = (TextView) findViewById(R.id.code);
        name = (EditText) findViewById(R.id.name);
        region = (EditText) findViewById(R.id.region);
        continent = (EditText) findViewById(R.id.continent);
        population = (EditText) findViewById(R.id.population);

        code.setText(country.getCode());
        name.setText(country.getName());
        region.setText(country.getRegion());
        continent.setText(country.getContinent());
        population.setText(String.valueOf(country.getPopulation()));


    }


    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.save:

                country.setName(name.getText().toString());
                country.setRegion(region.getText().toString());
                country.setContinent(continent.getText().toString());
                country.setPopulation(Integer.parseInt(population.getText().toString()));

                Bundle b = new Bundle();
                b.putParcelable("country", country);
                intent.putExtras(b);
                setResult(RESULT_OK,intent);
                finish();
                break;

            case R.id.cancel:
                setResult(RESULT_CANCELED,intent);
                finish();
                break;

        }

    }

}

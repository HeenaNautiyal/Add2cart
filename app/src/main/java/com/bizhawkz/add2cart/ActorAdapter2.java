package com.bizhawkz.add2cart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Heena on 3/24/2017.
 */
public class ActorAdapter2 extends ArrayAdapter<Actors> {
    ArrayList<Actors> actorList;
    LayoutInflater vi;
    int Resource,count=0,abc;
    ViewHolder holder;

    private boolean mHasMoreItems;
    private AdapterView listView;

    public ActorAdapter2(Context context, int resource, ArrayList<Actors> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        actorList = objects;
        mHasMoreItems=true;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.tvName = (TextView) v.findViewById(R.id.tvName);
            holder.btnadd=(Button)v.findViewById(R.id.bt_add);
            holder.tvHeight = (TextView) v.findViewById(R.id.tvHeight);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.tvName.setText(actorList.get(position).getName());
        holder.tvHeight.setText("Quantity: " +String.valueOf(abc));
        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=(Integer)v.getTag();
                Actors actors=actorList.get(position);
                actors.setQuantity(actors.getQuantity()+1);
                actorList.set(position,actors);
                abc=actorList.get(position).getQuantity();
                Log.e("position",String.valueOf(abc));
            }
        });
        holder.btnadd.setTag(position);
        return v;

    }

    static class ViewHolder {
        public TextView tvName;
        public TextView tvHeight;
        public Button btnadd;

    }


}
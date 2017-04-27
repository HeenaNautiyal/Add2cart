package com.bizhawkz.add2cart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Heena on 4/12/2017.
 */
public class ActorAdapter3 extends ArrayAdapter<Actors> {
    ArrayList<Actors> actorList;
    LayoutInflater vi;
    int Resource,count=0,abc;
    ViewHolder holder;

    private boolean mHasMoreItems;
    private AdapterView listView;

    public ActorAdapter3(Context context, int resource, ArrayList<Actors> objects) {
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
        Actors country= (Actors) this.getItem( position );
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.tvName = (TextView) v.findViewById(R.id.tvName);
            holder.btnadd=(Button)v.findViewById(R.id.bt_add);
            holder.tvHeight = (TextView) v.findViewById(R.id.tvHeight);
            holder.box=(CheckBox)v.findViewById(R.id.chk);
            holder.box.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    Actors country = (Actors) cb.getTag();
                    country.setIsRowSelected(cb.isChecked());
                }
            });
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.tvName.setText(actorList.get(position).getName());
        holder.tvHeight.setText("Quantity: " +String.valueOf(abc));
        holder.box.setChecked(actorList.get(position).isSelected());x
        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position=(Integer)v.getTag();
                final Actors actors=actorList.get(position);
                String abc=actors.getName();
                actors.setName(abc+"ing");
                actorList.set(position,actors);
                System.out.println("Array "+actorList);
                Log.e("position",abc);
                notifyDataSetChanged();
                holder.tvName.setText(actorList.get(position).getName());
            }

        });
        holder.btnadd.setTag(position);
        holder.box.setTag(country);

        return v;

    }

    public void updateAdapter(ArrayList<Actors> actorsList) {
        this.actorList=actorList;
    }

    static class ViewHolder {
        public TextView tvName;
        public TextView tvHeight;
        public Button btnadd;
        public CheckBox box;


    }

}

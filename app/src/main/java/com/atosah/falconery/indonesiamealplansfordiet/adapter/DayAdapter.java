package com.atosah.falconery.indonesiamealplansfordiet.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atosah.falconery.indonesiamealplansfordiet.R;
import com.atosah.falconery.indonesiamealplansfordiet.model.Plan;

import java.util.ArrayList;

public class DayAdapter extends ArrayAdapter<Plan> {

    private final LayoutInflater inflater;
    private final ArrayList<Plan> dayList;


    public DayAdapter(Context context, ArrayList<Plan> dayList) {
        super(context, R.layout.layout_day_list, dayList);
        this.dayList = dayList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dayList.size();
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder row;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_day_list, null);
            row = new ViewHolder();
            row.dayName = convertView.findViewById(R.id.titleTV);
            convertView.setTag(row);
        } else {
            row = (ViewHolder) convertView.getTag();
        }

        Plan plan = dayList.get(position);
        row.dayName.setText(plan.getTitle());
        return convertView;
    }

    private static class ViewHolder {
        public TextView dayName;
    }
}

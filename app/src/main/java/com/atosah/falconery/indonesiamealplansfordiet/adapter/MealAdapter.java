package com.atosah.falconery.indonesiamealplansfordiet.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atosah.falconery.indonesiamealplansfordiet.R;
import com.atosah.falconery.indonesiamealplansfordiet.activity.DetailMenuActivity;
import com.atosah.falconery.indonesiamealplansfordiet.model.Menu;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {
    private Activity activity;
    private ArrayList<Menu> stringArrayList;

    public MealAdapter(Activity activity, ArrayList<Menu> stringArrayList) {
        this.activity = activity;
        this.stringArrayList = stringArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_meal_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final Menu menu = stringArrayList.get(position);
        viewHolder.textView.setText(menu.getName());

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetailMenuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Menu", menu);
                intent.putExtras(bundle);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CardView parentLayout;
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.titleTV);
            parentLayout = view.findViewById(R.id.parent_layout);
        }
    }
}

package com.atosah.falconery.indonesiamealplansfordiet.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atosah.falconery.indonesiamealplansfordiet.R;
import com.atosah.falconery.indonesiamealplansfordiet.activity.DetailMenuActivity;
import com.atosah.falconery.indonesiamealplansfordiet.model.Menu;

import java.util.List;

public class RowListAdapter extends RecyclerView.Adapter<RowListAdapter.ViewHolder> {
    private Activity activity;
    private List<String> stringList;
    private Boolean visibleNumber;

    public RowListAdapter(Activity activity, List<String> stringList, Boolean visibleNumber) {
        this.activity = activity;
        this.stringList = stringList;
        this.visibleNumber = visibleNumber;
    }

    @Override
    public RowListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_row_list, parent, false);

        return new RowListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RowListAdapter.ViewHolder viewHolder, final int position) {
        int num = position + 1;
        String numString = Integer.toString(num);
        viewHolder.setNumber(numString);
        viewHolder.setTitle(stringList.get(position));

        if (visibleNumber) {
            viewHolder.setNumberVisible(visibleNumber);
        }
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView numberView;
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            numberView = view.findViewById(R.id.numberTV);
            textView = view.findViewById(R.id.titleTV);
        }

        public void setNumber(String number) {
            numberView.setText(number);
        }

        public void setTitle(String title) {
            textView.setText(title);
        }

        public void setNumberVisible(Boolean numberVisible) {
            numberView.setVisibility(View.VISIBLE);
        }
    }
}


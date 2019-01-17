package com.atosah.falconery.indonesiamealplansfordiet.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atosah.falconery.indonesiamealplansfordiet.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ImageAdapter extends PagerAdapter {
    private Activity activity;
    private List<String> stringList;

    public ImageAdapter(Activity activity, List<String> stringList) {
        this.activity = activity;
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final int pos = position;

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.pager_image_item, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);

        Glide.with(activity).load(stringList.get(pos))
                .apply(RequestOptions.centerCropTransform())
                .into(imageView);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

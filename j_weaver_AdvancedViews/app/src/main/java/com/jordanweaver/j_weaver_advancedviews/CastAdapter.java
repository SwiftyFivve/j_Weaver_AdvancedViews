package com.jordanweaver.j_weaver_advancedviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jordanweaver on 2/13/15.
 */
public class CastAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<castClass> mCasts;
    private static final int ID_CONSTANT = 0x01000000;

    public CastAdapter(Context mContext, ArrayList<castClass> mCasts) {
        this.mContext = mContext;
        this.mCasts = mCasts;
    }

    @Override
    public int getCount() {
        if (mCasts != null){
            return mCasts.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if(mCasts != null && position < mCasts.size() && position >= 0){
            return mCasts.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        if (mCasts != null){
            return ID_CONSTANT + position;
        } else {
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.activity_main, parent, false);
        }

        castClass item = (castClass) getItem(position);

        ((TextView)convertView.findViewById(R.id.bioText)).setText(item.castBio);
        ((TextView)convertView.findViewById(R.id.bioText2)).setText(item.castBio);

        return convertView;
    }
}

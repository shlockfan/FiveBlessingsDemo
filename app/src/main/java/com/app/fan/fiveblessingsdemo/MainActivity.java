package com.app.fan.fiveblessingsdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.app.fan.fiveblessingsdemo.adapter.RecyclingPagerAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.viewpager)
    FBViewPager FbViewPager;
    @Bind(R.id.page_container)
    RelativeLayout page_container;
    private FbAdapter mFbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FbViewPager.setPageTransformer(true, new ScalePageTransformer());
        page_container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return FbViewPager.dispatchTouchEvent(event);
            }
        });
        mFbAdapter = new FbAdapter(this);
        FbViewPager.setAdapter(mFbAdapter);
        initData();

    }

    private void initData() {
        ArrayList<Integer> pics = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pics.add(R.mipmap.ali);
        }
        FbViewPager.setOffscreenPageLimit(pics.size());
        mFbAdapter.addData(pics);

    }

    public static class FbAdapter extends RecyclingPagerAdapter {
        private Context mContext;
        private ArrayList<Integer> mList = new ArrayList<>();

        public FbAdapter(Context mContext) {
            this.mContext = mContext;
        }

        public void addData(ArrayList<Integer> pics) {
            mList.addAll(pics);
            notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            ImageView mImageView = null;
            if (convertView == null) {
                mImageView = new ImageView(mContext);
            } else {
                mImageView = (ImageView) convertView;
            }
            mImageView.setTag(position);
            mImageView.setImageResource(mList.get(position));
            return mImageView;
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }
}

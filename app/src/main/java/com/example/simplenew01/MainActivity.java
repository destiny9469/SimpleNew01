package com.example.simplenew01;

import android.app.ActionBar;
import android.provider.FontsContract;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.simplenew01.UTIL.DensityUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button categoryArrowRight;
    HorizontalScrollView categoryScrollView;
    private final int mflingVelocityPX = 1000;
    private int mflingVelocityDip;
    private final int COLUMNWIDTHPX = 450;
    private int mColumWidthDip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mColumWidthDip = DensityUtil.px2dip(this, COLUMNWIDTHPX);
        mflingVelocityDip = DensityUtil.px2dip(this, mflingVelocityPX);
        String[] gvCategoryArray = this.getResources().getStringArray(R.array.categories);

        List<HashMap<String, String>> categoryList = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < gvCategoryArray.length; i++) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("category_title", gvCategoryArray[i]);
            categoryList.add(hashMap);
        }
        ;
        SimpleAdapter categoryAdapter = new SimpleAdapter(this, categoryList, R.layout.category_title, new String[]{"category_title"}, new int[]{R.id.category_title});
        GridView gvCategory = new GridView(this);
        gvCategory.setColumnWidth(mColumWidthDip);
        gvCategory.setNumColumns(GridView.AUTO_FIT);
        gvCategory.setGravity(Gravity.CENTER);//设置对齐方式

        int width = mColumWidthDip * categoryList.size();
        ViewGroup.LayoutParams params = new DrawerLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        gvCategory.setLayoutParams(params);
        gvCategory.setAdapter(categoryAdapter);
        LinearLayout categoryLayout = (LinearLayout) this.findViewById(R.id.category_layout);
        categoryLayout.addView(gvCategory);


        categoryArrowRight = (Button) this.findViewById(R.id.category_arrow_right);
        categoryScrollView = (HorizontalScrollView) this.findViewById(R.id.category_scrollview);
        categoryArrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryScrollView.fling(mflingVelocityDip);
            }
        });
        gvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                TextView tvCategoryTitle = (TextView) view;
                for (int o = 0; o < parent.getCount(); o++) {
                    tvCategoryTitle = (TextView) parent.getChildAt(o);
                    tvCategoryTitle.setTextColor(0XFFADB2AD);
                    tvCategoryTitle.setBackgroundDrawable(null);

                }
                tvCategoryTitle = (TextView) view;
                tvCategoryTitle.setTextColor(0Xffffffff);
                tvCategoryTitle.setBackgroundResource(R.drawable.categorybar_item_background);
            }
        });
        //准备数据
        List<HashMap<String, String>> newsList = new ArrayList<>();
        for (int p = 0; p < 2; p++) {
            HashMap hashMap = new HashMap();
            hashMap.put("tvItemTitle", "Android课堂开课了");
            hashMap.put("tvItemDigest", "学习Android系统基础");
            hashMap.put("tvItemFrom", "G16531 class");
            hashMap.put("tvItemTime", "2018-05-16");
            newsList.add(hashMap);

        }
        //制作适配器
        SimpleAdapter newsListAdapter = new SimpleAdapter(this, newsList, R.layout.newslist_item, new String[]{"tvItemTitle", "tvItemDigest", "tvItemFrom", "tvItemTime"},
        new int[]{R.id.tvItemTitle,R.id.tvItemDigest,R.id.tvItemFrom,R.id.tvItemTime});

        //把适配器设置到ListView
        ListView lstNewsList = (ListView) this.findViewById(R.id.news_list);
        lstNewsList.setAdapter(newsListAdapter);

    }
}


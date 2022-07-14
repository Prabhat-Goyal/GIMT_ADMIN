package com.defenders.gimtadmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ss.com.bannerslider.Slider;

public class HomeActivity extends AppCompatActivity {

    private View parent_view;
    private Slider slider;

    private RecyclerView recyclerView;
    private HomeCategoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        parent_view = findViewById(R.id.parent_view);

        Slider.init(new PicassoImageLoadingService(this));
        setupViews();

        initComponent();
    }


    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(this, 8), true));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        List<HomeCategoryData> items = DataGenerator.getHomeCategory(this);

        mAdapter = new HomeCategoryAdapter(this, items);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new HomeCategoryAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, HomeCategoryData obj, int position) {

            }
        });

    }

    private void setupViews(){
        slider = findViewById(R.id.slider);
        slider.setAdapter(new MainSliderAdapter());
        slider.setSelectedSlide(0);
        slider.setInterval(5000);
    }
}

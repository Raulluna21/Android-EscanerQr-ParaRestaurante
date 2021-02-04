package com.luna.lector;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Tutorial extends AppCompatActivity {

    private ViewPager mSliderViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private Button btn_back, btn_next;
    private int mCurrentPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        btn_back = findViewById(R.id.btn_back);
        btn_next = findViewById(R.id.btn_next);
        mSliderViewPager = findViewById(R.id.slideViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(this);
        mSliderViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        mSliderViewPager.addOnPageChangeListener(viewListener);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btn_next.getText().toString().equals("Finalizar")){
                    Intent ecto = new Intent(Tutorial.this, MainActivity.class);
                    startActivity(ecto);
                    overridePendingTransition(R.anim.left_in,R.anim.left_out);
                }else{
                    mSliderViewPager.setCurrentItem(mCurrentPage + 1);
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSliderViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });


    }//final oncreate

    public void addDotsIndicator(int position){
        mDots = new TextView[10];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length ; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            mDotLayout.addView(mDots[i]);
        }
        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.whiteColor));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage = i;

            if (i == 0){
                btn_next.setEnabled(true);
                btn_back.setEnabled(false);
                btn_back.setVisibility(View.INVISIBLE); //YO QUITÈ 'IN'

                btn_next.setText("Siguiente");
                btn_back.setText("Cerrar");  // ES ""
            } else if(i == mDots.length -1){
                btn_next.setEnabled(true);
                btn_back.setEnabled(true);
                btn_back.setVisibility(View.VISIBLE);

                btn_next.setText("Finalizar");
                btn_back.setText("Regresar");

            } else {
                btn_next.setEnabled(true);
                btn_back.setEnabled(true);
                btn_back.setVisibility(View.VISIBLE);

                btn_next.setText("Siguiente");
                btn_back.setText("Regresar");

            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


}

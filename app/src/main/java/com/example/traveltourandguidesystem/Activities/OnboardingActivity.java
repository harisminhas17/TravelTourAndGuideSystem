package com.example.traveltourandguidesystem.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.traveltourandguidesystem.Adapters.ViewPager2Adapter;
import com.example.traveltourandguidesystem.R;


public class OnboardingActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    View circle_1,circle_2,circle_3;
    TextView textView;
    ImageView nextbutton;
    TextView skip_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        getSupportActionBar().hide();

        //find view by id
        viewPager2 = findViewById(R.id.viewpager);
        circle_1=findViewById(R.id.circle1);
        circle_2=findViewById(R.id.circle2);
        circle_3=findViewById(R.id.circle3);
        textView=findViewById(R.id.textView);
        nextbutton =findViewById(R.id.next_button2);
        skip_btn=findViewById(R.id.skip_btn);

        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(viewPager2Adapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            // This method is triggered when there is any scrolling activity for the current page
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            // triggered when you select a new page
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position==0)
                {
                    circle_1.setBackground(getDrawable(R.drawable.dark_circle));
                    circle_2.setBackground(getDrawable(R.drawable.light_circle));
                    circle_3.setBackground(getDrawable(R.drawable.light_circle));
                    textView.setText("Your Journey Starts Here");
                } else if (position==1) {
                    circle_2.setBackground(getDrawable(R.drawable.dark_circle));
                    circle_1.setBackground(getDrawable(R.drawable.light_circle));
                    circle_3.setBackground(getDrawable(R.drawable.light_circle));
                    textView.setText("Explore Pakistan with Ease");
                } else if (position==2) {
                    circle_3.setBackground(getDrawable(R.drawable.dark_circle));
                    circle_2.setBackground(getDrawable(R.drawable.light_circle));
                    circle_1.setBackground(getDrawable(R.drawable.light_circle));
                    textView.setText("Our Tours, Your Stories");
                }
            }

            // triggered when there is
            // scroll state will be changed
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewPager2.getCurrentItem();
                pos=pos+1;
                if (pos<3){

                    viewPager2.setCurrentItem(pos,true);
                }
                else{

                    startActivity(new Intent(OnboardingActivity.this,RegistrationActivity.class));
                }
            }
        });
        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnboardingActivity.this,RegistrationActivity.class));
            }
        });
    }
}
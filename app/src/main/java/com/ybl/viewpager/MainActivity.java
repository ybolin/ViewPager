package com.ybl.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;
    Button buttonFour;

    ViewPager mViewPager;

    List<Fragment> fragmentList;

    OneFragment oneFragment;
    TwoFragment twoFragment;
    ThreeFragment threeFragment;
    FourFragment fourFragment;

    ImageView imageviewOvertab;

    int screenWidth;

    int currenttab = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonOne = (Button) findViewById(R.id.btn_one);
        buttonTwo = (Button) findViewById(R.id.btn_two);
        buttonThree = (Button) findViewById(R.id.btn_three);
        buttonFour = (Button) findViewById(R.id.btn_four);

        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        fragmentList = new ArrayList<Fragment>();
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fourFragment = new FourFragment();

        fragmentList.add(oneFragment);
        fragmentList.add(twoFragment);
        fragmentList.add(threeFragment);
        fragmentList.add(fourFragment);

        screenWidth = getResources().getDisplayMetrics().widthPixels;

        buttonTwo.measure(0, 0);
        imageviewOvertab = (ImageView) findViewById(R.id.imgv_overtab);
        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams(screenWidth / 4, buttonTwo.getMeasuredHeight());
        imageParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        imageviewOvertab.setLayoutParams(imageParams);
        mViewPager.setAdapter(new MyFrageStatePagerAdapter(getSupportFragmentManager()));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btn_one:
                Toast.makeText(MainActivity.this,"aaaa",Toast.LENGTH_SHORT).show();
                changeView(0);
                break;
            case  R.id.btn_two:
                Toast.makeText(MainActivity.this,"bbb",Toast.LENGTH_SHORT).show();
                changeView(1);
                break;
            case  R.id.btn_three:
                Toast.makeText(MainActivity.this,"ccc",Toast.LENGTH_SHORT).show();
                changeView(2);
                break;
            case  R.id.btn_four:
                Toast.makeText(MainActivity.this,"ddd",Toast.LENGTH_SHORT).show();
                changeView(3);
                break;


        }

    }

    class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {

        public MyFrageStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
            int currentItem = mViewPager.getCurrentItem();
            if (currentItem == currenttab){
                return;
            }
            imageMove(mViewPager.getCurrentItem());
            currenttab=mViewPager.getCurrentItem();

        }
    }

    private void imageMove(int moveToTab){
        int startPosition = 0;
        int movetoPosition = 0;

        startPosition = currenttab*(screenWidth/4);
        movetoPosition = moveToTab*(screenWidth/4);
        TranslateAnimation translateAnimation = new TranslateAnimation(startPosition,movetoPosition, 0, 0);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(200);
        imageviewOvertab.startAnimation(translateAnimation);

    }
    private void changeView(int desTab){
        mViewPager.setCurrentItem(desTab,true);
    }



}

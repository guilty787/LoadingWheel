package com.wix.gil.loadingicons;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity
{
    LoadingWheelView mLoadingWheelView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadingWheelView = (LoadingWheelView)findViewById(R.id.loadingWheel);

        mLoadingWheelView.start();


    }

    public void onFive(View v)
    {
        mLoadingWheelView.setNumberOfSegments(5);
    }

    public void onTen(View v)
    {
        mLoadingWheelView.setNumberOfSegments(10);
    }

    public void onFifteen(View v)
    {
        mLoadingWheelView.setNumberOfSegments(15);
    }

    public void onTwenty(View v)
    {
        mLoadingWheelView.setNumberOfSegments(20);
    }
}

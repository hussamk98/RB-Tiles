package com.tinyapps.rbtiles;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.tinyapps.rbtiles.Database.DatabaseHelper;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 0;
    private final Handler mHideHandler = new Handler();
    private View mContentView, levels;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Button play, exit, type1, type2, type3;
     float sp;
    float px;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_fullscreen);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.mainmenu);
        levels = findViewById(R.id.levels);


        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        play = findViewById(R.id.play);
        exit = findViewById(R.id.exit);
        type1 = findViewById(R.id.type1);
        type2 = findViewById(R.id.type2);
        type3 = findViewById(R.id.type3);
        exit.setBackgroundColor(Color.parseColor("#E77067"));
        final Handler h = new Handler();

        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

        Cursor cursor = databaseHelper.retrieve3x1();
        if(cursor.getCount() == 1){
            cursor.moveToFirst();
            try {
                if(Integer.parseInt(cursor.getString(1)) >= 130){

                    type2.setBackgroundColor(Color.parseColor("#47B7E7"));
                }else {
                    type2.setBackgroundColor(Color.parseColor("#E77067"));
                }
            }catch (NumberFormatException e){

            }
        }

        cursor = databaseHelper.retrieve3x2();
        if(cursor.getCount() == 1){
            cursor.moveToFirst();
            try {
                if(Integer.parseInt(cursor.getString(1)) >= 130){

                    type3.setBackgroundColor(Color.parseColor("#47B7E7"));
                }else {
                    type3.setBackgroundColor(Color.parseColor("#E77067"));
                }
            }catch (NumberFormatException e){

            }
        }
         px = type2.getTextSize();
       sp = px / getResources().getDisplayMetrics().scaledDensity;

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mContentView.setVisibility(View.GONE);
                levels.setVisibility(View.VISIBLE);
                levels.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                type1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), type3x1.class);
                        startActivity(intent);
                        FullscreenActivity.this.finish();
                    }
                });
                type2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

                        Cursor cursor = databaseHelper.retrieve3x1();
                        if(cursor.getCount() == 1){
                            cursor.moveToFirst();
                            try {
                                if(Integer.parseInt(cursor.getString(1)) >= 130){
                                    Intent intent = new Intent(getApplicationContext(), type3x2.class);
                                    startActivity(intent);
                                    FullscreenActivity.this.finish();
                                }else {

                                    type3.setTextSize(TypedValue.COMPLEX_UNIT_SP,sp);
                                    type3.setText("3x3");
                                    type2.setTextSize(TypedValue.COMPLEX_UNIT_SP,sp-20);
                                    type2.setText("Score 130 or more in 3x1");

                                }
                            }catch (NumberFormatException e){

                            }
                        }

                    }
                });
                type3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

                        Cursor cursor = databaseHelper.retrieve3x1();
                        if(cursor.getCount() == 1){
                            cursor.moveToFirst();
                            try {
                                if(Integer.parseInt(cursor.getString(1)) >= 130){
                                   cursor = databaseHelper.retrieve3x2();
                                   if(cursor.getCount() == 1){
                                       cursor.moveToFirst();
                                       try{
                                           if(Integer.parseInt(cursor.getString(1)) >= 130){
                                               Intent intent = new Intent(getApplicationContext(), type3x3.class);
                                               startActivity(intent);
                                               FullscreenActivity.this.finish();
                                           }
                                           else{
                                               type3.setTextSize(TypedValue.COMPLEX_UNIT_SP,sp-20);
                                               type3.setText("Score 130 or more in 3x2");
                                               type2.setTextSize(TypedValue.COMPLEX_UNIT_SP,sp);
                                               type2.setText("3x2");
                                           }
                                       }catch (NumberFormatException e){

                                       }
                                   }
                                }else {


                                    type3.setTextSize(TypedValue.COMPLEX_UNIT_SP,sp-20);
                                    type3.setText("You need to unlock 3x2 first");
                                    type2.setTextSize(TypedValue.COMPLEX_UNIT_SP,sp);
                                    type2.setText("3x2");

                                }
                            }catch (NumberFormatException e){

                            }
                        }


                    }
                });

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullscreenActivity.this.finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        levels.setVisibility(View.GONE);
        mContentView.setVisibility(View.VISIBLE);

        type2.setText("3x2");
        type2.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);

        type3.setText("3x3");
        type3.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }


}

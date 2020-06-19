package com.tinyapps.rbtiles;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.util.Pair;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tinyapps.rbtiles.Database.DatabaseHelper;

import java.util.Random;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class type3x2 extends AppCompatActivity {
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
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
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

    LinearLayout t1, t2, t3, t4, t5, t6, main3x2_1, main3x2_2;
    int score = 0;
    boolean stopAnim = false;
    Handler handler;
    Handler handler1;
    boolean init = false, init1 = false;
    Random random;
    int lastRand, rand, lastrand1;
    boolean t1b = false, t2b = false, t3b = false, t4b = false, t5b = false, t6b = false;
    TextView s, gameOver;
    int count = 0;
    LinearLayout l1;
    Button retry;
    Runnable r1, r2;
    boolean incorrect1 = false, incorrect2 = false, incorrect3 = false, incorrect4 = false, incorrect5 = false, incorrect6 = false;
    boolean countScore = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_type3x2);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);

        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        l1  = findViewById(R.id.l1);
        retry = findViewById(R.id.retry);
        gameOver = findViewById(R.id.gameoverScore);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        s = findViewById(R.id.score1);
        main3x2_1 = findViewById(R.id.main3x2_1);
        main3x2_2 = findViewById(R.id.main3x2_2);

        r1 = new Runnable() {
            @Override
            public void run() {
                gameOver();
            }
        };
       r2 = new Runnable() {
           @Override
           public void run() {
               gameOver();
           }
       };

        random = new Random();
        handler = new Handler();
        handler1 = new Handler();


        rand();
        rand1();

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnim = true;

                if (Score(t1b)) {
                    stopAnim = true;
                    handler.removeCallbacks(r1);
                    if(countScore)
                    score++;

                    s.setText(score + "");
                    rand();

                } else {
                    incorrect1 = true;
                    gameOver();

                }

            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnim = true;

                if (Score(t2b)) {
                    stopAnim = true;
                    handler.removeCallbacks(r1);
                    if(countScore)
                    score++;

                    s.setText(score + "");
                    rand();
                } else {
                    incorrect2 = true;
                    gameOver();

                }
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnim = true;

                if (Score(t3b)) {
                    stopAnim = true;
                    handler.removeCallbacks(r1);
                    if(countScore)
                    score++;

                    s.setText(score + "");
                    rand();
                } else {
                    incorrect3 = true;
                    gameOver();

                }
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnim = true;

                if (Score(t4b)) {
                    stopAnim = true;
                    handler1.removeCallbacks(r2);
                    if(countScore)
                    score++;

                    s.setText(score + "");
                    rand1();
                } else {
                    incorrect4 = true;
                    gameOver();

                }
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnim = true;

                if (Score(t5b)) {
                    stopAnim = true;
                    handler1.removeCallbacks(r2);
                    if(countScore)
                    score++;

                    s.setText(score + "");
                    rand1();
                } else {
                    incorrect5 = true;
                    gameOver();

                }
            }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnim = true;

                if (Score(t6b)) {
                    stopAnim = true;
                    handler1.removeCallbacks(r2);
                    if(countScore)
                    score++;

                    s.setText(score + "");
                    rand1();
                } else {
                    incorrect6 = true;
                    gameOver();

                }
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init = false;
                init1 = false;
                countScore = true;
                rand();
                rand1();
                count = 0;
                main3x2_1.setVisibility(View.VISIBLE);
                main3x2_2.setVisibility(View.VISIBLE);
                score = 0;
                s.setText(score +"");
                s.setVisibility(View.VISIBLE);
                gameOver.setText("Score: "+score);
                l1.setVisibility(View.GONE);
            }
        });

        TextView main = findViewById(R.id.main);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FullscreenActivity.class);
                startActivity(intent);
                type3x2.this.finish();
            }
        });
    }

    private void animBlink(LinearLayout t) {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setDuration(100);
        AnimationSet animation = new AnimationSet(true); //change to false
        animation.addAnimation(fadeOut);
        t.setAnimation(animation);
    }

    private void showGameOverMenu() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        TextView highScore = findViewById(R.id.highScore);
        Cursor cursor = databaseHelper.retrieve3x2();
        if(cursor.getCount() == 1){
            cursor.moveToFirst();
            try {
                if(Integer.parseInt(cursor.getString(1)) < score){
                    if(databaseHelper.update3x2(Integer.toString(score))){
                        highScore.setText("New High Score: " + score);
                    }
                }else {
                    highScore.setText("High Score: " + cursor.getString(1));
                }
            }catch (NumberFormatException e){

            }
        }


        main3x2_1.setVisibility(View.GONE);
        main3x2_2.setVisibility(View.GONE);
        handler.removeCallbacks(r1);
        handler1.removeCallbacks(r2);

        stopAnimation(t1);
        stopAnimation(t2);
        stopAnimation(t3);
        stopAnimation(t4);
        stopAnimation(t5);
        stopAnimation(t6);


        s.setVisibility(View.GONE);
        gameOver.setText("Score: "+score);
        l1.setVisibility(View.VISIBLE);
    }

    private void gameOver() {

        countScore = false;
        final Handler h = new Handler();


        if(incorrect1){
            incorrect1 = false;

                //rand()
                if(lastRand == 0){
                    animBlink(t2);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t2);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t2);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t2);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastRand == 1){
                    animBlink(t1);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t1);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t1);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t1);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastRand == 2){
                    animBlink(t3);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t3);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t3);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t3);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }

                //rand1()
                if(lastrand1 == 0 ){
                    animBlink(t4);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t4);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t4);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t4);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastrand1 == 1){
                    animBlink(t5);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t5);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t5);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t5);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastrand1 == 2){
                    animBlink(t6);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t6);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t6);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t6);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }


        }
        else if(incorrect2){
            incorrect2 = false;

                //rand()
                if(lastRand == 0){
                    animBlink(t2);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t2);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t2);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t2);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastRand == 1){
                    animBlink(t1);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t1);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t1);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t1);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastRand == 2){
                    animBlink(t3);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t3);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t3);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t3);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }

                //rand1()
                if(lastrand1 == 0 ){
                    animBlink(t4);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t4);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t4);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t4);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastrand1 == 1){
                    animBlink(t5);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t5);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t5);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t5);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastrand1 == 2){
                    animBlink(t6);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t6);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t6);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t6);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }

        }
        else  if(incorrect3){
            incorrect3 = false;

                //rand()
                if(lastRand == 0){
                    animBlink(t2);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t2);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t2);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t2);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastRand == 1){
                    animBlink(t1);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t1);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t1);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t1);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastRand == 2){
                    animBlink(t3);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t3);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t3);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t3);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }

                //rand1()
                if(lastrand1 == 0 ){
                    animBlink(t4);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t4);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t4);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t4);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastrand1 == 1){
                    animBlink(t5);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t5);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t5);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t5);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastrand1 == 2){
                    animBlink(t6);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t6);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t6);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t6);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }

        }
        else if(incorrect4){
            incorrect4 = false;

                //rand()
                if(lastRand == 0){
                    animBlink(t2);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t2);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t2);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t2);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastRand == 1){
                    animBlink(t1);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t1);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t1);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t1);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastRand == 2){
                    animBlink(t3);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t3);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t3);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t3);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }

                //rand1()
                if(lastrand1 == 0 ){
                    animBlink(t4);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t4);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t4);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t4);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastrand1 == 1){
                    animBlink(t5);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t5);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t5);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t5);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastrand1 == 2){
                    animBlink(t6);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t6);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t6);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t6);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }

        }
        else if(incorrect5){
            incorrect5 = false;

                //rand()
                if(lastRand == 0){
                    animBlink(t2);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t2);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t2);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t2);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastRand == 1){
                    animBlink(t1);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t1);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t1);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t1);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastRand == 2){
                    animBlink(t3);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t3);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t3);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t3);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }

                //rand1()
                if(lastrand1 == 0 ){
                    animBlink(t4);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t4);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t4);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t4);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastrand1 == 1){
                    animBlink(t5);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t5);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t5);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t5);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastrand1 == 2){
                    animBlink(t6);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t6);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t6);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t6);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }

        }
        else if(incorrect6){
            incorrect6 = false;

                //rand()
                if(lastRand == 0){
                    animBlink(t2);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t2);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t2);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t2);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastRand == 1){
                    animBlink(t1);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t1);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t1);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t1);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastRand == 2){
                    animBlink(t3);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t3);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t3);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t3);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }

                //rand1()
                if(lastrand1 == 0 ){
                    animBlink(t4);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t4);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t4);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t4);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastrand1 == 1){
                    animBlink(t5);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t5);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t5);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t5);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(lastrand1 == 2){
                    animBlink(t6);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t6);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t6);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t6);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }

        }
        else{

                if(t1b){
//            incorrect1 = false;
                    stopAnimation(t1);
                    animBlink(t1);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t1);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t1);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t1);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(t2b){
//            incorrect2 = false;
                    stopAnimation(t2);
                    animBlink(t2);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t2);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t2);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t2);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else  if(t3b){
//            incorrect3 = false;
                    stopAnimation(t3);
                    animBlink(t3);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t3);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t3);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t3);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }

                if(t4b){
                    stopAnimation(t4);
                    animBlink(t4);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t4);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t4);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t4);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if (t5b){
                    stopAnimation(t5);
                    animBlink(t5);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t5);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t5);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t5);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }
                else if(t6b){
                    stopAnimation(t6);
                    animBlink(t6);
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animBlink(t6);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animBlink(t6);
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            animBlink(t6);
                                            h.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {

                                                    showGameOverMenu();
                                                }
                                            }, 410);
                                        }
                                    }, 110);
                                }
                            }, 110);
                        }
                    }, 110);
                }

        }




    }

    public void stopAnimation(LinearLayout v) {
        v.clearAnimation();
        v.animate().cancel();
    }

    private void anim(final LinearLayout t) {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        if (score >= 0 && score < 15)
            fadeOut.setDuration(1500);
        else if (score >= 15 && score < 35)
            fadeOut.setDuration(1200);
        else if (score >= 35 && score < 55)
            fadeOut.setDuration(1000);
        else if (score >= 55 && score < 75)
            fadeOut.setDuration(800);
        else if (score >= 75 && score < 125)
            fadeOut.setDuration(650);
        else
            fadeOut.setDuration(500);


        AnimationSet animation = new AnimationSet(true); //change to false
        animation.addAnimation(fadeOut);
        t.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!stopAnim) {
                    gameOver();
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    private void anim1(final LinearLayout t) {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        if (score >= 0 && score < 15)
            fadeOut.setDuration(1500);
        else if (score >= 15 && score < 35)
            fadeOut.setDuration(1200);
        else if (score >= 35 && score < 55)
            fadeOut.setDuration(1000);
        else if (score >= 55 && score < 75)
            fadeOut.setDuration(800);
        else if (score >= 75 && score < 125)
            fadeOut.setDuration(650);
        else
            fadeOut.setDuration(500);


        AnimationSet animation = new AnimationSet(true); //change to false
        animation.addAnimation(fadeOut);
        t.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!stopAnim) {
                    gameOver();
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    private boolean Score(boolean tb) {

        return tb;
    }


    private void rand() {

        if (!init) {

            init = true;


            rand = random.nextInt(3);
            if (rand == 0) {
                lastRand = 0;
                setColors(t1, t2, t3);
                t1b = false;
                t2b = true;
                t3b = false;

            } else if (rand == 1) {
                lastRand = 1;
                setColors(t2, t1, t3);
                t1b = true;
                t2b = false;
                t3b = false;

            } else if (rand == 2) {
                lastRand = 2;
                setColors(t1, t3, t2);
                t1b = false;
                t2b = false;
                t3b = true;

            }
        } else {

            count = count +1;
            if(count == 1){

                if (lastrand1 == 0) {
                    stopAnimation(t4);
                    anim1(t4);
                    handler1Callback();
                }
                if (lastrand1 == 1) {
                    stopAnimation(t5);
                    anim1(t5);
                    handler1Callback();
                }
                if (lastrand1 == 2) {
                    stopAnimation(t6);
                    anim1(t6);
                    handler1Callback();
                }
            }

            handlerCallback();



            rand = random.nextInt(3);
            if (lastRand == rand) {
                rand();
            }
            if (lastRand == 0) {
                stopAnimation(t2);
            }
            if (lastRand == 1) {
                stopAnimation(t1);
            }
            if (lastRand == 2) {
                stopAnimation(t3);
            }

            if (rand == 0) {
                lastRand = 0;
                setColors(t1, t2, t3);
                t1b = false;
                t2b = true;
                t3b = false;


                anim(t2);

            } else if (rand == 1) {
                lastRand = 1;
                setColors(t2, t1, t3);
                t1b = true;
                t2b = false;
                t3b = false;
                anim(t1);
            } else if (rand == 2) {
                lastRand = 2;
                setColors(t1, t3, t2);
                t1b = false;
                t2b = false;
                t3b = true;
                anim(t3);
            }
        }


    }


    private void rand1() {

        if (!init1) {

            init1 = true;


            rand = random.nextInt(3);
            if (rand == 0) {
                lastrand1 = 0;
                setColors(t5, t4, t6);
                t5b = false;
                t4b = true;
                t6b = false;

            } else if (rand == 1) {
                lastrand1 = 1;
                setColors(t4, t5, t6);
                t5b = true;
                t4b = false;
                t6b = false;

            } else if (rand == 2) {
                lastrand1 = 2;
                setColors(t5, t6, t4);
                t4b = false;
                t5b = false;
                t6b = true;

            }
        } else {

            count = count + 1;
            if(count == 1){

                if (lastRand == 0) {
                    stopAnimation(t2);
                    anim(t2);
                    handlerCallback();
                }
                if (lastRand == 1) {
                    stopAnimation(t1);
                    anim(t1);
                    handlerCallback();
                }
                if (lastRand == 2) {
                    stopAnimation(t3);
                    anim(t3);
                    handlerCallback();
                }
            }


            handler1Callback();


            rand = random.nextInt(3);
            if (lastrand1 == rand) {
                rand1();
            }
            if (lastrand1 == 0) {
                stopAnimation(t4);
            }
            if (lastrand1 == 1) {
                stopAnimation(t5);
            }
            if (lastrand1 == 2) {
                stopAnimation(t6);
            }

            if (rand == 0) {
                lastrand1 = 0;
                setColors(t5, t4, t6);
                t5b = false;
                t4b = true;
                t6b = false;


                anim1(t4);

            } else if (rand == 1) {
                lastrand1 = 1;
                setColors(t4, t5, t6);
                t5b = true;
                t4b = false;
                t6b = false;

                anim1(t5);

            } else if (rand == 2) {
                lastrand1 = 2;
                setColors(t4, t6, t5);
                t4b = false;
                t5b = false;
                t6b = true;

                anim1(t6);
            }
        }


    }

    private void setColors(LinearLayout t1, LinearLayout t2, LinearLayout t3) {

        t1.setBackgroundColor(Color.parseColor("#E77067")); //red
        t2.setBackgroundColor(Color.parseColor("#47B7E7")); //blue
        t3.setBackgroundColor(Color.parseColor("#E77067")); //red

    }

    private void handlerCallback() {
        if (score >= 0 && score < 15) {
            handler.postDelayed(r1, 1450);
        } else if (score >= 15 && score < 35) {
            handler.postDelayed(r1, 1150);
        } else if (score >= 35 && score < 55) {
            handler.postDelayed(r1, 950);
        } else if (score >= 55 && score < 75) {
            handler.postDelayed(r1, 750);
        } else if (score >= 75 && score < 125) {
            handler.postDelayed(r1, 600);
        } else {
            handler.postDelayed(r1, 450);
        }
    }

    private void handler1Callback() {
        if (score >= 0 && score < 15) {
            handler1.postDelayed(r2, 1450);
        } else if (score >= 15 && score < 35) {
            handler1.postDelayed(r2, 1150);
        } else if (score >= 35 && score < 55) {
            handler1.postDelayed(r2, 950);
        } else if (score >= 55 && score < 75) {
            handler1.postDelayed(r2, 750);
        } else if (score >= 75 && score < 125) {
            handler1.postDelayed(r2, 600);
        } else {
            handler1.postDelayed(r2, 450);
        }
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

    @Override
    public void onBackPressed() {


    }
}

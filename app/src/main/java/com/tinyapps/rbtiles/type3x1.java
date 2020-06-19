package com.tinyapps.rbtiles;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
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
public class type3x1 extends AppCompatActivity {
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

    LinearLayout t1, t2, t3;
    Random random;
    int lastRand, rand;
    boolean t1b = false, t2b = false, t3b = false;
    int score = 0;
    TextView s;
    boolean stopAnim = false;
    Handler handler;
    boolean init = false;
    TextView gameOver;
    LinearLayout l1;
    Button retry;
    boolean incorrect1 = false, incorrect2 = false, incorrect3 = false;
    boolean countScore = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_type3x1);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        gameOver = findViewById(R.id.gameoverScore);
        l1 = findViewById(R.id.l1);
        s = findViewById(R.id.score);
        retry = findViewById(R.id.retry);
        random = new Random();
        handler = new Handler();


        rand();

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAnim = true;

                if (Score(t1b)) {
                    stopAnim = true;
                    handler.removeCallbacksAndMessages(null);
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
                    handler.removeCallbacksAndMessages(null);
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
                    handler.removeCallbacksAndMessages(null);
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

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init = false;
                countScore = true;
                rand();
                t1.setVisibility(View.VISIBLE);
                t2.setVisibility(View.VISIBLE);
                t3.setVisibility(View.VISIBLE);
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
                type3x1.this.finish();
            }
        });

    }

    public void stopAnimation(LinearLayout v) {
        v.clearAnimation();
        v.animate().cancel();
    }

    private void anim(final LinearLayout t) {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        if (score > 0 && score < 15)
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

            if (score >= 0 && score < 15) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameOver();
                    }
                }, 1450);
            } else if (score >= 15 && score < 35) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameOver();
                    }
                }, 1150);
            } else if (score >= 35 && score < 55) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameOver();
                    }
                }, 950);
            } else if (score >= 55 && score < 75) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameOver();
                    }
                }, 750);
            } else if (score >= 75 && score < 125) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameOver();
                    }
                }, 600);
            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameOver();
                    }
                }, 450);
            }


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

    private void animBlink(LinearLayout t) {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setDuration(100);
        AnimationSet animation = new AnimationSet(true); //change to false
        animation.addAnimation(fadeOut);
        t.setAnimation(animation);
    }

    private void gameOver() {
        countScore = false;
        final Handler h = new Handler();
        //TODO--

        if(incorrect1){
            incorrect1 = false;

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
        }
        else if(incorrect2){
            incorrect2 = false;

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
        }
        else if(incorrect3){
            incorrect3 = false;

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
        }

    }

    private void showGameOverMenu() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        TextView highScore = findViewById(R.id.highScore);
        Cursor cursor = databaseHelper.retrieve3x1();
        if(cursor.getCount() == 1){
            cursor.moveToFirst();
            try {
                if(Integer.parseInt(cursor.getString(1)) < score){
                    if(databaseHelper.update3x1(Integer.toString(score))){
                        highScore.setText("New High Score: " + score);
                    }
                }else {
                    highScore.setText("High Score: " + cursor.getString(1));
                }
            }catch (NumberFormatException e){

            }
        }
        t1.setVisibility(View.GONE);
        t2.setVisibility(View.GONE);
        t3.setVisibility(View.GONE);
        stopAnimation(t1);
        stopAnimation(t2);
        stopAnimation(t3);
        handler.removeCallbacksAndMessages(null);

        s.setVisibility(View.GONE);
        gameOver.setText("Score: "+score);
        l1.setVisibility(View.VISIBLE);
    }


    private void setColors(LinearLayout t1, LinearLayout t2, LinearLayout t3) {

        t1.setBackgroundColor(Color.parseColor("#E77067")); //red
        t2.setBackgroundColor(Color.parseColor("#47B7E7")); //blue
        t3.setBackgroundColor(Color.parseColor("#E77067")); //red

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

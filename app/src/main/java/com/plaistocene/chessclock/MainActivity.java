package com.plaistocene.chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long START_TIMER = 3000;

    private Button mPlayerOneTimer;
    private Button mPlayerTwoTimer;

    private Button mSettings;
    private Button mReset;

    private CountDownTimer playerOneCountDownTimer;
    private CountDownTimer playerTwoCountDownTimer;

    private boolean mPlayerOneTimerRunning;
    private boolean mPlayerTwoTimerRunning;

    private long mPlayerOneTimeLeft = START_TIMER;
    private long mPlayerTwoTimeLeft = START_TIMER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayerOneTimer = findViewById(R.id.button_playerOneClockTimer);
        mPlayerTwoTimer = findViewById(R.id.button_playerTwoClockTimer);

        mSettings = findViewById(R.id.button_settings);
        mReset = findViewById(R.id.button_reset);

        mPlayerOneTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerOnePauseTimer();
                playerTwoStartTimer();
            }
        });

        mPlayerTwoTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerTwoPauseTimer();
                playerOneStartTimer();
            }
        });

        mSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notImplemented();
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetBothTimers();
            }
        });

        updatePlayerOneCountdownTimerText();
        updatePlayerTwoCountdownTimerText();
    }

    private void playerOneStartTimer() {
        playerOneCountDownTimer = new CountDownTimer(mPlayerOneTimeLeft, 1000) {
            @Override
            public void onTick(long timeUntilFinish) {
                mPlayerOneTimeLeft = timeUntilFinish;
                updatePlayerOneCountdownTimerText();
            }

            @Override
            public void onFinish() {
                mPlayerOneTimerRunning = false;
                mPlayerOneTimer.setBackground(Drawable.createFromPath("@android:color/black"));
                resetBothTimers();
            }
        }.start();

        mPlayerOneTimerRunning = true;
    }

    private void playerTwoStartTimer() {
        playerTwoCountDownTimer = new CountDownTimer(mPlayerTwoTimeLeft, 1000) {
            @Override
            public void onTick(long timeUntilFinish) {
                mPlayerTwoTimeLeft = timeUntilFinish;
                updatePlayerTwoCountdownTimerText();
            }

            @Override
            public void onFinish() {
                mPlayerTwoTimerRunning = false;
                mPlayerTwoTimer.setBackground(Drawable.createFromPath("@android:color/black"));
                resetBothTimers();
            }
        }.start();

        mPlayerTwoTimerRunning = true;
    }

    private void updatePlayerOneCountdownTimerText() {
        int minutes = (int) (mPlayerOneTimeLeft / 1000) / 60;
        int seconds = (int) (mPlayerOneTimeLeft / 1000) % 60;

        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mPlayerOneTimer.setText(timeLeft);
    }

    private void updatePlayerTwoCountdownTimerText() {
        int minutes = (int) (mPlayerTwoTimeLeft / 1000) / 60;
        int seconds = (int) (mPlayerTwoTimeLeft / 1000) % 60;

        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mPlayerTwoTimer.setText(timeLeft);
    }

    private void playerOnePauseTimer() {
        if (mPlayerOneTimerRunning) {
            playerOneCountDownTimer.cancel();
            mPlayerOneTimerRunning = false;
        }
    }

    private void playerTwoPauseTimer() {
        if (mPlayerTwoTimerRunning) {
            playerTwoCountDownTimer.cancel();
            mPlayerTwoTimerRunning = false;
        }
    }

    private void resetBothTimers() {
        playerOnePauseTimer();
        playerTwoPauseTimer();
        mPlayerOneTimeLeft = START_TIMER;
        mPlayerTwoTimeLeft = START_TIMER;
        updatePlayerOneCountdownTimerText();
        updatePlayerTwoCountdownTimerText();
    }

    private void notImplemented() {

    }
}




































































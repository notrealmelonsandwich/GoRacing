package com.melonsandwich.goracing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private ImageView computerCar, playerCar, finishLine;
    private TextView timer, trafficLight;

    private Timer gameTimer = new Timer();
    private Timer computerTimer = new Timer();

    private int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        computerCar = (ImageView)findViewById(R.id.pc_car);
        playerCar = (ImageView)findViewById(R.id.player_car);
        finishLine = (ImageView)findViewById(R.id.finish_line);
        timer = (TextView) findViewById(R.id.timer_text);
        trafficLight = (TextView)findViewById(R.id.traffic_light);
    }

    public void startGameButton(View view)
    {
        startGameTimer();
        startComputerTimer();
    }

    private boolean hasReachedFinishLine(ImageView car)
    {
        if (car.getX() >= finishLine.getX())
        {
            computerTimer.cancel();
            gameTimer.cancel();
            return true;
        }
        return false;
    }

    public void carDriveAction(View view)
    {
        int offset = 0;

        if (state == 2)
            offset = 30;

        if (state == 1)
            offset = -30;

        playerCar.setX(playerCar.getX() + offset);

        if (playerCar.getX() >= finishLine.getX()) {
            computerTimer.cancel();
            gameTimer.cancel();
        }
    }

    public void startGameTimer() {
        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(() ->
                {
                    state += 1;
                    if (state > 2)
                        state = 1;

                    String text = "";
                    int color = 0;
                    switch (state)
                    {
                        case 1:
                            text = "RED";
                            color = Color.RED;
                            break;
                        case 2:
                            text = "GREEN";
                            color = Color.GREEN;
                            break;
                    }
                    trafficLight.setText(text);
                    trafficLight.setTextColor(color);
                });
            }
        };
        gameTimer.scheduleAtFixedRate(timerTask, 0, 3000);
    }

    public void startComputerTimer() {
        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(() ->
                {
                    if (state == 2)
                        computerCar.setX(computerCar.getX() + 300);

                    if (hasReachedFinishLine(computerCar))
                        Toast.makeText(getApplicationContext(), "YOU LOSE!", Toast.LENGTH_LONG).show();
                });
            }
        };
        computerTimer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
}
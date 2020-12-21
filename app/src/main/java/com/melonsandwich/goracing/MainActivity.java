package com.melonsandwich.goracing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGameButton(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void settingsButton(View view) {
        final EditText input = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Setting player name");
        builder.setMessage("Enter new player name");
        builder.setView(input);

        builder.setNegativeButton("Cancel", (dialog, which) -> Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_LONG).show());
        builder.setPositiveButton("Confirm", (dialog, which) ->
        {
            Editable text = input.getText();

            if (text.length() != 0)
            {
                Data.setPlayerName(text.toString());
                Toast.makeText(getApplicationContext(), "You changed the name to: " + Data.getPlayerName(), Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "You didn't input anything!", Toast.LENGTH_LONG).show();
            }
        });

        builder.show();
    }

    public void exitButton(View view)
    {
        Toast.makeText(getApplicationContext(), "thanks for playing not wishing you'd come back", Toast.LENGTH_LONG).show();
        System.exit(0);
    }
}
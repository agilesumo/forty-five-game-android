package com.agilesumo.fortyfive;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void playClicked(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }

    public void rulesClicked(View view){
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);

    }

    public void rankingsClicked(View view){
        Intent intent = new Intent(this, RankingsActivity.class);
        startActivity(intent);

    }
}

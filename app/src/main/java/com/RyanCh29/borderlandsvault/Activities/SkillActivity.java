package com.RyanCh29.borderlandsvault.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.RyanCh29.borderlandsvault.R;

/*
 * SKILL ACTIVITY HAS BEEN PUT ON HOLD FOR THE TIME BEING
 */

public class SkillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill);
    }
    public void startMainActivity(View view) {
        //start loading activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void startWikiActivity(View view) {
        //start loading activity
        Intent intent = new Intent(this, WikiActivity.class);
        startActivity(intent);
    }
    public void startInventoryActivity(View view) {
        //start loading activity
        Intent intent = new Intent(this, InventoryActivity.class);
        startActivity(intent);
    }
}

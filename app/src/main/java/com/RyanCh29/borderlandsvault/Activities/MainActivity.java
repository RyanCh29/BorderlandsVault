package com.RyanCh29.borderlandsvault.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.RyanCh29.borderlandsvault.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






    }

    public void startInventoryActivity(View view) {
        //start loading activity
        Intent intent = new Intent(this, InventoryActivity.class);

        startActivity(intent);

    }

}

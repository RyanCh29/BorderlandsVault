package com.RyanCh29.borderlandsvault.Inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.RyanCh29.borderlandsvault.R;

public class DisplayItemActivity extends AppCompatActivity {
    String[] content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);

        //check if the intent has any extras, if extras are sent the gear is supposed to be displayed
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            content = extras.getStringArray("Content");
            //check the type of the content(pistol,grenade,shield,etc), and adjust the background image accordingly
            //populate all the edit texts and etc.
        }


    }

}

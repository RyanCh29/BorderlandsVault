package com.RyanCh29.borderlandsvault.Inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.RyanCh29.borderlandsvault.R;

public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        ImageView image = findViewById(R.id.imageView);

        String type;
        //check if the intent has any extras, if extras are sent the gear is supposed to be displayed
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            type = extras.getString("Type");
            //check the type of the content(pistol,grenade,shield,etc), and adjust the background image accordingly
            //populate all the edit texts and etc.
        } else {
            type = "";
        }

        //load the proper picture
        if(type.equals("ar")) {
            image.setImageResource(R.drawable.weapon_ar);
            setContentView(R.layout.activity_add_weapon);

        } else if(type.equals("launcher")) {
            image.setImageResource(R.drawable.weapon_launcher);
            setContentView(R.layout.activity_add_weapon);

        }else if(type.equals("pistol")) {
            image.setImageResource(R.drawable.weapon_pistol);
            setContentView(R.layout.activity_add_weapon);

        }else if(type.equals("shotgun")) {
            image.setImageResource(R.drawable.weapon_shotgun);
            setContentView(R.layout.activity_add_weapon);

        }else if(type.equals("sniper")) {
            image.setImageResource(R.drawable.weapon_sniper);
            setContentView(R.layout.activity_add_weapon);

        }else if(type.equals("smg")) {
            image.setImageResource(R.drawable.weapon_smg);
            setContentView(R.layout.activity_add_weapon);

        }



    }

    public void saveGear(View view) {
        //append the gear to the file
    }
}

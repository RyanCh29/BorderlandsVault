package com.RyanCh29.borderlandsvault.Inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.RyanCh29.borderlandsvault.R;

public class AddItemActivity extends AppCompatActivity {
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        ImageView image = findViewById(R.id.imageView);

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

        }else if(type.equals("shield")) {
            image.setImageResource(R.drawable.weapon_smg);
            //TODO: complete activity_add_shield layout
            setContentView(R.layout.activity_add_shield);

        }else if(type.equals("grenade mod")) {
            image.setImageResource(R.drawable.weapon_smg);
            //TODO: complete activity_add_grenade_mod layout
            setContentView(R.layout.activity_add_grenade_mod);

        }else if(type.equals("class mod")) {
            image.setImageResource(R.drawable.weapon_smg);
            //TODO: complete activity_add_class_mod layout
            setContentView(R.layout.activity_add_class_mod);

        }else if(type.equals("artifact")) {
            image.setImageResource(R.drawable.weapon_smg);
            //TODO: complete activity_add_artifact layout
            setContentView(R.layout.activity_add_artifact);

        }



    }

    public void saveGear(View view) {
        //append the gear to the file
        //check type variable for the proper file
    }
}

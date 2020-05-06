package com.RyanCh29.borderlandsvault.Inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.RyanCh29.borderlandsvault.CSV.CSVManipulator;
import com.RyanCh29.borderlandsvault.R;

import java.text.DateFormat;
import java.util.Date;

public class AddItemActivity extends AppCompatActivity {
    private String type;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView image;

        //check if the intent has any extras, if extras are sent it is the gear type used to determine the proper layout
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            type = extras.getString("Type");

        } else {
            type = "";
        }

        //load the proper layout for the type
        if(type.equals("ar")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_ar);

        } else if(type.equals("launcher")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_launcher);

        }else if(type.equals("pistol")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_pistol);

        }else if(type.equals("shotgun")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_shotgun);

        }else if(type.equals("sniper")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_sniper);

        }else if(type.equals("smg")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_smg);

        }else if(type.equals("shield")) {
            //TODO: complete activity_add_shield layout
            setContentView(R.layout.activity_add_shield);
//            image.setImageResource(R.drawable.gear_shield);

        }else if(type.equals("grenade mod")) {
            //TODO: complete activity_add_grenade_mod layout
            setContentView(R.layout.activity_add_grenade_mod);
//            image.setImageResource(R.drawable.gear_grenade_mod);

        }else if(type.equals("class mod")) {
            //TODO: complete activity_add_class_mod layout
            setContentView(R.layout.activity_add_class_mod);
//            image.setImageResource(R.drawable.gear_class_mod);

        }else if(type.equals("artifact")) {
            //TODO: complete activity_add_artifact layout
            setContentView(R.layout.activity_add_artifact);
//            image.setImageResource(R.drawable.gear_artifact);

        }

        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveGear();
                //return to last activity
                finish();
            }
        });

    }

    public void saveGear() {
        String[] str;
        //get date
        Date dateobj = new Date();
        String date = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(dateobj);
//        System.out.println(date + "=====================================================================================================================");

        //set universal views
        EditText score_edit = findViewById(R.id.score_editText);
        EditText lvl_edit = findViewById(R.id.level_editText);
        EditText name_edit = findViewById(R.id.name_editText);

        String score = score_edit.getText().toString();
        String lvl = lvl_edit.getText().toString();
        String name = name_edit.getText().toString();
//        String type;


        EditText anoint_edit = findViewById(R.id.anoint_editText);
        String anoint = anoint_edit.getText().toString();

        //TODO: figure out how to implement multiple bonus stats
        String bonuses;

        //append the gear to the file
        //check type variable for the proper file

        if(type.equals("ar") || type.equals("launcher") || type.equals("pistol") || type.equals("shotgun") || type.equals("sniper") || type.equals("smg")) {

            //TODO: add checks for empty inputs

            //get data from editTexts
            EditText dmg_edit = findViewById(R.id.dmg_editText);
            String dmg = dmg_edit.getText().toString();

            EditText acc_edit = findViewById(R.id.acc_editText);
            String accuracy = acc_edit.getText().toString();

            EditText hand_edit = findViewById(R.id.hand_editText);
            String handling = hand_edit.getText().toString();

            EditText reload_edit = findViewById(R.id.reload_editText);
            String reload = reload_edit.getText().toString();

            EditText fr_edit = findViewById(R.id.fr_editText);
            String fireRate = fr_edit.getText().toString();

            EditText mag_edit = findViewById(R.id.mag_editText);
            String magazine = mag_edit.getText().toString();

            EditText element_edit = findViewById(R.id.element_editText);
            String element = element_edit.getText().toString();

            //add everything to string array
            str = new String[]{date,score,lvl, name, type, dmg, accuracy,handling,reload,fireRate,magazine,element,anoint};

            //append to file
            CSVManipulator csv = new CSVManipulator();
            csv.CSVAppend(getApplicationContext(),"Borderlands_User_CSV_weapons.csv", str);


        } else if(type.equals("shield")) {
            //TODO: complete activity_add_shield layout
            setContentView(R.layout.activity_add_shield);
//            image.setImageResource(R.drawable.gear_shield);

        }else if(type.equals("grenade mod")) {
            //TODO: complete activity_add_grenade_mod layout
            setContentView(R.layout.activity_add_grenade_mod);
//            image.setImageResource(R.drawable.gear_grenade_mod);

        }else if(type.equals("class mod")) {
            //TODO: complete activity_add_class_mod layout
            setContentView(R.layout.activity_add_class_mod);
//            image.setImageResource(R.drawable.gear_class_mod);

        }else if(type.equals("artifact")) {
            //TODO: complete activity_add_artifact layout
            setContentView(R.layout.activity_add_artifact);
//            image.setImageResource(R.drawable.gear_artifact);

        }



    }
}

package com.RyanCh29.borderlandsvault.Inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.RyanCh29.borderlandsvault.CSV.CSVManipulator;
import com.RyanCh29.borderlandsvault.R;

import java.text.DateFormat;
import java.util.Date;

import static android.text.InputType.TYPE_CLASS_TEXT;

public class AddItemActivity extends AppCompatActivity {
    private String type;
    private Button saveButton;
    private Button addBonusButton;
    private int numBonus = 0;

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
                //return to InventoryActivity
                //TODO: get content to update when returning to InventoryActivity
                finish();
            }
        });

        addBonusButton = findViewById(R.id.add_bonus_button);
        addBonusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addBonusStat();
            }
        });

    }

    public void saveGear() {
        //get date
        String date = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(new Date());

        //set universal views (views that are present in all gear types)
        EditText score_edit = findViewById(R.id.score_editText);
        EditText lvl_edit = findViewById(R.id.level_editText);
        EditText name_edit = findViewById(R.id.name_editText);

        String score = score_edit.getText().toString();
        String lvl = lvl_edit.getText().toString();
        String name = name_edit.getText().toString();

        //TODO: figure out how to implement multiple bonus stats
        //idea: have button that adds EditTexts to a scroll view, when save is pressed all bonus stats are recorded
        String[] bonuses;

        CSVManipulator csv = new CSVManipulator();

        //append the gear to the file
        //check type variable for the proper file

        if(type.equals("ar") || type.equals("launcher") || type.equals("pistol") || type.equals("shotgun") || type.equals("sniper") || type.equals("smg")) {
            csv.CSVAppend(getApplicationContext(),"Borderlands_User_CSV_weapons.csv", saveWeapon(date, score, lvl, name));

        } else if(type.equals("shield")) {
            csv.CSVAppend(getApplicationContext(),"Borderlands_User_CSV_shields.csv", saveShield(date, score, lvl, name));

        }else if(type.equals("grenade mod")) {
            csv.CSVAppend(getApplicationContext(),"Borderlands_User_CSV_grenadeMods.csv", saveGrenadeMod(date, score, lvl, name));

        }else if(type.equals("class mod")) {
            csv.CSVAppend(getApplicationContext(),"Borderlands_User_CSV_classMods.csv", saveClassMod(date, score, lvl, name));

        }else if(type.equals("artifact")) {
            csv.CSVAppend(getApplicationContext(),"Borderlands_User_CSV_artifacts.csv", saveArtifact(date, score, lvl, name));

        }

    }


    public String[] saveWeapon(String date, String score, String lvl, String name) {
        //TODO: add checks for empty inputs

        String[] str = new String[13+numBonus];

        str[0] = date;
        str[1] = score;
        str[2] = lvl;
        str[3] = name;
        str[4] = type;


        //{date,score,lvl, name, type, dmg, accuracy,handling,reload,fireRate,magazine,element,anoint};
        //get data from editTexts
        EditText dmg_edit = findViewById(R.id.dmg_editText);
        str[5] = dmg_edit.getText().toString();

        EditText acc_edit = findViewById(R.id.acc_editText);
        str[6] = acc_edit.getText().toString();

        EditText hand_edit = findViewById(R.id.hand_editText);
        str[7] = hand_edit.getText().toString();

        EditText reload_edit = findViewById(R.id.reload_editText);
        str[8] = reload_edit.getText().toString();

        EditText fr_edit = findViewById(R.id.fr_editText);
        str[9] = fr_edit.getText().toString();

        EditText mag_edit = findViewById(R.id.mag_editText);
        str[10] = mag_edit.getText().toString();

        EditText element_edit = findViewById(R.id.element_editText);
        str[11] = element_edit.getText().toString();

        EditText anoint_edit = findViewById(R.id.anoint_editText);
        str[12] = anoint_edit.getText().toString();

        //get all the bonus stats
        LinearLayout lay = findViewById(R.id.bonus_layout);
        for(int i=0; i<numBonus; i++) {
            EditText bonus = (EditText) lay.getChildAt(i+1);

            str[13+i] = bonus.getText().toString();
        }


        //add everything to string array
        System.out.println(str);
        return str;
    }
    public String[] saveShield(String date, String score, String lvl, String name) {
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

        EditText anoint_edit = findViewById(R.id.anoint_editText);
        String anoint = anoint_edit.getText().toString();

        //add everything to string array
        String[] str = new String[]{date,score,lvl, name, type, dmg, accuracy,handling,reload,fireRate,magazine,element,anoint};
        return str;
    }
    public String[] saveGrenadeMod(String date, String score, String lvl, String name) {
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

        EditText anoint_edit = findViewById(R.id.anoint_editText);
        String anoint = anoint_edit.getText().toString();

        //add everything to string array
        String[] str = new String[]{date,score,lvl, name, type, dmg, accuracy,handling,reload,fireRate,magazine,element,anoint};
        return str;


    }
    public String[] saveClassMod(String date, String score, String lvl, String name) {
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

        EditText anoint_edit = findViewById(R.id.anoint_editText);
        String anoint = anoint_edit.getText().toString();

        //add everything to string array
        String[] str = new String[]{date,score,lvl, name, type, dmg, accuracy,handling,reload,fireRate,magazine,element,anoint};
        return str;
    }
    public String[] saveArtifact(String date, String score, String lvl, String name) {
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

        EditText anoint_edit = findViewById(R.id.anoint_editText);
        String anoint = anoint_edit.getText().toString();

        //add everything to string array
        String[] str = new String[]{date,score,lvl, name, type, dmg, accuracy,handling,reload,fireRate,magazine,element,anoint};
        return str;
    }

    public void addBonusStat() {
        //when button is pressed a new edit text is added to the scroll view

        LinearLayout layout = findViewById(R.id.bonus_layout);
        numBonus++;
        EditText newBonus = new EditText(getApplicationContext());
        newBonus.setTag("bonus" + numBonus);
        newBonus.setHint("Enter Bonus");
        newBonus.setBackground(null);
        newBonus.setTextColor(Color.parseColor("#ffffff"));
        newBonus.setHintTextColor(Color.parseColor("#ffffff"));
        newBonus.setInputType(TYPE_CLASS_TEXT);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        newBonus.setLayoutParams(params);

        layout.addView(newBonus);


    }


}

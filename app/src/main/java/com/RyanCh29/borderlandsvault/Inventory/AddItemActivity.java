package com.RyanCh29.borderlandsvault.Inventory;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        if (extras != null) {
            type = extras.getString("Type");

        } else {
            type = "";
        }

        //load the proper layout for the type
        if (type.equals("ar")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_ar);

        } else if (type.equals("launcher")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_launcher);

        } else if (type.equals("pistol")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_pistol);

        } else if (type.equals("shotgun")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_shotgun);

        } else if (type.equals("sniper")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_sniper);

        } else if (type.equals("smg")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_smg);

        } else if (type.equals("shield")) {
            setContentView(R.layout.activity_add_shield);

        } else if (type.equals("grenade mod")) {
            setContentView(R.layout.activity_add_grenade_mod);

        } else if (type.equals("mod")) {
            setContentView(R.layout.activity_add_class_mod);
            image = findViewById(R.id.imageView);
            TextView upperText = findViewById(R.id.upper_text_textView);

            String character = extras.getString("Character");

            if(character.equals("amara")) {
                image.setImageResource(R.drawable.class_mod_amara);
                upperText.setText("LEGENDARY SIREN CLASS MOD");

            } else if(character.equals("fl4k")) {
                image.setImageResource(R.drawable.class_mod_fl4k);
                upperText.setText("LEGENDARY BEASTMASTER CLASS MOD");

            } else if(character.equals("moze")) {
                image.setImageResource(R.drawable.class_mod_moze);
                upperText.setText("LEGENDARY GUNNER CLASS MOD");

            } else if(character.equals("zane")) {
                image.setImageResource(R.drawable.class_mod_zane);
                upperText.setText("LEGENDARY OPERATIVE CLASS MOD");

            }

            String name = extras.getString("Name");
            EditText nameEditText = findViewById(R.id.name_editText);
            nameEditText.setText(name);

            String skill1 = extras.getString("Skill 1");
            ImageView s1 = findViewById(R.id.skill_1_imageView);
            int id1 = getApplicationContext().getResources().getIdentifier("drawable/" + skill1, null, getApplicationContext().getPackageName());
            s1.setImageResource(id1);

            String skill2 = extras.getString("Skill 2");
            ImageView s2 = findViewById(R.id.skill_2_imageView);
            int id2 = getApplicationContext().getResources().getIdentifier("drawable/" + skill2, null, getApplicationContext().getPackageName());
            s2.setImageResource(id2);

            String skill3 = extras.getString("Skill 3");
            ImageView s3 = findViewById(R.id.skill_3_imageView);
            int id3 = getApplicationContext().getResources().getIdentifier("drawable/" + skill3, null, getApplicationContext().getPackageName());
            s3.setImageResource(id3);

        } else if (type.equals("artifact")) {
            setContentView(R.layout.activity_add_artifact);

        }

        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveGear();
                //return to InventoryActivity
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

        CSVManipulator csv = new CSVManipulator();

        //append the gear to the file
        //check type variable for the proper file

        if (type.equals("ar") || type.equals("launcher") || type.equals("pistol") || type.equals("shotgun") || type.equals("sniper") || type.equals("smg")) {
            csv.CSVAppend(getApplicationContext(), "Borderlands_User_CSV_weapons.csv", saveWeapon(date, score, lvl, name));

        } else if (type.equals("shield")) {
            csv.CSVAppend(getApplicationContext(), "Borderlands_User_CSV_shields.csv", saveShield(date, score, lvl, name));

        } else if (type.equals("grenade mod")) {
            csv.CSVAppend(getApplicationContext(), "Borderlands_User_CSV_grenadeMods.csv", saveGrenadeMod(date, score, lvl, name));

        } else if (type.equals("class mod")) {
            csv.CSVAppend(getApplicationContext(), "Borderlands_User_CSV_classMods.csv", saveClassMod(date, score, lvl, name));

        } else if (type.equals("artifact")) {
            csv.CSVAppend(getApplicationContext(), "Borderlands_User_CSV_artifacts.csv", saveArtifact(date, score, lvl, name));

        }

    }


    public String[] saveWeapon(String date, String score, String lvl, String name) {
        String[] str = new String[13 + numBonus];

        str[0] = date;
        str[1] = score;
        str[2] = lvl;
        str[3] = name;
        str[4] = type;

        //{date,score,lvl, name, type, dmg, accuracy,handling,reload,fireRate,magazine,element,anoint,bonuses...};
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
        for (int i = 0; i < numBonus; i++) {
            EditText bonus = (EditText) lay.getChildAt(i + 1);

            str[13 + i] = bonus.getText().toString();
        }

        return str;
    }

    public String[] saveShield(String date, String score, String lvl, String name) {
        String[] str = new String[10 + numBonus];

        str[0] = date;
        str[1] = score;
        str[2] = lvl;
        str[3] = name;
        str[4] = type;

        //{date,score,lvl,name,type,capacity,delay,rate,element,anoint,bonuses...};
        //get data from editTexts
        EditText cap_edit = findViewById(R.id.cap_editText);
        str[5] = cap_edit.getText().toString();

        EditText delay_edit = findViewById(R.id.delay_editText);
        str[6] = delay_edit.getText().toString();

        EditText rate_edit = findViewById(R.id.rate_editText);
        str[7] = rate_edit.getText().toString();

        EditText element_edit = findViewById(R.id.element_editText);
        str[8] = element_edit.getText().toString();

        EditText anoint_edit = findViewById(R.id.anoint_editText);
        str[9] = anoint_edit.getText().toString();

        //get all the bonus stats
        LinearLayout lay = findViewById(R.id.bonus_layout);
        for (int i = 0; i < numBonus; i++) {
            EditText bonus = (EditText) lay.getChildAt(i + 1);

            str[10 + i] = bonus.getText().toString();
        }

        return str;
    }

    public String[] saveGrenadeMod(String date, String score, String lvl, String name) {
        String[] str = new String[9 + numBonus];

        str[0] = date;
        str[1] = score;
        str[2] = lvl;
        str[3] = name;
        str[4] = type;

        //{date,score,lvl, name, type, dmg, radius,element,anoint, bonuses...};
        //get data from editTexts
        EditText dmg_edit = findViewById(R.id.dmg_editText);
        str[5] = dmg_edit.getText().toString();

        EditText radius_edit = findViewById(R.id.radius_editText);
        str[6] = radius_edit.getText().toString();

        EditText element_edit = findViewById(R.id.element_editText);
        str[7] = element_edit.getText().toString();

        EditText anoint_edit = findViewById(R.id.anoint_editText);
        str[8] = anoint_edit.getText().toString();

        //get all the bonus stats
        LinearLayout lay = findViewById(R.id.bonus_layout);
        for (int i = 0; i < numBonus; i++) {
            EditText bonus = (EditText) lay.getChildAt(i + 1);

            str[9 + i] = bonus.getText().toString();
        }

        return str;
    }

    public String[] saveClassMod(String date, String score, String lvl, String name) {
        //TODO: figure out best way to implement adding class mod
        //idea 1: similar to adding weapons it opens multiple dialogs starting when class mod is pressed a dialog for choosing character is opened then for the specific class mod
        //idea 2: a blank class mod is opened and the user must choose the character and the skills from a drop down menu
        //TODO idea 3: user chooses the character in a dialog and then the specific class mod in another dialog

        String[] str = new String[13 + numBonus];

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
        for (int i = 0; i < numBonus; i++) {
            EditText bonus = (EditText) lay.getChildAt(i + 1);

            str[13 + i] = bonus.getText().toString();
        }

        return str;
    }

    public String[] saveArtifact(String date, String score, String lvl, String name) {
        String[] str = new String[16 + numBonus];

        str[0] = date;
        str[1] = score;
        str[2] = lvl;
        str[3] = name;
        str[4] = type;

        //get data from editTexts
        EditText line1_edit = findViewById(R.id.line1_left_editText);
        str[5] = line1_edit.getText().toString();

        EditText line_left = findViewById(R.id.line2_left_editText);
        str[6] = line_left.getText().toString();

        EditText line_right = findViewById(R.id.line2_right_editText);
        str[7] = line_right.getText().toString();

        EditText line3_left = findViewById(R.id.line3_left_editText);
        str[8] = line3_left.getText().toString();

        EditText line3_right = findViewById(R.id.line3_right_editText);
        str[9] = line3_right.getText().toString();

        EditText line4_left = findViewById(R.id.line4_left_editText);
        str[10] = line4_left.getText().toString();

        EditText line4_right = findViewById(R.id.line4_right_editText);
        str[11] = line4_right.getText().toString();

        EditText stat1_edit = findViewById(R.id.stat1_editText);
        str[12] = stat1_edit.getText().toString();

        EditText stat2_edit = findViewById(R.id.stat2_editText);
        str[13] = stat2_edit.getText().toString();

        EditText stat3_edit = findViewById(R.id.stat3_editText);
        str[14] = stat3_edit.getText().toString();

        EditText stat4_edit = findViewById(R.id.stat4_editText);
        str[15] = stat4_edit.getText().toString();

        //get all the bonus stats
        LinearLayout lay = findViewById(R.id.bonus_layout);
        for (int i = 0; i < numBonus; i++) {
            EditText bonus = (EditText) lay.getChildAt(i + 1);

            str[16 + i] = bonus.getText().toString();
        }

        return str;
    }

    public void addBonusStat() {
        //TODO: fix the layout of editTexts added, mainly the height of each one
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

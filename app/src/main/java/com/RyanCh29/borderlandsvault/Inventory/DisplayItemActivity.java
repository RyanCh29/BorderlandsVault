package com.RyanCh29.borderlandsvault.Inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.RyanCh29.borderlandsvault.R;

import java.util.Arrays;

import static android.text.InputType.TYPE_CLASS_TEXT;

public class DisplayItemActivity extends AppCompatActivity {
    String[] content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_display_item);

        //check if the intent has any extras, if extras are sent the gear is supposed to be displayed
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            content = extras.getStringArray("Content");
            //check the type of the content(pistol,grenade,shield,etc), and adjust the background image accordingly
            //populate all the edit texts and etc.
        }
        ImageView image;
        String type = content[4];

        if (type.equals("ar")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_ar);
            showWeapon();

        } else if (type.equals("launcher")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_launcher);
            showWeapon();

        } else if (type.equals("pistol")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_pistol);
            showWeapon();

        } else if (type.equals("shotgun")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_shotgun);
            showWeapon();

        } else if (type.equals("sniper")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_sniper);
            showWeapon();

        } else if (type.equals("smg")) {
            setContentView(R.layout.activity_add_weapon);
            image = findViewById(R.id.imageView);
            image.setImageResource(R.drawable.weapon_smg);
            showWeapon();

        } else if (type.equals("shield")) {
            setContentView(R.layout.activity_add_shield);
            showShield();

        } else if (type.equals("grenade mod")) {
            setContentView(R.layout.activity_add_grenade_mod);
            showGrenadeMod();

        } else if (type.equals("mod")) {
            //TODO: complete activity_add_class_mod layout
            setContentView(R.layout.activity_add_class_mod);
            image = findViewById(R.id.imageView);
            TextView upperText = findViewById(R.id.upper_text_textView);

            String character = content[5];
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

            String name = content[3];
            EditText nameEditText = findViewById(R.id.name_editText);
            nameEditText.setText(name);

            String skill1 = content[6];
            ImageView s1 = findViewById(R.id.skill_1_imageView);
            int id1 = getApplicationContext().getResources().getIdentifier("drawable/" + skill1, null, getApplicationContext().getPackageName());
            s1.setImageResource(id1);

            String skill2 = content[7];
            ImageView s2 = findViewById(R.id.skill_2_imageView);
            int id2 = getApplicationContext().getResources().getIdentifier("drawable/" + skill2, null, getApplicationContext().getPackageName());
            s2.setImageResource(id2);

            String skill3 = content[8];
            ImageView s3 = findViewById(R.id.skill_3_imageView);
            int id3 = getApplicationContext().getResources().getIdentifier("drawable/" + skill3, null, getApplicationContext().getPackageName());
            s3.setImageResource(id3);

        } else if (type.equals("artifact")) {
            setContentView(R.layout.activity_add_artifact);
            showArtifact();
        }

    }

    public void addBonusStat(String text) {
        //TODO: fix the layout of editTexts added, mainly the height of each one

        LinearLayout layout = findViewById(R.id.bonus_layout);

        EditText newBonus = new EditText(getApplicationContext());
        newBonus.setText(text);
        newBonus.setBackground(null);
        newBonus.setTextColor(Color.parseColor("#ffffff"));
        newBonus.setHintTextColor(Color.parseColor("#ffffff"));
        newBonus.setInputType(TYPE_CLASS_TEXT);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        newBonus.setLayoutParams(params);

        layout.addView(newBonus);


    }

    public void showWeapon() {

        EditText score_edit = findViewById(R.id.score_editText);
        score_edit.setText(content[1]);

        EditText lvl_edit = findViewById(R.id.level_editText);
        lvl_edit.setText(content[2]);

        EditText name_edit = findViewById(R.id.name_editText);
        name_edit.setText(content[3]);

        //{date,score,lvl, name, type, dmg, accuracy,handling,reload,fireRate,magazine,element,anoint,bonuses...};
        //put data into editTexts
        EditText dmg_edit = findViewById(R.id.dmg_editText);
        dmg_edit.setText(content[5]);

        EditText acc_edit = findViewById(R.id.acc_editText);
        acc_edit.setText(content[6]);

        EditText hand_edit = findViewById(R.id.hand_editText);
        hand_edit.setText(content[7]);

        EditText reload_edit = findViewById(R.id.reload_editText);
        reload_edit.setText(content[8]);

        EditText fr_edit = findViewById(R.id.fr_editText);
        fr_edit.setText(content[9]);

        EditText mag_edit = findViewById(R.id.mag_editText);
        mag_edit.setText(content[10]);

        EditText element_edit = findViewById(R.id.element_editText);
        element_edit.setText(content[11]);

        EditText anoint_edit = findViewById(R.id.anoint_editText);
        anoint_edit.setText(content[12]);


        //get all the bonus stats
        LinearLayout lay = findViewById(R.id.bonus_layout);
        for (int i = 13; i < content.length; i++) {
            addBonusStat(content[i]);

        }
    }


    public void showShield() {
        EditText score_edit = findViewById(R.id.score_editText);
        score_edit.setText(content[1]);

        EditText lvl_edit = findViewById(R.id.level_editText);
        lvl_edit.setText(content[2]);

        EditText name_edit = findViewById(R.id.name_editText);
        name_edit.setText(content[3]);

        //{date,score,lvl,name,type,capacity,delay,rate,element,anoint,bonuses...};
        //put data into editTexts
        EditText cap_edit = findViewById(R.id.cap_editText);
        cap_edit.setText(content[5]);

        EditText delay_edit = findViewById(R.id.delay_editText);
        delay_edit.setText(content[6]);

        EditText rate_edit = findViewById(R.id.rate_editText);
        rate_edit.setText(content[7]);

        EditText element_edit = findViewById(R.id.element_editText);
        element_edit.setText(content[8]);

        EditText anoint_edit = findViewById(R.id.anoint_editText);
        anoint_edit.setText(content[9]);


        //get all the bonus stats
        LinearLayout lay = findViewById(R.id.bonus_layout);
        for (int i = 10; i < content.length; i++) {
            addBonusStat(content[i]);

        }
    }

    public void showGrenadeMod() {
        EditText score_edit = findViewById(R.id.score_editText);
        score_edit.setText(content[1]);

        EditText lvl_edit = findViewById(R.id.level_editText);
        lvl_edit.setText(content[2]);

        EditText name_edit = findViewById(R.id.name_editText);
        name_edit.setText(content[3]);

        //{date,score,lvl, name, type, dmg, radius,element,anoint, bonuses...};
        //put data into editTexts
        EditText dmg_edit = findViewById(R.id.dmg_editText);
        dmg_edit.setText(content[5]);

        EditText radius_edit = findViewById(R.id.radius_editText);
        radius_edit.setText(content[6]);

        EditText element_edit = findViewById(R.id.element_editText);
        element_edit.setText(content[7]);

        EditText anoint_edit = findViewById(R.id.anoint_editText);
        anoint_edit.setText(content[8]);


        //get all the bonus stats
        LinearLayout lay = findViewById(R.id.bonus_layout);
        for (int i = 9; i < content.length; i++) {
            addBonusStat(content[i]);

        }
    }

    public void showClassMod() {
        //TODO: figure out best way to implement adding class mod
        EditText score_edit = findViewById(R.id.score_editText);
        score_edit.setText(content[1]);

        EditText lvl_edit = findViewById(R.id.level_editText);
        lvl_edit.setText(content[2]);

        EditText name_edit = findViewById(R.id.name_editText);
        name_edit.setText(content[3]);

        //TODO: display info

    }

    public void showArtifact() {
        EditText score_edit = findViewById(R.id.score_editText);
        score_edit.setText(content[1]);

        EditText lvl_edit = findViewById(R.id.level_editText);
        lvl_edit.setText(content[2]);

        EditText name_edit = findViewById(R.id.name_editText);
        name_edit.setText(content[3]);

        //get data from editTexts
        EditText line1_edit = findViewById(R.id.line1_left_editText);
        line1_edit.setText(content[5]);

        EditText line2_left = findViewById(R.id.line2_left_editText);
        line2_left.setText(content[6]);

        EditText line2_right = findViewById(R.id.line2_right_editText);
        line2_right.setText(content[7]);

        EditText line3_left = findViewById(R.id.line3_left_editText);
        line3_left.setText(content[8]);

        EditText line3_right = findViewById(R.id.line3_right_editText);
        line3_right.setText(content[9]);

        EditText line4_left = findViewById(R.id.line4_left_editText);
        line4_left.setText(content[10]);

        EditText line4_right = findViewById(R.id.line4_right_editText);
        line4_right.setText(content[11]);

        //stats
        EditText stat1_edit = findViewById(R.id.stat1_editText);
        stat1_edit.setText(content[12]);

        EditText stat2_edit = findViewById(R.id.stat2_editText);
        stat2_edit.setText(content[13]);

        EditText stat3_edit = findViewById(R.id.stat3_editText);
        stat3_edit.setText(content[14]);

        EditText stat4_edit = findViewById(R.id.stat4_editText);
        stat4_edit.setText(content[15]);

        //get all the bonus stats
        LinearLayout lay = findViewById(R.id.bonus_layout);
        for (int i = 16; i < content.length; i++) {
            addBonusStat(content[i]);

        }
    }




}

package com.RyanCh29.borderlandsvault.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.RyanCh29.borderlandsvault.CSV.CSVManipulator;
import com.RyanCh29.borderlandsvault.R;

import java.util.ArrayList;
import java.util.List;

public class WikiActivity extends AppCompatActivity {
    private List<String[]> weaponsDB; //0
    private List<String[]> shieldsDB; //1
    private List<String[]> grenadesDB; //2
    private List<String[]> classModsDB; //3
    private List<String[]> artifactsDB; //4
    private List<String[]> anointsDB; //5
    private int currentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki);
        CSVManipulator csvManipulator = new CSVManipulator();
        //read wiki/asset files
        anointsDB = csvManipulator.CSVReadAsset(getApplicationContext(), "Borderlands_Database_CSV_anoints.csv");
        artifactsDB = csvManipulator.CSVReadAsset(getApplicationContext(), "Borderlands_Database_CSV_artifacts.csv");
        classModsDB = csvManipulator.CSVReadAsset(getApplicationContext(), "Borderlands_Database_CSV_classMods.csv");
        grenadesDB = csvManipulator.CSVReadAsset(getApplicationContext(), "Borderlands_Database_CSV_grenades.csv");
        shieldsDB = csvManipulator.CSVReadAsset(getApplicationContext(), "Borderlands_Database_CSV_shields.csv");
        weaponsDB = csvManipulator.CSVReadAsset(getApplicationContext(), "Borderlands_Database_CSV_weapons.csv");
        currentContent = 0;
        //set default output to all legendary weapons
        showContent(weaponsDB);

    }

    public void startMainActivity(View view) {
        //start loading activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void startInventoryActivity(View view) {
        //start loading activity
        Intent intent = new Intent(this, InventoryActivity.class);
        startActivity(intent);
    }
    public void startSkillActivity(View view) {
        //start loading activity
        Intent intent = new Intent(this, SkillActivity.class);
        startActivity(intent);
    }
    public void  makeToast(String[] item) {
        StringBuilder text = new StringBuilder();
        for(String str: item){
            text.append(" ").append(str).append(",");
        }
        Toast toast = Toast.makeText(getApplicationContext(), text.toString(), Toast.LENGTH_LONG);
        toast.show();
    }

    public void showContent(final List<String[]> content) {

        //clear existing
        LinearLayout layout = findViewById(R.id.table);
        layout.removeAllViews();

        int size = content.size();

        Button[] buttons = new Button[size];

        for(int i=0; i<size;i++) {
//            System.out.println(i);
            buttons[i] = new Button(getApplicationContext());
            buttons[i].setText(content.get(i)[0]);
            buttons[i].setId(i);

            final int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Code here executes on main thread after user presses button
                    makeToast(content.get(finalI));
                }
            });
            layout.addView(buttons[i]);

        }
    }

    public void changeContentLeft(View view) {

        if(currentContent==0) {
            currentContent=5;
        }
        else {
            currentContent-=1;
        }
//        System.out.println("CURRENT" + currentContent + "--------------------------------------------");
        //change banner text

        //change content
        if(currentContent==0) {
            showContent(weaponsDB);
        } else if (currentContent==1) {
            showContent(shieldsDB);

        }else if (currentContent==2) {
            showContent(grenadesDB);

        }else if (currentContent==3) {
            showContent(classModsDB);

        }else if (currentContent==4) {
            showContent(artifactsDB);

        }else if (currentContent==5) {
            showContent(anointsDB);

        }

    }
    public void changeContentRight(View view) {
        if(currentContent==5) {
            currentContent=0;
        }
        else {
            currentContent+=1;
        }


        //change banner text

        //change content
        if(currentContent==0) {
            showContent(weaponsDB);
        } else if (currentContent==1) {
            showContent(shieldsDB);

        }else if (currentContent==2) {
            showContent(grenadesDB);

        }else if (currentContent==3) {
            showContent(classModsDB);

        }else if (currentContent==4) {
            showContent(artifactsDB);

        }else if (currentContent==5) {
            showContent(anointsDB);

        }

    }

    public List<String[]> search(String[] keywords) {
        List<String[]> results = new ArrayList<>();

        return results;
    }
}

package com.RyanCh29.borderlandsvault.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.RyanCh29.borderlandsvault.CSV.CSVManipulator;
import com.RyanCh29.borderlandsvault.R;
import java.util.List;

public class InventoryActivity extends AppCompatActivity {
    private List<String[]> gear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        CSVManipulator csvManipulator = new CSVManipulator();

        //read user files
        List<String[]> artifacts = csvManipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_artifacts.csv");
        List<String[]> classMods = csvManipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_classMods.csv");
        List<String[]> grenades = csvManipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_grenadeMods.csv");
        List<String[]> shields = csvManipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_shields.csv");
        List<String[]> weapons = csvManipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_weapons.csv");





        //when activity is loaded file is read and gear is displayed from file
        readGear();
        showGear(gear);
    }
    public void startMainActivity(View view) {
        //start main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void startWikiActivity(View view) {
        //start wiki activity
        Intent intent = new Intent(this, WikiActivity.class);
        startActivity(intent);
    }
    public void startSkillActivity(View view) {
        //start skill activity
        Intent intent = new Intent(this, SkillActivity.class);
        startActivity(intent);
    }

    public void readGear() {
        //read from all gear files
        CSVManipulator manipulator = new CSVManipulator();
//        gear = manipulator.CSVReadAsset(getApplicationContext(), "Borderlands_Database_CSV_shields.csv");
        gear = manipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_gear.csv");

        System.out.println("read gear called");

    }

    public void showGear(List<String[]> stuff) {
        Button[] buttons = new Button[stuff.size()];

        TableLayout layout = findViewById(R.id.table);
        layout.removeAllViewsInLayout();
        for(int i=0; i<stuff.size();i++) {
            buttons[i] = new Button(getApplicationContext());
            buttons[i].setText((i+1) + ": " + stuff.get(i)[0]);
            buttons[i].setId(i);

            if(i>0 && (i%2)==0){
//                System.out.println(i);
//                System.out.println("hi");

                TableRow tr = new TableRow(getApplicationContext());
                tr.addView(buttons[i-1]);
                tr.addView(buttons[i]);
                layout.addView(tr);
//                System.out.println(gear.size());
            }

        }
        System.out.println(stuff.size());

        if(stuff.size()-1%2==1) {
            System.out.println("odd size");

            TableRow tr = new TableRow(getApplicationContext());
            tr.addView(buttons[stuff.size()]);
            layout.addView(tr);

        }
    }

//    public void addGear(View view) {
//        open dialog box where user will enter info
//        InventoryDialog dialog = new InventoryDialog();
//        dialog.show(getSupportFragmentManager(), "add weapon");
//    }

    public void saveGear() {
        CSVManipulator manipulator = new CSVManipulator();
//        List<String[]> things = new ArrayList<>();
//        String[] str = new String[]{"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"};
//        things.add(str);
//        things.add(str);
//        things.add(str);
//        System.out.println(things.get(0)[0]);
//        manipulator.CSVWrite(getApplicationContext(), "Borderlands_User_CSV_gear.csv", things);
//        things = manipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_gear.csv");
//        showGear(things);



        System.out.println("save gear called");
    }


//    @Override
//    public void getTexts(String name, String dmg, String accuracy, String handling, String reload, String fireRate, String mag) {
//        gear.add(new String[]{name, dmg, accuracy, handling, reload, fireRate, mag, "1", "1", "1", "1"});
//        System.out.println("dialog finished");
//        for(String[] str: gear) {
//            System.out.println("new gear: " + str[0]);
//        }
//        showGear(gear);
//    }
}

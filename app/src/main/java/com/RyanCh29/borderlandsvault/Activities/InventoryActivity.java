package com.RyanCh29.borderlandsvault.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.RyanCh29.borderlandsvault.CSV.CSVManipulator;
import com.RyanCh29.borderlandsvault.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class InventoryActivity extends AppCompatActivity {
    List<String[]> gear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        testCSVRead();
    }
    public void startMainActivity(View view) {
        //start loading activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void startWikiActivity(View view) {
        //start loading activity
        Intent intent = new Intent(this, WikiActivity.class);
        startActivity(intent);
    }
    public void startSkillActivity(View view) {
        //start loading activity
        Intent intent = new Intent(this, SkillActivity.class);
        startActivity(intent);
    }

    public void addGear(View view) {
        testCSVWrite();
    }

    public void showGear(List<String[]> stuff) {
        Button[] buttons = new Button[stuff.size()];

        TableLayout layout = findViewById(R.id.table);
        layout.removeAllViewsInLayout();
//        layout.addView(new Button(getApplicationContext()));
//        System.out.println(shields.size());
        for(int i=0; i<stuff.size();i++) {
            buttons[i] = new Button(getApplicationContext());
            buttons[i].setText((i+1) + ": " + stuff.get(i)[0]);
            buttons[i].setId(i);

            if(i>0 && (i%2)==0){
                System.out.println(i);
                System.out.println("hi");

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
    public void testCSVRead() {
        CSVManipulator manipulator = new CSVManipulator();
        gear = manipulator.CSVReadAsset(getApplicationContext(), "Borderlands_Database_CSV_shields.csv");

        showGear(gear);
        System.out.println("show gear 1");

    }

    public void testCSVWrite() {
        CSVManipulator manipulator = new CSVManipulator();
        List<String[]> things = new ArrayList<>();
        String[] str = new String[]{"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"};
        things.add(str);
        things.add(str);
        things.add(str);
        System.out.println(things.get(0)[0]);
        manipulator.CSVWrite(getApplicationContext(), "Borderlands_User_CSV_gear.csv", things);
        things = manipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_gear.csv");
        showGear(things);
        System.out.println("show gear 2");
    }




}

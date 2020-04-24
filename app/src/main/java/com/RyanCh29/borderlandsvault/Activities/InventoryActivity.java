package com.RyanCh29.borderlandsvault.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.RyanCh29.borderlandsvault.CSV.CSVManipulator;
import com.RyanCh29.borderlandsvault.R;

import java.util.List;

public class InventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        testCSV();
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

    public void testCSV() {
        CSVManipulator manipulator = new CSVManipulator();
        System.out.println("hi");
        List<String[]> shields = manipulator.CSVRead(getApplicationContext(), "Borderlands_Database_CSV_shields.csv");

        Button[] buttons = new Button[shields.size()];

        TableLayout layout = findViewById(R.id.table);
//        layout.addView(new Button(getApplicationContext()));
//        System.out.println(shields.size());
        for(int i=0; i<shields.size();i++) {
            System.out.println(i);
            buttons[i] = new Button(getApplicationContext());
            buttons[i].setText((i+1) + ": " + shields.get(i)[0]);
            buttons[i].setId(i);

            if(i>0 && (i%2)==1){
                TableRow tr = new TableRow(getApplicationContext());
                tr.addView(buttons[i-1]);
                tr.addView(buttons[i]);
                layout.addView(tr);

            }


        }
    }



}

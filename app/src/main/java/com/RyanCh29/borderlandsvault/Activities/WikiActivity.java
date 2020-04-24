package com.RyanCh29.borderlandsvault.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.RyanCh29.borderlandsvault.CSV.CSVManipulator;
import com.RyanCh29.borderlandsvault.R;

import java.util.List;

public class WikiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki);
        testCSV();
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

    public void testCSV() {
        CSVManipulator manipulator = new CSVManipulator();

        List<String[]> weapons = manipulator.CSVRead(getApplicationContext(), "Borderlands_Database_CSV_weapons.csv");

        Button[] buttons = new Button[weapons.size()];

        LinearLayout layout = findViewById(R.id.vert_layout);
//        layout.addView(new Button(getApplicationContext()));
//        System.out.println(weapons.size());
        for(int i=0; i<weapons.size();i++) {
            System.out.println(i);
            buttons[i] = new Button(getApplicationContext());
            buttons[i].setText((i+1) + ": " + weapons.get(i)[0]);
            buttons[i].setId(i);
            layout.addView(buttons[i]);


        }
    }

}

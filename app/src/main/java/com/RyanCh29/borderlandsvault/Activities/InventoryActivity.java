package com.RyanCh29.borderlandsvault.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.RyanCh29.borderlandsvault.CSV.CSVManipulator;
import com.RyanCh29.borderlandsvault.R;

import java.util.ArrayList;
import java.util.List;

public class InventoryActivity extends AppCompatActivity {
    private List<String[]> all; //0
    private List<String[]> weapons; //1
    private List<String[]> shields; //2
    private List<String[]> grenades; //3
    private List<String[]> classMods; //4
    private List<String[]> artifacts; //5

    private int currentContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        CSVManipulator csvManipulator = new CSVManipulator();

        //read user files
        artifacts = csvManipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_artifacts.csv");
        classMods = csvManipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_classMods.csv");
        grenades = csvManipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_grenadeMods.csv");
        shields = csvManipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_shields.csv");
        weapons = csvManipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_weapons.csv");

        all = new ArrayList<>();
        all.addAll(weapons);
        all.addAll(shields);
        all.addAll(grenades);
        all.addAll(classMods);
        all.addAll(artifacts);

        //set default output to all items
        currentContent = 0;
        changeBanner("All Legendaries");
        showContent(all);
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
        TableLayout layout = findViewById(R.id.table);
        layout.removeAllViews();

        int size = content.size();

        if(size==0) {
            //TODO: make the empty button look like the empty slots in the in-game inventory
            Button emptyButton = new Button(getApplicationContext());
            emptyButton.setText("Empty");
            layout.addView(emptyButton);
        }
        else {
            Button[] buttons;
            if(size%2==1) {
                buttons = new Button[size+1];
            }
            else {
                buttons = new Button[size];
            }

            TableRow tr = new TableRow(getApplicationContext());
            for(int i=0; i<buttons.length;i++) {
                if(i<size) {
//                    System.out.println(i);
                    buttons[i] = new Button(getApplicationContext());
                    buttons[i].setText(content.get(i)[0]);
                    buttons[i].setId(i);

                    final int finalI = i;
                    buttons[i].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            //TODO: improve onClick functionality to better display the info
                            makeToast(content.get(finalI));
                        }
                    });

                    tr.addView(buttons[i]);
                }
                else if(i==size) {
                    //TODO: make the empty button look like the empty slots in the in-game inventory
                    buttons[i] = new Button(getApplicationContext());
                    buttons[i].setText("Empty");
                    tr.addView(buttons[i]);

                }

                if((i%2)==1){
                    layout.addView(tr);
                    tr = new TableRow(getApplicationContext());
                }
            }
        }
    }

    public void changeBanner(String newBanner) {
        TextView banner = findViewById(R.id.bannerText);
        banner.setText(newBanner);
    }

    public void changeContentLeft(View view) {

        if(currentContent==0) {
            currentContent=5;
        }
        else {
            currentContent-=1;
        }
        //change banner text

        //change content
        if(currentContent==0) {
            changeBanner("All Legendaries");
            showContent(all);
        } else if (currentContent==1) {
            changeBanner("Legendary Weapons");
            showContent(weapons);
        }else if (currentContent==2) {
            changeBanner("Legendary Shields");
            showContent(shields);
        }else if (currentContent==3) {
            changeBanner("Legendary Grenade Mods");
            showContent(grenades);
        }else if (currentContent==4) {
            changeBanner("Legendary Class Mods");
            showContent(classMods);
        }else if (currentContent==5) {
            changeBanner("Legendary Artifacts");
            showContent(artifacts);
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
            changeBanner("All Legendaries");
            showContent(all);
        } else if (currentContent==1) {
            changeBanner("Legendary Weapons");
            showContent(weapons);
        }else if (currentContent==2) {
            changeBanner("Legendary Shields");
            showContent(shields);
        }else if (currentContent==3) {
            changeBanner("Legendary Grenade Mods");
            showContent(grenades);
        }else if (currentContent==4) {
            changeBanner("Legendary Class Mods");
            showContent(classMods);
        }else if (currentContent==5) {
            changeBanner("Legendary Artifacts");
            showContent(artifacts);
        }

    }
    //TODO: implement search functionality
    public List<String[]> search(String[] keywords) {
        List<String[]> results = new ArrayList<>();

        return results;
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

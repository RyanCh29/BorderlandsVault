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
import com.RyanCh29.borderlandsvault.Dialogs.ChoiceDialog;
import com.RyanCh29.borderlandsvault.Dialogs.WeaponDialog;
import com.RyanCh29.borderlandsvault.Inventory.AddItemActivity;
import com.RyanCh29.borderlandsvault.Inventory.DisplayItemActivity;
import com.RyanCh29.borderlandsvault.R;

import java.util.ArrayList;
import java.util.List;

public class InventoryActivity extends AppCompatActivity implements ChoiceDialog.ChoiceDialogListener,
        WeaponDialog.WeaponDialogListener {
    private List<String[]> all; //0
    private List<String[]> weapons; //1
    private List<String[]> shields; //2
    private List<String[]> grenades; //3
    private List<String[]> classMods; //4
    private List<String[]> artifacts; //5

    private int currentContent;
    private int choice;


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

        choice = 0;
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
                            Intent intent = new Intent(getBaseContext(), DisplayItemActivity.class);
                            intent.putExtra("Content",content.get(finalI));
                            startActivity(intent);

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


    public void addGear(View view) {
        //open choice dialog
        //user chooses item type
        //new activity is created where the info is entered
        //info is sent back to inventory activity
        openDialog(choice); // choice dialog
        choice = 0; // reset choice
    }

    //give parameter for which dialog to open
    public void openDialog(int c) {

        if(c == 0) {
            ChoiceDialog choiceDialog = new ChoiceDialog();
            choiceDialog.show(getSupportFragmentManager(),"choice dialog");
        } else if(c == 1) {
            WeaponDialog weaponDialog = new WeaponDialog();
            weaponDialog.show(getSupportFragmentManager(),"weapon dialog");
        }

    }

    public void getChoice(int c) {
        choice = c;

        //if choice is 1 open weapon dialog
        if(choice == 1) {
            openDialog(1);
        }
        //else open the activity
        else if(choice == 2) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("Type","shield");
            startActivity(intent);
        }
        else if(choice == 3) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("Type","grenade mod");
            startActivity(intent);
        }
        else if(choice == 4) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("Type","class mod");
            startActivity(intent);
        }
        else if(choice == 5) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("Type","artifact");
            startActivity(intent);
        }
        else if(choice > 5) {
            if(choice == 11) {
                Intent intent = new Intent(this, AddItemActivity.class);
                intent.putExtra("Type","ar");
                startActivity(intent);
            }
            else if(choice == 22) {
                Intent intent = new Intent(this, AddItemActivity.class);
                intent.putExtra("Type","launcher");
                startActivity(intent);
            }
            else if(choice == 33) {
                Intent intent = new Intent(this, AddItemActivity.class);
                intent.putExtra("Type","pistol");
                startActivity(intent);
            }
            else if(choice == 44) {
                Intent intent = new Intent(this, AddItemActivity.class);
                intent.putExtra("Type","shotgun");
                startActivity(intent);
            }
            else if(choice == 55) {
                Intent intent = new Intent(this, AddItemActivity.class);
                intent.putExtra("Type","sniper");
                startActivity(intent);
            }
            else if(choice == 66) {
                Intent intent = new Intent(this, AddItemActivity.class);
                intent.putExtra("Type","smg");
                startActivity(intent);
            }
        }
    }

    public void saveGear(final int id) {
        //save gear on a new thread
        Thread save = new Thread() {
            public void run() {

                CSVManipulator manipulator = new CSVManipulator();

                if(id == 1) {
                    manipulator.CSVWrite(getApplicationContext(), "Borderlands_User_CSV_weapons.csv", weapons);
                } else if(id == 2) {
                    manipulator.CSVWrite(getApplicationContext(), "Borderlands_User_CSV_shields.csv", shields);
                } else if(id == 3) {
                    manipulator.CSVWrite(getApplicationContext(), "Borderlands_User_CSV_grenadeMods.csv", grenades);
                } else if(id == 4) {
                    manipulator.CSVWrite(getApplicationContext(), "Borderlands_User_CSV_classMods.csv", classMods);
                } else if(id == 5) {
                    manipulator.CSVWrite(getApplicationContext(), "Borderlands_User_CSV_artifacts.csv", artifacts);
                }

                System.out.println(id + " saved");
            }
        };

        save.start();

    }
    public void addWeapon(String score, String lvl, String name, String type, String dmg,
                          String accuracy, String handling, String reload, String fireRate, String magazine, String element) {
        StringBuilder text = new StringBuilder();

        text.append(score).append(", ");
        text.append(lvl).append(", ");
        text.append(name).append(", ");
        text.append(type).append(", ");

        text.append(dmg).append(", ");
        text.append(accuracy).append(", ");
        text.append(handling).append(", ");
        text.append(reload).append(", ");
        text.append(fireRate).append(", ");
        text.append(magazine).append(", ");
        text.append(element).append(", ");

//        text.append(anoint).append(", ");
//        text.append(date).append(", ");
//        text.append(fav).append(", ");
//        text.append(bonuses).append(", ");


        //saving gear
        //add to list
        String[] str = {score, lvl, name, "type", dmg, accuracy, handling, reload, fireRate, magazine, element, "anointment", "date", "favourite", "bonus 1"};
        weapons.add(str);
        all.add(str);

        //update content showing
        showContent(weapons);
        //call method to save
        saveGear(1);

        Toast toast = Toast.makeText(getApplicationContext(), text.toString(), Toast.LENGTH_LONG);
        toast.show();
    }
    public void addShield(String score, String lvl, String name, String cap,
                          String rechargeDelay, String rechargeRate, String element) {
        StringBuilder text = new StringBuilder();

        text.append(score).append(", ");
        text.append(lvl).append(", ");
        text.append(name).append(", ");

        text.append(cap).append(", ");
        text.append(rechargeDelay).append(", ");
        text.append(rechargeRate).append(", ");
        text.append(element).append(", ");

//        text.append(anoint).append(", ");
//        text.append(date).append(", ");
//        text.append(fav).append(", ");
//        text.append(bonuses).append(", ");

        //saving gear
        //add to list
        String[] str = {score, lvl, name, "shield", cap, rechargeDelay, rechargeRate, element, "anointment", "date", "favourite", "bonus 1"};
        shields.add(str);
        all.add(str);

        //update content showing
        showContent(shields);
        //call method to save
        saveGear(2);
        Toast toast = Toast.makeText(getApplicationContext(), text.toString(), Toast.LENGTH_LONG);
        toast.show();
    }
    public void addGrenadeMod(String score, String lvl, String name, String dmg,
                          String radius, String element) {
        StringBuilder text = new StringBuilder();

        text.append(score).append(", ");
        text.append(lvl).append(", ");
        text.append(name).append(", ");

        text.append(dmg).append(", ");
        text.append(radius).append(", ");
        text.append(element).append(", ");

//        text.append(anoint).append(", ");
//        text.append(date).append(", ");
//        text.append(fav).append(", ");
//        text.append(bonuses).append(", ");

        //saving gear
        //add to list
        String[] str = {score, lvl, name, "grenade mod", dmg, radius, element, "anointment", "date", "favourite", "bonus 1"};
        grenades.add(str);
        all.add(str);

        //update content showing
        showContent(grenades);
        //call method to save
        saveGear(3);

        Toast toast = Toast.makeText(getApplicationContext(), text.toString(), Toast.LENGTH_LONG);
        toast.show();
    }
    public void addClassMod(String score, String lvl, String name, String skill1,
                          String skill2, String skill3) {
        StringBuilder text = new StringBuilder();

        text.append(score).append(", ");
        text.append(lvl).append(", ");
        text.append(name).append(", ");

        text.append(skill1).append(", ");
        text.append(skill2).append(", ");
        text.append(skill3).append(", ");

//        text.append(date).append(", ");
//        text.append(fav).append(", ");
//        text.append(bonuses).append(", ");

        //saving gear
        //add to list
        String[] str = {score, lvl, name, "class mod", skill1, skill2, skill3, "date", "favourite", "bonus 1"};
        classMods.add(str);
        all.add(str);

        //update content showing
        showContent(classMods);
        //call method to save
        saveGear(4);

        Toast toast = Toast.makeText(getApplicationContext(), text.toString(), Toast.LENGTH_LONG);
        toast.show();
    }

    public void addArtifact(String score, String lvl, String name, String prefix) {
        StringBuilder text = new StringBuilder();

        text.append(score).append(", ");
        text.append(lvl).append(", ");
        text.append(name).append(", ");

        text.append(prefix).append(", ");

//        text.append(date).append(", ");
//        text.append(fav).append(", ");
//        text.append(bonuses).append(", ");

        //saving gear
        //add to list
        String[] str = {score, lvl, name, "artifact", prefix, "date", "favourite", "bonus 1"};
        artifacts.add(str);
        all.add(str);

        //update content showing
        showContent(artifacts);
        //call method to save
        saveGear(5);

        Toast toast = Toast.makeText(getApplicationContext(), text.toString(), Toast.LENGTH_LONG);
        toast.show();
    }
}

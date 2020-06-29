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
import com.RyanCh29.borderlandsvault.Dialogs.CharacterDialog;
import com.RyanCh29.borderlandsvault.Dialogs.ChoiceDialog;
import com.RyanCh29.borderlandsvault.Dialogs.WeaponDialog;
import com.RyanCh29.borderlandsvault.Inventory.AddItemActivity;
import com.RyanCh29.borderlandsvault.Inventory.DisplayItemActivity;
import com.RyanCh29.borderlandsvault.R;

import java.util.ArrayList;
import java.util.List;

public class InventoryActivity extends AppCompatActivity implements ChoiceDialog.ChoiceDialogListener,
        WeaponDialog.WeaponDialogListener, CharacterDialog.CharacterDialogListener {
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

        readFiles();

        choice = 0;
        //set default output to all items
        currentContent = 0;
        changeBanner("All Legendaries");
        showContent(all);
    }

    public void readFiles() {
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
    }

    @Override
    protected void onResume() {
        //allows for lists to be updated after adding an item
        super.onResume();
        readFiles();
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
        System.out.println(text.toString());
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
                    buttons[i].setText(content.get(i)[3]);
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
        } else if(c == 2) {
            CharacterDialog characterDialog = new CharacterDialog();
            characterDialog.show(getSupportFragmentManager(),"character dialog");
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
//            Intent intent = new Intent(this, AddItemActivity.class);
//            intent.putExtra("Type","class mod");
//            startActivity(intent);
            openDialog(2);
        }
        else if(choice == 5) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("Type","artifact");
            startActivity(intent);
        }
        else if(choice > 5 && choice < 100) {
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
        } else if(choice > 100) {
            if(choice == 111) {
                Intent intent = new Intent(this, AddItemActivity.class);
                intent.putExtra("Type","amara");
                startActivity(intent);
            }
            else if(choice == 222) {
                Intent intent = new Intent(this, AddItemActivity.class);
                intent.putExtra("Type","fl4k");
                startActivity(intent);
            }
            else if(choice == 333) {
                Intent intent = new Intent(this, AddItemActivity.class);
                intent.putExtra("Type","moze");
                startActivity(intent);
            }
            else if(choice == 444) {
                Intent intent = new Intent(this, AddItemActivity.class);
                intent.putExtra("Type","zane");
                startActivity(intent);
            }
        }
    }
}

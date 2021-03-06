package com.RyanCh29.borderlandsvault.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.RyanCh29.borderlandsvault.CSV.CSVManipulator;
import com.RyanCh29.borderlandsvault.Dialogs.CharacterDialog;
import com.RyanCh29.borderlandsvault.Dialogs.ChoiceDialog;
import com.RyanCh29.borderlandsvault.Dialogs.ClassModDialog;
import com.RyanCh29.borderlandsvault.Dialogs.RemoveDialog;
import com.RyanCh29.borderlandsvault.Dialogs.WeaponDialog;
import com.RyanCh29.borderlandsvault.Inventory.AddItemActivity;
import com.RyanCh29.borderlandsvault.Inventory.DisplayItemActivity;
import com.RyanCh29.borderlandsvault.R;

import java.util.ArrayList;
import java.util.List;

public class InventoryActivity extends AppCompatActivity implements ChoiceDialog.ChoiceDialogListener,
        WeaponDialog.WeaponDialogListener, CharacterDialog.CharacterDialogListener,
        ClassModDialog.ClassModDialogListener, RemoveDialog.RemoveDialogListener {
    private List<String[]> weapons; //0
    private List<String[]> shields; //1
    private List<String[]> grenades; //2
    private List<String[]> classMods; //3
    private List<String[]> artifacts; //4

    private int currentContent;
    private String choice;

    private int removalList;
    private int removalItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        readFiles();

        choice = "";
        //set default output to weapons
        currentContent = 0;
        changeBanner("Legendary Weapons");
        showContent(weapons);
    }

    public void readFiles() {
        CSVManipulator csvManipulator = new CSVManipulator();

        //read user files
        artifacts = csvManipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_artifacts.csv");
        classMods = csvManipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_classMods.csv");
        grenades = csvManipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_grenadeMods.csv");
        shields = csvManipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_shields.csv");
        weapons = csvManipulator.CSVReadFile(getApplicationContext(), "Borderlands_User_CSV_weapons.csv");
    }

    @Override
    protected void onResume() {
        //allows for lists to be updated after adding an item
        super.onResume();
        readFiles();
        //set default output to all items
        currentContent = 0;
        changeBanner("Legendary Weapons");
        showContent(weapons);


    }

//    public void startMainActivity(View view) {
        //start main activity
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }

//    public void startWikiActivity(View view) {
        //start wiki activity
//        Intent intent = new Intent(this, WikiActivity.class);
//        startActivity(intent);
//    }

//    public void startSkillActivity(View view) {
        //start skill activity
//        Intent intent = new Intent(this, SkillActivity.class);
//        startActivity(intent);
//    }

    public void  makeToast(String[] item) {
        StringBuilder text = new StringBuilder();
        for(String str: item){
            text.append(" ").append(str).append(",");
        }
        System.out.println(text.toString());
        Toast toast = Toast.makeText(getApplicationContext(), text.toString(), Toast.LENGTH_LONG);
        toast.show();
    }

    public int getPadding(int pToDp) {
        float scale = getResources().getDisplayMetrics().density;
        int padding = (int) (pToDp*scale + 0.5f);
        return padding;
    }

    public void showContent(final List<String[]> content) {
        //clear existing
        LinearLayout layout = findViewById(R.id.linear_layoutV);
        layout.removeAllViews();
        LinearLayout lin = new LinearLayout(getApplicationContext());

        int size = content.size();

        if(size==0) {
            //TODO: make the empty button look like the empty slots in the in-game inventory
            ImageButton[] emptyButton = new ImageButton[2];

            for (int i = 0; i < 2; i++) {
                emptyButton[i] = new ImageButton(getApplicationContext());

                emptyButton[i] .setImageResource(R.drawable.inventory_empty_item_background);
                emptyButton[i] .setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,1));
                emptyButton[i] .setAdjustViewBounds(true);
                emptyButton[i] .setBackgroundResource(0);
                int padding = getPadding(4);
                emptyButton[i] .setPadding(padding,padding,padding,padding);
                emptyButton[i] .setScaleType(ImageView.ScaleType.FIT_CENTER);


                TextView text = new TextView(getApplicationContext());
                text.setText("EMPTY");
                text.setTextColor(Color.parseColor("#ffffff"));

                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                text.setLayoutParams(layoutParams);

                RelativeLayout rel = new RelativeLayout(getApplicationContext());
                rel.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1));

                rel.addView(emptyButton[i]);
                rel.addView(text);
                lin.addView(rel);


            }
            layout.addView(lin);

        }
        else {

            ImageButton[] buttons;
            if(size%2==1) {
                buttons = new ImageButton[size+1];
            }
            else {
                buttons = new ImageButton[size];
            }

//            LinearLayout lin = new LinearLayout(getApplicationContext());
            for(int i=0; i<buttons.length;i++) {
                if(i<size) {
//                    System.out.println(i);
                    buttons[i] = new ImageButton(getApplicationContext());
                    buttons[i].setImageResource(R.drawable.inventory_item_background);
                    buttons[i].setId(i);
                    buttons[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,1));
                    buttons[i].setAdjustViewBounds(true);
                    buttons[i].setBackgroundResource(0);
                    int padding = getPadding(4);
                    buttons[i].setPadding(padding,padding,padding,padding);
                    buttons[i].setScaleType(ImageView.ScaleType.FIT_CENTER);

                    //TODO: set text over top the image buttons
                    //centered
                    TextView nameText = new TextView(getApplicationContext());
                    nameText.setText(content.get(i)[3]);
                    nameText.setTextColor(Color.parseColor("#ffffff"));

                    RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    textParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

                    nameText.setLayoutParams(textParams);

                    //TODO: set text over top the image buttons
                    //bottom left
                    TextView scoreText = new TextView(getApplicationContext());
                    scoreText.setText(content.get(i)[1]);
                    scoreText.setTextColor(Color.parseColor("#ffffff"));

                    RelativeLayout.LayoutParams scoreParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    scoreParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);

                    padding = getPadding(8);
                    scoreText.setPadding(padding,padding,padding,padding);

                    scoreText.setLayoutParams(scoreParams);

                    final int finalI = i;

                    buttons[i].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), DisplayItemActivity.class);
                            intent.putExtra("Content",content.get(finalI));
                            startActivity(intent);

                            makeToast(content.get(finalI));
                        }
                    });

                    buttons[i].setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            //open a conformation dialog
                            //check for conformation
                            //remove object at 'i' from the content list and rewrite to file

                            removalItem = finalI;

                            //4
                            //find and remove item from proper list
                            String type = content.get(removalItem)[4];
                            if (type.equals("ar") || type.equals("launcher") || type.equals("pistol") || type.equals("shotgun") || type.equals("sniper") || type.equals("smg")) {
                                removalList = 0;
                            } else if (type.equals("shield")) {
                                removalList = 1;

                            } else if (type.equals("grenade mod")) {
                                removalList = 2;

                            } else if (type.equals("mod")) {
                                removalList = 3;

                            } else if (type.equals("artifact")) {
                                removalList = 4;
                            }

                            openDialog("remove");
                            return true;
                        }
                    });

                    RelativeLayout rel = new RelativeLayout(getApplicationContext());
                    rel.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1));

                    rel.addView(buttons[i]);
                    rel.addView(nameText);
                    rel.addView(scoreText);
                    lin.addView(rel);
                }
                else if(i==size) {
                    //make empty button
                    buttons[i] = new ImageButton(getApplicationContext());
                    buttons[i].setImageResource(R.drawable.inventory_empty_item_background);
                    buttons[i].setId(i);
                    buttons[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,1));
                    buttons[i].setAdjustViewBounds(true);
                    buttons[i].setBackgroundResource(0);
                    int padding = getPadding(4);
                    buttons[i].setPadding(padding,padding,padding,padding);
                    buttons[i].setScaleType(ImageView.ScaleType.FIT_CENTER);

                    TextView text = new TextView(getApplicationContext());
                    text.setText("EMPTY");
                    text.setTextColor(Color.parseColor("#ffffff"));

                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

                    text.setLayoutParams(layoutParams);

                    RelativeLayout rel = new RelativeLayout(getApplicationContext());
                    rel.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,1));

                    rel.addView(buttons[i]);
                    rel.addView(text);
                    lin.addView(rel);

                }

                if((i%2)==1){
                    lin.setOrientation(LinearLayout.HORIZONTAL);

                    layout.addView(lin);
                    lin = new LinearLayout(getApplicationContext());
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
            currentContent=4;
        }
        else {
            currentContent-=1;
        }
        //change content
        if (currentContent==0) {
            changeBanner("Legendary Weapons");
            showContent(weapons);
        }else if (currentContent==1) {
            changeBanner("Legendary Shields");
            showContent(shields);
        }else if (currentContent==2) {
            changeBanner("Legendary Grenade Mods");
            showContent(grenades);
        }else if (currentContent==3) {
            changeBanner("Legendary Class Mods");
            showContent(classMods);
        }else if (currentContent==4) {
            changeBanner("Legendary Artifacts");
            showContent(artifacts);
        }

    }
    public void changeContentRight(View view) {
        if(currentContent==4) {
            currentContent=0;
        }
        else {
            currentContent+=1;
        }
        //change content
        if (currentContent==0) {
            changeBanner("Legendary Weapons");
            showContent(weapons);
        }else if (currentContent==1) {
            changeBanner("Legendary Shields");
            showContent(shields);
        }else if (currentContent==2) {
            changeBanner("Legendary Grenade Mods");
            showContent(grenades);
        }else if (currentContent==3) {
            changeBanner("Legendary Class Mods");
            showContent(classMods);
        }else if (currentContent==4) {
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
        openDialog("add"); // choice dialog
        choice = ""; // reset choice
    }

    public void removeGear() {
        CSVManipulator csv = new CSVManipulator();

        if (removalList == 0) {
            //find and remove item from weapons list
            weapons.remove(removalItem);
            csv.CSVWrite(getApplicationContext(), "Borderlands_User_CSV_weapons.csv", weapons);
            showContent(weapons);

        } else if (removalList == 1) {
            shields.remove(removalItem);
            csv.CSVWrite(getApplicationContext(), "Borderlands_User_CSV_shields.csv", shields);
            showContent(shields);

        } else if (removalList == 2) {
            grenades.remove(removalItem);
            csv.CSVWrite(getApplicationContext(), "Borderlands_User_CSV_grenadeMods.csv", grenades);
            showContent(grenades);

        } else if (removalList == 3) {
            classMods.remove(removalItem);
            csv.CSVWrite(getApplicationContext(), "Borderlands_User_CSV_classMods.csv", classMods);
            showContent(classMods);

        } else if (removalList == 4) {
            artifacts.remove(removalItem);
            csv.CSVWrite(getApplicationContext(), "Borderlands_User_CSV_artifacts.csv", artifacts);
            showContent(artifacts);
        }

        removalList = 0;
        removalItem = 0;
    }

    //give parameter for which dialog to open
    public void openDialog(String c) {

        if(c.equals("add")) {
            ChoiceDialog choiceDialog = new ChoiceDialog();
            choiceDialog.show(getSupportFragmentManager(),"choice dialog");
        } else if(c.equals("weapon")) {
            WeaponDialog weaponDialog = new WeaponDialog();
            weaponDialog.show(getSupportFragmentManager(),"weapon dialog");
        } else if(c.equals("class")) {
            CharacterDialog characterDialog = new CharacterDialog();
            characterDialog.show(getSupportFragmentManager(),"character dialog");
        } else if(c.equals("amara")) {
            ClassModDialog modDialog = new ClassModDialog();

            Bundle arg = new Bundle();
            arg.putChar("character", 'a');
            modDialog.setArguments(arg);

            modDialog.show(getSupportFragmentManager(),"class mod dialog");

        } else if(c.equals("fl4k")) {
            ClassModDialog modDialog = new ClassModDialog();

            Bundle arg = new Bundle();
            arg.putChar("character", 'f');
            modDialog.setArguments(arg);

            modDialog.show(getSupportFragmentManager(),"class mod dialog");
        } else if(c.equals("moze")) {
            ClassModDialog modDialog = new ClassModDialog();

            Bundle arg = new Bundle();
            arg.putChar("character", 'm');
            modDialog.setArguments(arg);

            modDialog.show(getSupportFragmentManager(),"class mod dialog");
        } else if(c.equals("zane")) {
            ClassModDialog modDialog = new ClassModDialog();

            Bundle arg = new Bundle();
            arg.putChar("character", 'z');
            modDialog.setArguments(arg);

            modDialog.show(getSupportFragmentManager(),"class mod dialog");
        } else if(c.equals("remove")) {
            RemoveDialog removeDialog = new RemoveDialog();
            removeDialog.show(getSupportFragmentManager(),"Remove dialog");
        }

    }


    public void getChoice(String c) {
        choice = c;

        //if choice is 1 open weapon dialog
        if(choice.equals("weapon")) {
            openDialog(choice);
        } else if(choice.equals("shield")) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("Type","shield");
            startActivity(intent);
        } else if(choice.equals("grenade")) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("Type","grenade mod");
            startActivity(intent);
        } else if(choice.equals("class")) {
            openDialog(choice);
        } else if(choice.equals("artifact")) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("Type","artifact");
            startActivity(intent);
        }
        //weapon types
        else if(choice.equals("ar")) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("Type","ar");
            startActivity(intent);
        }
        else if(choice.equals("launcher")) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("Type","launcher");
            startActivity(intent);
        }
        else if(choice.equals("pistol")) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("Type","pistol");
            startActivity(intent);
        }
        else if(choice.equals("shotgun")) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("Type","shotgun");
            startActivity(intent);
        }
        else if(choice.equals("sniper")) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("Type","sniper");
            startActivity(intent);
        }
        else if(choice.equals("smg")) {
            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("Type","smg");
            startActivity(intent);
        }
        //remove dialog
        else if(choice.equals("remove")) {
            removeGear();
        } else if(choice.equals("do not remove")) {

        }
        //characters
        else if(choice.equals("amara")) {
            openDialog(choice);
        }
        else if(choice.equals("fl4k")) {
            openDialog(choice);
        }
        else if(choice.equals("moze")) {
            openDialog(choice);
        }
        else if(choice.equals("zane")) {
            openDialog(choice);
        }
        else {
            int id = getApplicationContext().getResources().getIdentifier(choice, "array", getApplicationContext().getPackageName());
            String[] mod = getResources().getStringArray(id);

            Intent intent = new Intent(this, AddItemActivity.class);
            intent.putExtra("Type","mod");
            intent.putExtra("Character",mod[0]);
            intent.putExtra("Name",mod[1]);
            intent.putExtra("Skill 1", mod[2]);
            intent.putExtra("Skill 2", mod[3]);
            intent.putExtra("Skill 3", mod[4]);
            startActivity(intent);
        }
    }
    //TODO: add functionality to edit items
}

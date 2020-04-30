package com.RyanCh29.borderlandsvault.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.RyanCh29.borderlandsvault.R;

public class ClassModDialog extends AppCompatDialogFragment {
    private EditText scoreEditText;
    private EditText levelEditText;
    private EditText nameEditText;
    private EditText dmgEditText;
    private EditText multiEditText;
    private EditText accEditText;
    private EditText handEditText;
    private EditText reloadEditText;
    private EditText fireEditText;
    private EditText magEditText;
    ClassModDialog.ClassModDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_class_mod, null);

        builder.setView(view)
//                .setTitle("Choose Gear to Add")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String score = scoreEditText.getText().toString();
                        String lvl = levelEditText.getText().toString();
                        String name = nameEditText.getText().toString();
                        String type = nameEditText.getText().toString();
                        String dmg = dmgEditText.getText().toString();
                        String accuracy = accEditText.getText().toString();
                        String handling = handEditText.getText().toString();
                        String reload = reloadEditText.getText().toString();
                        String fireRate = fireEditText.getText().toString();
                        String magazine = magEditText.getText().toString();
                        //still need to add bonus stats and anointments to this and the layout

                        listener.addClassMod(score,lvl,name,type,dmg,accuracy,handling,reload,fireRate,magazine);
                    }
                });

        //setup EditText areas
        scoreEditText= view.findViewById(R.id.itemScore_editText);
        levelEditText= view.findViewById(R.id.level_editText);
        nameEditText= view.findViewById(R.id.name_editText);
        dmgEditText= view.findViewById(R.id.dmg_editText);
        accEditText= view.findViewById(R.id.accuracy_editText);
        handEditText= view.findViewById(R.id.handling_editText);
        reloadEditText= view.findViewById(R.id.reload_editText);
        fireEditText= view.findViewById(R.id.fireRate_editText);
        magEditText= view.findViewById(R.id.magazineSize_editText);


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ClassModDialog.ClassModDialogListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement listener");
        }
    }

    public interface ClassModDialogListener {
        void addClassMod(String score, String lvl, String name, String type, String dmg,
                       String accuracy, String handling, String reload, String fireRate, String magazine);
    }
}

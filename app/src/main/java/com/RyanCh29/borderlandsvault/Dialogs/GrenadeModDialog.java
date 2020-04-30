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

public class GrenadeModDialog extends AppCompatDialogFragment {
    private EditText scoreEditText;
    private EditText levelEditText;
    private EditText nameEditText;
    private EditText dmgEditText;
    private EditText radiusEditText;
    private EditText elementEditText;
    GrenadeModDialog.GrenadeModDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_grenade_mod, null);

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
                        String radius = radiusEditText.getText().toString();
                        String element = elementEditText.getText().toString();
                        //still need to add bonus stats and anointments to this and the layout

                        listener.addGrenadeMod(score,lvl,name,dmg,radius,element);
                    }
                });

        //setup EditText areas
        scoreEditText= view.findViewById(R.id.itemScore_editText);
        levelEditText= view.findViewById(R.id.level_editText);
        nameEditText= view.findViewById(R.id.name_editText);
        dmgEditText= view.findViewById(R.id.dmg_editText);
        radiusEditText= view.findViewById(R.id.radius_editText);
        elementEditText= view.findViewById(R.id.element_editText);


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (GrenadeModDialog.GrenadeModDialogListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement listener");
        }
    }

    public interface GrenadeModDialogListener {
        void addGrenadeMod(String score, String lvl, String name, String dmg,
                           String radius, String element);
    }
}

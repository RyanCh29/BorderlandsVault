package com.RyanCh29.borderlandsvault.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.RyanCh29.borderlandsvault.R;

public class ChoiceDialog extends AppCompatDialogFragment {
    ChoiceDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_choose_gear, null);

        builder.setView(view)
                .setTitle("Choose Type");

        Button weapon = view.findViewById(R.id.weapon_button);
        weapon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice("weapon");
            }
        });
        Button shield = view.findViewById(R.id.shield_button);
        shield.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice("shield");
            }
        });
        Button grenade = view.findViewById(R.id.grenadeMod_button);
        grenade.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice("grenade");
            }
        });
        Button classMod = view.findViewById(R.id.classMod_button);
        classMod.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice("class");
            }
        });
        Button artifact = view.findViewById(R.id.artifact_button);
        artifact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice("artifact");
            }
        });


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ChoiceDialogListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement listener");
        }
    }

    public interface ChoiceDialogListener {
        void getChoice(String c);
    }


}

package com.RyanCh29.borderlandsvault.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.RyanCh29.borderlandsvault.R;

public class ClassModDialog extends AppCompatDialogFragment {
    ClassModDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_choose_mod, null);

        Bundle args = getArguments();
        char character = args.getChar("character");

        //get extras from activity
        //create buttons based on extras
        String[] mods = new String[0];
        String[] modNames = new String[0];

        if (character == 'a') {
            mods = getResources().getStringArray(R.array.amara_mods);
            modNames = getResources().getStringArray(R.array.amara_mods_names);

        } else if (character == 'f') {
            mods = getResources().getStringArray(R.array.fl4k_mods);
            modNames = getResources().getStringArray(R.array.fl4k_mods_names);

        } else if (character == 'm') {
            mods = getResources().getStringArray(R.array.moze_mods);
            modNames = getResources().getStringArray(R.array.moze_mods_names);

        } else if (character == 'z') {
            mods = getResources().getStringArray(R.array.zane_mods);
            modNames = getResources().getStringArray(R.array.zane_mods_names);

        }

        LinearLayout layout = view.findViewById(R.id.linear_layout);

        Button[] buttons = new Button[mods.length];
        for(int i = 0; i < mods.length; i++) {
            //create and configure buttons
            buttons[i] = new Button(getContext());
            buttons[i].setText(modNames[i]);

            final int finalI = i;
            final String[] finalMods = mods;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    listener.getChoice(finalMods[finalI]);
                }
            });

            //TODO: add buttons to the layout ot be able to input the skill point for each skill
            layout.addView(buttons[i]);
        }


        builder.setView(view)
                .setTitle("Choose Class Mod");

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
        void getChoice(String c);
    }
}


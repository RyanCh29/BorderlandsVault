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

public class CharacterDialog extends AppCompatDialogFragment {
    CharacterDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_choose_character, null);

        builder.setView(view)
                .setTitle("Choose Character");

        Button ar = view.findViewById(R.id.amara_button);
        ar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice("amara");
            }
        });
        Button launcher = view.findViewById(R.id.fl4k_button);
        launcher.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice("fl4k");
            }
        });
        Button pistol = view.findViewById(R.id.moze_button);
        pistol.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice("moze");
            }
        });
        Button shotgun = view.findViewById(R.id.zane_button);
        shotgun.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice("zane");
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (CharacterDialog.CharacterDialogListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement listener");
        }
    }

    public interface CharacterDialogListener {
        void getChoice(String c);
    }
}


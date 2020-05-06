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

public class WeaponDialog extends AppCompatDialogFragment {
    WeaponDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_choose_weapon, null);

        builder.setView(view)
                .setTitle("Choose Type");

        Button ar = view.findViewById(R.id.ar_button);
        ar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice(11);
            }
        });
        Button launcher = view.findViewById(R.id.launcher_button);
        launcher.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice(22);
            }
        });
        Button pistol = view.findViewById(R.id.pistol_button);
        pistol.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice(33);
            }
        });
        Button shotgun = view.findViewById(R.id.shotgun_button);
        shotgun.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice(44);
            }
        });
        Button sniper = view.findViewById(R.id.sniper_button);
        sniper.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice(55);
            }
        });
        Button smg = view.findViewById(R.id.smg_button);
        smg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice(66);
            }
        });


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (WeaponDialog.WeaponDialogListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement listener");
        }
    }

    public interface WeaponDialogListener {
        void getChoice(int c);
    }
}

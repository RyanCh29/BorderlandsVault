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
    private EditText skill1EditText;
    private EditText skill2EditText;
    private EditText skill3EditText;

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
                        String skill1 = skill1EditText.getText().toString();
                        String skill2 = skill2EditText.getText().toString();
                        String skill3 = skill3EditText.getText().toString();
                        //still need to add bonus stats and anointments to this and the layout

                        listener.addClassMod(score,lvl,name,skill1,skill2,skill3);
                    }
                });

        //setup EditText areas
        scoreEditText= view.findViewById(R.id.itemScore_editText);
        levelEditText= view.findViewById(R.id.level_editText);
        nameEditText= view.findViewById(R.id.name_editText);
        skill1EditText= view.findViewById(R.id.skill1_editText);
        skill2EditText= view.findViewById(R.id.skill2_editText);
        skill3EditText= view.findViewById(R.id.skill3_editText);


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
        void addClassMod(String score, String lvl, String name, String skill1,
                         String skill2, String skill3);
    }
}

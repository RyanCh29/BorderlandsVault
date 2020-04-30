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

public class ShieldDialog extends AppCompatDialogFragment {
    private EditText scoreEditText;
    private EditText levelEditText;
    private EditText nameEditText;
    private EditText capEditText;
    private EditText delayEditText;
    private EditText rateEditText;
    private EditText elementEditText;
    ShieldDialog.ShieldDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_shield, null);

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
                        String cap = capEditText.getText().toString();
                        String delay = delayEditText.getText().toString();
                        String rate = rateEditText.getText().toString();
                        String element = elementEditText.getText().toString();
                        //still need to add bonus stats and anointments to this and the layout

                        listener.addShield(score,lvl,name,cap,delay,rate,element);
                    }
                });

        //setup EditText areas
        scoreEditText= view.findViewById(R.id.itemScore_editText);
        levelEditText= view.findViewById(R.id.level_editText);
        nameEditText= view.findViewById(R.id.name_editText);
        capEditText= view.findViewById(R.id.cap_editText);
        delayEditText= view.findViewById(R.id.delay_editText);
        rateEditText= view.findViewById(R.id.rate_editText);
        elementEditText= view.findViewById(R.id.element_editText);


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (ShieldDialog.ShieldDialogListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement listener");
        }
    }

    public interface ShieldDialogListener {
        void addShield(String score, String lvl, String name, String cap,
                       String rechargeDelay, String rechargeRate, String element);
    }
}

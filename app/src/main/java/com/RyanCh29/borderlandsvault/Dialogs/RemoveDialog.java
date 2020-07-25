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

public class RemoveDialog extends AppCompatDialogFragment {
    RemoveDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_remove_item, null);

        builder.setView(view);

        Button yes = view.findViewById(R.id.yes_button);
        yes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice("remove");

            }
        });
        Button no = view.findViewById(R.id.no_button);
        no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.getChoice("do not remove");
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (RemoveDialog.RemoveDialogListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement listener");
        }
    }

    public interface RemoveDialogListener {
        void getChoice(String c);
    }
}

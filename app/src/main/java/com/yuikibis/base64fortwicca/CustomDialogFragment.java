package com.yuikibis.base64fortwicca;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class CustomDialogFragment extends DialogFragment {
    public static CustomDialogFragment newInstance(String title, String message) {
        CustomDialogFragment instance = new CustomDialogFragment();

        Bundle arguments = new Bundle();
        arguments.putString("title", title);
        arguments.putString("message", message);

        instance.setArguments(arguments);
        return instance;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String message = getArguments().getString("message");

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message);

        return alert.create();
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().finish();
    }
}

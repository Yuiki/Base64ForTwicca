package com.yuikibis.base64fortwicca;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();

        final Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }

        final String action = intent.getAction();
        switch (action) {
            case "jp.r246.twicca.ACTION_EDIT_TWEET":
                encode(intent);
                break;
            case "jp.r246.twicca.ACTION_SHOW_TWEET":
                decode(intent);
                break;
        }
    }

    public static String getApplicationName(Context context) {
        int stringId = context.getApplicationInfo().labelRes;
        return context.getString(stringId);
    }

    public void decode(Intent intent) {
        final String text = intent.getStringExtra(Intent.EXTRA_TEXT);
        final String decodedText = new String(Base64.decode(text, Base64.URL_SAFE));

        CustomDialogFragment dialog = CustomDialogFragment.newInstance(getApplicationName(this), decodedText);
        dialog.show(getFragmentManager(), this.toString());
    }

    private void encode(Intent intent) {
        final String text = intent.getStringExtra(Intent.EXTRA_TEXT);
        final String encodedText = Base64.encodeToString(text.getBytes(), Base64.DEFAULT).replace("\n", "");

        intent.putExtra(Intent.EXTRA_TEXT, encodedText);
        intent.putExtra("cursor", encodedText.length());
        setResult(RESULT_OK, intent);
        finish();
    }
}
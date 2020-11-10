package com.hfad.basicandroidlearningapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;

public class ImplicitIntentActivity extends AppCompatActivity {

    private EditText editTxtPhoneNumber;
    private EditText editTxtMessage;
    private EditText editTxtWebUrl;
    private EditText editTxtMessagePhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);

        editTxtPhoneNumber = findViewById(R.id.edit_txt_implicit_intent_phone);
        editTxtMessagePhoneNumber = findViewById(R.id.edit_txt_implicit_intent_message_phone_number);
        editTxtMessage = findViewById(R.id.edit_txt_implicit_intent_message);
        editTxtWebUrl = findViewById(R.id.edit_txt_implicit_intent_browser);

    }

    // Button click event listener
    public void sendRequest(View view) {
        // Check button id
        switch (view.getId()) {
            case R.id.button_start_implicit_intent_camera:
                takePhoto();
                break;

            case R.id.button_start_implicit_intent_phone:
                requestCallPhone(editTxtPhoneNumber.getText().toString());
                break;

            case R.id.button_start_implicit_intent_message:
                requestSendMessage(editTxtMessage.getText().toString(), editTxtMessagePhoneNumber.getText().toString());
                break;

            case R.id.button_start_implicit_intent_browser:
                requestWebBrowser(editTxtWebUrl.getText().toString());
                break;
        }
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    private void requestCallPhone(String phoneNumber) {

        if (phoneNumber == null || phoneNumber.length() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Phone number is invalid. Please input and try again!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        } else {
            callPhone(phoneNumber);
        }
    }

    private void callPhone(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    private void requestSendMessage(String message, String phoneNumber) {
        if (message == null || message.length() == 0 ||
                phoneNumber == null || phoneNumber.length() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Input info is invalid. Please input and try again!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        } else {
            sendMessage(message, phoneNumber);
        }
    }

    private void sendMessage(String message, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.putExtra("sms_body", message);
        intent.setData(Uri.parse("sms:" + phoneNumber));
        startActivity(intent);
    }

    private void requestWebBrowser(String url) {
        if (url == null || url.length() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Url is invalid. Please input and try again!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        } else {
            openWebBrowser(url);
        }
    }

    private void openWebBrowser(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
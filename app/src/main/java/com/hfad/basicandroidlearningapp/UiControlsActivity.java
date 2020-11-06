package com.hfad.basicandroidlearningapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UiControlsActivity extends AppCompatActivity {

    private EditText editTextName;
    private TextView textViewBirthDay;
    private ImageButton btnDatePicker;
    private EditText editTextAddress;
    private EditText editTextEmail;
    private Spinner spinnerEmail;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFeMale;
    private ImageButton buttonSave;
    private Calendar dayOfBirth;
    private String selectedDomain;


    private boolean flagFemale = false;
    private boolean flagMale = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_controls);
        mappingView();

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setDatePicker();
            }
        });

        // Email list processing
        setEmailList();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInformation();
            }
        });
    }

    private void setDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dayOfBirth = Calendar.getInstance();
                dayOfBirth.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy");
                textViewBirthDay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void mappingView() {
        editTextName = findViewById(R.id.edit_text_name);
        textViewBirthDay = findViewById(R.id.text_view_date);
        btnDatePicker = findViewById(R.id.button_date_picker);
        editTextEmail = findViewById(R.id.edit_text_email);
        spinnerEmail = findViewById(R.id.spinner_email);
        radioButtonMale = findViewById(R.id.radio_male);
        radioButtonFeMale = findViewById(R.id.radio_female);
        editTextAddress = findViewById(R.id.edit_text_address);
        buttonSave = findViewById(R.id.button_save);
    }

    public void RadioButtonClick(View view) {

        switch (view.getId()) {
            case R.id.radio_male:
                if (flagMale) {
                    radioButtonMale.setChecked(false);
                    radioButtonFeMale.setChecked(true);
                    flagMale = false;
                    flagFemale = true;
                } else {
                    radioButtonFeMale.setChecked(false);
                    flagMale = true;
                    flagFemale = false;
                }
                break;

            case R.id.radio_female:
                if (flagFemale) {
                    radioButtonMale.setChecked(true);
                    radioButtonFeMale.setChecked(false);
                    flagFemale = false;
                    flagMale = true;
                } else {
                    radioButtonMale.setChecked(false);
                    flagFemale = true;
                    flagMale = false;
                }
                break;
        }
    }

    private void setEmailList() {
        // Create Adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.domain_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmail.setAdapter(adapter);

        // Item selected processing
        spinnerEmail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDomain = adapter.getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void saveInformation() {
        String name = editTextName.getText().toString();
        String dateOfBirth = textViewBirthDay.getText().toString();
        String email = editTextEmail.getText().toString();

        if(name.length() == 0 || dateOfBirth.length() == 0 ||email.length() == 0 || (!flagMale & !flagFemale)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("One or more information was not entered");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else {
            Toast.makeText(this, "Register Success!", Toast.LENGTH_LONG);
        }
    }
}
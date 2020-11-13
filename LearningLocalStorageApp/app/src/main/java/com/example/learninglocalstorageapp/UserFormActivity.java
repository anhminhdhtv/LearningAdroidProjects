package com.example.learninglocalstorageapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserFormActivity extends AppCompatActivity {

    private EditText editTextName;
    private TextView textViewBirthDay;
    private ImageButton btnDatePicker;
    private EditText editTextAddress;
    private EditText editTextEmail;
    private Spinner spinnerEmail;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFeMale;
    private ImageButton buttonSave;
    private Date dayOfBirth;
    private String selectedDomain;
    private UserInfo currentUserInfo;

    private boolean flagFemale = false;
    private boolean flagMale = false;

    public static final String EXTRA_START_MODE = "EXTRA_START_MODE";
    public static final String EXTRA_UPDATED_USER_ID = "EXTRA_UPDATED_USER_ID";
    public static final String VALUE_START_MODE_INSERT = "start_mode_insert";
    public static final String VALUE_START_MODE_UPDATE = "start_mode_update";

    private String mStartMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uer_form);

        mappingView();

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDatePicker();
            }
        });

        // Email list processing
        setEmailList();

        Animation animationScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animationScale);
                saveInformation();
            }
        });

        mStartMode = getIntent().getStringExtra(EXTRA_START_MODE);
        if(mStartMode.equals(VALUE_START_MODE_UPDATE)){
            int id = getIntent().getIntExtra(EXTRA_UPDATED_USER_ID, 0);
            findUserById(id);
        }

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

    private void setDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                dayOfBirth =  Calendar.getInstance().getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy");
                textViewBirthDay.setText(simpleDateFormat.format(dayOfBirth.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();
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

    private void setUpdatedUserInfo(UserInfo userInfo){

        editTextName.setText(userInfo.getUserName());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy");
        dayOfBirth = userInfo.getDayOfBirth();
        textViewBirthDay.setText(simpleDateFormat.format(dayOfBirth.getTime()));

        if(userInfo.isMan()){
            radioButtonMale.setChecked(true);
            flagMale = true;
        }
        else {
            radioButtonFeMale.setChecked(true);
            flagFemale = true;
        }

        editTextAddress.setText(userInfo.getUserAddress());
        editTextEmail.setText(userInfo.getUserEmail());
    }

    private void saveInformation() {
        String name = editTextName.getText().toString();
        String dateOfBirth = textViewBirthDay.getText().toString();
        String email = editTextEmail.getText().toString();
        String address = editTextAddress.getText().toString();

        if(name.length() == 0 || dateOfBirth.length() == 0 ||email.length() == 0 || (!flagMale & !flagFemale)){

            buttonSave.setImageResource(R.drawable.ic_x_mark);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Register fail");
            builder.setMessage("One or more information was not entered");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    buttonSave.setImageResource(R.drawable.ic_check_mark);
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else {

            if(mStartMode.equals(VALUE_START_MODE_INSERT)){
                insert(new UserInfo(name, dayOfBirth, email, flagMale, address));
            }
            else {
                currentUserInfo.setUserName(name);
                currentUserInfo.setDayOfBirth(dayOfBirth);
                currentUserInfo.setUserEmail(email);
                currentUserInfo.setMan(flagMale);
                currentUserInfo.setUserAddress(address);
                update(currentUserInfo);
            }
        }
    }

    private void insert (UserInfo userInfo){
        new insertAsyncTask(UserDatabase.getDatabase(getApplication()).userInfoDao()).execute(userInfo);
    }

    private class insertAsyncTask extends AsyncTask<UserInfo, Void, Void> {

        private UserInfoDao mUserInfoDao;

        public insertAsyncTask(UserInfoDao dao) {
            this.mUserInfoDao = dao;
        }

        @Override
        protected Void doInBackground(UserInfo... userInfos) {
            mUserInfoDao.insert(userInfos[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
            Toast.makeText(getApplicationContext(), "Register Success!", Toast.LENGTH_LONG).show();
        }
    }

    private void update (UserInfo userInfo){
        new updateAsyncTask(UserDatabase.getDatabase(getApplication()).userInfoDao()).execute(userInfo);
    }

    private class updateAsyncTask extends AsyncTask<UserInfo, Void, Void>{

        private UserInfoDao mUserInfoDao;

        public updateAsyncTask(UserInfoDao dao) {
            this.mUserInfoDao = dao;
        }

        @Override
        protected Void doInBackground(UserInfo... userInfos) {
            mUserInfoDao.update(userInfos[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
            Toast.makeText(getApplicationContext(), "Update Success!", Toast.LENGTH_LONG).show();
        }
    }

    private void findUserById(int id){
        new findUserByIdAsyncTask(UserDatabase.getDatabase(getApplication()).userInfoDao()).execute(id);
    }

    private class findUserByIdAsyncTask extends AsyncTask<Integer, Void, UserInfo>{

        private UserInfoDao mUserInfoDao;

        public findUserByIdAsyncTask(UserInfoDao dao) {
            this.mUserInfoDao = dao;
        }

        @Override
        protected UserInfo doInBackground(Integer... integers) {
            return mUserInfoDao.findById(integers[0]);
        }

        @Override
        protected void onPostExecute(UserInfo userInfo) {
            super.onPostExecute(userInfo);
            currentUserInfo = userInfo;
            setUpdatedUserInfo(userInfo);
        }
    }



}
package com.dreams.theroommateagreement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdCreatorActivity extends AppCompatActivity {

    private ImageView userImage;
    private TextView adUserNameTextView, adCityPreferenceTextView,
            adMoveDateTextView, adIsApartmentAvailableTextView, adGenderPreferenceTextView,
            adBudgetMinimumTextView, adBudgetMaximumTextView, adNationalityPreferenceTextView;
    private EditText adCommentsEditText, adBudgetMiniumEditText,
            adBudgetMaximumEditText, adCityPreferenceEditText;
    private Switch adIsApartmentAvailableSwitch;
    private Spinner adNationalityPreferenceSpinner, adGenderPreferenceSpinner;
    private Button adPostingButton;

    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_ad_creator);

        getInstances();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        adPostingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

    }

    private void getInstances() {
        userImage = (ImageView) findViewById(R.id.imageView_userDisplayImage);
        adUserNameTextView = (TextView) findViewById(R.id.textView_userName);

        adCommentsEditText = (EditText) findViewById(R.id.editText_adComments);
        adBudgetMiniumEditText = (EditText) findViewById(R.id.editText_adBudgetMinimumValue);
        adBudgetMaximumEditText = (EditText) findViewById(R.id.editText_adBudgetMaximumValue);
        adCityPreferenceEditText = (EditText) findViewById(R.id.editText_adCityPreference);

        adIsApartmentAvailableSwitch = (Switch) findViewById(R.id.switch_adIsApartmentAvailable);

        adNationalityPreferenceSpinner = (Spinner) findViewById(R.id.spinner_adNationalityPreference);
        adGenderPreferenceSpinner = (Spinner) findViewById(R.id.spinner_adGenderPreference);

        adPostingButton = (Button) findViewById(R.id.button_postAd);

        firebaseDatabase = FirebaseDatabase.getInstance();
    }
}

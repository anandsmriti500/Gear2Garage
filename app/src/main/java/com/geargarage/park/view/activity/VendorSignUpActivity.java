package com.geargarage.park.view.activity;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.geargarage.park.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VendorSignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @Bind(R.id.garage_name)
    EditText _garageNameText;
    @Bind(R.id.garage_contact)
    EditText _garageContactText;
    @Bind(R.id.input_garage_email)
    EditText _garageEmailText;
    @Bind(R.id.garage_address)
    EditText _garageAddress;
    @Bind(R.id.owner_name)
    TextView _garageOwnerName;
    @Bind(R.id.garage_signup_btn)
    Button _garageSignUp;
    @Bind(R.id.link_login)
    TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        _garageSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "VendorSignup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _garageSignUp.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(VendorSignUpActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Retrieving your information...");
        progressDialog.show();

        String name = _garageNameText.getText().toString();
        String contact = _garageContactText.getText().toString();
        String email = _garageEmailText.getText().toString();
        String address = _garageAddress.getText().toString();
        String owner_name = _garageOwnerName.getText().toString();


        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _garageSignUp.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _garageSignUp.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String garage_name = _garageNameText.getText().toString();
        String contact = _garageContactText.getText().toString();
        String email = _garageEmailText.getText().toString();
        String address = _garageOwnerName.getText().toString();
        String ownerName = _garageNameText.getText().toString();


        if (garage_name.isEmpty() || garage_name.length() < 3) {
            _garageNameText.setError("at least 3 characters");
            valid = false;
        } else {
            _garageNameText.setError(null);
        }

        if (contact.isEmpty() || contact.length() < 10 || contact.length() > 10) {
            _garageContactText.setError("please enter valid mobile number");
            valid = false;
        } else {
            _garageContactText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _garageEmailText.setError("enter a valid email address");
            valid = false;
        } else {
            _garageEmailText.setError(null);
        }

        if (address.isEmpty() || address.length() < 4 || address.length() > 10) {
            _garageAddress.setError("atleast 10  alphanumeric characters");
            valid = false;
        } else {
            _garageAddress.setError(null);
        }

        if (ownerName.isEmpty() || ownerName.length() < 4) {
            _garageOwnerName.setError("at least 4 characters");
            valid = false;
        } else {
            _garageOwnerName.setError(null);
        }
        return valid;
    }
}
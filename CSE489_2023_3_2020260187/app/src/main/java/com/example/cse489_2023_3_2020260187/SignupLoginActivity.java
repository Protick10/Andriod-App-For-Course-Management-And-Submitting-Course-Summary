package com.example.cse489_2023_3_2020260187;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

public class SignupLoginActivity extends Activity {
    private TextView loginLink, loginToggleLabel, signup;
    private TableRow rowName, rowEmail, rowPhone, rowRePass;
    private boolean isLoginPage = false;



    EditText name, email, phone, user_id, passord, re_enterpassword ;
    CheckBox remenber_userid, remember_password;
    Button exit, go;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_login);

        //stayes logged in

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if (checkbox.equals("true")){

            Intent intent = new Intent(SignupLoginActivity.this,ClassLecturesActivity.class);
            startActivity(intent);
        }


        loginToggleLabel = findViewById(R.id.loginToggleLabel);
        signup = findViewById(R.id.signup);
        loginLink = findViewById(R.id.loginLink);
        rowName = findViewById(R.id.signupName);
        rowEmail = findViewById(R.id.signupEmail);
        rowPhone = findViewById(R.id.signupPhone);
        rowRePass = findViewById(R.id.signupRePass);

        this.changeView();




        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoginPage = !isLoginPage;
                changeView();
            }
        });

        email = findViewById(R.id.signupEtEmail);
        name = findViewById(R.id.signupEtName);
        phone = findViewById(R.id.signupEtPhone);
        user_id = findViewById(R.id.signupEtUserId);
        passord = findViewById(R.id.signupEtPassword);
        re_enterpassword = findViewById(R.id.signupEtRePassword);

        remenber_userid = findViewById(R.id.chkRemUserdId);
        remember_password = findViewById(R.id.chkRemPass);

        exit = findViewById(R.id.btnExit);
        go = findViewById(R.id.btnGo);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SignupLoginActivity().finish();
                System.exit(0);
            }
        });


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(name.getText().toString())){
                    name.setError("username is must");
                }



                else if(TextUtils.isEmpty(email.getText().toString())  ){
                    email.setError("email is not filled");
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    email.setError("email is not valid");
                }

                else if (TextUtils.isEmpty(phone.getText().toString())){
                    phone.setError("phone number is must");
                }

                else if (TextUtils.isEmpty(user_id.getText().toString())){
                    user_id.setError("userid is must");
                }

                else if (TextUtils.isEmpty(passord.getText().toString())){
                    passord.setError("password is must");
                }

                else if (!passord.getText().toString().equals(re_enterpassword.getText().toString()) ){
                    re_enterpassword.setError("re enter password is not matched");
                }

//                if (TextUtils.isEmpty(remenber_userid.getText().toString())){
//                    remenber_userid.setError("username is must");
//                }
//                if (TextUtils.isEmpty(remember_password.getText().toString())){
//                    remember_password.setError("username is must");
//                }

                else {
//                    SharedPreferences Pref = SignupLoginActivity.this.getSharedPreferences("myPref", MODE_PRIVATE);
                      SharedPreferences localPref = SignupLoginActivity.this.getSharedPreferences("localPref", MODE_PRIVATE) ;
                      SharedPreferences.Editor edit = localPref.edit();
                      edit.putString("email", email.getText().toString());
                      edit.putString("name", name.getText().toString());
                      edit.putString("phone", phone.getText().toString());
                      edit.putString("user_id", user_id.getText().toString());
                      edit.putString("password", passord.getText().toString());
                      edit.putString("re_enterpassword", re_enterpassword.getText().toString());
//                      edit.putBoolean("remember_userid", false);
//                      edit.putBoolean("remember_passord", false);
                      edit.apply();
                    Log.d("SharedPreferences", "Saved email: " + email.getText().toString());
                    Log.d("SharedPreferences", "Saved name: " + name.getText().toString());


//                    Intent intent = SignupLoginActivity.this.getIntent();
//                    intent.putExtra("username", (CharSequence) name);
                    Intent intent = new Intent(SignupLoginActivity.this, ClassLecturesActivity.class);
//                    intent.putExtra("UserName", name.getText().toString());
                    intent.putExtra("id", user_id.getText().toString());
                    intent.putExtra("name",name.getText().toString());
                    startActivity(intent);


                    //STAYS LOGGED IN..

                    if (remenber_userid.isChecked()){
                        SharedPreferences preferences = SignupLoginActivity.this.getSharedPreferences("checkbox", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("remember", "true");
                        editor.apply();
                    } else if (!remenber_userid.isChecked()) {

                        SharedPreferences preferences = SignupLoginActivity.this.getSharedPreferences("checkbox", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("remember", "false");
                        editor.apply();
                    }


                }
            }


        });
    }
    private void changeView(){
        if(isLoginPage) {
            rowName.setVisibility(View.GONE);
            rowEmail.setVisibility(View.GONE);
            rowPhone.setVisibility(View.GONE);
            rowRePass.setVisibility(View.GONE);
            signup.setText("Login");
            loginToggleLabel.setText("Don't have an account");
            loginLink.setText("Signup");
        } else{
            rowName.setVisibility(View.VISIBLE);
            rowEmail.setVisibility(View.VISIBLE);
            rowPhone.setVisibility(View.VISIBLE);
            rowRePass.setVisibility(View.VISIBLE);
            signup.setText("Signup");
            loginToggleLabel.setText("Already have an account");
            loginLink.setText("Login");
        }
    }





}
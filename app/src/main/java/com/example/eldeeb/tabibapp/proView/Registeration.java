package com.example.eldeeb.tabibapp.proView;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.eldeeb.tabibapp.R;
import com.example.eldeeb.tabibapp.proModels.sigleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registeration extends AppCompatActivity {
    Button register;
    RadioGroup gender;
    RadioButton sex;
    EditText Name, Email, Password, Age;
    String name, email, password, age;
    AlertDialog.Builder builder;
    String reg_url = "https://tabibapp.000webhostapp.com/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        register = findViewById(R.id.btn_signup);
        Name = findViewById(R.id.signup_input_name);
        Email = findViewById(R.id.signup_input_email);
        Password = findViewById(R.id.signup_input_password);
        Age = findViewById(R.id.signup_input_age);
        gender = findViewById(R.id.gender_radio_group);
        builder = new AlertDialog.Builder(Registeration.this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeration_action();
            }
        });
    }


//Button Action to registration

    public void registeration_action() {
        name = Name.getText().toString();
        email = Email.getText().toString();
        password = Password.getText().toString();
        age = Age.getText().toString();
        int intGender = gender.getCheckedRadioButtonId();
        sex = findViewById(intGender);

        if (!checkEmptyFields(new EditText[]{Name, Email, Password, Age})) {
            builder.setTitle("Error");
            builder.setMessage("Please fill all the fields");
            displayAlert("input error");
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, reg_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        Log.d("response", response);
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String code = jsonObject.getString("code");
                        String message = jsonObject.getString("message");
                        Log.d("jsonmessage", message);
                        builder.setTitle("Server");
                        builder.setMessage(message);
                        displayAlert(code);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("Error in hole", e.toString());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getBaseContext(), "Error" + error, Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", name);
                    params.put("email", email);
                    params.put("password", password);
                    params.put("age", age);
                    params.put("sex", sex.getText().toString());
                    Log.d("params", params.toString());
                    return params;
                }
            };

            sigleton.getInstance(Registeration.this).addToRequestQueue(stringRequest);
        }
    }

    //Alert dialogue for errors
    public void displayAlert(final String code) {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (code.equals("input error")) {
                    Password.setText("");
                } else if (code.equals("reg_failed")) {
                    Email.setText("");
                } else if (code.equals("reg_success")) {
                    finish();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //check for edittext have text or not
    private boolean checkEmptyFields(EditText[] fields) {
        for (int i = 0; i < fields.length; i++) {
            EditText currentField = fields[i];
            if (currentField.getText().toString().length() <= 0) {
                return false;
            }
        }
        return true;
    }


}

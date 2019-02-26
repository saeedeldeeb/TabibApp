package com.example.eldeeb.tabibapp;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    RelativeLayout rellay1, rellay2;
    Button signup, forgetpass;
    TextInputEditText Password;
    EditText User_name;
    Button log_in;
    String user_name, password;
    AlertDialog.Builder builder;
    String login_url = "https://tabibapp.000webhostapp.com/login.php";

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash

        //transfer to registration activity
        signup = findViewById(R.id.btn_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_register = new Intent(getBaseContext(), Registeration.class);
                startActivity(intent_to_register);
            }
        });
builder = new AlertDialog.Builder(LoginActivity.this);
        Password = findViewById(R.id.password_login);
        User_name = findViewById(R.id.userName_login);
        log_in = findViewById(R.id.btn_login);
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             login_action();
            }
        });

    }

    //Button Action to registration

    public void login_action() {
        user_name = User_name.getText().toString();
        password = Password.getText().toString();
        if (user_name.equals("") || password.equals("")) {
            builder.setTitle("Error");
            displayAlert("Enter a valid Email and Password ");
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Log.d("response", response);
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String code = jsonObject.getString("code");
                        if (code.equals("login_failed")) {
                            builder.setTitle("Login Error..");
                            displayAlert(jsonObject.getString("message"));
                        }else{
                            Toast.makeText(getBaseContext(),"Success Login",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
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
                    params.put("email", user_name);
                    params.put("password", password);
                    return params;
                }
            };
            sigleton.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
        }
    }

    public void displayAlert(String message) {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                User_name.setText("");
                Password.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}


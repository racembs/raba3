package com.tonikamitv.loginregister;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final DbConnection db=new DbConnection(getApplicationContext());
        final ArrayList <User> users=db.getAll();
        final Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        if(!users.isEmpty()){
            if(users.get(0).getRemember().equals("true")){

                intent.putExtra("mail",users.get(0).getMail());
                intent.putExtra("university",users.get(0).getUniversity());
                intent.putExtra("id",users.get(0).getId());
                intent.putExtra("password",users.get(0).getPassword());
                intent.putExtra("username",users.get(0).getUsername());
                LoginActivity.this.startActivity(intent);
                LoginActivity.this.finish();
            }
        }


        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        final Button bLogin = (Button) findViewById(R.id.bSignIn);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.remember_me_chekbox);

        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);

            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                String jid = jsonResponse.getString("id");
                                String jmail = jsonResponse.getString("mail");
                                String juniversity = jsonResponse.getString("university");
                                String jpassword = jsonResponse.getString("password");

                                String jusername = jsonResponse.getString("username");
                                int age = 22;


                                intent.putExtra("mail",jmail);
                                intent.putExtra("university",juniversity);
                                intent.putExtra("id",jid);
                                intent.putExtra("password",jpassword);
                                intent.putExtra("username",jusername);

                                db.truncate();
                                if(checkBox.isChecked()){
                                    db.InsertRowUser(Integer.parseInt(jid),jusername,jpassword,jmail,juniversity,"true");
                                }else{
                                    db.InsertRowUser(Integer.parseInt(jid),jusername,jpassword,jmail,juniversity,"false");
                                }
                                LoginActivity.this.startActivity(intent);
                                LoginActivity.this.finish();


                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest.LOGIN_REQUEST_URL = "http://atra7.000webhostapp.com/login.php";
                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}

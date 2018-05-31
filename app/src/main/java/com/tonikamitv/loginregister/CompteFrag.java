package com.tonikamitv.loginregister;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by RBS on 25-Jan-18.
 */

public class CompteFrag extends Fragment {
    String[] university=new String[]{"Esprit-Ghazela","Esprit-Charguia","Insat","Issat",
            "Enit","Enis","SupCom","Ipeis","Ipein","Ipeit","Ensi",
            "Iset-Sfax","Iset-Rades","Iset-Nabeul"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      final View myview=inflater.inflate(R.layout.compte_frag, container,false);

        final EditText username = (EditText) myview.findViewById(R.id.username_compte);
        final EditText password = (EditText) myview.findViewById(R.id.password_compte);
        final EditText mail = (EditText) myview.findViewById(R.id.mail_compte);
        final  AutoCompleteTextView auto = (AutoCompleteTextView) myview.findViewById(R.id.university_compte);
        //final Button btmodif = (Button) myview.findViewById(R.id.modifier_compte);
        final Button btmodif = (Button) myview.findViewById(R.id.modifier_compte);
        final DbConnection db=new DbConnection(getActivity());
        final ArrayList<User> users=db.getAll();
        username.setText(users.get(0).getUsername());
        password.setText(users.get(0).getPassword());
        mail.setText(users.get(0).getMail());
        auto.setText(users.get(0).getUniversity());
        ArrayAdapter adabter=new ArrayAdapter(myview.getContext(),
                android.R.layout.simple_dropdown_item_1line,university);
        auto.setThreshold(1);
        auto.setAdapter(adabter);


                btmodif.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ///etitre.setText(etitre.getText());



                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    if (success) {
                                        db.updateuser(String.valueOf(users.get(0).getId()), username.getText().toString(),password.getText().toString(),mail.getText().toString(),auto.getText().toString());
                                        Toast.makeText(getActivity(),"modification effectuée",Toast.LENGTH_LONG).show();
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                        builder.setMessage("Failed")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };




                UpdateUserRequest updateUserRequest = new UpdateUserRequest(String.valueOf(users.get(0).getId()),mail.getText().toString(), username.getText().toString(),password.getText().toString(),auto.getText().toString(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(updateUserRequest);

            }
        });
        return myview;
    }
}

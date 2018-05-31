package com.tonikamitv.loginregister;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import java.util.List;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by RBS on 25-Jan-18.
 */

public class AnnonceFrag extends Fragment {

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myview=inflater.inflate(R.layout.annonce_frag, container,false);
        final DbConnection db=new DbConnection(getActivity().getApplicationContext());
        final ArrayList <User> users=db.getAll();


        final EditText etitre =(EditText)myview.findViewById(R.id.titre);
        final EditText edesc = (EditText) myview.findViewById(R.id.desc);
        final EditText enbrmanquant = (EditText) myview.findViewById(R.id.nbrmanqaunt);
        final Button btajouter = (Button) myview.findViewById(R.id.add);


        btajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///etitre.setText(etitre.getText());

                final String titre = etitre.getText().toString();
                final String desc = edesc.getText().toString();
                final String nbrmanquant = enbrmanquant.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(getActivity(),"Annonce Déposée",Toast.LENGTH_LONG).show();
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



                AnnonceRequest ajoutAnnRequest = new AnnonceRequest(String.valueOf(users.get(0).getId()),titre,desc,nbrmanquant,responseListener);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(ajoutAnnRequest);
            }
        });
        return myview;
    }
}

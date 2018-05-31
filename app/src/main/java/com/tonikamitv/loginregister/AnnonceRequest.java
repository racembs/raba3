package com.tonikamitv.loginregister;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RBS on 27-Jan-18.
 */

public class AnnonceRequest extends StringRequest {
    private static final  String ADD_REQUEST_URL = "http://atra7.000webhostapp.com/ajoutannonce.php";
    private Map<String, String> params;

    public AnnonceRequest(String userid,String titre, String desc,String nbrmanquant, Response.Listener<String> listener) {
        super(Method.POST, ADD_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("userid", userid);
        params.put("titre", titre);
        params.put("description", desc);
        params.put("nbrmanquant", nbrmanquant);


    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


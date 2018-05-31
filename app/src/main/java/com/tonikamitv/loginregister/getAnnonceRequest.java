package com.tonikamitv.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RBS on 28-Jan-18.
 */

public class getAnnonceRequest extends StringRequest {
    private static final  String ADD_REQUEST_URL = "http://atra7.000webhostapp.com/getannonce.php";
    private Map<String, String> params;

    public getAnnonceRequest(Response.Listener<String> listener) {
        super(Method.POST, ADD_REQUEST_URL, listener, null);
        params = new HashMap<>();



    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}



package com.tonikamitv.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RBS on 30-May-18.
 */

public class DemandeRequest  extends StringRequest {
    public  static String REQUEST_URL ="";
    private Map<String, String> params;

    public DemandeRequest(String id_env,String id_rec, String id_annonce, Response.Listener<String> listener) {
        super(Method.POST, REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_env", id_env);
        params.put("id_rec", id_rec);
        params.put("id_annonce", id_annonce);

    }
    public DemandeRequest(String id_env,Response.Listener<String> listener) {
        super(Method.POST, REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_env", id_env);

    }
    public DemandeRequest(String id_rec,Response.Listener<String> listener,String owner) {
        super(Method.POST, REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_rec", id_rec);

    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
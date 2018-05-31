package com.tonikamitv.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RBS on 30-Jan-18.
 */

public class UpdateUserRequest extends StringRequest {
    private static final String UPDATE_REQUEST_URL = "http://atra7.000webhostapp.com/updateuser.php";
    private Map<String, String> params;

    public UpdateUserRequest(String id,String mail, String username, String password,String university, Response.Listener<String> listener) {
        super(Method.POST, UPDATE_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("id",id);
        params.put("mail", mail);
        params.put("username", username);
        params.put("password", password);
        params.put("university", university);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}



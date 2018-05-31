package com.tonikamitv.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://atra7.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String username, String password,String university, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("mail", name);
        params.put("username", username);
        params.put("password", password);
        params.put("university", university);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

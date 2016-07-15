package com.example.brunovalente.rotazul.DB;

/**
 * Created by brunovalente on 6/30/16.
 */
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://rotativoazul.site88.net/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String email, int CPF, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("CPF", CPF + "");
        params.put("email", email);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
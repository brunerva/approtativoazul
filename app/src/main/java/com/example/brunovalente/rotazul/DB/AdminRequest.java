package com.example.brunovalente.rotazul.DB;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brunovalente on 7/1/16.
 */
public class AdminRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://rotativoazul.site88.net/Search.php";
    private Map<String, String> params;

    public AdminRequest(String placa, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("placa", placa);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


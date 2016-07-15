package com.example.brunovalente.rotazul.DB;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brunovalente on 7/1/16.
 */
public class BuyRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://rotativoazul.site88.net/Buy.php";
    private Map<String, String> params;

    public BuyRequest( int user_id, Response.Listener<String> listener) {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("user_id", user_id+"");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

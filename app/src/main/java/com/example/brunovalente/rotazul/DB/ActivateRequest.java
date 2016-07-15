package com.example.brunovalente.rotazul.DB;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by brunovalente on 7/1/16.
 */
public class ActivateRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://rotativoazul.site88.net/Activate.php";
    private Map<String, String> params;

    public ActivateRequest(String placa, int user_id, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("placa", placa);
        params.put("user_id", user_id + "");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String strDate = ""+mdformat.format(calendar.getTime());
        params.put("data",strDate);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
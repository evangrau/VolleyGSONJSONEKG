package com.example.volleygsonjsonekg.placeholder;

import android.content.Context;
import android.content.res.Resources;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleygsonjsonekg.App;
import com.example.volleygsonjsonekg.GameCompanyModel;
import com.example.volleygsonjsonekg.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent {

    private final Context context = App.getContext();
    private final Resources res = context.getResources();

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<GameCompanyModel> ITEMS = new ArrayList<>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<String, GameCompanyModel> ITEM_MAP = new HashMap<>();

    public void jsonParse() {
        String url = res.getString(R.string.url);

        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = response.getJSONObject("record");
                            JSONArray jsonArray = object.getJSONArray("gameCompanies");

                            ITEMS.clear();
                            ITEM_MAP.clear();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject gameCompany = jsonArray.getJSONObject(i);
                                String name = gameCompany.getString("name");
                                int year = gameCompany.getInt("year");
                                String recentConsole = gameCompany.getString("recentConsole");
                                GameCompanyModel model = new GameCompanyModel(name, year, recentConsole);
                                ITEMS.add(model);
                                ITEM_MAP.put(name, model);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // NEXT, we need to use GSON to turn that JSON into a model
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // you should drop a breakpoint RIGHT HERE if you need to see the error coming back
                error.printStackTrace();
            }
        });
    }
}
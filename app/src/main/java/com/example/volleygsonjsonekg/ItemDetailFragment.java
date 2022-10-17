package com.example.volleygsonjsonekg;

import android.content.ClipData;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.DragEvent;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.example.volleygsonjsonekg.placeholder.PlaceholderContent;
import com.example.volleygsonjsonekg.databinding.FragmentItemDetailBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListFragment}
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The placeholder content this fragment is presenting.
     */
    private GameCompanyModel mItem;
    private CollapsingToolbarLayout mToolbarLayout;
    private TextView mTextView;
    private FloatingActionButton testingFab;

    private final Context context = App.getContext();
    private final Resources res = context.getResources();

    private RequestQueue mQueue;

    private final View.OnDragListener dragListener = (v, event) -> {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            ClipData.Item clipDataItem = event.getClipData().getItemAt(0);
            mItem = PlaceholderContent.ITEM_MAP.get(clipDataItem.getText().toString());
            updateContent();
        }
        return true;
    };
    private FragmentItemDetailBinding binding;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the placeholder content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = PlaceholderContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentItemDetailBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        mToolbarLayout = rootView.findViewById(R.id.toolbar_layout);
        mTextView = binding.itemDetail;
        testingFab = rootView.findViewById(R.id.fab);

        mQueue = Volley.newRequestQueue(getActivity());

        // Show the placeholder content as text in a TextView & in the toolbar if available.
        updateContent();
        rootView.setOnDragListener(dragListener);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateContent() {
        if (mItem != null) {
            mTextView.setText(mItem.getRecentConsole());
            if (mToolbarLayout != null) {
                mToolbarLayout.setTitle(mItem.getName());
            }
        }
        if (testingFab != null)
        {
            testingFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    testAllThatJazz();
                }
            });
        }
    }

    private void testAllThatJazz() {
        String url = res.getString(R.string.url);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = response.getJSONObject("record");
                            JSONArray jsonArray = object.getJSONArray("gameCompanies");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject gameCompany = jsonArray.getJSONObject(i);

                                String name = gameCompany.getString("name");
                                int year = gameCompany.getInt("year");
                                String recentConsole = gameCompany.getString("recentConsole");

                                mTextView.append(name + ", " + String.valueOf(year) + ", " + recentConsole + "\n\n");
                            }
                        } catch (JSONException e) {
                            mTextView.setText("That didn't work!");
                            e.printStackTrace();
                        }
                        // Display the response string in our convenient existing text view

//                        mTextView.setText("Response is: "+ response);
                        // NEXT, we need to use GSON to turn that JSON into a model
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // you should drop a breakpoint RIGHT HERE if you need to see the error coming back
                mTextView.setText("That didn't work!");
                error.printStackTrace();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(request);
    }
}
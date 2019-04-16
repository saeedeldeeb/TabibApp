package com.example.eldeeb.tabibapp.proView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.eldeeb.tabibapp.R;
import com.example.eldeeb.tabibapp.proPresenter.doctors;
import com.example.eldeeb.tabibapp.proPresenter.doctorsAdapter;
import com.example.eldeeb.tabibapp.proModels.sigleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class docs_list extends AppCompatActivity {

    private static final String url = "https://tabibapp.000webhostapp.com/doctors_searched_by_user.php";
    List<doctors> doclist;
    RecyclerView recyclerView;
    String reg, spec;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docs_list);

        Bundle b = getIntent().getExtras();

        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doclist = new ArrayList<>();
        reg = b.getString("region","cairo1");
        spec = b.getString("speciality_chosen","kids");
        loadDoctors();
    }


    private void loadDoctors() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("docs",response);
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject doctor = array.getJSONObject(i);

                                //adding the product to product list
                                doclist.add(new doctors(
                                        doctor.getString("doc_name"),
                                        doctor.getString("doc_image"),
                                        doctor.getString("doc_description"),
                                        doctor.getDouble("rating"),
                                        doctor.getDouble("price"),
                                        doctor.getString("days_from"),
                                        doctor.getString("days_to"),
                                        doctor.getString("hours_from"),
                                        doctor.getString("hours_to"),
                                        doctor.getString("governarator"),
                                        doctor.getString("region"),
                                        doctor.getString("specialization_name")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            doctorsAdapter adapter = new doctorsAdapter(docs_list.this, doclist);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), "Error" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("region", reg);
                params.put("specialization_name", spec);
                return params;
            }
        };

        //adding our stringrequest to queue
        sigleton.getInstance(docs_list.this).addToRequestQueue(stringRequest);
    }
}


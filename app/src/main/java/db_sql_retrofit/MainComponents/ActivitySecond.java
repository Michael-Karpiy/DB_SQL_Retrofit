package db_sql_retrofit.MainComponents;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import db_sql_retrofit.API.ApiClient;
import db_sql_retrofit.API.InterfaceApiClient;
import db_sql_retrofit.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySecond extends AppCompatActivity {

    EditText position, id, name, description;
    CardView cancel, edit, accept;

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        position = findViewById(R.id.position);
        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);

        edit = findViewById(R.id.edit);
        accept = findViewById(R.id.accept);
        cancel = findViewById(R.id.cancel);

        cancel.setOnClickListener(view -> onBackPressed());

        Intent intent = getIntent();
        String str = intent.getStringExtra("id");
        id.setText(str);

        InterfaceApiClient apiInterface = ApiClient.getApiClient().create(InterfaceApiClient.class);
        Call<ArrayList<Model>> call = apiInterface.getModel("DB_SQL_Retrofit", "id", intent.getStringExtra("id"));
        call.enqueue(new Callback<ArrayList<Model>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Model>> call, @NonNull Response<ArrayList<Model>> response) {
                if (response.isSuccessful()) {
                    JSONArray jsonarray = null;
                    try {
                        jsonarray = new JSONArray(new Gson().toJson(response.body()));
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                    for (int i = 0; i < Objects.requireNonNull(jsonarray).length(); i++) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = jsonarray.getJSONObject(i);
                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
                        try {
                            assert jsonObject != null;

                            position.setText(jsonObject.getString("position"));

                            name.setText(jsonObject.getString("name"));
                            description.setText(jsonObject.getString("description"));
                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Model>> call, @NonNull Throwable t) {

            }
        });

        edit.setOnClickListener(view -> {

            id.setEnabled(true);
            id.setTextColor(getResources().getColor(R.color.black));

            name.setEnabled(true);
            name.setTextColor(getResources().getColor(R.color.black));

            description.setEnabled(true);
            description.setTextColor(getResources().getColor(R.color.black));

            accept.setVisibility(View.VISIBLE);
            edit.setVisibility(View.GONE);
        });

        accept.setOnClickListener(view -> {
            String url = "http://cu14284.tmweb.ru/TarkovInfo/DB_SQL_Retrofit_UPDATE.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    response -> Log.d("Success", ""),
                    error -> Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()) {
                @Override
                public Map<String, String> getParams() {
                    Map<String, String> data = new HashMap<>();

                    data.put("id", id.getText().toString());
                    data.put("name", name.getText().toString());
                    data.put("description", description.getText().toString());

                    return data;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(ActivitySecond.this);
            requestQueue.add(stringRequest);

            id.setEnabled(false);
            id.setTextColor(getResources().getColor(android.R.color.system_accent1_300));

            name.setEnabled(false);
            name.setTextColor(getResources().getColor(android.R.color.system_accent1_300));

            description.setEnabled(false);
            description.setTextColor(getResources().getColor(android.R.color.system_accent1_300));

            accept.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE);
        });
    }
}
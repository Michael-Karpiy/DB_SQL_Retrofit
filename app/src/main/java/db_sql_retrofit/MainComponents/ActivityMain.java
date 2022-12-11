package db_sql_retrofit.MainComponents;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import db_sql_retrofit.API.ApiClient;
import db_sql_retrofit.API.InterfaceApiClient;
import db_sql_retrofit.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMain extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterMain mainAdapter;
    ArrayList<Model> models;
    CardView add, refresh;
    ImageView iv_refresh;

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        models = new ArrayList<>();

        CreateDatabase();

        add = findViewById(R.id.add);
        add.setOnClickListener(view -> {
            Intent intent = new Intent(ActivityMain.this, ActivityAdd.class);
            ActivityMain.this.startActivity(intent);
        });

        refresh = findViewById(R.id.refresh);
        iv_refresh = findViewById(R.id.iv_refresh);
        refresh.setOnClickListener(view -> {

            refresh.setEnabled(false);
            handler.postDelayed(() -> refresh.setEnabled(true), 300);

            RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(300);
            rotate.setInterpolator(new LinearInterpolator());

            iv_refresh.startAnimation(rotate);

            if (mainAdapter != null) {
                mainAdapter.clear();
            }
            CreateDatabase();
        });
    }

    public void CreateDatabase() {
        InterfaceApiClient apiInterface = ApiClient.getApiClient().create(InterfaceApiClient.class);
        Call<ArrayList<Model>> call = apiInterface.getModel("DB_SQL_Retrofit", "id", "");
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

                            Model model = new Model();
                            model.setPosition(jsonObject.getString("position"));
                            model.setId(jsonObject.getString("id"));
                            model.setName(jsonObject.getString("name"));
                            //model.sethName(jsonObject.getString("description"));
                            models.add(model);

                            mainAdapter = new AdapterMain(ActivityMain.this, models);

                            recyclerView.setAdapter(mainAdapter);

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
    }
}
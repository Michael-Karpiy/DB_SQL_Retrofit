package db_sql_retrofit.MainComponents;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import db_sql_retrofit.R;


public class ActivityAdd extends AppCompatActivity {

    EditText et_position, et_id, et_name, et_description;
    CardView cancel, cv_accept;
    TextView tv_accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        et_position = findViewById(R.id.et_position);
        et_id = findViewById(R.id.et_id);
        et_name = findViewById(R.id.et_name);
        et_description = findViewById(R.id.et_description);

        tv_accept = findViewById(R.id.tv_accept);
        cv_accept = findViewById(R.id.cv_accept);
        cancel = findViewById(R.id.cancel);

        cancel.setOnClickListener(view -> onBackPressed());

        cv_accept.setOnClickListener(view -> {
            if (et_position.getText().toString().trim().length() > 0) {
                String url = "http://website/DB_SQL_Retrofit_POST.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        response -> Log.d("Success", ""),
                        error -> Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()) {
                    @Override
                    public Map<String, String> getParams() {
                        Map<String, String> data = new HashMap<>();
                        if (et_position.getText().toString().equals("")) {
                            Toast.makeText(ActivityAdd.this, "position null", Toast.LENGTH_SHORT).show();
                        } else {
                            data.put("position", et_position.getText().toString());
                            data.put("id", "@" + et_id.getText().toString());
                            data.put("name", et_name.getText().toString());
                            data.put("description", et_description.getText().toString());
                        }
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            }
        });

        et_position.addTextChangedListener(etWatcher);
        et_id.addTextChangedListener(etWatcher);
        et_name.addTextChangedListener(etWatcher);
    }

    private final TextWatcher etWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            cv_accept.setEnabled(false);
            cv_accept.setCardBackgroundColor(getResources().getColor(R.color.system_accent1_50));
            tv_accept.setTextColor(getResources().getColor(R.color.system_accent1_100));
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String positionInput = et_position.getText().toString().trim();
            String idInput = et_id.getText().toString().trim();
            String nameInput = et_name.getText().toString().trim();

            if(!positionInput.isEmpty() && !idInput.isEmpty() && !nameInput.isEmpty()){
                cv_accept.setEnabled(true);
                cv_accept.setCardBackgroundColor(getResources().getColor(R.color.system_accent1_300));
                tv_accept.setTextColor(getResources().getColor(R.color.black));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
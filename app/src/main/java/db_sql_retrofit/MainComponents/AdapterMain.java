package db_sql_retrofit.MainComponents;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import db_sql_retrofit.R;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.ViewHolder> {

    Context context;
    ArrayList<Model> model;

    public AdapterMain(Context context, ArrayList<Model> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int number) {

        holder.position.setText(model.get(number).getPosition());
        holder.id.setText(model.get(number).getId());
        holder.name.setText(model.get(number).getName());

        holder.cardview.setOnClickListener(view -> {
            Intent intent = new Intent(context, ActivitySecond.class);
            intent.putExtra("id", model.get(number).getId());
            context.startActivity(intent);
        });

        holder.delete.setOnClickListener(view -> {
            int position = holder.getAdapterPosition();

            String url = "http://cu14284.tmweb.ru/TarkovInfo/DB_SQL_Retrofit_DELETE.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        model.remove(position);
                        notifyItemRemoved(position);
                    },
                    error -> Log.e("AAAAAAAAA", String.valueOf(error))) {
                @Override
                public Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();

                    params.put("position", model.get(position).getPosition());

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView position, id, name;
        CardView cardview, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            position = itemView.findViewById(R.id.position);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            cardview = itemView.findViewById(R.id.cardview);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    public void clear() {
        int size = model.size();
        model.clear();
        notifyItemRangeRemoved(0, size);
    }
}


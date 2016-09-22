package com.harish.collpollassignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText etUrl;
    private TrainsRecyclerAdapter trainsRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        etUrl = (EditText) findViewById(R.id.url);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void getTrains(View v) {

        RestApi restApi = new RestApi(etUrl.getText().toString());

        restApi.getTrains().enqueue(new Callback<List<Train>>() {
            @Override
            public void onResponse(Call<List<Train>> call, Response<List<Train>> response) {
                if (response != null && response.body() != null) {
                    Toast.makeText(MainActivity.this, "" + response.body().size(), Toast.LENGTH_SHORT).show();
                    trainsRecyclerAdapter = new TrainsRecyclerAdapter(getBaseContext(), response.body());
                    recyclerView.setAdapter(trainsRecyclerAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Train>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

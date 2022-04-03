package com.xavier.pouyadoux.prog1.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xavier.pouyadoux.prog1.api.model.Post;
import com.xavier.pouyadoux.prog1.api.model.Postadapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {



    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayoutManager layoutManager;
    Postadapter adapter;
    List<Post> postList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = findViewById(R.id.tarea_recyclerView);
        progressBar = findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Postadapter(postList);
        recyclerView.setAdapter(adapter);



        fetchPosts();



        /**
         * boton para añadir nueva tarea
         */
        Button mButtonAddTarea = (Button) findViewById(R.id.Button_add);
        mButtonAddTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * llamada fragment activity
                 */
                FragmentManager fm = getSupportFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.fragment_container);

                if (fragment == null) {
                    fragment = new ListFragment(postList);
                    fm.beginTransaction()
                            .add(R.id.fragment_container, fragment)
                            .commit();
                }
            }

        });

    }


    private void fetchPosts() {
        progressBar.setVisibility(View.VISIBLE);
        Retrofitclient.getREtrofitClient().getPost().enqueue(new Callback<List<Post>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null)
                    postList.addAll(response.body());
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
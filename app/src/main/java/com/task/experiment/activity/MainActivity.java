package com.task.experiment.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.task.experiment.R;
import com.task.experiment.adapter.CustomAdapter;
import com.task.experiment.model.PostData;
import com.task.experiment.viewmodel.ViewModel;

public class MainActivity extends AppCompatActivity implements CustomAdapter.ItemTapped {
    private static final String TAG = "MainActivity";

    RecyclerView recyclerView;
    ProgressBar progressBar;
    ViewModel viewModel;
    CustomAdapter adapter;
    JsonObject[] j;
    String name, owner, desc;
    static int LAUNCH_ACTIVITY_TWO = 1;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.searchItem) {
            Log.i(TAG, "onOptionsItemSelected: " + "itemClicked");
            startActivity(new Intent(this, SearchActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LAUNCH_ACTIVITY_TWO && resultCode == RESULT_OK && data != null) {
            name = data.getStringExtra("edit_name");
            owner = data.getStringExtra("edit_owner");
            desc = data.getStringExtra("edit_desc");
            position = data.getIntExtra("edit_position", 0);

            PostData updateData = new PostData(name, owner, desc, position);
            adapter.updatedData(updateData);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewData();
    }

    private void recyclerViewData() {
        adapter = new CustomAdapter(this, j, this);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String result = sharedPreferences.getString("query", "");

        viewModel.makeCall(result);

        viewModel.getMutableLiveData().observe(this, data -> {
            if (data != null) {
                j = data;
                adapter.setUpdatedList(j);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onTapped(int position, String name, String owner, String desc) {
        Toast.makeText(this, "item clicked " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        intent.putExtra("full_name", name);
        intent.putExtra("owner", owner);
        intent.putExtra("desc", desc);
        intent.putExtra("position", position);

        startActivityForResult(intent, LAUNCH_ACTIVITY_TWO);
    }
}

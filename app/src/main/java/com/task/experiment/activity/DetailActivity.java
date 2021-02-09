package com.task.experiment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.task.experiment.R;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    EditText nameEt, ownerEt, descEt;
    Button submitBtn;
    Intent intent;
    String name, editName, owner, editOwner, desc, editDesc;
    int position;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nameEt = findViewById(R.id.nameEt);
        ownerEt = findViewById(R.id.ownerEt);
        descEt = findViewById(R.id.descEt);
        submitBtn = findViewById(R.id.submitBtn);

        intent = getIntent();
        name = intent.getStringExtra("full_name");
        owner = intent.getStringExtra("owner");
        desc = intent.getStringExtra("desc");
        position = intent.getIntExtra("position", 0);

        nameEt.setText("Full Name:- " + name);
        ownerEt.setText("Owner:- " + owner);
        descEt.setText("Description:- " + desc);

        submitBtn.setOnClickListener(v -> {
            editName = nameEt.getText().toString();
            editOwner = ownerEt.getText().toString();
            editDesc = descEt.getText().toString();

            Intent editIntent = new Intent();
            editIntent.putExtra("edit_name", editName);
            editIntent.putExtra("edit_owner", editOwner);
            editIntent.putExtra("edit_desc", editDesc);
            editIntent.putExtra("edit_position", position);
            Log.i(TAG, "onCreate: " + position);
            setResult(Activity.RESULT_OK, editIntent);
            finish();
        });
    }
}
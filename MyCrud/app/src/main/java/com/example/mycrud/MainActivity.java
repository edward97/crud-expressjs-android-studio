package com.example.mycrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editEmail, editUser, editPass;
    Button btnSubmit, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = (EditText) findViewById(R.id.input_email);
        editUser = (EditText) findViewById(R.id.input_user);
        editPass = (EditText) findViewById(R.id.input_pass);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnList = (Button) findViewById(R.id.btn_list);

        btnSubmit.setOnClickListener(this);
        btnList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSubmit) {
            // Toast.makeText(MainActivity.this, "btn-submit", Toast.LENGTH_SHORT).show();
        } else if (view == btnList) {
            Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
            startActivity(intent);
            // Toast.makeText(MainActivity.this, "btn-history", Toast.LENGTH_SHORT).show();
        }
    }
}

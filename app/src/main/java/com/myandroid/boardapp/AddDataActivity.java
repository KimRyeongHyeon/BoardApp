package com.myandroid.boardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddDataActivity extends AppCompatActivity {

    private EditText edTitle, edUser, edPassword, edContent;
    private Button insertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        edTitle = findViewById(R.id.edTitle);
        edUser = findViewById(R.id.edUser);
        edPassword = findViewById(R.id.edPassword);
        edContent = findViewById(R.id.edContent);

        insertBtn = findViewById(R.id.insertBtn);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
    }

    private void insertData() {
        final String title = edTitle.getText().toString().trim();
        final String user = edUser.getText().toString().trim();
        final String password = edPassword.getText().toString().trim();
        final String content = edContent.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if(title.isEmpty() || title == null) {
            Toast.makeText(this, "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        } else if(user.isEmpty() || user == null) {
            Toast.makeText(this, "작성자를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        } else if(password.isEmpty() || password == null) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        } else if(content.isEmpty() || content == null) {
            Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://soulstring94.cafe24.com/board_insert.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equalsIgnoreCase("Data Inserted")) {
                                Toast.makeText(AddDataActivity.this, "작성 완료!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                                progressDialog.dismiss();
                            } else {
                                Toast.makeText(AddDataActivity.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AddDataActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("boardTitle", title);
                    params.put("boardUser", user);
                    params.put("boardPassword", password);
                    params.put("boardContent", content);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(AddDataActivity.this);
            requestQueue.add(request);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
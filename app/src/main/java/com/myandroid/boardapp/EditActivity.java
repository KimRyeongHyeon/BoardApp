package com.myandroid.boardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {

    private EditText edID, edTitle, edUser, edContent;
    private Button updateBtn;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edID = findViewById(R.id.ed_ID);
        edTitle = findViewById(R.id.ed_Title);
        edUser = findViewById(R.id.ed_User);
        edContent = findViewById(R.id.ed_Content);

        updateBtn = findViewById(R.id.updateBtn);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        edID.setText(MainActivity.boardArrayList.get(position).getId());
        edTitle.setText(MainActivity.boardArrayList.get(position).getTitle());
        edUser.setText(MainActivity.boardArrayList.get(position).getUser());
        edContent.setText(MainActivity.boardArrayList.get(position).getContent());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = edID.getText().toString();
                final String title = edTitle.getText().toString();
                final String user = edUser.getText().toString();
                final String content = edContent.getText().toString();

                progress();

                StringRequest request = new StringRequest(Request.Method.POST, "https://soulstring94.cafe24.com/board_update.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(EditActivity.this, response, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("boardNum", id);
                        params.put("boardTitle", "(수정) " + title);
                        params.put("boardUser", user);
                        params.put("boardContent", content);

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(EditActivity.this);
                requestQueue.add(request);
            }
        });
    }

    private void progress() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();
    }
}

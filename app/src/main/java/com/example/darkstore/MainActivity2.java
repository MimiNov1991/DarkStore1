package com.example.darkstore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MainActivity2 extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText cnfpassword;
    Button login;
    TextView register;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        username = (EditText) findViewById(R.id.edittext_username);
        password = (EditText) findViewById(R.id.edittext_password);
        cnfpassword = (EditText) findViewById(R.id.edittext_cnf_password);
        login = (Button) findViewById(R.id.button_login);
        register = (TextView) findViewById(R.id.textview_register);
        checkBox = (CheckBox) findViewById(R.id.checkBox2);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(loginIntent);

            }
        });

        username.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    Toast.makeText(MainActivity2.this, "" + username.getText().toString(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });




        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    Toast.makeText(MainActivity2.this, "" + password.getText().toString(), Toast.LENGTH_SHORT).show();
                    // login.performClick();
                    return true;
                }
                return false;
            }
        });

        cnfpassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    Toast.makeText(MainActivity2.this, "" + cnfpassword.getText().toString(), Toast.LENGTH_SHORT).show();
                    login.performClick();
                    return true;
                }
                return false;
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // show password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    cnfpassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {

                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    cnfpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    postDataLogin();


                } catch (Exception e) {
                    e.getMessage();
                }
            }


            public void postDataLogin() {

                final ProgressDialog loading = new ProgressDialog(MainActivity2.this);


                JSONObject object = new JSONObject();

                try {
                    //input your API parameters

                   /* {
                        "UserName":"IvanaR",
                            "OldPassword":"init1234",
                            "NewPassword":"Cimerka1234",
                            "Application":{"ApplicationCode":"MobApp"}
                    }*/


                    //

                    JSONObject jsonObjectStore = new JSONObject();
                    JSONObject userName = new JSONObject();
                    JSONObject lozinka = new JSONObject();
                    JSONObject aplikacija = new JSONObject();
                    JSONObject nova_lozinka = new JSONObject();
                    jsonObjectStore.put("StoreID", "213IP5");
                    aplikacija.put("ApplicationCode", "MobApp");
                    userName.put("UserName", "" + username.getText() + "");
                    lozinka.put("OldPassword", "" + password.getText() + "");
                    object.put("UserName", "" + username.getText() + "");
                    object.put("OldPassword", "" + password.getText() + "");
                    object.put("NewPassword", "" + cnfpassword.getText() + "");

                    // object.put("StoreUnit", jsonObjectStore);
                    object.put("Application", aplikacija);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Enter the correct url for your api service site
                // String url_post = "http://10.160.33.202:80/api/ArticleSlotPutaway/InsertArticleSlotPutaway";

                //http://10.160.33.202:80/api/AppUser/UpdateAppUserPassword
                // Produkcija url 10.163.252.26

                String url_post = "http://10.163.252.26/api/AppUser/UpdateAppUserPassword";

              //  String url_post = "http://10.160.33.202:80/api/AppUser/UpdateAppUserPassword";

                //   String url_post = "http://10.160.33.202:80/api/AppUser/UpdateAppUserPassword";
                try {
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url_post, object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                //loading.d("JSON", String.valueOf(response));
                                loading.dismiss();
                                String Error = response.getString("HttpStatusCode");

                                if (Error.equals("200")) {

                                    String Message400 = response.getString("Message");
                                    Toast.makeText(getApplicationContext(), "Ok" + Message400, Toast.LENGTH_SHORT).show();
                                    Intent mojIntent = new Intent(MainActivity2.this, MainActivity.class);
                                    startActivity(mojIntent);

                                }

                                if (Error.equals("400")) {

                                    String Message400 = response.getString("Message");
                                    Toast.makeText(getApplicationContext(), "Niste uneli lozinku kako treba" + Message400, Toast.LENGTH_SHORT).show();
                                    Intent mojIntent = new Intent(MainActivity2.this, MainActivity2.class);
                                    startActivity(mojIntent);

                                }


                                /////////////////////////////////////

                            } catch (JSONException e) {
                                e.printStackTrace();
                                loading.dismiss();
                            }
//                        resultTextView.setText("String Response : "+ response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            NetworkResponse response = error.networkResponse;
                            if (error instanceof ServerError && response != null) {
                                try {
                                    String res = new String(response.data,
                                            HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                    // Now you can use any deserializer to make sense of data
                                    JSONObject obj = new JSONObject(res);
                                } catch (UnsupportedEncodingException e1) {
                                    // Couldn't properly decode data to string
                                    e1.printStackTrace();
                                } catch (JSONException e2) {
                                    // returned data is not JSONObject?
                                    e2.printStackTrace();
                                }
                            }

                            loading.dismiss();
                            //MainActivity5.d("Error", "Error: " + error.getMessage());
                            Toast.makeText(MainActivity2.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    });

                    // RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    requestQueue.add(jsonObjectRequest);


                } catch (Exception e) {
                    e.printStackTrace();

                }


            }

            ;


        });


    }
};






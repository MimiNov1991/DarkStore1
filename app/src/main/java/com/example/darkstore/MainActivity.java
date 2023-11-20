package com.example.darkstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    TextView register;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.edittext_username);
        password = (EditText)findViewById(R.id.edittext_password);
        login = (Button) findViewById(R.id.button_login);
        register = (TextView) findViewById(R.id.textview_register);
        checkBox = (CheckBox) findViewById(R.id.checkBox2);

        Globalna global = new Globalna();
        String globalniToke = global.globalna;

        Globals sharedData = Globals.getInstance();
        Globals1 sharedData1 = Globals1.getInstance();




        username.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    Toast.makeText(MainActivity.this, "" + username.getText().toString(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);


        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    //Toast.makeText(MainActivity.this, "" + password.getText().toString(), Toast.LENGTH_SHORT).show();
                    login.performClick();
                   // return true;
                }
                return false;
            }
        });




       /* password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    login.performClick();

                }
                return false;
            }
        });*/



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent registerIntent = new Intent(MainActivity.this, MainActivity2.class);
               startActivity(registerIntent);
               finish();
            }
        });

       checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked){
                   // show password
                   password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

               } else {

                   password.setTransformationMethod(PasswordTransformationMethod.getInstance());
               }
           }
       });




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                 postDataLogin();


                } catch(Exception e){
                    e.getMessage();
                }
            }




           public void postDataLogin() {

                final ProgressDialog loading = new ProgressDialog(MainActivity.this);


                JSONObject object = new JSONObject();

                try {
                    //input your API parameters

                    JSONObject jsonObjectStore = new JSONObject();
                    JSONObject userName = new JSONObject();
                    JSONObject lozinka = new JSONObject();
                    JSONObject aplikacija = new JSONObject();
                    jsonObjectStore.put("StoreID", "213IP5");
                    aplikacija.put("ApplicationCode", "MobApp");
                    userName.put("UserName", ""+ username.getText() + "");
                    lozinka.put("Password",""+ password.getText()+"");
                    object.put("UserName", ""+ username.getText() + "");
                    object.put("Password",""+ password.getText()+"");
                    object.put("StoreUnit", jsonObjectStore);
                    object.put("Application", aplikacija);

                    String ime_op_gl = userName.getString("UserName");

                    sharedData1.setValue(ime_op_gl);






                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Enter the correct url for your api service site
                // String url_post = "http://10.160.33.202:80/api/ArticleSlotPutaway/InsertArticleSlotPutaway";

               //http://10.160.33.202:80/api/AppUser/UpdateAppUserPassword
               // Produkcija url 10.163.252.26

               String url_post = "http://10.163.252.26/api/AppUser/Login";

               //String url_post = "http://10.160.33.202:80/api/AppUser/Login";

            //   String url_post = "http://10.160.33.202:80/api/AppUser/UpdateAppUserPassword";
                try
                {
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url_post,object , new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                //loading.d("JSON", String.valueOf(response));
                                loading.dismiss();
                                String Error = response.getString("HttpStatusCode");



                                JSONObject jsonObjectData = new JSONObject();
                                JSONObject jsonObjectArticle = new JSONObject();
                                JSONObject jsonObjectInitLozinka = new JSONObject();

                                JSONObject jsonObjectToken = new JSONObject();

                                if (Error.equals("200")) {
                                    try{
                                        jsonObjectToken = response.getJSONObject("Data");

                                    }catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                }

                                if (Error.equals("200")) {

                                    String token = jsonObjectToken.getString("AccessToken");

                                    global.globalna = token;

                                    sharedData.setValue(token);



                                }





                                try {
                                    jsonObjectData = response.getJSONObject("Error");
                                    jsonObjectArticle = jsonObjectData .getJSONObject("Global");

                                   // jsonObjectInitLozinka = jsonObjectArticle.getJSONObject("Message");



                                    //initLozinka.setText(sf_art_pro);

                                }catch (JSONException e){
                                    //e.printStackTrace();
                                    if (Error.equals("200") && ( (jsonObjectToken.getString("AccessToken") != null && !jsonObjectToken.getString("AccessToken").isEmpty() && !jsonObjectToken.getString("AccessToken").equals("null")) ) ) {

                                        String Message400 = response.getString("Message");
                                        Toast.makeText(getApplicationContext(), "Ok" + Message400, Toast.LENGTH_SHORT).show();
                                        Intent mojIntent = new Intent(MainActivity.this, MainActivity4.class);
                                        startActivity(mojIntent);

                                    }
                                }

                                String ErrorInit = jsonObjectArticle.getString("Message");


                                if (Error.equals("400") && !"Promeni inicijalnu lozinku.".equals(ErrorInit)) {

                                    String Message400 = response.getString("Message");
                                    Toast.makeText(getApplicationContext(), "" + ErrorInit, Toast.LENGTH_SHORT).show();
                                    Intent mojIntent = new Intent(MainActivity.this, MainActivity.class);
                                    startActivity(mojIntent);

                                }



                                if (Error.equals("400") && ErrorInit.equals( "Promeni inicijalnu lozinku." )) {

                                    String Message400 = response.getString("Message");
                                    Toast.makeText(getApplicationContext(), "" + ErrorInit, Toast.LENGTH_SHORT).show();
                                    Intent mojIntent = new Intent(MainActivity.this, MainActivity2.class);
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
                            Toast.makeText(MainActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }




                    });

                   // RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    requestQueue.add(jsonObjectRequest);



                } catch (Exception e) {
                    e.printStackTrace();

                }



            };


        });


    }



    //}

};


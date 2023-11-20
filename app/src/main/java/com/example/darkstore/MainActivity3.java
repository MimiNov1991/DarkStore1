package com.example.darkstore;

import android.annotation.SuppressLint;
import org.apache.commons.io.FileUtils;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractCollection;


import com.android.volley.RequestQueue;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.squareup.picasso.Picasso;
//import com.squareup.picasso.PicassoProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class MainActivity3 extends AppCompatActivity {
    Button scan;
    TextView textViewBarKod;
    EditText sf_art;
    EditText naziv_art;
    EditText bar_kod_art;
    CheckBox checkBox;
    CheckBox checkBox1;
    EditText kms_art;
    Button ponisti;
    ImageView img_peak;


  /*  private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {



        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // get the content of both the edit text
            String bar_kod = bar_kod_art.getText().toString();


            // check whether both the fields are empty or not
            try {
                checkBox.setChecked(!bar_kod.isEmpty());


                popuniBarKod();

            } catch (Exception e ){
                e.getMessage();
            }

        }

        @Override
        public void afterTextChanged(Editable s) {
           // popuniBarKod();

        }
    };*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.izlaz) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
            builder.setTitle(R.string.app_name);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setMessage("Da li zelite da izadjete iz aplikacije")
                    .setCancelable(false)
                    .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("NE", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        if (id == R.id.logout){
            // Intent myintent = new Intent(MainActivity.this, Login.class);

            Intent myintent = new Intent(MainActivity3.this, MainActivity.class);
            startActivity(myintent);
            return  false;
        }

        if (id == R.id.pocetna){
            // Intent myintent = new Intent(MainActivity.this, Login.class);

            Intent myintent = new Intent(MainActivity3.this, MainActivity4.class);
            startActivity(myintent);
            return  false;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Globals sharedData = Globals.getInstance();

      //   scan = findViewById(R.id.scan);
      //  textViewBarKod = findViewById(R.id.textViewBarKod);
        bar_kod_art = findViewById(R.id.bar_kod_art);
        checkBox = findViewById(R.id.checkBox);
        ponisti = findViewById(R.id.ponisti);
        img_peak = findViewById(R.id.img_peak);
        checkBox1 = findViewById(R.id.checkBox1);

        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( checkBox1.isChecked()) {

                    bar_kod_art.setInputType(InputType.TYPE_CLASS_PHONE);
                    KeyListener keyListener1 = DigitsKeyListener.getInstance("1234567890");

                    bar_kod_art.setKeyListener(keyListener1);
                }
                else {
                    bar_kod_art.setRawInputType(InputType.TYPE_NULL);
                    bar_kod_art.setFocusable(true);
                }
            }
        });

        String bar_kod = bar_kod_art.getText().toString();

        if (bar_kod.isEmpty()) {
            bar_kod_art.requestFocus();
        }

        bar_kod_art.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // get the content of both the edit text
                String bar_kod = bar_kod_art.getText().toString();


                // check whether both the fields are empty or not
                try {
                    checkBox.setChecked(!bar_kod.isEmpty());


                    popuniBarKod();

                } catch (Exception e ){
                    e.getMessage();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                // popuniBarKod();

            }
        } );



      //  bar_kod_art.addTextChangedListener(textWatcher);

        bar_kod_art.setRawInputType(InputType.TYPE_NULL);
        bar_kod_art.setFocusable(true);


       // String url = "http://10.160.33.202:80/api/ArticleSlotPutaway/GetArticleSlotLocationByArticle?parStoreId=213IP5&parArticleBarCode=8606017106067";

        //String url = "http://10.160.33.202:80/api/ArticleSlotPutaway/GetArticleSlotLocationByArticle?parStoreId=213IP5&parArticleBarCode=" +textViewBarKod.getText()+"";

        ponisti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // bar_kod_art.setText(null);

                ((EditText) findViewById(R.id.bar_kod_art)).setText("");
                ((EditText) findViewById(R.id.sf_art)).setText("");
                ((EditText) findViewById(R.id.naziv_art)).setText("");
                ((EditText) findViewById(R.id.kms_art)).setText("");

                //((EditText) findViewById(R.id.datum_rute)).setText("");
               ((ImageView) findViewById(R.id.img_peak)).setImageResource(R.drawable.logo1);
                ((CheckBox) findViewById(R.id.checkBox1)).setChecked(false);
               // naziv_art.setText(null);
               // sf_art.setText(null);
               // kms_art.setText(null);
                Toast.makeText(getApplicationContext(), "Uspesno ste ponistili podatke.Skenirajte ponovo!", Toast.LENGTH_SHORT).show();

                FileUtils.deleteQuietly(getApplicationContext().getCacheDir());


            }
        });




      //  String url = "http://10.160.33.202:80/api/ArticleSlotPutaway/GetArticleSlotLocationByArticle?parStoreId=213IP5&parArticleBarCode=" +bar_kod_art.getText()+"";


        //"http://10.160.33.202:80/api/ArticleSlotLocation/GetArticleSlotLocationByArticle?parStoreId=213IP5&parArticleBarCode="
        String url = " http://10.160.33.202:80/api/ArticleSlotLocation/GetArticleSlotLocationByArticle?parStoreId=213IP5&parArticleBarCode=S"+bar_kod_art.getText()+"";


        // String url = "http://10.160.33.202:80/api/ArticleSlotPutaway/GetArticleSlotLocationByArticle?parStoreId=213IP5&parArticleBarCode=" +bar_kod_art.getText()+"";
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
        try{
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //   String b = "Abc12312";
                    EditText sf_art = (EditText) findViewById(R.id.sf_art);
                    EditText naziv_art =(EditText)  findViewById(R.id.naziv_art);
                    EditText kms_art =(EditText) findViewById(R.id.kms_art);
                    ImageView img_slika = (ImageView) findViewById(R.id.img_peak);

                    JSONObject jsonObjectData = new JSONObject();
                    JSONObject jsonObjectArticle = new JSONObject();
                    JSONObject jsonObjectArticleCode = new JSONObject();
                    JSONObject jsonObjectArticleLevel = new JSONObject();

                    try {
                        jsonObjectData = response.getJSONObject("Data");
                        jsonObjectArticle = jsonObjectData .getJSONObject("Article");
                        String sf_art_pro = (String) jsonObjectArticle.get("ArticleCode");



                        sf_art.setText(sf_art_pro);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }


                    try {
                        jsonObjectData = response.getJSONObject("Data");
                        jsonObjectArticle = jsonObjectData .getJSONObject("Article");
                        String naziv_artikla = (String) jsonObjectArticle.get("ArticleName");
                        naziv_art.setText(naziv_artikla);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                    try {
                        jsonObjectData = response.getJSONObject("Data");
                        jsonObjectArticle = jsonObjectData .getJSONObject("Article");
                        String url_slike = (String) jsonObjectArticle.get("ArticleImageUrl");

                       // Picasso.with(context).load(url_slike).into(img_slika);
                        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView)
                        //Picasso.get().load(url_slike).resize(300, 300).into(img_slika);

                       // naziv_art.setText(naziv_artikla);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }


                    try {

                        jsonObjectData = response.getJSONObject("Data");
                        jsonObjectArticle = jsonObjectData .getJSONObject("Rack");
                        String slotLocation = (String) jsonObjectArticle.get("ReplenishmentAisle");

                        kms_art.setText(slotLocation);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                }

            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    String a = "Abc";
                }
            }  ) ;

            ExampleRequestQueue.add(jsonObjectRequest);
        }catch (Exception e){
            e.printStackTrace();  ;



        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent registerIntent = new Intent(MainActivity3.this, MainActivity4.class);
        startActivity(registerIntent);
        finish();
       // setContentView(R.layout.activity_main2);
    }



    private void popuniBarKod() {

        String header = Globals.getInstance().getValue();

        //String url = "http://10.160.33.202:80/api/ArticleSlotPutaway/GetArticleSlotLocationByArticle?parStoreId=213IP5&parArticleBarCode=" +bar_kod_art.getText()+"";
        //String url = "http://10.160.33.202:80/api/ArticleSlotLocation/GetArticleSlotLocationByArticle?parStoreId=213IP5&parArticleBarCode=" +bar_kod_art.getText()+"";

        // tempo003 213IP1 - http://10.163.248.26/api/ArticleSlotLocation/GetArticleSlotLocationByArticle?parStoreId=213IP1&parArticleBarCode=
        // Produkcija 10.163.252.26
        String url = "http://10.163.252.26/api/ArticleSlotLocation/GetArticleSlotLocationByArticle?parStoreId=213IP5&parArticleBarCode=" +bar_kod_art.getText()+"";
        //String url = "http://10.160.33.202:80/api/ArticleSlotLocation/GetArticleSlotLocationByArticle?parStoreId=213IP5&parArticleBarCode=" +bar_kod_art.getText()+"";
       // String url = "http://10.163.248.26/api/ArticleSlotLocation/GetArticleSlotLocationByArticle?parStoreId=213IP1&parArticleBarCode=" +bar_kod_art.getText()+"";



        try{

            /*Map<String,String> params=new HashMap<String,String>();
            params.put("token",header);*/

            JSONObject postparams=new JSONObject();
            postparams.put("Authorization", "Bearer"+   header);

            RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
          //  JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,  null, new Response.Listener<JSONObject>(){
                @Override
                public void onResponse(JSONObject response) {
                    //   String b = "Abc12312";
                    EditText sf_art = (EditText) findViewById(R.id.sf_art);
                    EditText naziv_art =(EditText)  findViewById(R.id.naziv_art);
                    EditText kms_art =(EditText) findViewById(R.id.kms_art);
                    ImageView img_slika = (ImageView) findViewById(R.id.img_peak);

                    JSONObject jsonObjectData = new JSONObject();
                    JSONObject jsonObjectArticle = new JSONObject();
                    JSONObject jsonObjectArticleCode = new JSONObject();
                    JSONObject jsonObjectArticleLevel = new JSONObject();

                    try {
                       jsonObjectData = response.getJSONObject("Data");
                        jsonObjectArticle = jsonObjectData .getJSONObject("Article");
                        String sf_art_pro = (String) jsonObjectArticle.get("ArticleCode");

                        sf_art.setText(sf_art_pro);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                    try {
                        jsonObjectData = response.getJSONObject("Data");
                        jsonObjectArticle = jsonObjectData .getJSONObject("Article");

                        String naziv_artikla = (String) jsonObjectArticle.get("ArticleName");

                      // String substr1=naziv_artikla.substring(naziv_artikla.indexOf("|"));
                        int position = naziv_artikla.indexOf("|");


                        int lastposition = naziv_artikla.length();


                        Spannable WordtoSpan = new SpannableString(naziv_artikla);
                        WordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), position, lastposition, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                       naziv_art.setText(WordtoSpan);

                    }catch (JSONException e){

                    e.printStackTrace();
                }

                    try {
                        jsonObjectData = response.getJSONObject("Data");
                        jsonObjectArticle = jsonObjectData .getJSONObject("Article");

                        //
                        String url_slike = (String) jsonObjectArticle.get("ArticleImageUrl");

                        //String url_slike = "https://wes05dvapa15384lmapslike.blob.core.windows.net/slikeart/7175449.jpg";

                       // Picasso.with(MainActivity3.this).load(url_slike).resize(50, 50).into(img_slika);

                        Picasso.get().load(url_slike).resize(80, 80).into(img_slika);

                        //Picasso.get().load(intent.getStringExtra(FLAG)).into(binding.ivCountryPoster);

                        Picasso.Builder builder = new Picasso.Builder(MainActivity3.this);



                        builder.listener(new Picasso.Listener()
                        {
                            @Override
                            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
                            {
                                exception.printStackTrace();
                               // Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                                ((ImageView) findViewById(R.id.img_peak)).setImageResource(R.drawable.logo1);

                            }
                        });
                        builder.build().load(url_slike).into(img_slika);




                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                       // sf_art.setText(sf_art_pro);



                    try {

                        jsonObjectData = response.getJSONObject("Data");
                        jsonObjectArticle = jsonObjectData .getJSONObject("Rack");
                        String slotLocation = (String) jsonObjectArticle.get("ReplenishmentAisle");

                        kms_art.setText(slotLocation);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                }

            }, new Response.ErrorListener() {






                @Override
                public void onErrorResponse(VolleyError error) {

                    String a = "Abc";
                }



            }  )
            {
                /**
                 * Passing some request headers
                 * */
                @NonNull
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Bearer " + header);
                    return headers;
                }


            };





            ExampleRequestQueue.add(jsonObjectRequest);
        }catch (Exception e){
            e.printStackTrace();



        }
    }






    }


package com.example.darkstore;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import java.io.File;
import org.apache.commons.io.FileUtils;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;


public class MainActivity5 extends AppCompatActivity {
    Button scan;
    TextView textViewBarKod;

    //Button scanKomisiono;
   // TextView komisionoScan;
    Button ponisti;
    EditText bar_kod_komisiono;
    Button sacuvaj;
    EditText vreme;
    EditText kolicina;
    EditText slika;
    EditText sf_art;
    EditText naziv_art;
    EditText kms_art;
    EditText regal;
    EditText rolka;
    EditText operater;
    String sf_pos_rj = "213IP5";
    EditText bar_kod_art;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    ImageView slika_sort;

    private RequestQueue requestQueueNovi;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.izlaz) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity5.this);
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

            Intent myintent = new Intent(MainActivity5.this, MainActivity.class);
            startActivity(myintent);
            return  false;
        }

        if (id == R.id.pocetna){
            // Intent myintent = new Intent(MainActivity.this, Login.class);

            Intent myintent = new Intent(MainActivity5.this, MainActivity4.class);
            startActivity(myintent);
            return  false;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent registerIntent = new Intent(MainActivity5.this, MainActivity4.class);
        startActivity(registerIntent);
        finish();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        ////////////////

        //// Test test test

       //  scan = (Button) findViewById(R.id.scan);
       // textViewBarKod =(TextView) findViewById(R.id.textViewBarKod);

      //  scanKomisiono =(Button) findViewById(R.id.scan_komisiono);
      //  komisionoScan = (TextView) findViewById(R.id.textViewBarKodKomisionoMesto);
        ponisti = (Button) findViewById(R.id.ponisti);
      //  vreme = (EditText) findViewById(R.id.dat_upisa) ;
       // ponisti = (Button) findViewById(R.id.ponisti);
        kolicina = (EditText) findViewById(R.id.kolicina);

        kolicina.setInputType(InputType.TYPE_CLASS_PHONE);

        KeyListener keyListener = DigitsKeyListener.getInstance("1234567890");

        kolicina.setKeyListener(keyListener);


      //  slika = (EditText) findViewById(R.id.datum_rute);
        sf_art = (EditText) findViewById(R.id.sf_art);
        naziv_art = (EditText) findViewById(R.id.naziv_art);
        kms_art = (EditText) findViewById(R.id.kms_art);
       // regal = (EditText) findViewById(R.id.regal);
       // rolka = (EditText) findViewById(R.id.rolka);
      //  operater = (EditText) findViewById(R.id.upisao);
        sacuvaj = (Button) findViewById(R.id.save);
        bar_kod_art = (EditText) findViewById(R.id.bar_kod_art);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox1);

        bar_kod_komisiono = (EditText) findViewById(R.id.bar_kod_komisiono);
        slika_sort = (ImageView) findViewById(R.id.img_peak);



        Globals1 sharedData1 = Globals1.getInstance();

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( checkBox3.isChecked()) {


                    bar_kod_art.setInputType(InputType.TYPE_CLASS_PHONE);
                 //   bar_kod_komisiono.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);


                    KeyListener keyListener1 = DigitsKeyListener.getInstance("1234567890");

                    bar_kod_art.setKeyListener(keyListener);
                   // bar_kod_komisiono.setKeyListener(keyListener);
                    }

                else {

                    bar_kod_art.setRawInputType(InputType.TYPE_NULL);
                    bar_kod_art.setFocusable(true);


                    bar_kod_komisiono.setRawInputType(InputType.TYPE_NULL);
                    bar_kod_komisiono.setFocusable(true);
                }


            }
        });



            bar_kod_art.setRawInputType(InputType.TYPE_NULL);
            bar_kod_art.setFocusable(true);


            bar_kod_komisiono.setRawInputType(InputType.TYPE_NULL);
            bar_kod_komisiono.setFocusable(true);


        String bar_kod = bar_kod_art.getText().toString();
        String bar_kod_provera = bar_kod_komisiono.getText().toString();

        if ( checkBox3.isChecked()) {

        } else if  (bar_kod.isEmpty() && bar_kod_provera.isEmpty() && checkBox3.isChecked() == false ) {
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
                    checkBox1.setChecked(!bar_kod.isEmpty());




                    popuniBarKod();


                  //  bar_kod_art.setFocusable(false);
                  //  bar_kod_art.setFocusableInTouchMode(false);

                    String provera = bar_kod_komisiono.getText().toString();
                    String bar_kod_art_provera = bar_kod_art.getText().toString();



                   // bar_kod_komisiono.setFocusable(true);
                   // bar_kod_komisiono.setFocusableInTouchMode(true);

                } catch (Exception e ){
                    e.getMessage();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                // popuniBarKod();
                if(bar_kod_art.toString().length() > 0 && checkBox3.isChecked() == false){
                    bar_kod_komisiono.requestFocus();
                }
                bar_kod_komisiono.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        // get the content of both the edit text

                        String komisiono =  bar_kod_komisiono.getText().toString();



                        // check whether both the fields are empty or not
                        try {
                            checkBox2.setChecked(!komisiono.isEmpty());

                            String b = kms_art.getText().toString();
                            String a = bar_kod_komisiono.getText().toString();

                            try {
                                if (a.equals(b)) {
                                    //kolicina.setFocusableInTouchMode(true);
                                    //kolicina.setFocusable(true);
                                    kolicina.setFocusable(true);
                                    kolicina.setEnabled(true);

                                } /*else {

                                    kolicina.setFocusable(false);
                                    kolicina.setEnabled(false);

                                    Toast.makeText(getApplicationContext(), "Ne mozete menjati kolicinu lokacije se ne slazu", Toast.LENGTH_SHORT).show();


                               }*/
                            }catch (Exception e){
                                e.getMessage();
                            }


                        } catch (Exception e ){
                            e.getMessage();
                        }

                    }



                    @Override
                    public void afterTextChanged(Editable s) {
                        // popuniBarKod();

                        try {
                            String b = kms_art.getText().toString();
                            String a = bar_kod_komisiono.getText().toString();
                            if (a.equals(b) || a!= null || !a.isEmpty() || b!=null || !b.isEmpty()) {
                                kolicina.setFocusableInTouchMode(true);
                                //kolicina.setFocusable(true);
                                kolicina.setFocusable(true);
                                kolicina.setEnabled(true);

                            } else {

                                kolicina.setFocusable(false);
                                kolicina.setEnabled(false);
                                Toast.makeText(getApplicationContext(), "Ne mozete menjati kolicinu lokacije se ne slazu", Toast.LENGTH_SHORT).show();



                            }
                        }catch (Exception e){
                            e.getMessage();
                        }

                    }
                });

            }
        }


        );




     //   bar_kod_komisiono.setFocusable(true);
     //   bar_kod_komisiono.setFocusableInTouchMode(true);



      /*  if (provera.isEmpty() && !bar_kod_art_provera.isEmpty()) {

            bar_kod_komisiono.requestFocus();
        }


        bar_kod_komisiono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // get the content of both the edit text

                String komisiono =  bar_kod_komisiono.getText().toString();

                // check whether both the fields are empty or not
                try {
                    checkBox2.setChecked(!komisiono.isEmpty());

                    String b = kms_art.getText().toString();;
                    String a = bar_kod_komisiono.getText().toString();

                    try {
                        if (a.equals(b)) {
                            //kolicina.setFocusableInTouchMode(true);
                            //kolicina.setFocusable(true);
                            kolicina.setFocusable(true);
                            kolicina.setEnabled(true);

                        } else {



                        }
                    }catch (Exception e){
                        e.getMessage();
                    }


                } catch (Exception e ){
                    e.getMessage();
                }

            }



            @Override
            public void afterTextChanged(Editable s) {
                // popuniBarKod();

            }
        });
*/



        // bar_kod_art.addTextChangedListener(textWatcher);
     //   bar_kod_komisiono.addTextChangedListener(textWatcher1);


        ponisti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDateTimeDialog(dolazak);

                ((EditText) findViewById(R.id.bar_kod_art)).setText("");
                ((EditText) findViewById(R.id.sf_art)).setText("");
                ((EditText) findViewById(R.id.naziv_art)).setText("");
             //   ((EditText) findViewById(R.id.datum_rute)).setText("");
                ((ImageView) findViewById(R.id.img_peak)).setImageResource(R.drawable.logo1);
                ((EditText) findViewById(R.id.kms_art)).setText("");
                ((EditText) findViewById(R.id.bar_kod_komisiono)).setText("");
                ((EditText) findViewById(R.id.kolicina)).setText("");


                ((CheckBox) findViewById(R.id.checkBox1)).setChecked(false);
                kolicina.setFocusable(true);
                kolicina.setEnabled(true);

        //        getAvailableMemory(MainActivity5.this);
               // System.gc();
          //  clearMemory(MainActivity5.this);
               // ((CheckBox) findViewById(R.id.checkBox2)).setChecked(false);

                Toast.makeText(getApplicationContext(), "Uspesno ste ponistili podatke.Skenirajte ponovo!", Toast.LENGTH_SHORT).show();

                //clearCache();

                ocistiKes();

               // deleteCache(getApplicationContext());

              //  FileUtils.deleteQuietly(getApplicationContext().getCacheDir());

          //      Intent myintent1 = new Intent(MainActivity5.this, StageManager.class);
             //   startActivity(myintent1);



                if (bar_kod.isEmpty() && bar_kod_provera.isEmpty()) {
                    bar_kod_art.requestFocus();
                }

           /*     bar_kod_art.addTextChangedListener(new TextWatcher() {
                                                       @Override
                                                       public void beforeTextChanged(CharSequence s, int start, int count, int after) {



                                                       }

                                                       @Override
                                                       public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                           // get the content of both the edit text
                                                           String bar_kod = bar_kod_art.getText().toString();


                                                           // check whether both the fields are empty or not
                                                           try {
                                                               checkBox1.setChecked(!bar_kod.isEmpty());


                                                                       popuniBarKod();
                                                                       String provera = bar_kod_komisiono.getText().toString();
                                                                       String bar_kod_art_provera = bar_kod_art.getText().toString();


                                                           } catch (Exception e ){
                                                               e.getMessage();
                                                           }
                                                       }
                                                       @Override
                                                       public void afterTextChanged(Editable s) {
                                                           // popuniBarKod();
                                                           if(bar_kod_art.toString().length() > 0 && checkBox3.isChecked() == false){
                                                               bar_kod_komisiono.requestFocus();
                                                           }
                                                           bar_kod_komisiono.addTextChangedListener(new TextWatcher() {
                                                               @Override
                                                               public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                                               }
                                                               @Override
                                                               public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                                   // get the content of both the edit text
                                                                   String komisiono =  bar_kod_komisiono.getText().toString();

                                                                   // check whether both the fields are empty or not
                                                                   try {
                                                                       checkBox2.setChecked(!komisiono.isEmpty());

                                                                       String b = kms_art.getText().toString();
                                                                       String a = bar_kod_komisiono.getText().toString();

                                                                       try {
                                                                           if (a.equals(b) || a!= null || !a.isEmpty() || b!=null || !b.isEmpty()) {
                                                                               kolicina.setFocusableInTouchMode(true);
                                                                               //kolicina.setFocusable(true);
                                                                               kolicina.setFocusable(true);
                                                                               kolicina.setEnabled(true);

                                                                           } else {

                                                                              kolicina.setFocusable(false);
                                                                              kolicina.setEnabled(false);
                                                                               Toast.makeText(getApplicationContext(), "Ne mozete menjati kolicinu lokacije se ne slazu", Toast.LENGTH_SHORT).show();
                                                                           }


                                                                       }catch (Exception e){
                                                                           e.getMessage();
                                                                       }

                                                                   } catch (Exception e ){
                                                                       e.getMessage();
                                                                   }

                                                               }



                                                               @Override
                                                               public void afterTextChanged(Editable s) {
                                                                   // popuniBarKod();



                                                               }
                                                           });

                                                       }
                                                   }


                );*/

            }
        });





        RequestQueue requestQueue = Volley.newRequestQueue(this);

      //  SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
     //   String s1 = sh.getString("username", "");

        sacuvaj.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String kolicina_menjana = kolicina.getText().toString();
                String a = kms_art.getText().toString();
                String b = bar_kod_komisiono.getText().toString();
             /*   String testmiljan = kms_art.getText().toString();
                bar_kod_komisiono.setText(testmiljan);
                kolicina_menjana = "25";*/

                try {
                    if  (kolicina_menjana.isEmpty() || kolicina_menjana == "0")  {
                        Toast.makeText(getApplicationContext(), "Ne mozete sacuvati kolicina je 0 ili nije ni uneta ", Toast.LENGTH_SHORT).show();
                    } else {

                        //sendWorkPostRequest();


                        postData();
                    }
                } catch(Exception e){
                    e.getMessage();
                }
            }

            // Post Request For JSONObject
            public void postData() {
                final ProgressDialog loading = new ProgressDialog(MainActivity5.this);

                // loading.setMessage("Please Wait...");
                // loading.setCanceledOnTouchOutside(false);
                // loading.show();



                JSONObject object = new JSONObject();

                try {
                    //input your API parameters

                    JSONObject jsonObjectStore = new JSONObject();
                    JSONObject userArticleBar = new JSONObject();
                    JSONObject jsonObjectSlotLocation = new JSONObject();
                    jsonObjectStore.put("StoreID", "213IP5");
                    userArticleBar.put("ArticleBarCode", ""+ bar_kod_art.getText() + "");
                    jsonObjectSlotLocation.put("SlotLocation",""+ kms_art.getText()+"");
                    object.put("StoreUnit", jsonObjectStore);
                    object.put("Article", userArticleBar);
                    object.put("Rack", jsonObjectSlotLocation);

                    object.put("Quantity", "" + kolicina.getText() + "");
                    //object.put("Quantity","25");
                 //   object.put("WhoChanged", ""+ s1 +"");
                    String ime_op_gl = Globals1.getInstance().getValue();
                    sharedData1.setValue(ime_op_gl);
                  //  object.put("WhoChanged", "Operater1");

                    object.put("WhoChanged",ime_op_gl);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String header = Globals.getInstance().getValue();
                // Enter the correct url for your api service site
                // Prava bezanijska kosa

                // 10.163.252.26

                String url_post = "http://10.163.252.26/api/ArticleSlotPutaway/InsertArticleSlotPutaway";

                //String url_post = "http://10.160.33.202:80/api/ArticleSlotPutaway/InsertArticleSlotPutaway";
                 // String url_post = "http://10.163.248.26/api/ArticleSlotPutaway/InsertArticleSlotPutaway";
                try {
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url_post,object , new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                //loading.d("JSON", String.valueOf(response));
                                loading.dismiss();
                                String Error = response.getString("HttpStatusCode");

                                if (Error.equals("400") ) {

                                    String Message400 = response.getString("Message");
                                    Toast.makeText(getApplicationContext(), "Neuspesno sacuvano!"+Message400, Toast.LENGTH_SHORT).show();

                                } else if (Error.equals("500")) {
                                    //JSONObject body = response.getJSONObject("body");
                                    String Message500 = response.getString("Message");
                                    Toast.makeText(getApplicationContext(), "Neuspesno sacuvano!"+Message500, Toast.LENGTH_SHORT).show();
                                }else if (Error.equals("200")) {
                                    //JSONObject body = response.getJSONObject("body");
                                    saveData();
                                    ocistiKes();
                                   // bar_kod_art.setFocusable(true);
                                    //bar_kod_art.setFocusableInTouchMode(true);
                                    //Toast.makeText(getApplicationContext(), "Uspesno sacuvano....!", Toast.LENGTH_SHORT).show();

                                    //////////////////////////////////////////

                                    String bar_kod = bar_kod_art.getText().toString();
                                    String bar_kod_provera = bar_kod_komisiono.getText().toString();

                                     if (bar_kod.isEmpty() && bar_kod_provera.isEmpty()) {
                                        bar_kod_art.requestFocus();
                                    }

      /*                              bar_kod_art.addTextChangedListener(new TextWatcher() {
                                       @Override
                                      public void beforeTextChanged(CharSequence s, int start, int count, int after) {



                                      }

                                        @Override
                                         public void onTextChanged(CharSequence s, int start, int before, int count) {
                                          // get the content of both the edit text
                                          String bar_kod = bar_kod_art.getText().toString();


                                         // check whether both the fields are empty or not
                                         try {
                                          checkBox1.setChecked(!bar_kod.isEmpty());


                                          popuniBarKod();
                                          String provera = bar_kod_komisiono.getText().toString();
                                          String bar_kod_art_provera = bar_kod_art.getText().toString();

                                         } catch (Exception e ){
                                            e.getMessage();
                                             }
                                       }
                                         @Override
                                         public void afterTextChanged(Editable s) {
                                         // popuniBarKod();
                                          if(bar_kod_art.toString().length() > 0 && checkBox3.isChecked() == false){
                                             bar_kod_komisiono.requestFocus();
                                           }

                                          ///////////////////////////////


                                          ///////////////////////////////
                                           bar_kod_komisiono.addTextChangedListener(new TextWatcher() {
                                            @Override
                                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                            }
                                             @Override
                                             public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                // get the content of both the edit text

                                                 String komisiono =  bar_kod_komisiono.getText().toString();

                                                 // check whether both the fields are empty or not
                                                 try {
                                                     checkBox2.setChecked(!komisiono.isEmpty());

                                                     String b = kms_art.getText().toString();;
                                                     String a = bar_kod_komisiono.getText().toString();

                                                     try {
                                                         if (a.equals(b)) {
                                                             //kolicina.setFocusableInTouchMode(true);
                                                             //kolicina.setFocusable(true);
                                                             kolicina.setFocusable(true);
                                                             kolicina.setEnabled(true);

                                                         } else {

                                                             kolicina.setFocusable(false);
                                                             kolicina.setEnabled(false);

                                                             Toast.makeText(getApplicationContext(), "Ne mozete menjati kolicinu lokacije se ne slazu", Toast.LENGTH_SHORT).show();


                                                         }
                                                     }catch (Exception e){
                                                         e.getMessage();
                                                     }


                                                 } catch (Exception e ){
                                                     e.getMessage();
                                                 }

                                             }



                                               @Override
                                               public void afterTextChanged(Editable s) {
                                                   // popuniBarKod();

                                               }
                                             });

                                            }
                                          } */


                                  //  );

                                    /////////////////////////////////////

                                }



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
                            Toast.makeText(MainActivity5.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }






                    }

                    )
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
                            ;
                    //RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                       requestQueue.add(jsonObjectRequest);
                   // ((EditText) findViewById(R.id.bar_kod_art)).setText("");
                   // ((EditText) findViewById(R.id.sf_art)).setText("");
                   // ((EditText) findViewById(R.id.naziv_art)).setText("");
                   // ((ImageView) findViewById(R.id.img_peak)).setImageResource(R.drawable.logo1);
                    //   ((EditText) findViewById(R.id.datum_rute)).setText("");
                   // ((EditText) findViewById(R.id.kms_art)).setText("");
                  //  ((EditText) findViewById(R.id.bar_kod_komisiono)).setText("");
                   // ((EditText) findViewById(R.id.kolicina)).setText("");

                } catch (Exception e) {
                    e.printStackTrace();

                }



            };

            //}

        });



    }

    public void saveData() {

        Toast.makeText(getApplicationContext(), "Uspesno sacuvano....!", Toast.LENGTH_SHORT).show();
        ((EditText) findViewById(R.id.bar_kod_art)).setText("");
        ((EditText) findViewById(R.id.sf_art)).setText("");
        ((EditText) findViewById(R.id.naziv_art)).setText("");
        ((ImageView) findViewById(R.id.img_peak)).setImageResource(R.drawable.logo1);
        //   ((EditText) findViewById(R.id.datum_rute)).setText("");
        ((EditText) findViewById(R.id.kms_art)).setText("");
        ((EditText) findViewById(R.id.bar_kod_komisiono)).setText("");
        ((EditText) findViewById(R.id.kolicina)).setText("");
        ((CheckBox) findViewById(R.id.checkBox1)).setChecked(false);
        FileUtils.deleteQuietly(this.getCacheDir());
        this.getExternalCacheDir();
       // ocistiKes();

    };
    // drugi bar kod


    //komisionoScan.setText(intentResult.getContents());

    private void popuniBarKod() {

       // String url = "http://10.160.33.202:80/api/ArticleSlotLocation/GetArticleSlotLocationByArticle?parStoreId=213IP5&parArticleBarCode=8606017106067";
        //String url = "http://10.163.248.26/api/ArticleSlotLocation/GetArticleSlotLocationByArticle?parStoreId=213IP1&parArticleBarCode=" +bar_kod_art.getText()+"";


        String header = Globals.getInstance().getValue();

       // Produkcija 10.163.252.26

        // 213Ip5
//        String url = "http://10.160.33.202:80/api/ArticleSlotLocation/GetArticleSlotLocationByArticle?parStoreId=213IP5&parArticleBarCode=" +bar_kod_art.getText()+"";
        String url = "http://10.163.252.26/api/ArticleSlotLocation/GetArticleSlotLocationByArticle?parStoreId=213IP5&parArticleBarCode=P" +bar_kod_art.getText()+"";


        // 213IP1
     //   String url = "http://10.163.248.26/api/ArticleSlotLocation/GetArticleSlotLocationByArticle?parStoreId=213IP1&parArticleBarCode=" +bar_kod_art.getText()+"";
        //String url = "http://10.163.248.26/api/ArticleSlotLocation/GetArticleSlotLocationByArticle?parStoreId=213IP5&parArticleBarCode=" +bar_kod_art.getText()+"";




        try{




            RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //   String b = "Abc12312";
                    EditText sf_art = (EditText) findViewById(R.id.sf_art);
                    EditText naziv_art =(EditText)  findViewById(R.id.naziv_art);
                    EditText kms_art =(EditText) findViewById(R.id.kms_art);

                    JSONObject jsonObjectData = new JSONObject();
                    JSONObject jsonObjectArticle = new JSONObject();
                    JSONObject jsonObjectArticleCode = new JSONObject();
                    JSONObject jsonObjectArticleLevel = new JSONObject();
                    //JSONObject jsonObjectArticleLevel2 = new JSONObject();
                    //JSONObject jsonObjectArticleLevel3 = new JSONObject();
                    //JSONObject jsonObjectArticleLevel4 = new JSONObject();
                    //String sf_art_pro;
                    try {
                        jsonObjectData = response.getJSONObject("Data");
                        jsonObjectArticle = jsonObjectData .getJSONObject("Article");
                        String sf_art_pro = (String) jsonObjectArticle.get("ArticleCode");


                        //jsonObjectArticleCode = jsonObjectArticle.getJSONObject("ArticleCode");
                        // sf_art_pro = jsonObjectArticleCode.getString("");
                        sf_art.setText(sf_art_pro);
                        // sf_art.setText(jsonObjectArticleCode.getString("ArticleCode"));
                    }catch (JSONException e){
                        e.printStackTrace();
                    }


                    try {
                        jsonObjectData = response.getJSONObject("Data");
                        jsonObjectArticle = jsonObjectData .getJSONObject("Article");
                        String naziv_artikla = (String) jsonObjectArticle.get("ArticleName");

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
                        String url_slike = (String) jsonObjectArticle.get("ArticleImageUrl");
                        // String url_slika_1 = "https://wes05dvapa15384lmapslike.blob.core.windows.net/slikeart/7192421.jpg";

//                        Picasso.with(MainActivity5.this).load(url_slike).resize(80, 80).into(slika_sort);

                     /*   Picasso.get().load(url_slike).resize(80, 80).into(slika_sort);

                        Picasso.Builder builder = new Picasso.Builder(MainActivity5.this);

                        builder.listener(new Picasso.Listener()
                        {
                            @Override
                            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
                            {
                                exception.printStackTrace();
                                ((ImageView) findViewById(R.id.img_peak)).setImageResource(R.drawable.logo1);
                            }
                        });
                        builder.build().load(url_slike).into(slika_sort);

*/

                        Glide.with(MainActivity5.this)
                                .load(url_slike )
                                .override(80, 80) .error(R.drawable.logo1)
                                .into(slika_sort);





                    }catch (JSONException e){
                        e.printStackTrace();
                    }



                    try {

                        jsonObjectData = response.getJSONObject("Data");
                        jsonObjectArticle = jsonObjectData .getJSONObject("Rack");
                        String slotLocation = (String) jsonObjectArticle.get("SlotLocation");

                        kms_art.setText(slotLocation);


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

          /*  if (requestQueueNovi == null) {
                requestQueueNovi = Volley.newRequestQueue(this);
                requestQueueNovi.add(jsonObjectRequest);
            } else {
               // requestQueueNovi = Volley.newRequestQueue(this);
                requestQueueNovi.add(jsonObjectRequest);
                //RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);

               // ExampleRequestQueue.add(jsonObjectRequest);
            }*/
           // requestQueueNovi.add(jsonObjectRequest);
        }catch (Exception e){
            e.printStackTrace();



        }
    }

   /* private boolean clearCache() {
       // return deleteDir(new File(getApplicationContext().getCacheDir().getAbsolutePath()));
        //deleteDirNovi();


    }*/
    public  void ocistiKes(){
        deleteCache(this);
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e)

        {
            String a = "Abc";

            e.getMessage();
            e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    /*public static void deleteDirNovi(File directory, boolean removeAll) {
        if (directory == null || !directory.isDirectory()) {
            return;
        }

        for (File file : directory.listFiles()) {
            if (file.isDirectory() && removeAll) {
                deleteDirNovi(file, removeAll);
            } else {
                file.delete();
            }
        }

        directory.delete();
    }

    private boolean deleteDir(File file) {

        if (file.isDirectory()) {
            boolean res = true;
            for (File child : file.listFiles()) {
                res = deleteDir(child) && res;
            }//w  w w .  j  a v a  2 s.c o  m
            res = file.delete() && res;
            return res;
        } else {
            return file.delete();
        }
    }

*/

    private final static String TAG_SYSTEM_UTIL = "SystemUtil";

  private void clearMemory(Context context) {
        long beforemem = getAvailableMemory(context);

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfos;
        List<ActivityManager.RunningServiceInfo> runningServiceInfos;
        if (am != null) {
            runningAppProcessInfos = am.getRunningAppProcesses();
            runningServiceInfos = am.getRunningServices(25);

            if (runningAppProcessInfos != null) {
                for (ActivityManager.RunningAppProcessInfo r : runningAppProcessInfos) {
                    if (r.importance > ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                        String[] pkgList = r.pkgList;
                        for (String p : pkgList) {
                            am.killBackgroundProcesses(p);
                            am.clearWatchHeapLimit();
                            am.clearApplicationUserData();
                            am.isLowRamDevice();

                        }//from   w w w.jav a 2s .co m
                    }
                }
            }

            if (runningServiceInfos != null) {
                for (ActivityManager.RunningServiceInfo r : runningServiceInfos) {

                }
            }
        }

        long aftermem = getAvailableMemory(context);

    }

   private long getAvailableMemory(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        am.getMemoryInfo(memoryInfo);

        Log.d(TAG_SYSTEM_UTIL, "before clear the memory is " + memoryInfo.availMem / (4 * 1024));

        return memoryInfo.availMem / (4 * 1024);
    }


 /*private void doSomethingMemoryIntensive() {

        // Before doing something that requires a lot of memory,
        // check whether the device is in a low memory state.
        ActivityManager.MemoryInfo memoryInfo = getAvailableMemory();

        if (!memoryInfo.lowMemory) {
            // Do memory intensive work.
        }
    }

    // Get a MemoryInfo object for the device's current memory status.
    private ActivityManager.MemoryInfo getAvailableMemory() {
        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }
*/

}










// komisiono mesto skeniranje dugme taj kod za to i popunjavanje rezultaata




















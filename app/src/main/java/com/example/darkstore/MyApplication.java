package com.example.darkstore;
import androidx.appcompat.app.AppCompatActivity;

public class MyApplication extends AppCompatActivity{
    private String someVariable;

    public String getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }
}


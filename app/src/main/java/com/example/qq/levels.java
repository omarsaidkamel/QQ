package com.example.qq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class levels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
    }

    public void go(View view) {
        RadioButton rbe =findViewById(R.id.rbe);
        RadioButton rbm =findViewById(R.id.rbm);
        RadioButton rbh =findViewById(R.id.rbh);
        Bundle b=new Bundle();
        if(rbe.isChecked())
        {
            Intent irbe=new Intent(this,game.class);
            b.putString("level","Easy");
            irbe.putExtras(b);
            startActivity(irbe);
        }
        else if(rbm.isChecked())
        {
            Intent irbm=new Intent(this,game.class);
            b.putString("level","Medium");
            irbm.putExtras(b);
            startActivity(irbm);
        }
        else if(rbh.isChecked())
        {
            Intent irbh=new Intent(this,game.class);
            b.putString("level","Hard");
            irbh.putExtras(b);
            startActivity(irbh);
        }
        else if(!rbh.isChecked()){
            Toast.makeText(this, "Please Choose Level To Start The Game.", Toast.LENGTH_LONG).show();
        }
    }
}

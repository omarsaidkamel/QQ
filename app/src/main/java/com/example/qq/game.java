package com.example.qq;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class game extends AppCompatActivity {

    int time, num1, num2, opt, ans, tans, r, t, counter = 0;
    Random random = new Random();
    EditText answer;
    TextView timer;
    CountDownTimer Counterdown;
    Intent lv;
    AlertDialog.Builder builder;

    void fail() {
        try {
            Counterdown.cancel();
            builder.setMessage("Sorry,You lost.\nYour Score = " + counter)
                    .setIcon(R.drawable.logo)
                    .setTitle("Wrong Answer !")
                    .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            cal(r);
                            counter = 0;
                            answer.setText("");
                            f_timer(time);
                        }
                    })
                    .setNegativeButton("I Give up", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            counter = 0;
                            startActivity(lv);

                        }
                    }).show();
        } catch (Exception e) {
            Log.e("error", "fail: ", e);
        }
    }

    void support(int t) {


        if (t == 0)
            Toast.makeText(this, "Excellent", Toast.LENGTH_SHORT).show();
        else if (t == 1)
            Toast.makeText(this, "Great", Toast.LENGTH_SHORT).show();
        else if (t == 2)
            Toast.makeText(this, "Brilliant", Toast.LENGTH_SHORT).show();
        else if (t == 3)
            Toast.makeText(this, "Marvelous", Toast.LENGTH_SHORT).show();
        else if (t == 4)
            Toast.makeText(this, "Spectacular", Toast.LENGTH_SHORT).show();
        else if (t == 5)
            Toast.makeText(this, "Awesome", Toast.LENGTH_SHORT).show();
        else if (t == 6)
            Toast.makeText(this, "Wonderful", Toast.LENGTH_SHORT).show();
        else if (t == 7)
            Toast.makeText(this, "Fantastic", Toast.LENGTH_SHORT).show();


    }

    void cal(int r) {
        try {


            TextView number1 = findViewById(R.id.Fnum);
            TextView op = findViewById(R.id.op);
            TextView number2 = findViewById(R.id.snum);

            //first number generation
            num1 = random.nextInt(r);
            number1.setText(String.valueOf(num1));

            //operation generation
            opt = random.nextInt(2);
            if (opt == 0)
                op.setText(String.valueOf('+'));
            if (opt == 1)
                op.setText(String.valueOf('*'));

            //second number generation
            num2 = random.nextInt(r);
            number2.setText(String.valueOf(num2));
        } catch (Exception e) {
            Log.e("error2", "cal");
        }
    }

    void sub(int ans, int tans) {
        try {


            builder = new AlertDialog.Builder(this);
            lv = new Intent(this, levels.class);
            answer = findViewById(R.id.answer);
            if (ans == tans) {
                t = random.nextInt(8);
                support(t);
                counter++;
                cal(r);
                answer.setText("");
                Counterdown.cancel();
                f_timer(time);
            } else {
                fail();
            }
        } catch (Exception e) {
            Log.e("error3", "sub");
        }
    }

    void f_timer(int time) {
        try {


            Counterdown = new CountDownTimer(time, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer.setText("Timer : " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    timer.setText("Failed !");
                    fail();
                }
            }.start();
        } catch (Exception e) {
            Log.e("error 4", "f_timer");

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        builder = new AlertDialog.Builder(this);
        TextView level_show = findViewById(R.id.level_show);
        Bundle b = getIntent().getExtras();
        String level = b.getString("level");
        level_show.setText("Level : " + level);
        timer = findViewById(R.id.timer);
        if (level.equals("Easy")) {

            time = 11000;
            r = 10;
            cal(r);
        }

        if (level.equals("Medium")) {

            time = 11000;
            r = 100;
            cal(r);
        }

        if (level.equals("Hard")) {

            time = 11000;
            r = 1000;
            cal(r);
        }
        f_timer(time);

    }

    public void submit(View view) {
        try {


            answer = findViewById(R.id.answer);
            if (opt == 0) {
                ans = num1 + num2;
                if (answer.getText() != null && !answer.getText().toString().isEmpty()) {
                    tans = Integer.parseInt(answer.getText().toString());
                    sub(ans, tans);
                }
            }

            if (opt == 1) {
                ans = num1 * num2;
                if (answer.getText() != null && !answer.getText().toString().isEmpty()) {
                    tans = Integer.parseInt(answer.getText().toString());
                    sub(ans, tans);
                }
            }
        } catch (Exception e) {
            Log.e("error5", "submit", e);
        }
    }
}

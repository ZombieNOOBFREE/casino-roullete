package ru.example.casinoroullete;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private ImageView rul;
    private Random random;
    private int old_degree = 0;
    private int degree = 0;
    private static final float FACTOR = 4.86f;

    private String[] numbers = {"32 RED","15 BLACK","19 RED","4 BLACK",
            "21 RED","2 BLACK","25 RED","17 BLACK", "34 RED",
            "6 BLACK","27 RED","13 BLACK","36 RED","11 BLACK","30 RED",
            "8 BLACK","23 RED","10 BLACK","5 RED","24 BLACK","16 RED","33 BLACK",
            "1 RED","20 BLACK","14 RED","31 BLACK","9 RED","22 BLACK","18 RED",
            "29 BLACK","7 RED","28 BLACK","12 RED","35 BLACK","3 RED","26 BLACK","0"};




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        rul = findViewById(R.id.rul);
        random = new Random();
    }

    public void btnstr(View view) {
        old_degree = degree % 360;
        degree = random.nextInt(3600) + 720;
        RotateAnimation rotate = new RotateAnimation(old_degree,degree,RotateAnimation.RELATIVE_TO_SELF,0.5f, RotateAnimation.RELATIVE_TO_SELF,0.5f);
    rotate.setDuration(360);
    rotate.setFillAfter(true);
    rotate.setInterpolator(new DecelerateInterpolator());
    rotate.setAnimationListener(new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            result.setText("");
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            result.setText(360 - (degree%360));
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });
    rul.startAnimation(rotate);
    }
    private String getResult(int degree){
        String text = "";
        int x = 1;
        int y = 3;
        for(int i = 0; i < 37; i++){
            if (degree >= (FACTOR * x) && degree < (FACTOR * y)){
                text = numbers[i];
            }
            x += 2;
            y += 2;

        }
        if (degree >= (FACTOR * 73) && degree < 360 || degree >=0 && degree < (FACTOR * 1)){
                text = numbers[numbers.length - 1];
        }
        return text;
    }
}
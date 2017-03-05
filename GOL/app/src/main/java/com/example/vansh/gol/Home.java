package com.example.vansh.gol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    public Button play;
    public Button rules;
    public void init(){
        play = (Button)findViewById(R.id.playbutton);
        rules = (Button)findViewById(R.id.rulesbutton);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play = new Intent(Home.this, MainActivity.class);
                startActivity(play);
            }
        });
        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rules = new Intent(Home.this, Rules.class);
                startActivity(rules);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }
}

package com.example.vansh.gol;


import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;

import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import static android.content.DialogInterface.*;
import static com.example.vansh.gol.R.*;
public class MainActivity extends AppCompatActivity {

    public static boolean state[][]=new boolean[15][15];
    public static int rowColumn[][] = new int[15][15];
    GridLayout layout;
    int r,k;


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("ABC","Orientation changed");
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {

        }
        else if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT)
        {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout=(GridLayout) findViewById(R.id.grid1);
        layout.removeAllViews();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Resources r = getResources();
        int dp = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, r.getDisplayMetrics());
        final int heightWidth = (metrics.widthPixels - (dp * 30)) / 15;
        int counter = 0;
        for(int i=0;i<=14;i++){
            for(int j=0;j<=14;j++){
                final Button button = new Button(this);
                final GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(i),GridLayout.spec(j));
                params.setMargins(dp,dp,dp,dp);
                params.width = heightWidth;
                params.height = heightWidth;
                button.setLayoutParams(params);
                button.setPadding(dp,dp,dp,dp);
                button.setBackgroundColor(getResources().getColor(android.R.color.white));
                layout.addView(button);
                button.setId(counter);
                counter++;
                button.setTag(i+","+j);
                state[i][j]=false;
                rowColumn[i][j]=0;
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String tag = (String)button.getTag();
                        String[] splitTag = tag.split(",");
                        int currentI = Integer.parseInt(splitTag[0]);
                        int currentJ = Integer.parseInt(splitTag[1]);
                        int k=currentI;
                        int m=currentJ;
                        rowColumn[k][m]++;
                        if(rowColumn[k][m]%2!=0) {
                            state[k][m]=true;
                            button.setBackground( getResources().getDrawable(drawable.circle));
                        }
                        else{
                            v.setBackgroundColor(getResources().getColor(android.R.color.white));
                            state[k][m]=false;
                        }
                    }
                });
            }
        }
        Button reset=(Button)findViewById(id.resetbtn);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setMessage("Do you really want to reset?");

                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"Yes", new OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int counter=0;
                        for(int a=0;a<=14;a++) {
                            for (int b=0;b<=14;b++) {
                                Button currentButton = (Button) findViewById(counter);
                                counter++;
                                state[a][b]=false;
                                currentButton.setBackgroundColor(getResources().getColor(android.R.color.white));
                            }
                        }
                    }
                });

                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"No", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        //next generation
        Button next=(Button)findViewById(id.nextbtn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int counter = 0;
                for(int c=0;c<=14;c++){
                    for(int d=0;d<=14;d++){
                        Button currentButton = (Button) findViewById(counter);
                        counter++;
                        int live=0,dead=0;
                        if(c>0 && d>0 && state[c-1][d-1]==true)
                            live++;
                        else
                            dead++;
                        if(d<14 && state[c][d+1]==true)
                            live++;
                        else
                            dead++;

                        if(c<14 && d<14 && state[c+1][d+1]==true)
                            live++;
                        else
                            dead++;
                        if(c<14 && d>0 && state[c+1][d-1]==true)
                            live++;
                        else
                            dead++;
                        if(c>0 && state[c-1][d]==true)
                            live++;
                        else
                            dead++;
                        if(d>0 && state[c][d-1]==true)
                            live++;
                        else
                            dead++;
                        if(c<14 && state[c+1][d]==true)
                            live++;
                        else
                            dead++;
                        if(c>0 && d<14 && state[c-1][d+1]==true)
                            live++;
                        else
                            dead++;

                        if(state[c][d]==true && (live==2 || live==3)) {
                            currentButton.setBackground( getResources().getDrawable(drawable.circle));
                            state[c][d] = true;
                        }
                        if(state[c][d]==true && live>3) {
                            state[c][d] = false;
                            currentButton.setBackgroundColor(getResources().getColor(android.R.color.white));
                        }
                        if(state[c][d]==true && live<2) {
                            state[c][d] = false;
                            currentButton.setBackgroundColor(getResources().getColor(android.R.color.white));
                        }
                        if(state[c][d]==false && live==3) {
                            state[c][d] = true;
                            currentButton.setBackground( getResources().getDrawable(drawable.circle));

                        }
                        currentButton.invalidate();
                    }
                }
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}

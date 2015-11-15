package com.example.android.pig;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.media.MediaPlayer;
import android.view.View;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

    boolean teama=true;int scorea=0,scoreb=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addpoints(View view) {
        if (scorea < 100 && scoreb < 100) {
            int k = (int) (5 * Math.random() + 1);

            ImageView scr = (ImageView) findViewById(R.id.scoreondice);
            switch (k) {
                case 1:
                    scr.setImageResource(R.drawable.pic1);
                    break;
                case 2:
                    scr.setImageResource(R.drawable.pic2);
                    break;
                case 3:
                    scr.setImageResource(R.drawable.pic3);
                    break;
                case 4:
                    scr.setImageResource(R.drawable.pic4);
                    break;
                case 5:
                    scr.setImageResource(R.drawable.pic5);
                    break;
                case 6:
                    scr.setImageResource(R.drawable.pic6);
                    break;
            }
            if (teama == true && k != 1) {
                scorea += k;
                TextView scr1 = (TextView) findViewById(R.id.team_a_score);
                scr1.setText(Integer.toString(scorea));
                TextView scr2 = (TextView) findViewById(R.id.chance);
                if(scorea>=100)
                scr2.setText("Team a wins");
            }
           else if (teama == false && k != 1) {
                scoreb += k;

                TextView scr1 = (TextView) findViewById(R.id.team_b_score);
                scr1.setText(Integer.toString(scoreb));
                TextView scr2 = (TextView) findViewById(R.id.chance);
                if(scoreb>=100)
                    scr2.setText("Team b wins");
            }
           else if (k == 1) {
                if (teama == true) {
                    scorea += 1;
                    teama = false;
                    TextView scr2 = (TextView) findViewById(R.id.chance);
                    scr2.setText("Team b is playing");
                    TextView scr1 = (TextView) findViewById(R.id.team_b_score);

                    scoreb += scorea;
                    scr1.setText(Integer.toString(scoreb));
                    if (scoreb >= 100)
                        scr2.setText("Team b wins");
                    scorea = 0;
                    TextView scr3 = (TextView) findViewById(R.id.team_a_score);
                    scr3.setText(Integer.toString(scorea));
                } else {
                    scoreb += 1;
                    teama = true;
                    TextView scr2 = (TextView) findViewById(R.id.chance);
                    scr2.setText("Team a is playing");
                    TextView scr1 = (TextView) findViewById(R.id.team_a_score);

                    scorea += scoreb;
                    scr1.setText(Integer.toString(scorea));
                    if (scorea >= 100)
                        scr2.setText("Team a wins");
                    scoreb = 0;
                    TextView scr3 = (TextView) findViewById(R.id.team_b_score);
                    scr3.setText(Integer.toString(scoreb));
                }
            }
        }
    }
    public void settozero(View view)
    {

                TextView scr1=(TextView)findViewById(R.id.team_a_score);
                TextView scr2=(TextView)findViewById(R.id.team_b_score);
                scr1.setText("0");
                scr2.setText("0");
                scoreb=0;
                scorea=0;
            }
    public void hold(View view) {
     //   int k = (int) (5 * Math.random() + 1);
     //   TextView scr = (TextView) findViewById(R.id.scoreondice);
    //    scr.setText(Integer.toString(k));
            if (teama == true) {

                teama = false;

                TextView scr1 = (TextView) findViewById(R.id.team_a_score);
                scr1.setText(Integer.toString(scorea));
                TextView scr = (TextView) findViewById(R.id.chance);
                scr.setText("Team b is playing");
            }
            else {
                teama = true;

                TextView scr1 = (TextView) findViewById(R.id.team_b_score);
                scr1.setText(Integer.toString(scoreb));
                TextView scr = (TextView) findViewById(R.id.chance);
                scr.setText("Team a is playing");

            }
        }
    }




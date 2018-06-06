package com.example.akhilbatchu.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int StartingColor=0;
    boolean newgame=false;
    String winner;
    int i=0;
    int[] game={2,2,2,2,2,2,2,2,2,};// if starting color is zero we choose blue
    //if starting color is 1 the turn goes for blue

    public void dropIn(View view) {
        ImageView image = (ImageView) view;
        TextView text = (TextView)findViewById(R.id.textView);

        int[][] winningpositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

        if (StartingColor == 0 && game[Integer.parseInt(image.getTag().toString())]==2 && newgame==false) {

            image.setTranslationY(-500f);
            image.setImageResource(R.drawable.bluecirlce);
            game[Integer.parseInt(image.getTag().toString())]=StartingColor;
            StartingColor=1;
            i++;
            image.animate().translationYBy(500f).rotation(3600).setDuration(500);
        }
        else if(StartingColor ==1  && game[Integer.parseInt(image.getTag().toString())]==2 && newgame==false)
        {
            image.setTranslationY(-500f);
            image.setImageResource(R.drawable.red);
            game[Integer.parseInt(image.getTag().toString())]=StartingColor;
            StartingColor=0;
            i++;
            image.animate().translationYBy(500f).rotation(3600).setDuration(500);
        }

        for(int[] checking : winningpositions)
        {
            if(game[checking[0]]==game[checking[1]] && game[checking[1]] == game[checking[2]] && game[checking[0]]!=2)
            {
                if(game[checking[0]]==0)
                {
                    makeToast("Blue Color wins");
                    newgame=true;
                    winner = "Congratulations Blue!!!!!!!!!!!!";

                }
                else
                {
                    makeToast("Red Color wins");
                    newgame=true;
                    winner = "Congratulations Red!!!!!!!!!!!!";
                }
                text.setText(winner);
                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.MyLinearLayout);
                linearLayout.setVisibility(View.VISIBLE);
            }
            else if(newgame!=true &&i==game.length)
            {
                text.setText("Game is Draw!!!!");
                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.MyLinearLayout);
                linearLayout.setVisibility(View.VISIBLE);
            }


        }



    }

    public void PlayAgain(View view)
    {
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.MyLinearLayout);
        linearLayout.setVisibility(View.INVISIBLE);
        StartingColor=0;
        for(int i=0;i<game.length;i++)
        {
            game[i]=2;
        }
            newgame=false;
            GridLayout gridLayout = (GridLayout)findViewById(R.id.MyGridLayout);
            for(int i=0;i<gridLayout.getChildCount();i++)
            {
                ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
            }


    }
    public void makeToast(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;

    /*Player Representation
      0 =>X
      1 =>O
    */
    int activePlayer = 0;

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    /*State Meanings
        0 - X
        1 - O
        2 - Null
    */

    int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(gameState[tappedImage] == 2 && gameActive)
        {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0)
            {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            }
            else
            {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        //Check if winner found
        int winner = 0;
        for(int[] winPosition : winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2){
                //Winner exists - find who!
                TextView status = findViewById(R.id.status);
                gameActive = false;
                winner = 1;
                if(gameState[winPosition[0]] == 0)
                {
                    status.setText("X is the winner!!!");
                }
                else if(gameState[winPosition[0]] == 1)
                {
                    status.setText("O is the winner!!!");
                }
            }
        }

        //Draw condition
        int draw = 0;
        for(int i=0 ;i<gameState.length; i++)
        {
            if(gameState[i]!=2)
            {
                draw++;
            }
        }
        if(draw == 9 && winner==0)
        {
            TextView status = findViewById(R.id.status);
            status.setText("Game Draw");
            gameActive = false;
        }

        //End of game
//        if(!gameActive)
//        {
//            gameReset(view);
//        }
    }

    public void gameReset(View view)
    {
        gameActive = true;
        activePlayer = 0;
        for(int i = 0; i<gameState.length; i++)
        {
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
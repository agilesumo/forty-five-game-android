package com.agilesumo.fortyfive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;


public class GameActivity extends AppCompatActivity {

    private Hand currentHand;

    private Card firstCardCom2;

    private Card secondCardCom2;

    private Card thirdCardCom2;

    private Card fourthCardCom2;

    private Card fifthCardCom2;

    private ImageView firstCardCom2Image;

    private ImageView secondCardCom2Image;

    private ImageView thirdCardCom2Image;

    private ImageView fourthCardCom2Image;

    private ImageView fifthCardCom2Image;

    private Card firstCardCom1;

    private Card secondCardCom1;

    private Card thirdCardCom1;

    private Card fourthCardCom1;

    private Card fifthCardCom1;

    private ImageView firstCardCom1Image;

    private ImageView secondCardCom1Image;

    private ImageView thirdCardCom1Image;

    private ImageView fourthCardCom1Image;

    private ImageView fifthCardCom1Image;

    private Card firstCardPlayer;

    private Card secondCardPlayer;

    private Card thirdCardPlayer;

    private Card fourthCardPlayer;

    private Card fifthCardPlayer;

    private ImageView firstCardPlayerImage;

    private ImageView secondCardPlayerImage;

    private ImageView thirdCardPlayerImage;

    private ImageView fourthCardPlayerImage;

    private ImageView fifthCardPlayerImage;


    private ImageView firstCardPlayedImage;

    private ImageView secondCardPlayedImage;

    private ImageView thirdCardPlayedImage;

    private boolean firstCardNext = true;

    private boolean secondCardNext = false;

    private boolean thirdCardNext = false;

    private Card upTrumpCard;

    private ImageView upTrumpCardImage;

    private TextView scoreTextPlayer;

    private TextView scoreTextCom1;

    private TextView scoreTextCom2;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        currentHand = new Hand();

        Card[] playersCards = currentHand.getPlayersCards();

        //initialise players cards

        firstCardPlayer = playersCards[0];
        firstCardPlayerImage = (ImageView) findViewById(R.id.first_card_player);
        firstCardPlayerImage.setImageResource(firstCardPlayer.getCardImageId());

        secondCardPlayer = playersCards[1];
        secondCardPlayerImage = (ImageView) findViewById(R.id.second_card_player);
        secondCardPlayerImage.setImageResource(secondCardPlayer.getCardImageId());

        thirdCardPlayer = playersCards[2];
        thirdCardPlayerImage = (ImageView) findViewById(R.id.third_card_player);
        thirdCardPlayerImage.setImageResource(thirdCardPlayer.getCardImageId());

        fourthCardPlayer = playersCards[3];
        fourthCardPlayerImage = (ImageView) findViewById(R.id.fourth_card_player);
        fourthCardPlayerImage.setImageResource(fourthCardPlayer.getCardImageId());

        fifthCardPlayer = playersCards[4];
        fifthCardPlayerImage = (ImageView) findViewById(R.id.fifth_card_player);
        fifthCardPlayerImage.setImageResource(fifthCardPlayer.getCardImageId());

        Card[] com1Cards = currentHand.getCom1Cards();

        // initialise computer p1 players cards

        firstCardCom1 = com1Cards[0];
        firstCardCom1Image = (ImageView) findViewById(R.id.first_card_computer1);
        firstCardCom1Image.setImageResource(firstCardCom1.getCardImageId());

        secondCardCom1 = com1Cards[1];
        secondCardCom1Image = (ImageView) findViewById(R.id.second_card_computer1);
        secondCardCom1Image.setImageResource(secondCardCom1.getCardImageId());

        thirdCardCom1 = com1Cards[2];
        thirdCardCom1Image = (ImageView) findViewById(R.id.third_card_computer1);
        thirdCardCom1Image.setImageResource(thirdCardCom1.getCardImageId());

        fourthCardCom1 = com1Cards[3];
        fourthCardCom1Image = (ImageView) findViewById(R.id.fourth_card_computer1);
        fourthCardCom1Image.setImageResource(fourthCardCom1.getCardImageId());

        fifthCardCom1 = com1Cards[4];
        fifthCardCom1Image = (ImageView) findViewById(R.id.fifth_card_computer1);
        fifthCardCom1Image.setImageResource(fifthCardCom1.getCardImageId());

        // initialise computer p2 players cards

        Card[] com2Cards = currentHand.getCom2Cards();


        firstCardCom2 = com2Cards[0];
        firstCardCom2Image = (ImageView) findViewById(R.id.first_card_computer2);
        firstCardCom2Image.setImageResource(firstCardCom2.getCardImageId());

        secondCardCom2 = com2Cards[1];
        secondCardCom2Image = (ImageView) findViewById(R.id.second_card_computer2);
        secondCardCom2Image.setImageResource(secondCardCom2.getCardImageId());

        thirdCardCom2 = com2Cards[2];
        thirdCardCom2Image = (ImageView) findViewById(R.id.third_card_computer2);
        thirdCardCom2Image.setImageResource(thirdCardCom2.getCardImageId());

        fourthCardCom2 = com2Cards[3];
        fourthCardCom2Image = (ImageView) findViewById(R.id.fourth_card_computer2);
        fourthCardCom2Image.setImageResource(fourthCardCom2.getCardImageId());

        fifthCardCom2 = com2Cards[4];
        fifthCardCom2Image = (ImageView) findViewById(R.id.fifth_card_computer2);
        fifthCardCom2Image.setImageResource(fifthCardCom2.getCardImageId());

        firstCardPlayedImage = (ImageView) findViewById(R.id.first_card_played);

        secondCardPlayedImage = (ImageView) findViewById(R.id.second_card_played);

        thirdCardPlayedImage = (ImageView) findViewById(R.id.third_card_played);

        upTrumpCard = currentHand.getTrumpCard();
        upTrumpCardImage = (ImageView) findViewById(R.id.up_trump_card);
        upTrumpCardImage.setImageResource(upTrumpCard.getCardImageId());

        scoreTextPlayer = (TextView) findViewById(R.id.player_score_text);
        scoreTextCom1 = (TextView) findViewById(R.id.computer1_score_text);
        scoreTextCom2 = (TextView) findViewById(R.id.computer2_score_text);




    }

    public void comCardClicked(View view){
        if (firstCardNext) {
            switch (view.getId()) {
                case R.id.first_card_computer1:
                    firstCardCom1Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(firstCardCom1.getCardImageId());
                    currentHand.setFirstPlayed(firstCardCom1, Hand.computer1);
                    break;
                case R.id.second_card_computer1:
                    secondCardCom1Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(secondCardCom1.getCardImageId());
                    currentHand.setFirstPlayed(secondCardCom1, Hand.computer1);
                    break;
                case R.id.third_card_computer1:
                    thirdCardCom1Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(thirdCardCom1.getCardImageId());
                    currentHand.setFirstPlayed(thirdCardCom1, Hand.computer1);
                    break;
                case R.id.fourth_card_computer1:
                    fourthCardCom1Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(fourthCardCom1.getCardImageId());
                    currentHand.setFirstPlayed(fourthCardCom1, Hand.computer1);
                    break;
                case R.id.fifth_card_computer1:
                    fifthCardCom1Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(fifthCardCom1.getCardImageId());
                    currentHand.setFirstPlayed(fifthCardCom1,Hand.computer1);
                    break;
            }

            firstCardNext = false;
            secondCardNext = true;
            thirdCardNext = false;
            return;


        }


        else if (secondCardNext) {
            switch (view.getId()) {
                case R.id.first_card_computer1:
                    firstCardCom1Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(firstCardCom1.getCardImageId());
                    currentHand.setSecondPlayed(firstCardCom1, Hand.computer1);
                    break;
                case R.id.second_card_computer1:
                    secondCardCom1Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(secondCardCom1.getCardImageId());
                    currentHand.setSecondPlayed(secondCardCom1, Hand.computer1);
                    break;
                case R.id.third_card_computer1:
                    thirdCardCom1Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(thirdCardCom1.getCardImageId());
                    currentHand.setSecondPlayed(thirdCardCom1, Hand.computer1);
                    break;
                case R.id.fourth_card_computer1:
                    fourthCardCom1Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(fourthCardCom1.getCardImageId());
                    currentHand.setSecondPlayed(fourthCardCom1, Hand.computer1);
                    break;
                case R.id.fifth_card_computer1:
                    fifthCardCom1Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(fifthCardCom1.getCardImageId());
                    currentHand.setSecondPlayed(fifthCardCom1, Hand.computer1);
                    break;
            }
            firstCardNext = false;
            secondCardNext = false;
            thirdCardNext = true;
            return;


        }

        else if (thirdCardNext) {
            switch (view.getId()) {
                case R.id.first_card_computer1:
                    firstCardCom1Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(firstCardCom1.getCardImageId());
                    currentHand.setThirdPlayed(firstCardCom1, Hand.computer1);
                    break;
                case R.id.second_card_computer1:
                    secondCardCom1Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(secondCardCom1.getCardImageId());
                    currentHand.setThirdPlayed(secondCardCom1, Hand.computer1);
                    break;
                case R.id.third_card_computer1:
                    thirdCardCom1Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(thirdCardCom1.getCardImageId());
                    currentHand.setThirdPlayed(thirdCardCom1, Hand.computer1);
                    break;
                case R.id.fourth_card_computer1:
                    fourthCardCom1Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(fourthCardCom1.getCardImageId());
                    currentHand.setThirdPlayed(fourthCardCom1, Hand.computer1);
                    break;
                case R.id.fifth_card_computer1:
                    fifthCardCom1Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(fifthCardCom1.getCardImageId());
                    currentHand.setThirdPlayed(fifthCardCom1, Hand.computer1);
                    break;
            }
            firstCardNext = true;
            secondCardNext = false;
            thirdCardNext = false;

            trickFinishedUpdate();



        }

    }

    public void com2CardClicked(View view) {
        if (firstCardNext) {
            switch (view.getId()) {
                case R.id.first_card_computer2:
                    firstCardCom2Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(firstCardCom2.getCardImageId());
                    currentHand.setFirstPlayed(firstCardCom2, Hand.computer2);
                    break;
                case R.id.second_card_computer2:
                    secondCardCom2Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(secondCardCom2.getCardImageId());
                    currentHand.setFirstPlayed(secondCardCom2, Hand.computer2);
                    break;
                case R.id.third_card_computer2:
                    thirdCardCom2Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(thirdCardCom2.getCardImageId());
                    currentHand.setFirstPlayed(thirdCardCom2, Hand.computer2);
                    break;
                case R.id.fourth_card_computer2:
                    fourthCardCom2Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(fourthCardCom2.getCardImageId());
                    currentHand.setFirstPlayed(fourthCardCom2, Hand.computer2);
                    break;
                case R.id.fifth_card_computer2:
                    fifthCardCom2Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(fifthCardCom2.getCardImageId());
                    currentHand.setFirstPlayed(fifthCardCom2, Hand.computer2);
                    break;
            }
            firstCardNext = false;
            secondCardNext = true;
            thirdCardNext = false;
            return;

        } else if (secondCardNext) {
            switch (view.getId()) {
                case R.id.first_card_computer2:
                    firstCardCom2Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(firstCardCom2.getCardImageId());
                    currentHand.setSecondPlayed(firstCardCom2, Hand.computer2);
                    break;
                case R.id.second_card_computer2:
                    secondCardCom2Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(secondCardCom2.getCardImageId());
                    currentHand.setSecondPlayed(secondCardCom2, Hand.computer2);
                    break;
                case R.id.third_card_computer2:
                    thirdCardCom2Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(thirdCardCom2.getCardImageId());
                    currentHand.setSecondPlayed(thirdCardCom2, Hand.computer2);
                    break;
                case R.id.fourth_card_computer2:
                    fourthCardCom2Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(fourthCardCom2.getCardImageId());
                    currentHand.setSecondPlayed(fourthCardCom2, Hand.computer2);
                    break;
                case R.id.fifth_card_computer2:
                    fifthCardCom2Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(fifthCardCom2.getCardImageId());
                    currentHand.setSecondPlayed(fifthCardCom2, Hand.computer2);
                    break;
            }

            firstCardNext = false;
            secondCardNext = false;
            thirdCardNext = true;
            return;

        }

        else if (thirdCardNext) {
            switch (view.getId()) {
                case R.id.first_card_computer2:
                    firstCardCom2Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(firstCardCom2.getCardImageId());
                    currentHand.setThirdPlayed(firstCardCom2, Hand.computer2);
                    break;
                case R.id.second_card_computer2:
                    secondCardCom2Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(thirdCardCom2.getCardImageId());
                    currentHand.setThirdPlayed(secondCardCom2, Hand.computer2);
                    break;
                case R.id.third_card_computer2:
                    thirdCardCom2Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(thirdCardCom2.getCardImageId());
                    currentHand.setThirdPlayed(thirdCardCom2, Hand.computer2);
                    break;
                case R.id.fourth_card_computer2:
                    fourthCardCom2Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(fourthCardCom2.getCardImageId());
                    currentHand.setThirdPlayed(fourthCardCom2, Hand.computer2);
                    break;
                case R.id.fifth_card_computer2:
                    fifthCardCom2Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(fifthCardCom2.getCardImageId());
                    currentHand.setThirdPlayed(fifthCardCom2, Hand.computer2);
                    break;
            }
            firstCardNext = true;
            secondCardNext = false;
            thirdCardNext = false;
            trickFinishedUpdate();

        }

    }


    public void playerCardClicked(View view) {
        if (firstCardNext) {

            switch (view.getId()) {
                case R.id.first_card_player:
                    firstCardPlayerImage.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(firstCardPlayer.getCardImageId());
                    currentHand.setFirstPlayed(firstCardPlayer, Hand.player);
                    break;
                case R.id.second_card_player:
                    secondCardPlayerImage.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(secondCardPlayer.getCardImageId());
                    currentHand.setFirstPlayed(secondCardPlayer, Hand.player);
                    break;
                case R.id.third_card_player:
                    thirdCardPlayerImage.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(thirdCardPlayer.getCardImageId());
                    currentHand.setFirstPlayed(thirdCardPlayer, Hand.player);
                    break;
                case R.id.fourth_card_player:
                    fourthCardPlayerImage.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(fourthCardPlayer.getCardImageId());
                    currentHand.setFirstPlayed(fourthCardPlayer, Hand.player);
                    break;
                case R.id.fifth_card_player:
                    fifthCardPlayerImage.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(fifthCardPlayer.getCardImageId());
                    currentHand.setFirstPlayed(fifthCardPlayer, Hand.player);
                    break;
            }
            firstCardNext = false;
            secondCardNext = true;
            thirdCardNext = false;
            return;
        }
        else if (secondCardNext) {
            switch (view.getId()) {
                case R.id.first_card_player:
                    firstCardPlayerImage.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(firstCardPlayer.getCardImageId());
                    currentHand.setSecondPlayed(firstCardPlayer, Hand.player);
                    break;
                case R.id.second_card_player:
                    secondCardPlayerImage.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(secondCardPlayer.getCardImageId());
                    currentHand.setSecondPlayed(secondCardPlayer, Hand.player);
                    break;
                case R.id.third_card_player:
                    thirdCardPlayerImage.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(thirdCardPlayer.getCardImageId());
                    currentHand.setSecondPlayed(thirdCardPlayer, Hand.player);
                    break;
                case R.id.fourth_card_player:
                    fourthCardPlayerImage.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(fourthCardPlayer.getCardImageId());
                    currentHand.setSecondPlayed(fourthCardPlayer, Hand.player);
                    break;
                case R.id.fifth_card_player:
                    fifthCardPlayerImage.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(fifthCardPlayer.getCardImageId());
                    currentHand.setSecondPlayed(fifthCardPlayer, Hand.player);
                    break;
            }

            firstCardNext = false;
            secondCardNext = false;
            thirdCardNext = true;
            return;
        }

        else if (thirdCardNext) {
            switch (view.getId()) {
                case R.id.first_card_player:
                    firstCardPlayerImage.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(firstCardPlayer.getCardImageId());
                    currentHand.setThirdPlayed(firstCardPlayer, Hand.player);
                    break;
                case R.id.second_card_player:
                    secondCardPlayerImage.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(secondCardPlayer.getCardImageId());
                    currentHand.setThirdPlayed(secondCardPlayer, Hand.player);
                    break;
                case R.id.third_card_player:
                    thirdCardPlayerImage.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(thirdCardPlayer.getCardImageId());
                    currentHand.setThirdPlayed(thirdCardPlayer, Hand.player);
                    break;
                case R.id.fourth_card_player:
                    fourthCardPlayerImage.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(fourthCardPlayer.getCardImageId());
                    currentHand.setThirdPlayed(fourthCardPlayer, Hand.player);
                    break;
                case R.id.fifth_card_player:
                    fifthCardPlayerImage.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(fifthCardPlayer.getCardImageId());
                    currentHand.setThirdPlayed(fifthCardPlayer, Hand.player);
                    break;
            }
            firstCardNext = true;
            secondCardNext = false;
            thirdCardNext = false;
            trickFinishedUpdate();

        }

    }

    //=======================================================================================
    // Helper Methods
    //=======================================================================================

    private void trickFinishedUpdate(){
        firstCardNext = true;
        secondCardNext = false;
        thirdCardNext = false;

        currentHand.updateScore();
        scoreTextPlayer.setText("Score: " + currentHand.getScore(Hand.player));
        scoreTextCom1.setText("Score: " + currentHand.getScore(Hand.computer1));
        scoreTextCom2.setText("Score: " + currentHand.getScore(Hand.computer2));

        // Add a time delay of 2 seconds before displaying result
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                firstCardPlayedImage.setImageDrawable(null);
                secondCardPlayedImage.setImageDrawable(null);
                thirdCardPlayedImage.setImageDrawable(null);


                if (currentHand.isHandFinished()) {
                    Log.d("andyoc", "got hand finished");

                    Toast bestTrumpOutput = Toast.makeText(GameActivity.this, currentHand.getBestTrumpStr(),
                            Toast.LENGTH_LONG);
                    bestTrumpOutput.setGravity(Gravity.CENTER, 0, 0);
                    bestTrumpOutput.show();
                    scoreTextPlayer.setText("Score: " + currentHand.getScore(Hand.player));
                    scoreTextCom1.setText("Score: " + currentHand.getScore(Hand.computer1));
                    scoreTextCom2.setText("Score: " + currentHand.getScore(Hand.computer2));


                }
            }
        }, 2500);                   // Do something after 5s = 5000ms



    }
}

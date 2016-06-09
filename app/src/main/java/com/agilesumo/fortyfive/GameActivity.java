package com.agilesumo.fortyfive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity {

    private Hand currentHand;

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

    private boolean isFirstCardPlayed = false;

    private Card upTrumpCard;

    private ImageView upTrumpCardImage;

    private TextView scoreTextPlayer;

    private TextView scoreTextCom1;






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

        // initialise computer players cards

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

        firstCardPlayedImage = (ImageView) findViewById(R.id.first_card_played);

        secondCardPlayedImage = (ImageView) findViewById(R.id.second_card_played);

        upTrumpCard = currentHand.getTrumpCard();
        upTrumpCardImage = (ImageView) findViewById(R.id.up_trump_card);
        upTrumpCardImage.setImageResource(upTrumpCard.getCardImageId());

        scoreTextPlayer = (TextView) findViewById(R.id.player_score_text);
        scoreTextCom1 = (TextView) findViewById(R.id.computer1_score_text);




    }

    public void comCardClicked(View view){
        if (!isFirstCardPlayed) {
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
            isFirstCardPlayed = true;
            return;

        }


        else if (isFirstCardPlayed) {
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
            isFirstCardPlayed = false;

            // Add a time delay of 2 seconds before displaying result
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    firstCardPlayedImage.setImageDrawable(null);
                    secondCardPlayedImage.setImageDrawable(null);
                }
            }, 1500);                   // Do something after 5s = 5000ms

            Log.d("andyoc", "got to update playerscore = " + currentHand.getScore(Hand.player));
            currentHand.updateScore();
            scoreTextPlayer.setText("Score: " + currentHand.getScore(Hand.player));
            scoreTextCom1.setText("Score: " + currentHand.getScore(Hand.computer1));



        }

    }


    public void playerCardClicked(View view) {
        if (!isFirstCardPlayed) {

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
            isFirstCardPlayed = true;
            return;
        }
        else if (isFirstCardPlayed) {
            switch (view.getId()) {
                case R.id.first_card_player:
                    firstCardPlayerImage.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(firstCardPlayer.getCardImageId());
                    currentHand.setSecondPlayed(firstCardPlayer,  Hand.player);
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

            isFirstCardPlayed = false;

            // Add a time delay of 2 seconds before displaying result
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    firstCardPlayedImage.setImageDrawable(null);
                    secondCardPlayedImage.setImageDrawable(null);
                }
            },1500);                   // Do something after 5s = 5000ms

            currentHand.updateScore();
            scoreTextPlayer.setText("Score: " + currentHand.getScore(Hand.player));
            scoreTextCom1.setText("Score: " + currentHand.getScore(Hand.computer1));
        }
    }
}

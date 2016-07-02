package com.agilesumo.fortyfive;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import java.util.Arrays;


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

    private ImageView aceTrumpsImage;


    private boolean firstCardNext = true;

    private boolean secondCardNext = false;

    private boolean thirdCardNext = false;

    private boolean aceTrumpsToBeUpdated = false;

    private Card upTrumpCard;

    private ImageView upTrumpCardImage;

    private TextView scoreTextPlayer;

    private TextView scoreTextCom1;

    private TextView scoreTextCom2;

    private Player leadingPlayer;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //http://stackoverflow.com/questions/23289168/reassigning-objects-in-java-with-new
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

        if(currentHand.hasAceTrumps()){
            Player aceTrumpsPlayer = currentHand.getAceTrumpsPlayer();
            String hasAceOutput = "";
            if((Hand.player).equals(aceTrumpsPlayer)){
                hasAceOutput += "You have the ace of trumps";

            }

            else if((Hand.computer1).equals(aceTrumpsPlayer)){
                hasAceOutput += "Computer P1 has ace of trumps";
            }

            else{
                hasAceOutput += "ComputerP2 has ace of trumps";
            }

            hasAceOutput += ". Choose a card to discard to claim the turned up trump card.";

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(hasAceOutput)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

            int aceTrumpsIndex = currentHand.getAceOfTrumpsPosition();
            if(aceTrumpsPlayer.equals(Hand.player)){

                aceTrumpsToBeUpdated = true;
                if(aceTrumpsIndex == 0){
                    firstCardPlayerImage.setClickable(false);
                    firstCardPlayerImage.setColorFilter(Color.rgb(123, 123, 123),
                            android.graphics.PorterDuff.Mode.MULTIPLY);
                    aceTrumpsImage = firstCardPlayerImage;

                }
                else if(aceTrumpsIndex == 1){
                    secondCardPlayerImage.setClickable(false);
                    secondCardPlayerImage.setColorFilter(Color.rgb(123, 123, 123),
                            android.graphics.PorterDuff.Mode.MULTIPLY);
                    aceTrumpsImage = secondCardPlayerImage;

                }
                else if(aceTrumpsIndex == 2){
                    thirdCardPlayerImage.setClickable(false);
                    thirdCardPlayerImage.setColorFilter(Color.rgb(123, 123, 123),
                            android.graphics.PorterDuff.Mode.MULTIPLY);
                    aceTrumpsImage = thirdCardPlayerImage;

                }
                else if(aceTrumpsIndex ==3){
                    fourthCardPlayerImage.setClickable(false);
                    fourthCardPlayerImage.setColorFilter(Color.rgb(123, 123, 123),
                            android.graphics.PorterDuff.Mode.MULTIPLY);
                    aceTrumpsImage = fourthCardPlayerImage;

                }
                else if(aceTrumpsIndex ==4){
                    fifthCardPlayerImage.setClickable(false);
                    fifthCardPlayerImage.setColorFilter(Color.rgb(123, 123, 123),
                            android.graphics.PorterDuff.Mode.MULTIPLY);
                    aceTrumpsImage = fifthCardPlayerImage;

                }

            }

        }

        leadingPlayer = Hand.player;

    }

    public void playerCardClicked(View view) {

        if(aceTrumpsToBeUpdated){
            upTrumpCardImage.setColorFilter(Color.rgb(123, 123, 123),
                    android.graphics.PorterDuff.Mode.MULTIPLY);
            switch (view.getId()) {
                case R.id.first_card_player:
                    firstCardPlayerImage.setImageResource(currentHand.getTrumpCard().getCardImageId());
                    firstCardPlayer = currentHand.getTrumpCard();
                    currentHand.updatePlayerCard(currentHand.getTrumpCard(),0);
                    aceTrumpsImage.setClickable(true);
                    aceTrumpsImage.clearColorFilter();
                    break;
                case R.id.second_card_player:
                    secondCardPlayerImage.setImageResource(currentHand.getTrumpCard().getCardImageId());
                    secondCardPlayer = currentHand.getTrumpCard();
                    currentHand.updatePlayerCard(currentHand.getTrumpCard(),1);
                    aceTrumpsImage.setClickable(true);
                    aceTrumpsImage.clearColorFilter();
                    break;

                case R.id.third_card_player:
                    thirdCardPlayerImage.setImageResource(currentHand.getTrumpCard().getCardImageId());
                    thirdCardPlayer = currentHand.getTrumpCard();
                    currentHand.updatePlayerCard(currentHand.getTrumpCard(),2);
                    aceTrumpsImage.setClickable(true);
                    aceTrumpsImage.clearColorFilter();
                    break;

                case R.id.fourth_card_player:
                    fourthCardPlayerImage.setImageResource(currentHand.getTrumpCard().getCardImageId());
                    fourthCardPlayer = currentHand.getTrumpCard();
                    currentHand.updatePlayerCard(currentHand.getTrumpCard(),3);
                    aceTrumpsImage.setClickable(true);
                    aceTrumpsImage.clearColorFilter();
                    break;

                case R.id.fifth_card_player:
                    fifthCardPlayerImage.setImageResource(currentHand.getTrumpCard().getCardImageId());
                    fifthCardPlayer = currentHand.getTrumpCard();
                    currentHand.updatePlayerCard(currentHand.getTrumpCard(),4);
                    aceTrumpsImage.setClickable(true);
                    aceTrumpsImage.clearColorFilter();
                    break;

            }

            aceTrumpsToBeUpdated = false;
        }

        else if (firstCardNext) {

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

            playerLedFinishTrick();


            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (!currentHand.isHandFinished()) {
                        if (currentHand.getLastTrickWinner().equals(Hand.computer1)) {
                            computer1LeadTrick();
                        } else if (currentHand.getLastTrickWinner().equals(Hand.computer2)) {
                            computer2LeadTrick();
                        }
                    }


                    return;


                }
            }, 2100);



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

            computer1FinishTrick();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if(!currentHand.isHandFinished()){
                        if(currentHand.getLastTrickWinner().equals(Hand.computer1)){
                            computer1LeadTrick();
                        }
                        else if(currentHand.getLastTrickWinner().equals(Hand.computer2)){
                            computer2LeadTrick();
                        }
                    }
                    return;


                }
            }, 2100);                   // Do something after 5s = 5000ms



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

            if(!currentHand.isHandFinished()){
                if(currentHand.getLastTrickWinner().equals(Hand.computer1)){
                    computer1LeadTrick();

                }
                else if(currentHand.getLastTrickWinner().equals(Hand.computer2)){
                    computer2LeadTrick();

                }

            }


            return;

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

        // Add a time delay of 3 seconds before displaying result
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                firstCardPlayedImage.setImageDrawable(null);
                secondCardPlayedImage.setImageDrawable(null);
                thirdCardPlayedImage.setImageDrawable(null);

/*
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
                */

            }
        }, 3000);                   // Do something after 5s = 5000ms



    }



    private void playerLedFinishTrick(){

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Card secondPlayed = currentHand.playSecondCard(Hand.computer1);
                currentHand.setSecondPlayed(secondPlayed, Hand.computer1);
                final int positionOfSecondPlayed = Arrays.asList(currentHand.getCom1Cards()).indexOf(secondPlayed);
                // Add a time delay of 2 seconds before displaying result


                if(positionOfSecondPlayed == 0){
                    firstCardCom1Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(firstCardCom1.getCardImageId());

                }
                else if(positionOfSecondPlayed == 1){
                    secondCardCom1Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(secondCardCom1.getCardImageId());

                }
                else if(positionOfSecondPlayed == 2){
                    thirdCardCom1Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(thirdCardCom1.getCardImageId());

                }
                else if(positionOfSecondPlayed == 3){
                    fourthCardCom1Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(fourthCardCom1.getCardImageId());

                }
                else{
                    fifthCardCom1Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(fifthCardCom1.getCardImageId());

                }

                firstCardNext = false;
                secondCardNext = false;
                thirdCardNext = true;


            }
        }, 1500);                   // Do something after 5s = 5000ms



        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Card thirdPlayed = currentHand.playThirdCard(Hand.computer2);
                currentHand.setThirdPlayed(thirdPlayed, Hand.computer2);
                final int positionOfThirdPlayed = Arrays.asList(currentHand.getCom2Cards()).indexOf(thirdPlayed);

                if(positionOfThirdPlayed == 0){
                    firstCardCom2Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(firstCardCom2.getCardImageId());

                }
                else if(positionOfThirdPlayed == 1){
                    secondCardCom2Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(secondCardCom2.getCardImageId());

                }
                else if(positionOfThirdPlayed == 2){
                    thirdCardCom2Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(thirdCardCom2.getCardImageId());

                }
                else if(positionOfThirdPlayed == 3){
                    fourthCardCom2Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(fourthCardCom2.getCardImageId());

                }
                else{
                    fifthCardCom2Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(fifthCardCom2.getCardImageId());

                }

                firstCardNext = true;
                secondCardNext = false;
                thirdCardNext = false;

                trickFinishedUpdate();

            }
        }, 2000);                   // Do something after 5s = 5000ms






    }



    private void computer1LeadTrick() {


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Card firstPlayed = currentHand.playFirstCard(Hand.computer1);
                currentHand.setFirstPlayed(firstPlayed, Hand.computer1);
                int positionOfSecondPlayed = Arrays.asList(currentHand.getCom1Cards()).indexOf(firstPlayed);

                if (positionOfSecondPlayed == 0) {
                    firstCardCom1Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(firstCardCom1.getCardImageId());

                } else if (positionOfSecondPlayed == 1) {
                    secondCardCom1Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(secondCardCom1.getCardImageId());

                } else if (positionOfSecondPlayed == 2) {
                    thirdCardCom1Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(thirdCardCom1.getCardImageId());

                } else if (positionOfSecondPlayed == 3) {
                    fourthCardCom1Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(fourthCardCom1.getCardImageId());

                } else {
                    fifthCardCom1Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(fifthCardCom1.getCardImageId());

                }

                firstCardNext = false;
                secondCardNext = true;
                thirdCardNext = false;



            }
        }, 5500);                   // Do something after 5s = 5000ms


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Card secondPlayed = currentHand.playSecondCard(Hand.computer2);
                currentHand.setSecondPlayed(secondPlayed, Hand.computer2);
                int positionOfSecondPlayed = Arrays.asList(currentHand.getCom2Cards()).indexOf(secondPlayed);

                if (positionOfSecondPlayed == 0) {
                    firstCardCom2Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(firstCardCom2.getCardImageId());

                } else if (positionOfSecondPlayed == 1) {
                    secondCardCom2Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(secondCardCom2.getCardImageId());

                } else if (positionOfSecondPlayed == 2) {
                    thirdCardCom2Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(thirdCardCom2.getCardImageId());

                } else if (positionOfSecondPlayed == 3) {
                    fourthCardCom2Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(fourthCardCom2.getCardImageId());

                } else {
                    fifthCardCom2Image.setVisibility(View.INVISIBLE);
                    secondCardPlayedImage.setImageResource(fifthCardCom2.getCardImageId());

                }

                firstCardNext = false;
                secondCardNext = false;
                thirdCardNext = true;



            }
        }, 6000);                   // Do something after 5s = 5000ms





    }


    private void computer2LeadTrick(){

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Card firstPlayed = currentHand.playFirstCard(Hand.computer2);
                currentHand.setFirstPlayed(firstPlayed, Hand.computer2);
                int positionOfFirstPlayed = Arrays.asList(currentHand.getCom2Cards()).indexOf(firstPlayed);

                if (positionOfFirstPlayed == 0) {
                    firstCardCom2Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(firstCardCom2.getCardImageId());

                } else if (positionOfFirstPlayed == 1) {
                    secondCardCom2Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(secondCardCom2.getCardImageId());

                } else if (positionOfFirstPlayed == 2) {
                    thirdCardCom2Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(thirdCardCom2.getCardImageId());

                } else if (positionOfFirstPlayed == 3) {
                    fourthCardCom2Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(fourthCardCom2.getCardImageId());

                } else {
                    fifthCardCom2Image.setVisibility(View.INVISIBLE);
                    firstCardPlayedImage.setImageResource(fifthCardCom2.getCardImageId());

                }

                firstCardNext = false;
                secondCardNext = true;
                thirdCardNext = false;



            }
        }, 6000);                   // Do something after 5s = 5000ms

    }


    private void computer1FinishTrick(){

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Card thirdPlayed = currentHand.playThirdCard(Hand.computer1);
                currentHand.setThirdPlayed(thirdPlayed, Hand.computer1);
                int positionOfThirdPlayed = Arrays.asList(currentHand.getCom1Cards()).indexOf(thirdPlayed);

                if(positionOfThirdPlayed == 0){
                    firstCardCom1Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(firstCardCom1.getCardImageId());

                }
                else if(positionOfThirdPlayed == 1){
                    secondCardCom1Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(secondCardCom1.getCardImageId());

                }
                else if(positionOfThirdPlayed == 2){
                    thirdCardCom1Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(thirdCardCom1.getCardImageId());

                }
                else if(positionOfThirdPlayed == 3){
                    fourthCardCom1Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(fourthCardCom1.getCardImageId());

                }
                else{
                    fifthCardCom1Image.setVisibility(View.INVISIBLE);
                    thirdCardPlayedImage.setImageResource(fifthCardCom1.getCardImageId());

                }

                firstCardNext = true;
                secondCardNext = false;
                thirdCardNext = false;

                trickFinishedUpdate();


            }
        }, 2000);                   // Do something after 5s = 5000ms

    }


}

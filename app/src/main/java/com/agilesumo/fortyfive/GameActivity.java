package com.agilesumo.fortyfive;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.widget.LinearLayout.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;

import org.w3c.dom.Text;

import java.util.Arrays;


public class GameActivity extends AppCompatActivity {

    //version 2


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

    private boolean aceTrumpsToBeUpdated = false;

    private Card upTrumpCard;

    private ImageView upTrumpCardImage;

    private TextView scoreTextPlayer;

    private TextView scoreTextCom1;

    private TextView scoreTextCom2;

    private Player leadingPlayer;

    private FortyFiveGame game;

    private TextView totalScorePlayerText;

    private TextView totalScoreCom1Text;

    private TextView totalScoreCom2Text;

    private TextView com1DealerText;

    private TextView com2DealerText;

    private TextView playerDealerText;

    private LinearLayout playersCardsLayout;

    private LinearLayout com1CardsLayout;

    private LinearLayout com2CardsLayout;






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = new FortyFiveGame();

        firstCardPlayerImage = (ImageView) findViewById(R.id.first_card_player);
        secondCardPlayerImage = (ImageView) findViewById(R.id.second_card_player);
        thirdCardPlayerImage = (ImageView) findViewById(R.id.third_card_player);
        fourthCardPlayerImage = (ImageView) findViewById(R.id.fourth_card_player);
        fifthCardPlayerImage = (ImageView) findViewById(R.id.fifth_card_player);

        firstCardCom1Image = (ImageView) findViewById(R.id.first_card_computer1);
        secondCardCom1Image = (ImageView) findViewById(R.id.second_card_computer1);
        thirdCardCom1Image = (ImageView) findViewById(R.id.third_card_computer1);
        fourthCardCom1Image = (ImageView) findViewById(R.id.fourth_card_computer1);
        fifthCardCom1Image = (ImageView) findViewById(R.id.fifth_card_computer1);

        firstCardCom2Image = (ImageView) findViewById(R.id.first_card_computer2);
        secondCardCom2Image = (ImageView) findViewById(R.id.second_card_computer2);
        thirdCardCom2Image = (ImageView) findViewById(R.id.third_card_computer2);
        fourthCardCom2Image = (ImageView) findViewById(R.id.fourth_card_computer2);
        fifthCardCom2Image = (ImageView) findViewById(R.id.fifth_card_computer2);

        firstCardPlayedImage = (ImageView) findViewById(R.id.first_card_played);

        secondCardPlayedImage = (ImageView) findViewById(R.id.second_card_played);

        thirdCardPlayedImage = (ImageView) findViewById(R.id.third_card_played);

        upTrumpCardImage = (ImageView) findViewById(R.id.up_trump_card);

        com1DealerText = (TextView) findViewById(R.id.computer1_dealer);
        com2DealerText = (TextView) findViewById(R.id.computer2_dealer);
        playerDealerText = (TextView) findViewById(R.id.player_dealer);

        scoreTextPlayer = (TextView) findViewById(R.id.player_score_text);
        scoreTextCom1 = (TextView) findViewById(R.id.computer1_score_text);
        scoreTextCom2 = (TextView) findViewById(R.id.computer2_score_text);

        totalScorePlayerText = (TextView) findViewById(R.id.player_total_score);
        totalScoreCom1Text = (TextView) findViewById(R.id.computer1_total_score);
        totalScoreCom2Text = (TextView) findViewById(R.id.computer2_total_score);

        playersCardsLayout = (LinearLayout) findViewById(R.id.layout_cards_player);
        com1CardsLayout = (LinearLayout) findViewById(R.id.layout_cards_computer1);
        com2CardsLayout = (LinearLayout) findViewById(R.id.layout_cards_computer2);

        playersCardsLayout.setBackgroundColor(Color.parseColor("#d3d6fc"));




        initialiseHand();

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
                    break;
                case R.id.second_card_player:
                    secondCardPlayerImage.setImageResource(currentHand.getTrumpCard().getCardImageId());
                    secondCardPlayer = currentHand.getTrumpCard();
                    currentHand.updatePlayerCard(currentHand.getTrumpCard(),1);
                    break;

                case R.id.third_card_player:
                    thirdCardPlayerImage.setImageResource(currentHand.getTrumpCard().getCardImageId());
                    thirdCardPlayer = currentHand.getTrumpCard();
                    currentHand.updatePlayerCard(currentHand.getTrumpCard(),2);
                    break;

                case R.id.fourth_card_player:
                    fourthCardPlayerImage.setImageResource(currentHand.getTrumpCard().getCardImageId());
                    fourthCardPlayer = currentHand.getTrumpCard();
                    currentHand.updatePlayerCard(currentHand.getTrumpCard(),3);
                    break;

                case R.id.fifth_card_player:
                    fifthCardPlayerImage.setImageResource(currentHand.getTrumpCard().getCardImageId());
                    fifthCardPlayer = currentHand.getTrumpCard();
                    currentHand.updatePlayerCard(currentHand.getTrumpCard(),4);
                    break;

            }

            currentHand.createBestTrumpStr();
            aceTrumpsToBeUpdated = false;
            beginHand();
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

    private void initialiseHand(){
        //http://stackoverflow.com/questions/23289168/reassigning-objects-in-java-with-new
        currentHand = new Hand();

        upTrumpCard = currentHand.getTrumpCard();

        boolean isAceUpTrumpCard = false;

        if(upTrumpCard.getValue() == Card.ACE){
            Player dealer = game.getDealer();
            isAceUpTrumpCard = true;
            if(dealer.equals(Hand.computer1) || dealer.equals(Hand.computer2)) {
                currentHand.aceIsTrumpsUpdate(dealer);
            }
        }

        upTrumpCardImage.setImageResource(upTrumpCard.getCardImageId());

        Card[] playersCards = currentHand.getPlayersCards();

        //initialise players cards

        firstCardPlayer = playersCards[0];
        firstCardPlayerImage.setImageResource(firstCardPlayer.getCardImageId());

        secondCardPlayer = playersCards[1];
        secondCardPlayerImage.setImageResource(secondCardPlayer.getCardImageId());

        thirdCardPlayer = playersCards[2];
        thirdCardPlayerImage.setImageResource(thirdCardPlayer.getCardImageId());

        fourthCardPlayer = playersCards[3];
        fourthCardPlayerImage.setImageResource(fourthCardPlayer.getCardImageId());

        fifthCardPlayer = playersCards[4];
        fifthCardPlayerImage.setImageResource(fifthCardPlayer.getCardImageId());

        Card[] com1Cards = currentHand.getCom1Cards();

        // initialise computer p1 players cards

        firstCardCom1 = com1Cards[0];

        secondCardCom1 = com1Cards[1];

        thirdCardCom1 = com1Cards[2];

        fourthCardCom1 = com1Cards[3];

        fifthCardCom1 = com1Cards[4];

        // initialise computer p2 players cards

        Card[] com2Cards = currentHand.getCom2Cards();

        firstCardCom2 = com2Cards[0];

        secondCardCom2 = com2Cards[1];

        thirdCardCom2 = com2Cards[2];

        fourthCardCom2 = com2Cards[3];

        fifthCardCom2 = com2Cards[4];



        scoreTextPlayer.setText("Player > Hand: 0");
        scoreTextCom1.setText("COM P1 > Hand: 0");
        scoreTextCom2.setText("COM P2 > Hand: 0");


        if(currentHand.hasAceTrumps() && !isAceUpTrumpCard) {
            Player aceTrumpsPlayer = currentHand.getAceTrumpsPlayer();
            String hasAceOutput = "";

            if ((Hand.player).equals(aceTrumpsPlayer)) {
                hasAceOutput += "You have the ace of trumps";
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

                aceTrumpsToBeUpdated = true;

                playersCardsLayout.setBackgroundColor(Color.parseColor("#d3d6fc"));
                setPlayerCardsClickable();


            }
            else if ((Hand.computer1).equals(aceTrumpsPlayer)) {
                hasAceOutput += "Computer P1 has ace of trumps.";
                hasAceOutput += "It has discarded one of it's cards and claimed the up trump card";

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(hasAceOutput)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                beginHand();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                upTrumpCardImage.setColorFilter(Color.rgb(123, 123, 123),
                        android.graphics.PorterDuff.Mode.MULTIPLY);


            }

            else {
                hasAceOutput += "ComputerP2 has ace of trumps. ";
                hasAceOutput += "It has discarded one of it's cards and claimed the up trump card";

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(hasAceOutput)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                beginHand();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                upTrumpCardImage.setColorFilter(Color.rgb(123, 123, 123),
                        android.graphics.PorterDuff.Mode.MULTIPLY);
            }

        }

        else if(upTrumpCard.getValue() == Card.ACE){

            Player dealer = game.getDealer();
            String hasAceOutput = "";

            if ((Hand.player).equals(dealer)) {
                hasAceOutput += "You can claim the up trump card as you are the dealer and it is an ace. ";
                hasAceOutput += "Choose a card to discard to claim the turned up trump card.";

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(hasAceOutput)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                aceTrumpsToBeUpdated = true;

                playersCardsLayout.setBackgroundColor(Color.parseColor("#d3d6fc"));
                setPlayerCardsClickable();


            }
            else if ((Hand.computer1).equals(dealer)) {
                hasAceOutput += "Computer P1 is the dealer and an ace is trumps.";
                hasAceOutput += "It has discarded one of it's cards and claimed the up trump card";

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(hasAceOutput)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                beginHand();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                upTrumpCardImage.setColorFilter(Color.rgb(123, 123, 123),
                        android.graphics.PorterDuff.Mode.MULTIPLY);



            }

            else {
                hasAceOutput += "Computer P2 is the dealer and an ace is trumps. ";
                hasAceOutput += "It has discarded one of it's cards and claimed the up trump card";

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(hasAceOutput)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                beginHand();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                upTrumpCardImage.setColorFilter(Color.rgb(123, 123, 123),
                        android.graphics.PorterDuff.Mode.MULTIPLY);
            }


        }


        leadingPlayer = Hand.player;


    }

    private void trickFinishedUpdate(){
        firstCardNext = true;
        secondCardNext = false;
        thirdCardNext = false;

        currentHand.updateScore();

        scoreTextPlayer.setText("Player > Hand: " + currentHand.getScore(Hand.player));
        scoreTextCom1.setText("COM P1 > Hand: " + currentHand.getScore(Hand.computer1));
        scoreTextCom2.setText("COM P2 > Hand: " + currentHand.getScore(Hand.computer2));

        game.addToScore(currentHand.getLastTrickWinner());

        totalScorePlayerText.setText("Total: " + game.getTotalScore(Hand.player));
        totalScoreCom1Text.setText("Total: " + game.getTotalScore(Hand.computer1));
        totalScoreCom2Text.setText("Total: " + game.getTotalScore(Hand.computer2));


        // Add a time delay of 3 seconds before displaying result
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                firstCardPlayedImage.setImageDrawable(null);
                secondCardPlayedImage.setImageDrawable(null);
                thirdCardPlayedImage.setImageDrawable(null);

                playersCardsLayout.setBackgroundColor(0);
                com1CardsLayout.setBackgroundColor(0);
                com2CardsLayout.setBackgroundColor(0);

                disablePlayerCardsClickable();

                if(!currentHand.isHandFinished()) {

                    if (currentHand.getLastTrickWinner().equals(Hand.player)) {
                        playersCardsLayout.setBackgroundColor(Color.parseColor("#d3d6fc"));
                        setPlayerCardsClickable();
                    } else if (currentHand.getLastTrickWinner().equals(Hand.computer1)) {
                        com1CardsLayout.setBackgroundColor(Color.parseColor("#d3d6fc"));
                    } else {
                        com2CardsLayout.setBackgroundColor(Color.parseColor("#d3d6fc"));
                    }
                }




                if (currentHand.isHandFinished()) {
                    Log.d("andyoc", "got hand finished");

                    Toast bestTrumpOutput = Toast.makeText(GameActivity.this, currentHand.getBestTrumpStr(),
                            Toast.LENGTH_LONG);
                    bestTrumpOutput.setGravity(Gravity.CENTER, 0, 0);
                    bestTrumpOutput.show();
                    scoreTextPlayer.setText("Player > Hand: " + currentHand.getScore(Hand.player));
                    scoreTextCom1.setText("COM P1 > Hand: " + currentHand.getScore(Hand.computer1));
                    scoreTextCom2.setText("COM P2 > Hand: " + currentHand.getScore(Hand.computer2));

                    if(currentHand.getBestTrumpPlayer() != null) {
                        game.addToScore(currentHand.getBestTrumpPlayer());
                    }
                    totalScorePlayerText.setText("Total: " + game.getTotalScore(Hand.player));
                    totalScoreCom1Text.setText("Total: " + game.getTotalScore(Hand.computer1));
                    totalScoreCom2Text.setText("Total: " + game.getTotalScore(Hand.computer2));

                    if(game.isGameOver()){
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Player gameWinner = game.getWinner();
                                int winnersScore = game.getTotalScore(gameWinner);

                                String gameFinishedOutputStr;

                                gameFinishedOutputStr = "Game is finished. ";

                                if(gameWinner.equals(Hand.player)){
                                    gameFinishedOutputStr += "Congratulations you are " +
                                            "the winner with a score of " + winnersScore;
                                }

                                else if(gameWinner.equals(Hand.computer1)){
                                    gameFinishedOutputStr += "Unlucky Computer P1 has won this game " +
                                            "with a score of " + winnersScore;
                                }

                                else{
                                    gameFinishedOutputStr += "Unlucky Computer P2 has won this game " +
                                            "with a score of " + winnersScore;
                                }

                                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                                builder.setMessage(gameFinishedOutputStr)
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                finish();
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();

                            }
                        }, 3500);
                    }

                    else {

                        addNextHandButton();
                    }


                }


            }
        }, 3000);                   // Do something after 5s = 5000ms



    }



    private void playerLedFinishTrick(){

        final Handler handler = new Handler();
        playersCardsLayout.setBackgroundColor(0);
        disablePlayerCardsClickable();
        com1CardsLayout.setBackgroundColor(Color.parseColor("#d3d6fc"));
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

                com1CardsLayout.setBackgroundColor(0);
                com2CardsLayout.setBackgroundColor(Color.parseColor("#d3d6fc"));


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

        playersCardsLayout.setBackgroundColor(0);
        com2CardsLayout.setBackgroundColor(0);
        com1CardsLayout.setBackgroundColor(Color.parseColor("#d3d6fc"));

        disablePlayerCardsClickable();



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

                com1CardsLayout.setBackgroundColor(0);
                com2CardsLayout.setBackgroundColor(Color.parseColor("#d3d6fc"));



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

                com2CardsLayout.setBackgroundColor(0);
                playersCardsLayout.setBackgroundColor(Color.parseColor("#d3d6fc"));
                setPlayerCardsClickable();



            }
        }, 6000);                   // Do something after 5s = 5000ms





    }


    private void computer2LeadTrick(){

        playersCardsLayout.setBackgroundColor(0);
        com1CardsLayout.setBackgroundColor(0);
        com2CardsLayout.setBackgroundColor(Color.parseColor("#d3d6fc"));

        disablePlayerCardsClickable();


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

                com2CardsLayout.setBackgroundColor(0);
                playersCardsLayout.setBackgroundColor(Color.parseColor("#d3d6fc"));

                setPlayerCardsClickable();



            }
        }, 6000);                   // Do something after 5s = 5000ms

    }


    private void computer1FinishTrick(){

        playersCardsLayout.setBackgroundColor(0);
        com1CardsLayout.setBackgroundColor(Color.parseColor("#d3d6fc"));

        disablePlayerCardsClickable();

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


    private void addNextHandButton(){
        firstCardPlayerImage.setVisibility(View.GONE);
        secondCardPlayerImage.setVisibility(View.GONE);
        thirdCardPlayerImage.setVisibility(View.GONE);
        fourthCardPlayerImage.setVisibility(View.GONE);
        fifthCardPlayerImage.setVisibility(View.GONE);


        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_cards_player);

        //set the properties for button
        final Button nextButton = new Button(this);
        nextButton.setBackgroundResource(R.drawable.green_button);
        nextButton.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        nextButton.setText("Next Hand");

        OnClickListener buttonListener = new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                // to do test diming of ace resets to no dimming after a new hand is started
                // diming initally caused by player having ace

                // also check up truned trump card dimming after the up trump is robbed
                // is is reset on new hand
                // triggered by robbing by player com1 com2

                if(currentHand.hasAceTrumps()){
                    upTrumpCardImage.clearColorFilter();
                }

                game.nextDealer();

                initialiseHand();


                firstCardPlayerImage.setVisibility(View.VISIBLE);
                secondCardPlayerImage.setVisibility(View.VISIBLE);
                thirdCardPlayerImage.setVisibility(View.VISIBLE);
                fourthCardPlayerImage.setVisibility(View.VISIBLE);
                fifthCardPlayerImage.setVisibility(View.VISIBLE);

                firstCardCom1Image.setVisibility(View.VISIBLE);
                secondCardCom1Image.setVisibility(View.VISIBLE);
                thirdCardCom1Image.setVisibility(View.VISIBLE);
                fourthCardCom1Image.setVisibility(View.VISIBLE);
                fifthCardCom1Image.setVisibility(View.VISIBLE);

                firstCardCom2Image.setVisibility(View.VISIBLE);
                secondCardCom2Image.setVisibility(View.VISIBLE);
                thirdCardCom2Image.setVisibility(View.VISIBLE);
                fourthCardCom2Image.setVisibility(View.VISIBLE);
                fifthCardCom2Image.setVisibility(View.VISIBLE);

                nextButton.setVisibility(View.GONE);



                if(!currentHand.hasAceTrumps() && upTrumpCard.getValue() != Card.ACE) {
                    beginHand();
                }

                else if(currentHand.hasAceTrumps() || upTrumpCard.getValue() == Card.ACE){
                    Player nextDealer = game.getDealer();
                    if (nextDealer.equals(Hand.player)) {
                        playerDealerText.setText("Dealer");
                        com2DealerText.setText("");
                    } else if (nextDealer.equals(Hand.computer1)) {
                        com1DealerText.setText("Dealer");
                        playerDealerText.setText("");
                    } else if (nextDealer.equals(Hand.computer2)) {
                        com2DealerText.setText("Dealer");
                        com1DealerText.setText("");
                    }
                }


            }
        };
        nextButton.setOnClickListener(buttonListener);

        //add button to the layout
        layout.addView(nextButton);
    }

    private void beginHand(){
        Player nextDealer = game.getDealer();
        if (nextDealer.equals(Hand.player)) {
            playerDealerText.setText("Dealer");
            com2DealerText.setText("");
            computer1LeadTrick();
        } else if (nextDealer.equals(Hand.computer1)) {
            com1DealerText.setText("Dealer");
            playerDealerText.setText("");
            computer2LeadTrick();
        } else if (nextDealer.equals(Hand.computer2)) {
            com2DealerText.setText("Dealer");
            com1DealerText.setText("");
            playersCardsLayout.setBackgroundColor(Color.parseColor("#d3d6fc"));
            setPlayerCardsClickable();
        }

    }


    private void setPlayerCardsClickable(){

        firstCardPlayerImage.setEnabled(true);
        secondCardPlayerImage.setEnabled(true);
        thirdCardPlayerImage.setEnabled(true);
        fourthCardPlayerImage.setEnabled(true);
        fifthCardPlayerImage.setEnabled(true);

    }

    private void disablePlayerCardsClickable(){


        firstCardPlayerImage.setEnabled(false);
        secondCardPlayerImage.setEnabled(false);
        thirdCardPlayerImage.setEnabled(false);
        fourthCardPlayerImage.setEnabled(false);
        fifthCardPlayerImage.setEnabled(false);

    }



}

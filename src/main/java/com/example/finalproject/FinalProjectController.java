package com.example.finalproject;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;



public class FinalProjectController implements Initializable {

    @FXML
    private Label pDexLabel;
    @FXML
    private Label pIntLabel;
    @FXML
    private Label pStrLabel;
    @FXML
    private Label pNameLabel;
    @FXML
    private Label pHealthLabel;
    @FXML
    private Button fightButton;
    @FXML
    private Button runButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button sleepButton;
    @FXML
    private Label mMonsterName;
    @FXML
    private Label mDexLabel;
    @FXML
    private Label mIntLabel;
    @FXML
    private Label mStrLabel;
    @FXML
    private Label mHealthLabel;
    @FXML
    private VBox mBox;
    @FXML
    private Label totalGold;
    @FXML
    private Label roomsCleared;
    private int numRoomClear;
    @FXML
    private Label totalSteps;
    private int totalStepsTaken;
    @FXML
    private Label totalMonstersKilled;
    private int monstersKilled;
    @FXML
    private Button upButton;
    @FXML
    private Button leftButton;
    @FXML
    private Button downButton;
    @FXML
    private Button rightButton;
    @FXML
    private Button rollButton;
    @FXML
    private Label rollValLabel;
    @FXML
    private Label damageValLabel;
    @FXML
    private Label monRollValLabel;
    @FXML
    private Label monDamageValLabel;
    @FXML
    private HBox pDamageValBox;
    @FXML
    private GridPane monRollValGrid;
    @FXML
    private TextArea promptArea;
    @FXML
    private Rectangle topWall;
    @FXML
    private Rectangle leftWall;
    @FXML
    private Rectangle bottomWall;
    @FXML
    private Rectangle rightWall;
    @FXML
    private Polygon playerSprite;
    @FXML
    private Rectangle combatBackground;
    @FXML
    private Rectangle travelBackground;
    private Boolean inFight;
    private Boolean room;
    private Boolean monsterInRoom;
    private Boolean search;
    private Boolean alive;
    @FXML
    private Circle enemySprite;
    @FXML
    private ToggleButton adminToggle;
    @FXML
    private VBox adminBox;
    @FXML
    private Button adminKillPlayer;
    @FXML
    private CheckBox adminSleepMon;
    private Boolean sleepMon;
    @FXML
    private CheckBox adminRunMon;
    private Boolean runMon;

    @FXML
    private String monsterName;
    private int monsterHealth;
    private int monsterStrength;
    private int monsterDexterity;
    private int monsterIntelligence;


    private String playerName;
    private int playerHealth;
    private int playerTotalGold;
    private int playerStrength;
    private int playerDexterity;
    private int playerIntelligence;
    @FXML
    private Button resetButton;

    Dice dice = new Dice();

    public void makePlayer() {

        Player user = new Player("Wally");
        playerName = user.getPlayerName();
        playerHealth = user.getPlayerHealth();
        playerTotalGold = user.getPlayerGold();
        playerStrength = user.getPlayerStrength();
        playerDexterity = user.getPlayerDexterity();
        playerIntelligence = user.getPlayerIntelligence();

        pHealthLabel.setText(String.valueOf(playerHealth));
        pNameLabel.setText(playerName);
        pStrLabel.setText(String.valueOf(playerStrength));
        pDexLabel.setText(String.valueOf(playerDexterity));
        pIntLabel.setText(String.valueOf(playerIntelligence));
        totalGold.setText(String.valueOf(playerTotalGold));
        numRoomClear = 0;
        roomsCleared.setText("0");
        totalStepsTaken = 0;
        totalSteps.setText("0");
        monstersKilled = 0;
        totalMonstersKilled.setText("0");
    }

    public void makeMonster() {
        Monster monster = new Monster("Monster Steve");
        monsterName = monster.getMonsterName();
        monsterHealth = monster.getMonsterHealth();
        monsterStrength = monster.getMonsterStrength();
        monsterDexterity = monster.getMonsterDexterity();
        monsterIntelligence = monster.getMonsterIntelligence();

        mMonsterName.setText(monsterName);
        mHealthLabel.setText(String.valueOf(monsterHealth));
        mStrLabel.setText(String.valueOf(monsterStrength));
        mDexLabel.setText(String.valueOf(monsterDexterity));
        mIntLabel.setText(String.valueOf(monsterIntelligence));

    }

    public void makeWall() {
        int wallChance = new Random().nextInt(4);
        switch (wallChance) {
            case 0: topWall.setVisible(true);
                break;
            case 1: rightWall.setVisible(true);
                break;
            case 2: bottomWall.setVisible(true);
                break;
            case 3: leftWall.setVisible(true);
                break;
        }
        checkRoom();
    }

    public Boolean checkRoom() {
        if (topWall.isVisible() && bottomWall.isVisible()) {
            if (rightWall.isVisible() && leftWall.isVisible()) {
                room = true;
                travelBackground.setVisible(false);
                combatBackground.setVisible(true);
                playerSprite.setRotate(0);
                roomSetup();
            }
        } else {
            room = false;
        }
        return room;
    }

    public void roomSetup() {
        int u = new Random().nextInt(3);

        if (u == 0) {
            makeMonster();
            mBox.setVisible(true);
            enemySprite.setVisible(true);
            monsterInRoom = true;
            promptArea.appendText("A monster has appeared! \n"
                    + "You can either fight it or try to run away.\n");

        } else {
            promptArea.clear();
            monsterInRoom = false;
            promptArea.appendText("The room seems empty. \n"
                    + "Maybe there is gold hidden around the room. \n"
                    + "Or you can sleep now to regain your lost health. \n"
                    + "But be careful, theres always monsters nearby... \n");
        }
    }

    public void resetRoom() {
        promptArea.appendText("\nYou may continue onward.");


        totalGold.setText(String.valueOf(playerTotalGold));
        pHealthLabel.setText(String.valueOf(playerHealth));
        pDamageValBox.setVisible(false);


        topWall.setVisible(false);
        leftWall.setVisible(false);
        bottomWall.setVisible(false);
        rightWall.setVisible(false);


        monRollValGrid.setVisible(false);
        travelBackground.setVisible(true);
        combatBackground.setVisible(false);
        enemySprite.setVisible(false);
        monsterInRoom = false;
        inFight = false;
        room = false;
        search = false;
        numRoomClear += 1;
        roomsCleared.setText(String.valueOf(numRoomClear));
    }


    public void resetGame() {
        adminBox.setVisible(false);
        runMon = false;
        adminRunMon.setSelected(false);
        sleepMon = false;
        adminSleepMon.setSelected(false);

        resetButton.setVisible(false);
        alive = true;
        rollValLabel.setText(" ");

        makePlayer();
        mBox.setVisible(false);
        resetRoom();
        numRoomClear = 0;
        roomsCleared.setText(String.valueOf(numRoomClear));
        promptArea.clear();
        promptArea.appendText("Hello adventurer! Use the arrow keys to move, \n"
                + "walls will close in on you as you walk around. \n"
                + "Be careful of monsters roaming around, \n"
                + "and make sure you look for gold when possible!");
    }


    public void checkAlive() {
        if (playerHealth <= 0) {
            resetButton.setVisible(true);
            alive = false;
            promptArea.appendText("\nYou died. Click the reset button to play again.");
        }
    }


    public FinalProjectController () {
        room = false;
        inFight = false;
        search = false;
        alive = true;
        runMon = false;
        sleepMon = false;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mBox.setVisible(false);
        resetGame();
    }


    @FXML
    private void moveKeys(ActionEvent event) {


        if (room == false && alive) {


            mBox.setVisible(false);
            promptArea.clear();
            rollValLabel.setText(" ");

            if (event.getSource() == upButton ) {
                playerSprite.setRotate(0);
                if (topWall.isVisible() == false) {
                    makeWall();
                    totalStepsTaken++;
                    totalSteps.setText(String.valueOf(totalStepsTaken));
                }
            }

            if (event.getSource() == downButton ) {
                playerSprite.setRotate(180);
                if (bottomWall.isVisible() == false) {
                    makeWall();
                    totalStepsTaken++;
                    totalSteps.setText(String.valueOf(totalStepsTaken));
                }
            }

            if (event.getSource() == leftButton ) {
                playerSprite.setRotate(270);
                if (leftWall.isVisible() == false) {
                    makeWall();
                    totalStepsTaken++;
                    totalSteps.setText(String.valueOf(totalStepsTaken));
                }
            }

            if (event.getSource() == rightButton ) {
                playerSprite.setRotate(90);
                if (rightWall.isVisible() == false) {
                    makeWall();
                    totalStepsTaken++;
                    totalSteps.setText(String.valueOf(totalStepsTaken));
                }
            }
        }
    }

    @FXML
    private void abilityBar(ActionEvent event) {


        if (alive && room && monsterInRoom) {

            if (inFight == false) {
                if (event.getSource() == fightButton ) {

                    promptArea.clear();
                    monRollValGrid.setVisible(true);
                    pDamageValBox.setVisible(true);
                    rollValLabel.setText(" ");
                    damageValLabel.setText(" ");
                    monRollValLabel.setText(" ");
                    monDamageValLabel.setText(" ");
                    promptArea.appendText("You have chosen to fight the monster! \n"
                            + "Roll the die to attack the monster and try to stay alive! \n");
                    inFight = true;
                }

                if (event.getSource() == runButton ) {
                    promptArea.clear();
                    promptArea.appendText("You have chosen to try to run from the fight \n");

                    int monsterRunRoll = dice.roll();
                    if (runMon == true) {
                        monsterRunRoll = (monsterIntelligence - 1);                    }

                    if (monsterRunRoll < monsterIntelligence) {
                        int monsterRunAttack = dice.rollDamage(monsterRunRoll,playerDexterity,monsterStrength);
                        if (runMon == true) {
                            monsterRunAttack = (playerHealth / 3);
                        }

                        if (monsterRunAttack > 0) {
                            playerHealth -= monsterRunAttack;
                            pHealthLabel.setText(String.valueOf(playerHealth));
                            promptArea.appendText("The monster attacked you as you ran away! \n"
                                    + "It dealt " + monsterRunAttack + " damage as you fled!");

                        } else {
                            promptArea.appendText("The monster saw you run and tried to attack you. \n"
                                    + "It missed and dealth 0 damage. \n");
                        }

                    } else {
                        promptArea.appendText("You ran away safely.");

                    }
                    checkAlive();
                    resetRoom();
                }

                if (event.getSource() == searchButton ) {
                    promptArea.clear();
                    promptArea.appendText("You can't search the room! Theres a monster here! \n");
                }

                //sleep button
                if (event.getSource() == sleepButton ) {
                    promptArea.clear();
                    promptArea.appendText("You can't sleep now! Theres a monster in the room! \n");
                }
            }

            if (inFight) {

                if (event.getSource() == runButton ) {
                    promptArea.clear();
                    promptArea.appendText("You have chosen to try to run from the fight \n");
                    int monsterRunRoll = dice.roll();
                    if (runMon == true) {
                        monsterRunRoll = (monsterIntelligence - 1);
                        ;                    }

                    if (monsterRunRoll < monsterIntelligence) {
                        int monsterRunAttack = dice.rollDamage(monsterRunRoll,playerDexterity,monsterStrength);
                        if (runMon == true) {
                            monsterRunAttack = 5;
                        }

                        if (monsterRunAttack > 0) {
                            playerHealth -= monsterRunAttack;
                            pHealthLabel.setText(String.valueOf(playerHealth));
                            promptArea.appendText("The monster attacked you as you ran away! \n"
                                    + "It dealt " + monsterRunAttack + " damage as you fled!");

                        } else {
                            promptArea.appendText("The monster saw you run and tried to attack you. \n"
                                    + "It missed and dealth 0 damage. \n");
                        }

                    } else {
                        promptArea.appendText("You ran away safely.");

                    }
                    checkAlive();
                    resetRoom();
                }

                if(event.getSource() == rollButton) {
                    promptArea.clear();

                    int playerDamageRoll = dice.roll();
                    int monsterDamageRoll = dice.roll();

                    int playerAttack = dice.rollDamage(playerDamageRoll, monsterDexterity, playerStrength);
                    damageValLabel.setText(String.valueOf(playerAttack));
                    rollValLabel.setText(String.valueOf(playerDamageRoll));

                    if (playerAttack > 0) {
                        promptArea.appendText("You rolled a " + playerDamageRoll +
                                " and hit the monster for " + playerAttack + " health! \n");
                        monsterHealth -= playerAttack;
                        mHealthLabel.setText(String.valueOf(monsterHealth));

                    } else {
                        promptArea.appendText("You rolled a " + playerDamageRoll +
                                " and missed the monster doing 0 damage! \n");
                    }

                    if(monsterHealth > 0) {

                        promptArea.appendText("The monster now has " + monsterHealth
                                + " health remaining. \n");

                        int monsterAttack = dice.rollDamage(monsterDamageRoll, playerDexterity, monsterStrength);

                        if (monsterAttack > 0) {
                            monRollValLabel.setText(String.valueOf(monsterDamageRoll));
                            monDamageValLabel.setText(String.valueOf(monsterAttack));
                            playerHealth -= monsterAttack;
                            pHealthLabel.setText(String.valueOf(playerHealth));
                            promptArea.appendText("The monster rolled a " + monsterDamageRoll
                                    + " and hit you for " + monsterAttack + " health! \n");
                            checkAlive();
                        }


                        if (monsterAttack == 0) {
                            monRollValLabel.setText(String.valueOf(monsterDamageRoll));
                            monDamageValLabel.setText(String.valueOf(monsterAttack));
                            promptArea.appendText("The monster rolled a " + monsterDamageRoll
                                    + " and missed you dealing 0 damage!");
                        }


                    } else {
                        promptArea.appendText("You killed the monster! \n");
                        mHealthLabel.setText("dead");
                        monstersKilled += 1;
                        totalMonstersKilled.setText(String.valueOf(monstersKilled));
                        resetRoom();
                    }
                }
            }
        }

        if (alive && room && monsterInRoom == false) {

            if (search == false) {

                if (event.getSource() == fightButton) {
                    promptArea.clear();
                    promptArea.appendText("Theres nothing to fight here. \n");
                }

                if (event.getSource() == runButton) {
                    promptArea.clear();
                    promptArea.appendText("Theres nothing to run from, relax. \n");
                }

                if (event.getSource() == searchButton) {
                    promptArea.clear();
                    promptArea.appendText("Roll the die. \n If the roll is less than your "
                            + "intelligence, you may find some gold nearby...");
                    search = true;
                }

                if (event.getSource() == sleepButton) {
                    promptArea.clear();
                    promptArea.appendText("You try to sleep to regain some strength\n");

                    int sleepAttackChance = 1 + new Random().nextInt(7);
                    if (sleepMon == true) {
                        sleepAttackChance = 3;
                    }
                    int noSleep = 3;

                    if (sleepAttackChance == noSleep) {
                        makeMonster();
                        mBox.setVisible(false);
                        int sleepAttackRoll = dice.roll();
                        int sleepAttackDamage = dice.rollDamage(sleepAttackRoll, playerDexterity, monsterStrength);
                        if (sleepMon == true) {
                            sleepAttackDamage = 5;
                        }

                        if (sleepAttackDamage > 0) {
                            promptArea.appendText("A monster attacked you in your sleep! \n"
                                    + "You gained no stregnth and lost " + sleepAttackDamage +
                                    " health. \n");
                            playerHealth -= sleepAttackDamage;
                            resetRoom();
                            checkAlive();
                        } else {
                            promptArea.appendText("You wake up feeling well-rested. \n"
                                    + "You are back at full health. \n");
                            playerHealth = 20;
                            resetRoom();
                        }

                    } else {
                        promptArea.clear();
                        promptArea.appendText("You wake up feeling well-rested. \n"
                                + "You are back at full health. \n");
                        playerHealth = 20;
                        resetRoom();
                    }

                }
            }

            if (event.getSource() == rollButton && search) {
                promptArea.clear();
                int searchRoll = dice.roll();
                rollValLabel.setText(String.valueOf(searchRoll));

                if (searchRoll < playerIntelligence) {
                    int goldFound = new Random().nextInt(1000);
                    playerTotalGold += goldFound;
                    promptArea.appendText("You found " +
                            String.valueOf(goldFound) + " gold in your search! \n");
                    resetRoom();
                } else {
                    promptArea.appendText("You didn't find any gold in your search \n");
                    resetRoom();
                }
            }
        }
    }

    @FXML
    private void resetGameButton(ActionEvent event) {
        if(alive == false) {
            resetGame();
        }
    }
    @FXML
    private void adminAction(ActionEvent event) {

        if (adminToggle.isSelected() == true) {
            adminBox.setVisible(true);
        } else {
            adminBox.setVisible(false);
        }

        if (event.getSource() == adminKillPlayer) {
            playerHealth = 0;
            pHealthLabel.setText(String.valueOf(playerHealth));
            checkAlive();
        }

        if (adminRunMon.isSelected() == true) {
            runMon = true;
        } else {
            runMon = false;
        }

        if (adminSleepMon.isSelected() == true) {
            sleepMon = true;
        } else {
            sleepMon = false;
        }
    }
}

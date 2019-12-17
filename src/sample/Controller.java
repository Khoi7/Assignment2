/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2019C
  Assessment: Assignment 2
  Author: Vu Duy Khoi
  ID: s3694615
  Created  date: 15/12/2019
  Last modified: 16/12/2019
  Acknowledgement:
  http://www.tutormay.com/tutorial/tutorial-series/17/586 (for background music)
*/

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    // FXML ids
    @FXML
    private ProgressBar timeBar;
    @FXML
    private MediaView mediaView;
    @FXML
    private Label scoreLabel;
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox levelVBox;
    @FXML
    private Button playButton;
    @FXML
    private Button quitButton;
    @FXML
    private Label level;
    @FXML
    private ImageView playSound;
    @FXML
    private ImageView muteSound;
    @FXML
    private ImageView iv1;
    @FXML
    private ImageView iv2;
    @FXML
    private ImageView iv3;
    @FXML
    private ImageView iv4;
    @FXML
    private ImageView iv5;
    @FXML
    private ImageView iv6;
    @FXML
    private ImageView iv7;
    @FXML
    private ImageView iv8;
    @FXML
    private ImageView iv9;
    @FXML
    private ImageView iv10;
    @FXML
    private ImageView iv11;
    @FXML
    private ImageView iv12;
    @FXML
    private ImageView iv13;
    @FXML
    private ImageView iv14;
    @FXML
    private ImageView iv15;
    @FXML
    private ImageView iv16;
    @FXML
    private ImageView iv17;
    @FXML
    private ImageView iv18;
    @FXML
    private ImageView iv19;
    @FXML
    private ImageView iv20;

    // Storing variables
    Image Cup = new Image("Cup.jpg");

    public Image selectedPhotos[] = new Image[20];

    public Image photo[] = {new Image("DucChinh.jpg"),
                            new Image("HoangDuc.jpg"),
                            new Image("HungDung.jpg"),
                            new Image("QuangHai.jpg"),
                            new Image("TanSinh.jpg"),
                            new Image("TienDung.jpg"),
                            new Image("TienLinh.jpg"),
                            new Image("TrongHoang.jpg"),
                            new Image("VanHau.jpg"),
                            new Image("VanToan.jpg"),
                            new Image("DucChinh.jpg"),
                            new Image("HoangDuc.jpg"),
                            new Image("HungDung.jpg"),
                            new Image("QuangHai.jpg"),
                            new Image("TanSinh.jpg"),
                            new Image("TienDung.jpg"),
                            new Image("TienLinh.jpg"),
                            new Image("TrongHoang.jpg"),
                            new Image("VanHau.jpg"),
                            new Image("VanToan.jpg")};

    boolean speakerCheck = true;
    public int score = 0;
    public int totalScore;
    public int flippedCard = 0;
    public int pairFound = 0;
    public float timeleft = 120;

    public void randomCards() {
        // Initialize photoList
        ArrayList<Image> photoList = new ArrayList();
        for (int i = 0; i < 20; i++) {
            photoList.add(photo[i]);
        }
        // Put cards in random position
        int numberOfRandom = 20;
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int temp = random.nextInt(numberOfRandom);
            selectedPhotos[i] = photoList.get(temp);
            photoList.remove(temp);
            numberOfRandom--;
        }
    }
    public void startCountDown() {
        timeBar.setProgress(timeleft/120);
        Timer timer = new Timer();
        TimerTask countDown = new TimerTask() {
            @Override
            public void run() {
                if(timeleft > 0){
                    timeleft--;
                }
                timeBar.setProgress(timeleft/120);
            }
        };
        timer.schedule(countDown, 1000, 3000);
    }

    // Actions with button for choosing level
    public void pressLevel1 (ActionEvent chooseLevel1) {
        levelVBox.setVisible(false);
        gridPane.setVisible(true);
        level.setText("1");
        startCountDown();
    }
    public void pressLevel2 (ActionEvent chooseLevel2) {
        levelVBox.setVisible(false);
        gridPane.setVisible(true);
        level.setText("2");
        startCountDown();
    }
    public void pressLevel3 (ActionEvent chooseLevel3) {
        levelVBox.setVisible(false);
        gridPane.setVisible(true);
        level.setText("3");
        startCountDown();
    }

    // Action with speaker icon
    public void speakerPress (MouseEvent click) {
        if (click.getButton() == MouseButton.PRIMARY) {
            if (speakerCheck == true) {
                playSound.setVisible(false);
                muteSound.setVisible(true);
                speakerCheck = false;
                mediaView.getMediaPlayer().pause();
            } else if (speakerCheck == false) {
                playSound.setVisible(true);
                muteSound.setVisible(false);
                speakerCheck = true;
                mediaView.getMediaPlayer().play();
            }
        }
    }

    // Background music
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Media music = new Media(new File("Background.mp3").toURI().toString());
        MediaPlayer player = new MediaPlayer(music);
        mediaView.setMediaPlayer(player);
        player.setAutoPlay(true);
        player.setCycleCount(MediaPlayer.INDEFINITE);
    }

    // Action with play button
    public void pressPlay (ActionEvent play) {
        playButton.setVisible(false);
        quitButton.setVisible(false);
        levelVBox.setVisible(true);
        scoreLabel.setText(Integer.toString(score));
        randomCards();
    }
    // Action with quit button
    public void pressQuit (ActionEvent quit) {
        quitButton.setVisible(false);
        playButton.setVisible(false);
        levelVBox.setVisible(false);
        gridPane.setVisible(false);
    }
    // Action with cards
    public boolean cardMatch(ImageView v1, ImageView v2) {
        if (v1.getImage().getUrl().compareTo(v2.getImage().getUrl()) == 0) {
            return true;
        } else return false;
    }

    public boolean cardCheckFlipped(ImageView iv) {
        if (iv.getImage().getUrl().compareTo(Cup.getUrl()) == 0) {       // Compare the URL to see if they are the same image
            return false;
        }
        return true;
    }

    ImageView flippedCard1, flippedCard2;
    public void pressCard(MouseEvent cardClick) {
        // Intialize the ArrayList
        ArrayList<String> ivList = new ArrayList();
        ivList.add(iv1.getId());
        ivList.add(iv2.getId());
        ivList.add(iv3.getId());
        ivList.add(iv4.getId());
        ivList.add(iv5.getId());
        ivList.add(iv6.getId());
        ivList.add(iv7.getId());
        ivList.add(iv8.getId());
        ivList.add(iv9.getId());
        ivList.add(iv10.getId());
        ivList.add(iv11.getId());
        ivList.add(iv12.getId());
        ivList.add(iv13.getId());
        ivList.add(iv14.getId());
        ivList.add(iv15.getId());
        ivList.add(iv16.getId());
        ivList.add(iv17.getId());
        ivList.add(iv18.getId());
        ivList.add(iv19.getId());
        ivList.add(iv20.getId());

        ImageView target = (ImageView) cardClick.getTarget();

        TimerTask task =  new TimerTask() {
            @Override
            public void run() {
                if (cardMatch(flippedCard1, flippedCard2)) {
                    flippedCard2.setDisable(true);
                    flippedCard1.setDisable(true);
                    flippedCard = 0;
                    pairFound++;
                    System.out.println(pairFound);
                } else if (!cardMatch(flippedCard1, flippedCard2)) {
                    target.setImage(Cup);
                    flippedCard--;
                }
            }
        };
        Timer timer = new Timer();
        int delay;
        switch (Integer.parseInt(level.getText())) {
            case 1:
                delay = 3000;
                break;
            case 2:
                delay = 2000;
                break;
            case 3:
                delay = 1000;
                break;
            default:
                delay = 0;
                break;
        }

        if (cardClick.getButton() == MouseButton.PRIMARY) {
            if (cardCheckFlipped(target) == false) {
                if (flippedCard >= 2) {
                }
                else if (flippedCard < 2) {
                    int temp = ivList.indexOf(target.getId());
                    target.setImage(selectedPhotos[temp]);
                    flippedCard++;
                    if(flippedCard == 1) {
                        flippedCard1 = (ImageView)cardClick.getSource();
                    } else if (flippedCard == 2) {
                        flippedCard2 = (ImageView)cardClick.getSource();
                    }
                    timer.schedule(task, delay);
                }
            }
            //(not allow to manually flip back down anymore)
            // else if (cardCheckFlipped(target) == true) {
//                target.setImage(Cup);
//                flippedCard--;
//                if (cardMatch(target, flippedCard1)) {
//                    flippedCard1 = flippedCard2;
//                }
//            }
        }
    }
}

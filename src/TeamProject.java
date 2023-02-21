import processing.core.PApplet;
import processing.core.PImage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
public class TeamProject extends PApplet {
    PImage titleScreen,title,quit,quitHover,play,playHover,menuScreen,home,
            homeHover,mainMenu,breathingAnimation,userSelect;
    final int HOME=0;
    final int USER=1;
    final int MENU=2;
    final int ANIMATION=3;
    final int STARTUPQUESTION=4;
    final int QCLOUD=0;
    final int QCLOUDRESULT=1;
    float circleX=(float) width*4;
    float circleY= (float) (height*3);
    float breathingCycle = 7000;
    float circleCount = 20;
    float circleRadius = 60;
    float circleSpacing = circleRadius / 9;
    float circleRotation = -1;
    float breathingFactor = 0.0F;
    int questionPicker = (int) random(3);
    int gameMode=USER;
    int qPickScreen=QCLOUD;
    int userScore = 0;
    static int user1ExpStart;
    static int user2ExpStart;
    static int user3ExpStart;
    int user1Exp = user1ExpStart;
    int user2Exp = user2ExpStart;
    int user3Exp = user3ExpStart;
    static File userData=new File("userData.txt");
    boolean initialStartup = true;
    String textboxInput = "";



    public void settings() {
        size(800, 800);
        //surface.setResizable(true);
    }

    public void draw() {
        switch(gameMode) {
            case(HOME):
                homeScreen();
                break;
            case(USER):
                userSelect();
                break;
            case(MENU):
                menuScreen();
                break;
            case(ANIMATION):
                breathingAnimation();
                break;
            case(STARTUPQUESTION):
                switch(qPickScreen){
                    case(QCLOUD):
                        startupQuestionScreen();
                        break;
                    case(QCLOUDRESULT):
                        resultScreen();
                        break;
                }
                break;
        }
    }

    public static void main(String[] args) {
        String[] processingArgs = {"TeamProject"};
        TeamProject teamProject = new TeamProject();
        PApplet.runSketch(processingArgs, teamProject);
        try{
            user1ExpStart= Integer.parseInt(Files.readAllLines(Paths.get("userData.txt")).get(2));
            user2ExpStart= Integer.parseInt(Files.readAllLines(Paths.get("userData.txt")).get(4));
            user3ExpStart= Integer.parseInt(Files.readAllLines(Paths.get("userData.txt")).get(6));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        long lines=0;
//        try{
//            LineNumberReader lnr = new LineNumberReader(new FileReader(userData));
//            while (lnr.readLine()!=null){
//                lines=lnr.getLineNumber();
//                if (lines==2){
//                    user1Exp=
//                }
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    public void breathingAnimation() {
        background(0);
        float breathingPhase = millis() % breathingCycle;
        if (breathingPhase < breathingCycle / 2) {
            breathingFactor = (float) (breathingPhase / (breathingCycle / 2.0));
        } else {
            breathingFactor = (float) (2 - breathingPhase / (breathingCycle / 2.0));
        }
        for (int i = 0; i < circleCount; i++) {
            fill(173, 216, 230, 98);
            stroke(255, 100);
            float x = circleX + (circleRadius + circleSpacing * breathingFactor) * sin(radians(circleRotation + 360 / circleCount * i));
            float y = circleY + (circleRadius + circleSpacing * breathingFactor) * cos(radians(circleRotation + 360 / circleCount * i));
            ellipse(x, y, circleRadius * 2 * breathingFactor, circleRadius * 2 * breathingFactor);
        }

        fill(173, 216, 230,180);
        textSize(15);
        textAlign(CENTER, BOTTOM);
        text("Be still, and bring your attention to your breathing", width/2, height-30);

        fill(255,290);
        textSize(15);
        textAlign(CENTER, BOTTOM);
        text("Inhale, and exhale all the negativity", width/2, height-60);
        home=loadImage("HomeConcept.png");
        homeHover=loadImage("HomeHoverConcept.png");
        if(mouseX>=width-170&&mouseX<=width-25&&mouseY>=10&&mouseY<=160){
            image(homeHover,width-170,10,150,150);
        } else {
            image(home,width-170,10,150,150);
        }
        if(mousePressed==true&&mouseX>=width-170&&mouseX<=width-25&&mouseY>=10&&mouseY<=160){
            gameMode=HOME;
        }
    }
    public void homeScreen(){
        //surface.setSize(800,800);
        titleScreen=loadImage("TitleScreen.png");
        imageMode(CORNER);
        image(titleScreen,0,0,width,height);
        title=loadImage("TitleConcept.png");
        image(title,135,63,500,125);
        quit=loadImage("QuitConcept.png");
        quitHover=loadImage("QuitHoverConcept.png");
        play=loadImage("PlayConcept.png");
        image(play,width-160,height-180);
        playHover=loadImage("PlayHover.png");
        if (mousePressed==true&&mouseX>=50&&mouseX<=250&&mouseY>=height-210&&mouseY<=height-10) {
            exit();
        }
        if (mouseX>=50&&mouseX<=250&&mouseY>=height-210&&mouseY<=height-10){
            image(quitHover,50,height-210);
        }else{
            image(quit,50,height-210);
        }
        if (mousePressed==true&&mouseX>=width-160&&mouseX<=width&&mouseY>=height-150&&mouseY<=height-30){
            gameMode=MENU;
        }
        if(mouseX>=width-160&&mouseX<=width&&mouseY>=height-150&&mouseY<=height-30){
            image(playHover,width-160,height-180);
        }
    }
    public void userSelect() {
        menuScreen=loadImage("MenuScreenConcept.png");
        image(menuScreen,0,0,width,height);
        userSelect=loadImage("UserSelect.png");
        image(userSelect,170,8);

    }
    public void menuScreen() {
        menuScreen=loadImage("MenuScreenConcept.png");
        image(menuScreen,0,0,width,height);
        home=loadImage("HomeConcept.png");
        homeHover=loadImage("HomeHoverConcept.png");
        if(mouseX>=20&&mouseX<=150&&mouseY>=10&&mouseY<=140){
            image(homeHover,5,1,150,150);
        }else{
            image(home,5,1,150,150);
        }
        if(mousePressed==true&&mouseX>=20&&mouseX<=150&&mouseY>=10&&mouseY<=140){
            gameMode=HOME;
        }
        play=loadImage("PlayConcept.png");
        playHover=loadImage("PlayHover.png");
        mainMenu=loadImage("MainMenuConcept.png");
        image(mainMenu,170,8);
        breathingAnimation=loadImage("breathingAnimationText.png");
        if(mouseX>=5&&mouseX<=105&&mouseY>=190&&mouseY<=290){
            image(playHover,5,190,100,100);
        }else{
            image(play,5,190,100,100);
        }
        if(mousePressed==true&&mouseX>=5&&mouseX<=105&&mouseY>=190&&mouseY<=290){
            gameMode=ANIMATION;
        }
        image(breathingAnimation,140,198);

    }
    public void questionCloud() {
        noStroke();
        fill(255);
        int cloudWidth = 375;
        int cloudHeight = 275;
        ellipse(width/2, height/2 - 100, cloudWidth, cloudHeight);
        ellipse(width/2 + 150, height/2, cloudWidth, cloudHeight);
        ellipse(width/2 - 150, height/2, cloudWidth, cloudHeight);
        ellipse(width/2 + 75, height/2 + 100, cloudWidth, cloudHeight);
        ellipse(width/2 - 75, height/2 + 100, cloudWidth, cloudHeight);
        textSize(28);
        textAlign(CENTER, CENTER);
        fill(0);
        if (questionPicker == 0) {
            text("How many glasses of water have you drank today?", width/2, height/2 - 50);
        } else if (questionPicker==1) {
            text("How many hours did you sleep last night?", width/2, height/2 - 50);
        } else if (questionPicker == 2) {
            text("How many minutes did you spend exercising today?", width/2, height/2 - 50);
        }
    }
    public void questionTextBox() {
        fill(100);
        rect(width/2-200, height/2 + 25, 400, 50);
        fill(255);
        textSize(28);
        text(textboxInput, width/2, height/2 + 50);
        fill(100);
        rect(width/2 + 200, height/2 + 25, 80, 50);
        fill(255);
        textSize(20);
        text("Submit", width/2 + 225, height/2 + 50);
    }
    public void keyPressed() {
        if (keyCode == BACKSPACE) {
            textboxInput = textboxInput.substring(0, max(0, textboxInput.length() - 1));
        } else if (keyCode != SHIFT && keyCode != CONTROL && keyCode != ALT) {
            textboxInput = textboxInput + key;
        }
    }
    public void mousePressed() {
        if (mouseX > width/2 + 200 && mouseX < width/2 + 280 && mouseY > height/2 + 25 && mouseY < height/2 + 100) {
            userScore = Integer.parseInt(textboxInput);
            qPickScreen=QCLOUDRESULT;
        }
    }
    public void startupQuestionScreen(){
        questionCloud();
        questionTextBox();
    }
    public void resultScreen() {
        fill(255);
        rect(0, 0, width, height);
        fill(0);
        textSize(32);
        textAlign(CENTER, CENTER);
        text("Congratulations! ", width/2, height/2 - 75);
        textSize(28);
        text("You have earned " + userScore * 10 + " points", width/2, height/2 + 25);
    }
}

import processing.core.PApplet;
import processing.core.PImage;

import javax.swing.*;

public class TeamProject extends PApplet {
    PImage titleScreen,title,quit,quitHover,play,playHover,menuScreen,home,
            homeHover,mainMenu,breathingAnimation,userSelect,next,nextHover;
    final int HOME=0;
    final int PLANT=1;
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
    int gameMode=HOME;
    int qPickScreen=QCLOUD;
    int userScore = 0;
    int plantCounter = 0;
    PImage[] plantAnimation=new PImage[7];
    String textboxInput = "";
    boolean showResult = false;
    boolean canAnswer = false;


    public void settings() {
        size(800, 800);
        for(int i=0; i<plantAnimation.length;i++){
            plantAnimation[i]=loadImage("plant"+(i+1)+".png");
        }
    }

    public void draw() {
        switch(gameMode) {
            case(HOME):
                homeScreen();
                break;
            case(PLANT):
                plantAnimation();
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
            gameMode=PLANT;
        }
        if(mouseX>=width-160&&mouseX<=width&&mouseY>=height-150&&mouseY<=height-30){
            image(playHover,width-160,height-180);
        }
    }
    public void plantAnimation(){
        next=loadImage("NextConcept.png");
        nextHover=loadImage("NextHoverConcept.png");

        imageMode(CORNER);
//        image(plantAnimation[plantCounter/90%plantAnimation.length],0,0,width,height);
        if(plantCounter<180 && (plantCounter/20)%2==0){
            image(plantAnimation[0],0,0,width,height);
        } else if (plantCounter<180 && (plantCounter/20)%2!=0){
            image(plantAnimation[1],0,0,width,height);
        } else if (plantCounter>=180&&plantCounter<360&&(plantCounter/20)%2==0){
            image(plantAnimation[2],0,0,width,height);
        } else if (plantCounter>=180&&plantCounter<360&&(plantCounter/20)%2!=0){
            image(plantAnimation[3],0,0,width,height);
        } else if (plantCounter>=360&&plantCounter<400){
            image(plantAnimation[4],0,0,width,height);
        } else if (plantCounter>=400&&(plantCounter/20)%2==0){
            image(plantAnimation[5],0,0,width,height);
        } else if (plantCounter>=400&&(plantCounter/20)%2!=0){
            image(plantAnimation[6],0,0,width,height);
        }
        if (plantCounter>580&&mouseX>=700&&mouseX<=800&&mouseY>=715&&mouseY<=800){
            image(nextHover,700,715,100,100);
        } else if (plantCounter>580){
            image(next,700,715,100,100);
        }
        if (plantCounter>580&&mouseX>=700&&mouseX<=800&&mouseY>=715&&mouseY<=800&&mousePressed==true){
            gameMode=STARTUPQUESTION;
        }
        plantCounter++;
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
        textSize(24);
        text("Click to answer", width/2, height/2 + 20);
        textSize(20);
        text("Session score: "+userScore+".",120,760);
    }
//    public void questionTextBox() {
//        fill(100);
//        rect(width/2-200, height/2 + 25, 400, 50);
//        fill(255);
//        textSize(28);
//        text(textboxInput, width/2, height/2 + 50);
//        fill(100);
//        rect(width/2 + 200, height/2 + 25, 80, 50);
//        fill(255);
//        textSize(20);
//        text("Submit", width/2 + 225, height/2 + 50);
//    }
//    public void keyPressed() {
//        if (keyCode == BACKSPACE) {
//            textboxInput = textboxInput.substring(0, max(0, textboxInput.length() - 1));
//        } else if (keyCode != SHIFT && keyCode != CONTROL && keyCode != ALT) {
//            textboxInput = textboxInput + key;
//        }
//    }
    public void mousePressed() {
        if (mouseX > width/2 - 200 && mouseX < width/2 + 200 && mouseY > height/2 - 100 && mouseY < height/2 + 100 && canAnswer) {
            textboxInput = JOptionPane.showInputDialog(null, "Enter your answer: ");
            if (textboxInput != null && !textboxInput.equals("")) {
                try {
                    userScore = Integer.parseInt(textboxInput);
                    userScore = userScore*10;
                    canAnswer = false;
                    qPickScreen=QCLOUDRESULT;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    public void startupQuestionScreen(){
        canAnswer=true;
        rectMode(CORNER);
        fill(66,135,245);
        rect(0,0,width,height);
        questionCloud();
        if (mousePressed){
            mousePressed();
        }
    }
    public void resultScreen() {
        background(74, 176, 101);
        textSize(32);
        textAlign(CENTER, CENTER);
        fill(0);
        text("Congratulations!", width/2, height/2 - 75);
        textSize(28);
        text("You have earned " + userScore + " points", width/2, height/2 + 25);
        textSize(20);
        text("Session score: "+userScore+".",120,760);
    }
}

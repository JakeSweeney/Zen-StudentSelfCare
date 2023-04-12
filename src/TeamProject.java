import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.*;
import javax.swing.*;

public class TeamProject extends PApplet {
    PImage titleScreen,title,quit,quitHover,play,playHover,menuScreen,home,
            homeHover,mainMenu,breathingAnimation,next,nextHover,websiteHub,
            musicBorder,musicNotes,pause,pauseHover,add,addHover,minus,minusHover,calmingMusic;
    final int HOME=0;
    final int PLANT=1;
    final int MENU=2;
    final int ANIMATION=3;
    final int STARTUPQUESTION=4;
    final int WEBSITEHUB = 5;
    final int MUSIC = 6;
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
    float noteX = 0;
    float noteY = 0;
    float volume = (float) 0.2;
    int questionPicker = (int) random(3);
    int gameMode=HOME;
    int qPickScreen=QCLOUD;
    int userScore = 0;
    int plantCounter = 0;
    int buffer = 0;
    SoundFile music;
    PImage[] plantAnimation=new PImage[7];
    PImage[] images = new PImage[9];
    String textboxInput = "";
    String[] urls = {
            "https://www.mmu.ac.uk/library", "https://my.mmu.ac.uk/campusm/home#menu", "https://studenthub.mmu.ac.uk/",
            "https://www.myfitnesspal.com/", "https://github.com/", "https://www.youtube.com/",
            "https://www.linkedin.com", "https://www.instagram.com/", "https://www.stackoverflow.com"
    };
    boolean showResult = false;
    boolean canAnswer = false;
    boolean startup = true;
    boolean ping = true;

    public void settings() {
        size(800, 800);
        for(int i=0; i<plantAnimation.length;i++){
            plantAnimation[i]=loadImage("plant"+(i+1)+".png");
        }
        for (int i = 0; i < images.length; i++) {
            images[i] = loadImage("Image" + (i + 1) + ".jpg");
        }
        music=new SoundFile(this,"calm.mp3");
        menuScreen=loadImage("MenuScreenConcept.png");
        home=loadImage("HomeConcept.png");
        homeHover=loadImage("HomeHoverConcept.png");
        titleScreen=loadImage("TitleScreen.png");
        title=loadImage("TitleConcept.png");
        quit=loadImage("QuitConcept.png");
        quitHover=loadImage("QuitHoverConcept.png");
        play=loadImage("PlayConcept.png");
        playHover=loadImage("PlayHover.png");
        next=loadImage("NextConcept.png");
        nextHover=loadImage("NextHoverConcept.png");
        mainMenu=loadImage("MainMenuConcept.png");
        breathingAnimation=loadImage("breathingAnimationText.png");
        websiteHub=loadImage("websiteHubText.png");
        musicBorder=loadImage("musicBorder.png");
        musicNotes=loadImage("musicNotes.png");
        pause=loadImage("pause.png");
        pauseHover=loadImage("pauseHover.png");
        add=loadImage("add.png");
        addHover=loadImage("addHover.png");
        minus=loadImage("minus.png");
        minusHover=(loadImage("minusHover.png"));
        calmingMusic=loadImage("calmingMusic.png");
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
            case(WEBSITEHUB):
                websiteHub();
                break;
            case(MUSIC):
                music();
                break;
        }
    }

    public static void main(String[] args) {
        String[] processingArgs = {"TeamProject"};
        TeamProject teamProject = new TeamProject();
        PApplet.runSketch(processingArgs, teamProject);
    }

    public void homeScreen(){

        imageMode(CORNER);
        image(titleScreen,0,0,width,height);
        image(title,135,63,500,125);
        image(play,width-160,height-180);
        if (mousePressed==true&&mouseX>=50&&mouseX<=250&&mouseY>=height-210&&mouseY<=height-10) {
            exit();
        }
        if (mouseX>=50&&mouseX<=250&&mouseY>=height-210&&mouseY<=height-10){
            image(quitHover,50,height-210);
        }else{
            image(quit,50,height-210);
        }
        if (mousePressed==true&&mouseX>=width-160&&mouseX<=width&&mouseY>=height-150&&mouseY<=height-30&&startup==true){
            gameMode=PLANT;
        } else if (mousePressed==true&&mouseX>=width-160&&mouseX<=width&&mouseY>=height-150&&mouseY<=height-30&&startup==false){
            gameMode=MENU;
        }
        if(mouseX>=width-160&&mouseX<=width&&mouseY>=height-150&&mouseY<=height-30){
            image(playHover,width-160,height-180);
        }
    }
    public void plantAnimation(){
        startup=false;
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
        if(mouseX>=700&&mouseX<=800&&mouseY>=700&&mouseY<=800){
            image(nextHover,700,700,100,100);
        } else {
            image(next,700,700,100,100);
        }
        if(mousePressed==true&&mouseX>=700&&mouseX<=800&&mouseY>=700&&mouseY<=800){
            gameMode=MENU;
        }
    }
    public void menuScreen() {
        image(menuScreen,0,0,width,height);

        if(mouseX>=20&&mouseX<=150&&mouseY>=10&&mouseY<=140){
            image(homeHover,5,1,150,150);
        }else{
            image(home,5,1,150,150);
        }
        if(mousePressed==true&&mouseX>=20&&mouseX<=150&&mouseY>=10&&mouseY<=140){
            gameMode=HOME;
        }

        image(mainMenu,170,8);

        if(mouseX>=38&&mouseX<=85&&mouseY>=217&&mouseY<=257){
            image(playHover,5,190,100,100);
        }else{
            image(play,5,190,100,100);
        }
        if(mousePressed&&mouseX>=38&&mouseX<=85&&mouseY>=217&&mouseY<=257){
            gameMode=ANIMATION;
        }
        image(breathingAnimation,140,198);
        if(mouseX>=38&&mouseX<=85&&mouseY>=344&&mouseY<=384){
            image(playHover,5,317,100,100);
        }else{
            image(play,5,317,100,100);
        }
        if(mousePressed&&mouseX>38&&mouseX<=85&&mouseY>=344&&mouseY<=384) {
            gameMode=WEBSITEHUB;
        }
        image(websiteHub,140,325);
        if(mouseX>=38&&mouseX<=85&&mouseY>=478&&mouseY<=517){
            image(playHover,5,450,100,100);
        } else {
            image(play,5,450,100,100);
        }
        if(mouseX>=38&&mouseX<=85&&mouseY>=478&&mouseY<=517&&mousePressed){
            gameMode=MUSIC;
        }
        image(calmingMusic,140,450);
        text("Session score: "+userScore+".",700,50);
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
        if(mouseX>=width-170&&mouseX<=width-25&&mouseY>=10&&mouseY<=160){
            image(homeHover,width-170,10,150,150);
        } else {
            image(home,width-170,10,150,150);
        }
        if(mousePressed==true&&mouseX>=width-170&&mouseX<=width-25&&mouseY>=10&&mouseY<=160){
            //userScore=userScore+20;
            gameMode=HOME;
        }
    }
    public void websiteHub(){
        buffer++;
        background(66,135,245);

        int tileSize=(width / 3)-30;
        int tileX=mouseX/(width/3);
        int tileY=mouseY/(width/3);
        int index=tileX+tileY*3;
        for (int i = 0; i < images.length; i++) {
            int x = 40+ ((i % 3) * tileSize);
            int y = 30+((i / 3) * tileSize);
            if (mouseX > x && mouseX < x + tileSize && mouseY > y && mouseY < y + tileSize) {
                tint(0, 153, 104, 126);
            } else {
                noTint();}
            image(images[i], x, y, tileSize, tileSize);
        }

        if(mouseX>=40&&mouseX<275&&mouseY>=30&&mouseY<265&&mousePressed&&buffer>10){
            link(urls[0]);
            userScore=userScore+20;
        } else if(mouseX>=275&&mouseX<512&&mouseY>=30&&mouseY<265&&mousePressed&&buffer>10){
            link(urls[1]);
            userScore=userScore+20;
        } else if(mouseX>=512&&mouseX<748&&mouseY>=30&&mouseY<265&&mousePressed&&buffer>10){
            link(urls[2]);
            userScore=userScore+20;
        }else if(mouseX>=40&&mouseX<275&&mouseY>=265&&mouseY<501&&mousePressed&&buffer>10){
            link(urls[3]);
            userScore=userScore+20;
        }else if(mouseX>=275&&mouseX<512&&mouseY>=265&&mouseY<501&&mousePressed&&buffer>10){
            link(urls[4]);
            userScore=userScore+20;
        }else if(mouseX>=512&&mouseX<748&&mouseY>=265&&mouseY<501&&mousePressed&&buffer>10){
            link(urls[5]);
            userScore=userScore+20;
        }else if(mouseX>=40&&mouseX<275&&mouseY>=501&&mouseY<738&&mousePressed&&buffer>10){
            link(urls[6]);
            userScore=userScore+20;
        }else if(mouseX>=275&&mouseX<512&&mouseY>=501&&mouseY<738&&mousePressed&&buffer>10){
            link(urls[7]);
            userScore=userScore+20;
        }else if(mouseX>=512&&mouseX<748&&mouseY>=501&&mouseY<738&&mousePressed&&buffer>10){
            link(urls[8]);
            userScore=userScore+20;
        }
//        if (index<urls.length&&mousePressed==true){
//            link(urls[index]);
//        }
        imageMode(CORNER);
        if(mouseX>=700&&mouseX<=800&&mouseY>=730&&mouseY<=800){
            noTint();
            image(homeHover,700,730,80,80);
        } else {
            noTint();
            image(home, 700, 730, 80, 80);
        }
        if(mouseX>=700&&mouseX<=800&&mouseY>=730&&mouseY<=800&&mousePressed){
            buffer=0;
            gameMode=HOME;
        }
    }
    public void music(){
        image(menuScreen,0,0,width,height);
        image(musicBorder,0,0,width,height);
        image(musicNotes,noteX,noteY,width,height);
        if(noteY<=0){
            ping=true;
        } else if(noteY>=90){
            ping=false;
        }
        if(ping){
            noteY++;
        } else{
            noteY--;
        }
        if(mouseX>=350&&mouseX<415&&mouseY>=712&&mouseY<=770){
            image(playHover,300,670,150,150);
        } else {
            image(play, 300, 670, 150, 150);
        }
        if(mouseX>=350&&mouseX<415&&mouseY>=712&&mouseY<=770&&mousePressed){
            music.play(1, volume);
            userScore=userScore+50;
        }
//        if(mouseX>=420&&mouseX<500&&mouseY>=712&&mouseY<=770){
//            image(pauseHover,420,700,80,80);
//        } else {
//            image(pause,420,700,80,80);
//        }
//        if(mouseX>=420&&mouseX<500&&mouseY>=712&&mouseY<=770&&mousePressed){
//            music.pause();
//        }
        if(mouseX>=5&&mouseX<105&&mouseY>=670&&mouseY<800){
            image(addHover,5,670,100,100);
        } else {
            image(add,5,670,100,100);
        }
        if(mouseX>=5&&mouseX<105&&mouseY>=670&&mouseY<800&&mousePressed){
            volume=(float)(volume+0.1);
            music.amp(volume);
        }
//        if(mouseX>=150&&mouseX<250&&mouseY>=670&&mouseY<800){
//            image(minusHover,150,670,100,100);
//        } else {
//            image(minus,150,670,100,100);
//        }
//        if(mouseX>=150&&mouseX<250&&mouseY>=670&&mouseY<800&&mousePressed){
//            volume=(float)(volume-0.01);
//            System.out.println(volume);
//            music.amp(volume);
//        }
        if(mouseX>=700&&mouseX<=800&&mouseY>=700&&mouseY<=780){
            image(homeHover,700,700,100,100);
        } else {
            image(home, 700, 700, 100, 100);
        }
        if(mouseX>=700&&mouseX<=800&&mouseY>=700&&mouseY<=780&&mousePressed){
            music.stop();
            gameMode=HOME;
        }

    }
}

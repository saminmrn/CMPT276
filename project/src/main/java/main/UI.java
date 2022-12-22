package main;
//import object images
import objects.obj_heart;
import objects.obj_banana;
import objects.obj_apple;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI 
{
    public final BufferedImage heartImage,bananaImage,appleImage;
    public final DecimalFormat dFormat = new DecimalFormat("#0.00");
    private Graphics2D g2;
    private Simulator sim;
    private Font PublicPixel, arial_40;
    private boolean messageOn = false;
    private String message = "";
    private int messageCounter = 0;
    private double playTime = 0;
    private int commandNum = 0;
    private int tester;

    public UI(Simulator sim) {
        this.sim = sim;
        try {
            InputStream is = getClass().getResourceAsStream("/font/PublicPixel-z84yD.ttf");
            PublicPixel = Font.createFont(Font.TRUETYPE_FONT, is);
        }
        catch(FontFormatException e) {
            //TODO
            e.printStackTrace();
        }
        catch (IOException e){
            //TODO
            e.printStackTrace();
        }
        arial_40 = new Font("Arial",Font.PLAIN,40);

        obj_heart heart = new obj_heart();
        heartImage = heart.image;
        obj_banana banana = new obj_banana(0, 0);
        bananaImage = banana.image;
        obj_apple apple = new obj_apple(0, 0);
        appleImage = apple.image;
    }

    // Setters
    public void setPlayTime(int val) { this.playTime = val; }
    public void setCommandNum(int val) { this.commandNum = val; }

    // Getters
    public double getPlayTime() { return this.playTime; }
    public int getCommandNum() { return this.commandNum; }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2=g2;
        g2.setColor(Color.white);
        g2.setFont(arial_40);
        g2.drawString("x" + sim.player.get_lives(), 90 , 40);
        g2.setFont(PublicPixel);
        g2.setColor(Color.white);
        g2.drawImage(heartImage,40, 6, sim.tileSize-5, sim.tileSize-5, null);


        //title state
        if(sim.gameState == sim.titleState) {
            drawTitle();
        }

        //Add play state things in here
        if(sim.gameState == sim.playGameState){
            // Timer
            playTime +=(double)1/60;
            g2.setFont(arial_40);
            //Score
            g2.drawString("Score:"+ sim.player.get_score(), 250, 40);
            //Time
            g2.drawString("Time:"+ dFormat.format(playTime), sim.tileSize*11,40);
            //Current Level
            g2.drawString("Current Level:"+ (sim.currentMap+1), sim.tileSize*18, 40);
        }
        //Pause state
        if(sim.gameState == sim.pauseState){
            drawPauseScreen();
        }
        //Game Over state
        if(sim.gameState==sim.gameOverSate)
        {
            drawGameOverScreen();
        }
        //Win State
        if(sim.gameState==sim.gameWinSate)
        {
            drawGameWinScreen();
        }
        //Transition State
        if(sim.gameState == sim.transitionState) {
            drawTransitionScreen();
        }

        //message function
        if(messageOn == true) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, sim.tileSize/2,sim.tileSize*5);
            //timer for message time
            messageCounter++;

            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
    public void drawTitle() {
        g2.setColor(new Color(178,102,255));
        g2.fillRect(0,0,sim.ScreenWidth+sim.tileSize,sim.ScreenHeight+sim.tileSize);
        //Title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,55F));
        int x = getXforCenteredText("Minion Laboratory")+192;
        int y = sim.tileSize*3;
        //Shadow
        g2.setColor(Color.gray);
        g2.drawString("Minion Laboratory",x+5,y+5);
        //Title
        g2.setColor(Color.yellow);
        g2.drawString("Minion Laboratory",x,y);

        //title image
        x = sim.ScreenWidth/2 - 60;
        y += sim.tileSize*2;
        g2.drawImage(sim.player.down1,x,y,sim.tileSize*2,sim.tileSize*2,null);
        g2.drawImage(bananaImage,40, 60, sim.tileSize*2, sim.tileSize*2, null);
        g2.drawImage(bananaImage,sim.tileSize*23, 60, sim.tileSize*2, sim.tileSize*2, null);
        g2.drawImage(bananaImage,x + sim.tileSize* -5, y, sim.tileSize*2, sim.tileSize*2, null);
        g2.drawImage(appleImage,x + sim.tileSize*5, y, sim.tileSize*2, sim.tileSize*2, null);
        g2.drawImage(appleImage,x + sim.tileSize*-9, y,  sim.tileSize*2, sim.tileSize*2, null);
        g2.drawImage(bananaImage,x + sim.tileSize*9, y,  sim.tileSize*2, sim.tileSize*2, null);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        x = getXforCenteredText("START GAME")+194;
        y += sim.tileSize*5;
        g2.setColor(Color.gray);
        g2.drawString("START GAME",x+5,y+5);
        g2.setColor(new Color(108, 242, 5));
        g2.drawString("START GAME",x,y);
        if(commandNum == 0){
            g2.setColor(Color.yellow);
            g2.drawString(">",x-sim.tileSize,y);
        }


        x = getXforCenteredText("QUIT")+194;
        y += sim.tileSize;
        g2.setColor(Color.gray);
        g2.drawString("QUIT",x+5,y+5);
        g2.setColor(new Color(108, 242, 5));
        g2.drawString("QUIT",x,y);
        if(commandNum == 1){
            g2.setColor(Color.yellow);
            g2.drawString(">",x-sim.tileSize,y);
        }
        this.tester = 2;

    }
    public void drawPauseScreen() {
        //Paused Message

        String text = "PAUSED";
        int x= getXforCenteredText2(text);
        int y=sim.ScreenHeight/2;
        g2.setColor(new Color(50,50,50,150));
        g2.fillRect(x-6*sim.tileSize,y-80,sim.tileSize*12, sim.tileSize*3);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        g2.drawString(text, x-5*sim.tileSize, y);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        g2.drawString("Time:"+ dFormat.format(playTime),sim.tileSize*9,408);
        g2.setFont(arial_40);
        g2.drawString("Score:"+ sim.player.get_score(), 250, 40);
        g2.drawString("Current Level:"+ (sim.currentMap+1), sim.tileSize*18, 40);
        this.tester = 3;
    }
    public void drawGameOverScreen()
    {
        g2.setColor(new Color(200,0,0,150));
        g2.fillRect(0,0 , sim.ScreenWidth, sim.ScreenHeight);
        String text= "GAME OVER";
        int x= getXforCenteredText(text);
        int y=sim.get_tileSize()*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100f));
        g2.setColor(Color.white);
        g2.drawString(text, x-5*sim.tileSize, y-4);

        //retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text= "Retry";
        x=getXforCenteredText2(text);
        y+=sim.get_tileSize()*7;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.setColor(Color.yellow);
            g2.drawString(">",x-sim.tileSize,y);
        }

        text= "Menu";
        x=getXforCenteredText2(text);
        y+= sim.tileSize*1;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.setColor(Color.yellow);
            g2.drawString(">",x-sim.tileSize,y);
        }
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(80f));
        y-= sim.tileSize*3;
        g2.drawString("Time:"+ dFormat.format(playTime),x - sim.tileSize*6,y);
        x -=sim.tileSize*4;
        y-= sim.tileSize*2;
        g2.drawString("Score:"+ sim.player.get_score(),x,y);
        this.tester = 4;

    }
    public void drawGameWinScreen()
    {
        String text= "YOU WON!";
        g2.setColor(new Color(0,100,0,150));
        g2.fillRect(0,0 , sim.ScreenWidth, sim.ScreenHeight);
        int x= getXforCenteredText(text)- sim.tileSize*2;
        int y=sim.get_tileSize()*3;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100f));
        //shadow
        g2.setColor(Color.pink);
        g2.drawString(text, x-(2*sim.tileSize)-5, y-4);
        //text
        g2.setColor(Color.white);
        g2.drawString(text, x-2*sim.tileSize, y+1);

        g2.setFont(g2.getFont().deriveFont(60f));
        text="Score: ";
        x=getXforCenteredText2(text)-20 ;
        y+= sim.tileSize*2;;
        g2.drawString(text+sim.player.get_score(),x,y);

        //End Image
        x = sim.ScreenWidth/2 - 60;
        y += sim.tileSize;
        g2.drawImage(sim.player.down1,x,y,sim.tileSize*2,sim.tileSize*2,null);

        //Time
        g2.setFont(g2.getFont().deriveFont(50f));
        x = sim.ScreenWidth/2 - 130 ;
        y += sim.tileSize*3;
        g2.drawString("Time:"+ dFormat.format(playTime),x-2*sim.tileSize,y);
        //Options

        text= "Play Again?";
        x=getXforCenteredText2(text);
        y+=sim.get_tileSize()*2+30;
        g2.setColor(Color.white);
        g2.drawString(text, x+30, y);
        if(commandNum == 0){
            g2.setColor(Color.yellow);
            g2.drawString(">",x-sim.tileSize,y);
        }

        text= "Quit";
        x=getXforCenteredText2(text);
        y+= 55;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.setColor(Color.yellow);
            g2.drawString(">",x-sim.tileSize,y);
        }
        this.tester = 5;
    }
    public void drawTransitionScreen(){
        String text = "Next Level!";
        int x= getXforCenteredText2(text);
        int y=sim.ScreenHeight/2;
        g2.setColor(new Color(50,50,50,150));
        g2.fillRect(x-10*sim.tileSize,y- sim.tileSize*4,sim.tileSize*20, sim.tileSize*8);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        g2.drawString(text, x-9*sim.tileSize, y-2*sim.tileSize);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        text= "Move onto level ";
        x=getXforCenteredText2(text);
        if(sim.currentMap < 2) {
            g2.drawString(text + (sim.currentMap+1) +"?", x, y);
        }
        if(sim.currentMap == 2){
            text= "Move onto the final level?";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
            g2.drawString(text, x- sim.tileSize-15, y);
        }
        text= "Continue";
        x=getXforCenteredText2(text);
        y+=sim.get_tileSize()*2;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.setColor(Color.yellow);
            g2.drawString(">",x-sim.tileSize,y);
        }

        text= "Quit";
        x=getXforCenteredText2(text);
        y+= 55;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.setColor(Color.yellow);
            g2.drawString(">",x-sim.tileSize,y);
        }

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawString("Score:"+ sim.player.get_score(), 250, 40);
        g2.drawString("Time:"+ dFormat.format(playTime), sim.tileSize*11,40);

    }
    public int getXforCenteredText(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x=sim.get_screen_width()/3- length/2;
        return x;
    }
    public int getXforCenteredText2(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x=sim.get_screen_width()/2- length/2;
        return x;
    }

}

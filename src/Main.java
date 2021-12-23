import acm.graphics.*;
import acm.program.GraphicsProgram;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioInputStream;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Main extends GraphicsProgram{
    public static final int HEIGHT = 650;
    public static final int WIDTH = 1000;
    protected Player player;
    protected Level level;
    public GImage background, clicktoStart,try_again, exit_now, youWin;
    public GImage loading_sc, title, backButton, bg, menu_girl, playButton;
    public GImage howToPlay, about, howToButton, aboutButton, skeriBg;
    public GImage[] black_key = new GImage[7];
    public MusicPlayer mp = new MusicPlayer();
    public boolean backPresent = false;
    public boolean startGame = false;
    private boolean playerMove=false;
    public GRect creepy;
    private GLabel check;
    public int key_count = 0;
    public int level_count = 1;
    public boolean win = false;
    public void returnToRun(){
        menu();
        while(!startGame)
        {
            waitForClick();
        }
        //clicktoStart.sendForward();
        remove(clicktoStart);
        //level.init0(player);
        //add(check,100,100);
        playGame(level_count);
    }
    public void run(){
         addMouseListeners();
         add(level,0,0);
        // playMusic("assets/end.wav");
        mp.setFile("assets/ostwav.wav");
        mp.play();
        check=new GLabel("");
         check.setFont("monospace-bold-50");
         returnToRun();
     }
    public void playGame(int i){
        loading_sc = new GImage("file:assets/loading_sc.png");
        loading_sc.setSize(WIDTH,HEIGHT);
        add(loading_sc,0,0);
        loading_sc.sendToFront();
        level.loadLevel(i,this,player);
        remove(loading_sc);
        add(creepy);
        addKeys(); // black keys display
        updateKeys(key_count);
        boolean loop = true;
        boolean collide_end = false;
        boolean collide_enemy = false;
        while(loop){
            if(player.collideWithType(level,"END",true)){
                 level_count++;
                 collide_end = true;
                 loop=false;
             }
             if(player.collideWithType(level,"ENEMY",true)){
                 loop=false;
                 collide_enemy = true;
             }
             if(player.collideWithType(level,"KEY",true)){
                 mp.setFile("assets/key_get.wav");
                 mp.play();
                 player.addKey();
                 black_key[key_count].setImage("file:assets/key.png");
                 black_key[key_count].setSize(50,50);
                 key_count++;
             }
             if(key_count == 7 && collide_end) {loop=false; win=true;}
             if(playerMove) {
                 player.move(level);
             }
             level.updateEnemy(player);
             level.updatePlayer(player);
             pause(20);
         }
         if(!win && collide_enemy)  {
             key_count = 0;
             player.keys = 0;
             win = false;
             level_count = 1;
             skeriBg = new GImage("file:assets/gameover.png");
             skeriBg.setSize(WIDTH,HEIGHT);
             add(skeriBg,0,0);
             //playMusic("assets/death.wav");
             mp.setFile("assets/death.wav");
             mp.play();
             waitForClick();
             remove(skeriBg);
             newGame();}
         else if (win)
         {
             youWin = new GImage("assets/win.png");
             youWin.setSize(WIDTH,HEIGHT);
             add(youWin,0,0);
             mp.setFile("assets/win.wav");
             mp.play();
             pause(10000);
             exit();
         }
         else if (collide_end){
             newGame();
         }
    }
    public void newGame() {
        updateBGinLVLUP();
        level.reset();
        level = new Level();
        add(level,0,0);
        playGame(level_count);
    }
    public void updateKeys(int key_count) {
        for(int i=0; i < key_count; i++)
        {
            black_key[i].setImage("file:assets/key.png");
            black_key[i].setSize(50,50);
        }
    }

//EVENTS
    public void mouseMoved(MouseEvent e) {
        GObject hover = getElementAt(e.getX(),e.getY());
        if(hover == menu_girl && menu_girl !=null) {
            menu_girl.setImage("file:assets/load_sc_girl_hover.png");
            menu_girl.setSize(600,600);
        }
        else {
            if(menu_girl != null)
            {
                menu_girl.setImage("file:assets/load_sc_girl.png");
                menu_girl.setSize(600,600);
            }
        }
        if(hover == playButton && playButton != null) {
            playButton.setImage("file:assets/play_hover.png");
            playButton.setSize(400,100);
        }
        else {
            if(playButton != null)
            {
                playButton.setImage("file:assets/play_disp.png");
                playButton.setSize(400,100);
            }
        }
        if(hover == howToButton && howToButton != null) {
            howToButton.setImage("file:assets/how_hover.png");
            howToButton.setSize(400,100);
        }

        else {
            if(howToButton != null)
            {
                howToButton.setImage("file:assets/how_disp.png");
                howToButton.setSize(400,100);
            }
        }
        if(hover == aboutButton && aboutButton != null) {
            aboutButton.setImage("file:assets/about_hover.png");
            aboutButton.setSize(400,100);
        }
        else {
            if(aboutButton != null)
            {
                aboutButton.setImage("file:assets/about_disp.png");
                aboutButton.setSize(400,100);
            }
        }

        if(hover == backButton && backPresent && backButton!= null) {
            backButton.setImage("file:assets/back_hover.png");
            backButton.setSize(200,100);
        }
        else if (backPresent && backButton != null) {
            backButton.setImage("file:assets/back_disp.png");
            backButton.setSize(200,100);
        }

        if(hover == try_again && try_again != null && !win)
        {
            try_again.setImage("file:assets/load_tryagain_hover.png");
            try_again.setSize(400,100);
        }

        else if (!win && try_again != null){
            try_again.setImage("file:assets/load_tryagain.png");
            try_again.setSize(400,100);
        }

        if(hover == exit_now && exit_now !=null && !win)
        {
            exit_now.setImage("file:assets/load_exit_hover.png");
            exit_now.setSize(400,100);
        }
        else if(!win && exit_now != null)
        {
            exit_now.setImage("file:assets/load_exit.png");
            exit_now.setSize(400,100);
        }
    }
    public void mouseClicked(MouseEvent e) {
        GObject click = getElementAt(e.getX(),e.getY());
        if(click == playButton && playButton != null) {
            playButton.setImage("file:assets/play_click.png");
            playButton.setSize(400,100);
            startGame = true;
            loadGame();
            clicktoStart = new GImage("file:assets/waitForClick.png");
            clicktoStart.setSize(WIDTH,HEIGHT);
            add(clicktoStart,0,0);
        }
        else {
            playButton.setImage("file:assets/play_disp.png");
            playButton.setSize(400,100);
        }
        if(click == howToButton && howToButton != null) {
            loadGame();
            howToButton.setImage("file:assets/how_click.png");
            howToButton.setSize(400,100);
            showHowToPlay();
        }
        else {
            howToButton.setImage("file:assets/how_disp.png");
            howToButton.setSize(400,100);
        }
        if(click == aboutButton && aboutButton != null) {
            loadGame();
            aboutButton.setImage("file:assets/about_click.png");
            aboutButton.setSize(200,100);
            showAbout();
        }
        else {
            aboutButton.setImage("file:assets/about_disp.png");
            aboutButton.setSize(400,100);
        }
        if(click == backButton && backButton != null) {
            if(howToPlay != null) remove(howToPlay);
            if(about != null) remove(about);
            if(backButton != null) remove(backButton);
            menu();
        }
        if(click == try_again && try_again != null)
        {
            if(skeriBg!=null) remove(skeriBg);
            if(try_again!= null) remove(try_again);
            if(exit_now!=null) remove(exit_now);
            key_count = 0;
            System.out.println("putangina");
            startGame = false;
            win = false;
            level_count=1;
            key_count=0;
            newGame();
            //add(playButton);
        }
        else if(click == exit_now && exit_now != null)
        {
            System.exit(1);
        }
    }
    public void keyPressed(KeyEvent keyEvent){//movement
         if(keyEvent.getKeyCode()==KeyEvent.VK_UP){
             player.faceNORTH();
             playerMove=true;
         }
        else if(keyEvent.getKeyCode()==KeyEvent.VK_DOWN){
            player.faceSOUTH();
            playerMove=true;
        }
        else if(keyEvent.getKeyCode()==KeyEvent.VK_LEFT){
            player.faceWEST();
            playerMove=true;
        }
         else if(keyEvent.getKeyCode()==KeyEvent.VK_RIGHT){
             player.faceEAST();
             playerMove=true;
         }
    }
    public void keyReleased(KeyEvent keyEvent){
        if(keyEvent.getKeyCode()==KeyEvent.VK_UP||keyEvent.getKeyCode()==KeyEvent.VK_LEFT||keyEvent.getKeyCode()==KeyEvent.VK_DOWN||keyEvent.getKeyCode()==KeyEvent.VK_RIGHT){
            playerMove=false;
        }
    }
    public void init(){
        level = new Level();
        player = new Player(10,10);
        creepy = new GRect(WIDTH,HEIGHT);
        creepy.setFilled(true);
        background = new GImage("assets/background.png");
        background.setSize(WIDTH,HEIGHT);
        setSize(WIDTH,HEIGHT);
        add(background,0,0);
        setBackground(Color.BLACK);
        creepy.setFillColor(new Color(0,0,0,100));
    }
    public void menu() {
         backPresent = false;
         bg = new GImage("file:assets/load_sc_bg1.png");
         bg.setSize(WIDTH,HEIGHT);
         add(bg,0,0);
         title = new GImage("file:assets/title.png");
         title.setSize(800,150);
         add(title,100,0);
         playButton = new GImage("file:assets/play_disp.png");
         playButton.setSize(400,100);
         add(playButton,550,150);
         howToButton = new GImage("file:assets/how_disp.png");
         howToButton.setSize(400,100);
         add(howToButton,550,300);
         aboutButton = new GImage("file:assets/about_disp.png");
         aboutButton.setSize(400,100);
         add(aboutButton,550,450);
         menu_girl = new GImage("file:assets/load_sc_girl.png");
         menu_girl.setSize(600,600);
         add(menu_girl,0,100);
//         menuObject.add(bg);
//         menuObject.add(menu_girl);
//         menuObject.add(playButton);
//         menuObject.add(howToButton);
//         menuObject.add(aboutButton);
//         menuObject.add(title);
    }
    public void loadGame() {
         if(title!= null) remove(title);
         if(bg!=null) remove(bg);
         if(menu_girl != null) remove(menu_girl);
         if(playButton != null) remove(playButton);
         if(howToButton != null) remove(howToButton);
         if(howToButton != null) remove(aboutButton);
     }
    public void showHowToPlay() {
         howToPlay = new GImage("file:assets/howtoplay_disp.png");
         howToPlay.setSize(WIDTH,HEIGHT);
         add(howToPlay,0,0);
         backButton = new GImage("file:assets/back_disp.png");
         backButton.setSize(200,100);
         add(backButton,0,0);
         backPresent = true;
     }
    public void showAbout() {
         about = new GImage("file:assets/about_show.png");
         about.setSize(WIDTH,HEIGHT);
         add(about,0,0);
         backButton = new GImage("file:assets/back_disp.png");
         backButton.setSize(200,100);
         add(backButton,0,0);
         backPresent = true;
     }
    public void addKeys() {
         int x = 650;
        for(int i = 0; i<7; i++)
        {
            black_key[i] = new GImage("file:assets/black_key.png");
            black_key[i].setSize(50,50);
            add(black_key[i],x,0);
            x+=50;
        }
    }
    public void updateBGinLVLUP() {
        creepy = new GRect(WIDTH,HEIGHT);
        creepy.setFilled(true);
        background = new GImage("assets/background.png");
        background.setSize(WIDTH,HEIGHT);
        setSize(WIDTH,HEIGHT);
        add(background,0,0);
        setBackground(Color.BLACK);
        creepy.setFillColor(new Color(0,0,0,100));
    }
    public static void main(String[] args) {
        new Main().start(args);
    }
}
import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class GameScen extends JPanel  {
    private Car pleyer;
    private CustomRectangel[] obstacles;
    private int Score=0;
//

    public boolean Winer=true;
          public boolean play=true;
    public GameScen(int x, int y, int WHIDTH , int HIGHET) {

        this.setBounds(x, y, WHIDTH, HIGHET);
        //first screen  +button start +image +color gray in background

      /*  JButton start = new JButton("start game");
        start.setBounds(WHIDTH/2,0,100,50);
        add(start);*/



        //player
        this.pleyer = new Car();
        this.mainGameLoop();

        //obstacles  +image+bug

        Random random = new Random();
        int lower = -800002;
        int maxer = 1;
        this.obstacles = new CustomRectangel[4000];
        for (int i = 0; i < this.obstacles.length; i++) {
            CustomRectangel obstacle = null;
            do {
                int e = limit(random.nextInt(WHIDTH));

                obstacle = new CustomRectangel(e, random.nextInt(maxer - lower) + lower, randomImage());//image
            } while (obstacle.CheckCollision(this.pleyer.getFront()));
            this.obstacles[i] = obstacle;
        }

        //game over +new screen

    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //main Color
        g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), getHeight());
        //Score

        g.setColor(Color.BLACK);
        g.setFont(new Font("serif",Font.BOLD,20));
        g.drawString("Score :"+Score,250,30);
        //
        //restart
        if(play==false) {
            if (Winer == false) {
                g.setColor(Color.BLACK);
                g.setFont(new Font("serif", Font.BOLD, 50));
                g.drawString("Game over :", 148, 200);
                g.drawString(" enter to the restart :", 50, 250);
                g.drawString("your Score :" + Score, 110, 300);
                JButton restart = new JButton("Start");
                restart.setBounds(220, 350, 150, 100);
                this.add(restart);
            }
        }
        //Winer
        if(Score>100000) {
            Score = 100000;
        }
        if(Score==100000){
            play =false;
            Winer=true;
            g.setColor(Color.black);
            g.setFont(new Font("serif", Font.BOLD, 50));
            g.drawString("you win:",200,270);
            g.drawString(" enter to the restart :", 80, 325);
            JButton restart = new JButton("Start");
            restart.setBounds(220, 350, 150, 100);
            this.add(restart);
        }



        //playe

        this.pleyer.paintComponent(g);
        //obstacle
        for (int i = 0; i < this.obstacles.length; i++) {
            String c =obstacles[i].getPhoto();
            this.obstacles[i].paint(g, c);
        }

    }

    //calcul limit
    public int limit(int a) {

        if (a < 50) {
            return 50;
        } else if (a > 480) {
            return 480;
        } else {
            return a;
        }
    }
     //MoveDownObstackes
    public void moveDownObstacles() {
        if(play==true){
        for (int i = 0; i < obstacles.length; i++) {
            try {
                obstacles[i].MoveDownObstacles();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        }
    }

    //game over//collision + bug + if obstacle collision in another obstacle
   public void gameOver() {
        for (int i = 0; i < obstacles.length; i++) {
            //  obstacles[i].MoveDownObstacles();
            if (obstacles[i].CheckCollision(this.pleyer.getFront())) {
                play = false;
                Winer=false;
                //    JLabel GameOver = new JLabel("Game Over!!!");
                //   GameOver.setBounds(120, 270, 150, 100);
                //  this.add(GameOver);

                   }
                 }
                }

                public void mainGameLoop () {

                    new Thread(() -> {
                        PleyerMovment PleyerMovment = new PleyerMovment(this.pleyer);
                        this.setFocusable(true);
                        this.requestFocus();
                        this.addKeyListener(PleyerMovment);
                        while (true) {
                            try {
                                try {
                                    moveDownObstacles();
                                } catch (NullPointerException e) {
                                    e.printStackTrace();
                                }
                                if(play==true)

                                Score++;
                                gameOver();
                               repaint();
                                Thread.sleep(15);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                public String randomImage () {
                    String[] imageURL = new String[4];
                    imageURL[0] = "src/alpina final.png";
                    imageURL[1] = "src/mercedese f1.png";
                    imageURL[2] = "src/red.png";
                    imageURL[3] = "src/red bull f1.png";

                    Random random = new Random();

                    String a = imageURL[random.nextInt(4)];
                    return a;
                }

            }

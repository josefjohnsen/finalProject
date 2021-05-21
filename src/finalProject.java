import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class finalProject extends PApplet {

//Snake Final
//Josef Johnsen
//TODO: create grid
int x, y, dir, speed, c, x2, y2, len, score, aniX, aniY, aniDir, rectX, highScore;
boolean gameOver, start, win;
Box[] boxes;
StartBox[] startBoxes;
Food[] foods;
Timer timer1;



public void setup() {
  

  boxes = new Box[625];
  foods = new Food[1];
  startBoxes = new StartBox[7];
  x = 120;
  score = 0;
  highScore = 0;
  y=60;
  aniX = 500;
  aniY = 20;
  rectX = 270;
  x2 = 120;
  y2 = 260;
  dir = 3;
  aniDir = 3;
  speed = 100; //         speed
  len = 1;
  c = 205;
  gameOver = false;
  start = true;
  win = false;
  timer1 = new Timer(speed);
  timer1.start();
  int index = 0;
  for (int bx = 0; bx<25; bx++) {
    for (int by = 0; by<25; by++) {
      boxes[index++] =new Box(bx, by,index);
    }
  }

  for (int i = 0; i<7; i++) {
    startBoxes[i] =new StartBox(480-(i*20), 20);
  }

  foods[0] = new Food(25, 20);
  foods[0].full = true;
}

public void draw() {
  if (start == false && win == false) {
    background(220);
    fill(255);
    rect(20, 20, 500, 500);
    grid();
    snake();
    move();
    outOfBounds();
    food();
    fill(0);
    text("Score = "+score, 200, 15);
    if (score>highScore) {
      highScore = score;
    }
    text("High Score = "+highScore, 340, 15);
    if(score==625){
      win = true;
    }
  } else {
    startScreen();
  }
}

public void snake() {
  fill(0, c, 0);
  rect(x, y, 20, 20);
}

public void keyPressed() {
  if ((key == 'w' || (key == CODED && keyCode == UP)) && dir != 3) {
    dir = 1;
  }
  if ((key == 'a' || (key == CODED && keyCode == LEFT)) && dir != 4) {
    dir = 2;
  }
  if ((key == 's' || (key == CODED && keyCode == DOWN)) && dir != 1) {
    dir = 3;
  }
  if ((key == 'd' || (key == CODED && keyCode == RIGHT)) && dir != 2) {
    dir = 4;
  }
  if (key == ENTER && start == true) {
    startGame();
  }
}

public void move() {
  if (timer1.isFinished()) {

    for (int i = 0; i < 625; i++) {
      if (x==boxes[i].x&&y==boxes[i].y&&boxes[i].wf>0) {
        gameOver = true;
      }
      if (x == boxes[i].x&&y==boxes[i].y && foods[0].full == true) {
        boxes[i].wf = score;
      }


      if (boxes[i].wf >0) {
        boxes[i].full = true;
        boxes[i].wf --;
      } else {
        boxes[i].full = false;
      }
    }

    if (dir == 1) {
      y -= 20;
    }
    if (dir == 2) {
      x -= 20;
    }
    if (dir == 3) {
      y += 20;
    }
    if (dir == 4) {
      x += 20;
    }
    timer1.start();
  }
}

public void outOfBounds() {
  if (x>510 || x<20 || y>510 || y<20) {
    gameOver = true;
  }
  if (gameOver == true) {
    dir = 0;
    start = true;
  }
}

public void food() {
  fill(255, 0, 0);
  rect(x2, y2, 20, 20);
  if (x == x2 && y == y2) {
    len += 1;
    x2 = (PApplet.parseInt(random(25)));
    y2 = (PApplet.parseInt(random(25)));
    while (boxes[(x2*25)+y2].full == true){
    x2 = (PApplet.parseInt(random(25)));
    y2 = (PApplet.parseInt(random(25)));
    }
    x2 *= 20;
    x2 += 20;
    y2 *= 20;
    y2 += 20;
    foods[0].x = (PApplet.parseInt(random(25)));
    foods[0].y = (PApplet.parseInt(random(25)));
    foods[0].x*=20;
    foods[0].y*=20;
    foods[0].x+=20;
    foods[0].y+=20;
    score++;
  }
}

public void grid() {

  for (int i = 0; i < 625; i++) {

    boxes[i].display();
  }
  textSize(14);
}

public void startScreen() {
  background(0);
  textAlign(CENTER);
  textSize(34);
  fill(0, 100, 0);
  text("Snake Game!", width/2, (height/2)-150);
  textSize(24);
  fill(0, 0, 150);
  text("By: Josef Johnsen", width/2, (height/2)-100);
  textSize(16);
  fill(255);
  text("Chose a speed below and then press 'ENTER' to start!", width/2, (height/2)-50);
  textSize(18);
  text("Speed = "+speed, width/2, (height/2)-20);
  textSize(18);
  text("Last Score = "+score, width/2, (height/2)+50);  
  textSize(18);
  text("High Score = "+highScore, width/2, (height/2)+80);
  textSize(16);
  fill(20);
  stroke(0, 255, 0);
  rect(100, 380, 335, 80);
  fill(255);
  text("To move, use 'WASD' or the arrow keys.", width/2, (height/2)+130);
  text("Collect apples to grow, but remember:", width/2, (height/2)+155);
  text("Dont run into the wall or yourself!", width/2, (height/2)+180);
  stroke(255);
  line(70, 280, 470, 280);
  stroke(0);
  speedControl();
  startAnimation();
}

public void startAnimation() {
  fill(0, 255, 0);

  if (timer1.isFinished()) {
    for (int i = 0; i < 7; i++) {
      startBoxes[i].aniMove();
    } 
    timer1.start();
  }
  for (int i = 0; i < 7; i++) {
    startBoxes[i].display();
  }
}

public void speedControl() {
  if (mouseY>265&&mouseY<295&&mousePressed==true&&mouseX>70&&mouseX<470) {
    rectX = mouseX;
    speed = ((mouseX-70)/4)*2;
    timer1.totalTime = speed;
  }
  fill(255);
  rect(rectX, 275, 5, 10);
}

public void startGame() {
  start = false;
  gameOver = false;
  x = 120;
  score = 0;
  len = 1;
  y=60;
  dir = 3;
  for (int i = 0; i < 625; i++) {
    boxes[i].full = false;
    boxes[i].wf = 0;
  }
  
}
class Box {
  boolean full;
  int x, y, r,g,b, num,wf;

  Box(int x,int y,int num) {
    full = false;

    
    x*=20;
    x+=20;
    y*=20;
    y+=20;
    this.x = x;
    this.y = y;
    this.num = num;
  }


public void display() {
  if (full==true) {
    r = 0;
    g=255;
    b=0;
  } else {
    r = 255;
    g = 255;
    b = 255;

  }
  fill(r, g, b);
  
  rect(x,y,20, 20);
}
}
class Food {
  boolean full;
  int x, y, r,g,b, num,wf;

  Food(int x,int y) {
    full = false;

    
    x*=20;
    x+=20;
    y*=20;
    y+=20;
    this.x = x;
    this.y = y;
  }


public void display() {
    r = 0;
    g=255;
    b=0;
  fill(r, g, b);
  
  rect(x,y,20, 20);
}
}
class StartBox {
  boolean full;
  int x, y,dir;

  StartBox(int x,int y) {
    this.x = x;
    this.y = y;
    dir = 4;
  }


public void display() {
  fill(0,255,0);
  rect(x,y,20,20);


}

public void aniMove(){
    if(x==500 && y==20){
      dir = 3;
    }
      if(x==500 && y==500){
      dir = 2;
    }
      if(x==20 && y==500){
      dir = 1;
    }
      if(x==20 && y==20){
      dir = 4;
    }
    if (dir == 1){
      y -= 20;
   }
   if (dir == 4){
      x += 20;
   }
     if (dir == 3){
      y += 20;
   }
       if (dir == 2){
      x -= 20;
   }
}


}
// Example 10-5: Object-oriented timer
//By Danial Shiffman

class Timer {

  int savedTime; // When Timer started
  int totalTime; // How long Timer should last

  Timer(int tempTotalTime) {
    totalTime = tempTotalTime;
  }

  // Starting the timer
  public void start() {
    // When the timer starts it stores the current time in milliseconds.
    savedTime = millis();
  }

  // The function isFinished() returns true if 5,000 ms have passed. 
  // The work of the timer is farmed out to this method.
  public boolean isFinished() { 
    // Check how much time has passed
    int passedTime = millis()- savedTime;
    if (passedTime > totalTime) {
      return true;
    } else {
      return false;
    }
  }
}
  public void settings() {  size(540, 540); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "finalProject" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

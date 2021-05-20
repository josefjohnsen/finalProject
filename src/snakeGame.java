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

public class snakeGame extends PApplet {

//Snake Final
//Josef Johnsen
//TODO: create grid
int x, y, dir, speed, c, x2, y2, len, score;
boolean gameOver;
Box[] boxes;
Food[] foods;
Timer timer1;



public void setup() {
  

  boxes = new Box[625];
  foods = new Food[1];
  x = 120;
  y=60;
  x2 = 260;
  y2 = 260;
  dir = 3;
  speed = 100; //         speed
  len = 1;
  c = 205;
  gameOver = false;
  timer1 = new Timer(speed);
  timer1.start();
  int index = 0;
  for (int bx = 0; bx<25; bx++) {
    for (int by = 0; by<25; by++) {
      boxes[index++] =new Box(bx, by);
    }
  }
  foods[0] = new Food(25,20);
  foods[0].full = true;
}

public void draw() {

  background(220);
  fill(255);
  rect(20, 20, 500, 500);
  grid();
  for (int i = 20; i<520; i+=20) {
    //   line(i,20,i,520);
    // line(20,i,520,i);
  }
  snake();
  move();
  outOfBounds();
  food();
}

public void snake() {
  fill(0, c, 0);
  rect(x, y, 20, 20);
}

public void keyPressed() {
  if (key == 'w' && dir != 3) {
    dir = 1;
  }
  if (key == 'a' && dir != 4) {
    dir = 2;
  }
  if (key == 's' && dir != 1) {
    dir = 3;
  }
  if (key == 'd' && dir != 2) {
    dir = 4;
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
  }
}

public void food() {
  fill(255, 0, 0);
  rect(x2, y2, 20, 20);
  if (x == x2 && y == y2) {
    len += 1;
    x2 = (PApplet.parseInt(random(25)));
    foods[0].x = (PApplet.parseInt(random(25)));
    foods[0].y = (PApplet.parseInt(random(25)));
    foods[0].x*=20;
    foods[0].y*=20;
    foods[0].x+=20;
    foods[0].y+=20;
    x2 *= 20;
    x2 += 20;
    y2 = (PApplet.parseInt(random(25)));
    y2 *= 20;
    y2 += 20;
    score++;
  }
}

public void grid() {

  for (int i = 0; i < 625; i++) {

    boxes[i].display();
  }
  println(score);
}
class Box {
  boolean full;
  int x, y, r,g,b, num,wf;

  Box(int x,int y) {
    full = false;

    
    x*=20;
    x+=20;
    y*=20;
    y+=20;
    this.x = x;
    this.y = y;
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
    String[] appletArgs = new String[] { "snakeGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

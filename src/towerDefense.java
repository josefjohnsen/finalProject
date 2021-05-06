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

public class towerDefense extends PApplet {

//Tower Defense
//Josef Johnsen

//TODO
//create lazer from tower to ship
int c;
ArrayList<Ship> ships;
ArrayList<Tower> towers;
ArrayList<Timer> timers;
ArrayList<Timer> timers2;
Timer timer1, tier2;



public void setup() {
  
  c = 255;
  ships = new ArrayList();
  towers = new ArrayList();
  timers = new ArrayList();
  timers2 = new ArrayList();

}

public void draw() {
  background(c);
  detectShip();
   objectDisplay();
  lazerShooting();
 

}



public void detectShip() {


  for (int i = 0; i < ships.size(); i++) {
    Ship ship = ships.get(i);



        for (int l = 0; l <timers.size(); l++) {
          Timer timer = timers.get(l);


          for (int m = 0; m <timers2.size(); m++) {
            Timer timer2 = timers2.get(m);


    for (int k = 0; k < towers.size(); k++) {
      Tower tower = towers.get(k);

      if (dist(ship.xcenter, ship.ycenter, tower.xcenter, tower.ycenter)<tower.radius/2) {



            if (timer.isFinished()) { 
              tower.lazerDisplay = true;
              timer.start();
              timer2.start();
             ship.done = false;
            }
                          if (tower.lazerDisplay == true){
                tower.lazer(ship.xcenter,ship.ycenter);
              }
            if (timer2.isFinished() && ship.done == false) {
              tower.lazerDisplay = false;
                ship.health -= 10;
                  ship.done = true;
              } 

          }
        }
      }
    }
  }
}

public void lazerShooting() {
  //lazer ship collition  
  for (int i = 0; i < ships.size(); i++) {
    Ship ship = ships.get(i);


    if (ship.health<1) {
      ships.remove(ship);
    }
    if (ship.y<-3) {
      ships.remove(ship);
    }
  }
}


public void mousePressed() {
  ships.add(new Ship(mouseX, mouseY));
}

public void keyPressed() {
  towers.add(new Tower(mouseX, mouseY));
  timers.add(new Timer(100));
  timers2.add(new Timer(60));
}

public void objectDisplay() {
  for (int i = 0; i < ships.size(); i++) {
    Ship ship = ships.get(i);
    ship.display();
  }

  for (int k = 0; k < towers.size(); k++) {
    Tower tower = towers.get(k);
    tower.display();
    
  }
}
//////Tower Defense
////Josef Johnsen

////TODO
////create lazer from tower to ship
//int c;
//ArrayList<Lazer> lazers;
//ArrayList<Ship> ships;
//ArrayList<Tower> towers;
//ArrayList<Timer> timers;
////ArrayList<Timer> timers2;
//Timer timer1, tier2;



//void setup() {
//  size(500, 500);
//  c = 255;
//  ships = new ArrayList();
//  lazers = new ArrayList();
//  towers = new ArrayList();
//  timers = new ArrayList();
////  timers2 = new ArrayList();
//  timer1 = new Timer(100);
//  timer1.start();

//}

//void draw() {
//  background(c);
//  detectShip();
//  lazerShooting();
//  objectDisplay();

// // println(lazers.size());
//}



//void detectShip() {


//  for (int i = 0; i < ships.size(); i++) {
//    Ship ship = ships.get(i);

//    for (int k = 0; k < towers.size(); k++) {
//      Tower tower = towers.get(k);

//      for (int l = 0; l <timers.size(); l++) {
//        Timer timer = timers.get(l);
//          //for (int m = 0; l <timers2.size(); m++) {
//        //Timer timer2 = timers2.get(m);
//        if (dist(ship.xcenter, ship.ycenter, tower.xcenter, tower.ycenter)<tower.radius/2) {
    
//          timer1.start();
//           if (timer.isFinished()) {
//               ship.health -= 10;  
//               println(ship.health);
//            lazers.add(new Lazer(tower.xcenter, tower.ycenter, ship.xcenter, ship.ycenter));
//            timer.start();
//            //timer2.start();
//          //}
//        }
//        }
//      }
//    }
//  }
//}

//void lazerShooting() {
//  //lazer ship collition  
//  for (int i = 0; i < ships.size(); i++) {
//    Ship ship = ships.get(i);


//    for (int j = 0; j< lazers.size(); j++) {
//      Lazer lazer = lazers.get(j);
//              //  for (int m = 0; m <timers2.size(); m++) {
//              //Timer timer2 = timers2.get(m);
      
//      //if (timer2.isFinished()) {
//      //  ship.health -= lazer.damage;
//      //  lazers.remove(lazer);
//     // }
//      if (ship.health<1) {
//        ships.remove(ship);
//      }
//      if (ship.y<-3) {
//        ships.remove(ship);
//      }
//    }
//    }
//    //}
//  }


//void mousePressed() {
//  ships.add(new Ship(mouseX, mouseY));
//}

//void keyPressed() {
//  towers.add(new Tower(mouseX, mouseY));
//  timers.add(new Timer(100));
////  timers2.add(new Timer(20));
//}

//void objectDisplay() {
//  for (int i = 0; i < ships.size(); i++) {
//    Ship ship = ships.get(i);
//    ship.display();
//  }
//  for (int j = 0; j< lazers.size(); j++) {
//    Lazer lazer = lazers.get(j);
//    lazer.display();
//  }
//  for (int k = 0; k < towers.size(); k++) {
//    Tower tower = towers.get(k);
//    tower.display();
//  }
//}
//class Lazer {
//  //member variable
//  int x, y, x2,y2, speed, rad, damage;
//  color c1,c2,c3;

//  //constructor
//  Lazer(int x, int y,int x2, int y2) {
//    this.x=x;
//    this.y=y;
//    this.x2 = x2;
//    this.y2 = y2;
//    speed = 10;
//    rad = 4;
//    damage = 10;
//    c1 = int(random(175,255));
//    c2 = int(random(175,255));
//    c3 = int(random(175,255));
//  }

//  //member methods

 

//  boolean reachedTop() {
//    if (y<-3) {
//      return true;
//    } else {
//      return false;
//    }
//  }

//  void display() {
//    stroke(c1,c2,c3);
//    strokeWeight(6);
//    line(x,y,x2,y2);
//    strokeWeight(1);
//    stroke(0);
//  }
//}
class Ship {
  //member variables
  int type,health,x,y,xcenter,ycenter,speed;
  boolean done = true;
  
  //constructor
  Ship(int x, int y){
    this.x = x;
    this.y = y;
    speed = 10;
    xcenter = (x+(x+15))/2;
    ycenter = (y+(y+20))/2;
    health = 50;
  }
  
  //methods
  

  
  
   public void display(){
     fill(0,0,255);
     rect(x,y,15,20);
     y-=speed;//2 = speed
    xcenter = (x+(x+15))/2;
    ycenter = (y+(y+20))/2;
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
class Tower{
  //member variables
  int type,health,x,y,xlength,ywidth,xcenter,ycenter,radius;
  int c1,c2,c3;
  boolean lazerDisplay;
  
  //constructor
  Tower(int x, int y){
    this.x = x;
    this.y = y;
    radius = 100;
    health = 10;
    xlength = 10;
    ywidth = 10;
    xcenter = (x+(x+xlength))/2;
    ycenter = (y+(y+ywidth))/2;
    c1 = PApplet.parseInt(random(175,255));
    c2 = PApplet.parseInt(random(175,255));
    c3 = PApplet.parseInt(random(175,255));
  }
  
  //methods
  public void display(){
    fill(175,50);
    circle(xcenter,ycenter,radius);
    fill(0);
    rect(x,y,xlength,ywidth);
  }
  
  public void lazer(int x2, int y2){
    stroke(c1,c2,c3);
    strokeWeight(10);
    line(x,y,x2,y2);
    strokeWeight(1);
    stroke(0);
  }
  
}
  public void settings() {  size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "towerDefense" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

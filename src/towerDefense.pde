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



void setup() {
  size(500, 500);
  c = 255;
  ships = new ArrayList();
  towers = new ArrayList();
  timers = new ArrayList();
  timers2 = new ArrayList();

}

void draw() {
  background(c);
  detectShip();
   objectDisplay();
  lazerShooting();
 

}



void detectShip() {


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

void lazerShooting() {
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


void mousePressed() {
  ships.add(new Ship(mouseX, mouseY));
}

void keyPressed() {
  towers.add(new Tower(mouseX, mouseY));
  timers.add(new Timer(100));
  timers2.add(new Timer(60));
}

void objectDisplay() {
  for (int i = 0; i < ships.size(); i++) {
    Ship ship = ships.get(i);
    ship.display();
  }

  for (int k = 0; k < towers.size(); k++) {
    Tower tower = towers.get(k);
    tower.display();
    
  }
}

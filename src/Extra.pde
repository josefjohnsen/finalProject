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

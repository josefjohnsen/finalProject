//Snake Final
//Josef Johnsen
//TODO: create grid
int x, y, dir, speed, c, x2, y2, len, score, aniX, aniY, aniDir, rectX, highScore;
boolean gameOver, start, win;
Box[] boxes;
StartBox[] startBoxes;
Food[] foods;
Timer timer1;



void setup() {
  size(540, 540);

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

void draw() {
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

void snake() {
  fill(0, c, 0);
  rect(x, y, 20, 20);
}

void keyPressed() {
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

void move() {
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

void outOfBounds() {
  if (x>510 || x<20 || y>510 || y<20) {
    gameOver = true;
  }
  if (gameOver == true) {
    dir = 0;
    start = true;
  }
}

void food() {
  fill(255, 0, 0);
  rect(x2, y2, 20, 20);
  if (x == x2 && y == y2) {
    len += 1;
    x2 = (int(random(25)));
    y2 = (int(random(25)));
    while (boxes[(x2*25)+y2].full == true){
    x2 = (int(random(25)));
    y2 = (int(random(25)));
    }
    x2 *= 20;
    x2 += 20;
    y2 *= 20;
    y2 += 20;
    foods[0].x = (int(random(25)));
    foods[0].y = (int(random(25)));
    foods[0].x*=20;
    foods[0].y*=20;
    foods[0].x+=20;
    foods[0].y+=20;
    score++;
  }
}

void grid() {

  for (int i = 0; i < 625; i++) {

    boxes[i].display();
  }
  textSize(14);
}

void startScreen() {
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

void startAnimation() {
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

void speedControl() {
  if (mouseY>265&&mouseY<295&&mousePressed==true&&mouseX>70&&mouseX<470) {
    rectX = mouseX;
    speed = ((mouseX-70)/4)*2;
    timer1.totalTime = speed;
  }
  fill(255);
  rect(rectX, 275, 5, 10);
}

void startGame() {
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

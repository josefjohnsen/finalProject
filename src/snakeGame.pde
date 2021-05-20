//Snake Final
//Josef Johnsen
//TODO: create grid
int x, y, dir, speed, c, x2, y2, len, score;
boolean gameOver;
Box[] boxes;
Food[] foods;
Timer timer1;



void setup() {
  size(540, 540);

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

void draw() {

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

void snake() {
  fill(0, c, 0);
  rect(x, y, 20, 20);
}

void keyPressed() {
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
  }
}

void food() {
  fill(255, 0, 0);
  rect(x2, y2, 20, 20);
  if (x == x2 && y == y2) {
    len += 1;
    x2 = (int(random(25)));
    foods[0].x = (int(random(25)));
    foods[0].y = (int(random(25)));
    foods[0].x*=20;
    foods[0].y*=20;
    foods[0].x+=20;
    foods[0].y+=20;
    x2 *= 20;
    x2 += 20;
    y2 = (int(random(25)));
    y2 *= 20;
    y2 += 20;
    score++;
  }
}

void grid() {

  for (int i = 0; i < 625; i++) {

    boxes[i].display();
  }
  println(score);
}

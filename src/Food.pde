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


void display() {
    r = 0;
    g=255;
    b=0;
  fill(r, g, b);
  
  rect(x,y,20, 20);
}
}

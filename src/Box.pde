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


void display() {
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

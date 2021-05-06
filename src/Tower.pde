class Tower{
  //member variables
  int type,health,x,y,xlength,ywidth,xcenter,ycenter,radius;
  color c1,c2,c3;
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
    c1 = int(random(175,255));
    c2 = int(random(175,255));
    c3 = int(random(175,255));
  }
  
  //methods
  void display(){
    fill(175,50);
    circle(xcenter,ycenter,radius);
    fill(0);
    rect(x,y,xlength,ywidth);
  }
  
  void lazer(int x2, int y2){
    stroke(c1,c2,c3);
    strokeWeight(10);
    line(x,y,x2,y2);
    strokeWeight(1);
    stroke(0);
  }
  
}

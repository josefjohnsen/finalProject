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
  

  
  
   void display(){
     fill(0,0,255);
     rect(x,y,15,20);
     y-=speed;//2 = speed
    xcenter = (x+(x+15))/2;
    ycenter = (y+(y+20))/2;
   }
  
}

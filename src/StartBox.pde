class StartBox {
  boolean full;
  int x, y,dir;

  StartBox(int x,int y) {
    this.x = x;
    this.y = y;
    dir = 4;
  }


void display() {
  fill(0,255,0);
  rect(x,y,20,20);


}

void aniMove(){
    if(x==500 && y==20){
      dir = 3;
    }
      if(x==500 && y==500){
      dir = 2;
    }
      if(x==20 && y==500){
      dir = 1;
    }
      if(x==20 && y==20){
      dir = 4;
    }
    if (dir == 1){
      y -= 20;
   }
   if (dir == 4){
      x += 20;
   }
     if (dir == 3){
      y += 20;
   }
       if (dir == 2){
      x -= 20;
   }
}


}

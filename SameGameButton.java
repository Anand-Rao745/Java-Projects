import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/**
 * A class to represent a Button for the Same Game.
 * @author  Anand Rao
 */ 
public class SameGameButton extends Button{
 
  private int x;
  
  private int y;
  
  private boolean hasBeenChecked=false;
  

  public SameGameButton(int y,int x, boolean hasBeenChecked){
    this.y=y;
    this.x=x;
    this.hasBeenChecked=hasBeenChecked;
  }
  

  public int getX(){
    return x;
  }
  

  public void setX(int x){
    this.x=x;
  }
  

  public int getY(){
    return y;
  }
  

  public void setY(int y){
    this.y=y;
  }
  

  public boolean getHasBeenChecked(){
    return hasBeenChecked;
  }
  

  public void setHasBeenChecked(boolean hasBeenChecked){
    this.hasBeenChecked=hasBeenChecked;
  }
}

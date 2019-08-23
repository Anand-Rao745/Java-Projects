import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/**
 * A class to represent a Button for the Same Game.
 * @author  Anand Rao
 */ 
public class SameGameButton extends Button{
  /*Stores the x coordinate of the button*/
  private int x;
  /*Stores the y coordinate of the button*/
  private int y;
  /*Stores true or false if the button has been checked*/
  private boolean hasBeenChecked=false;
  
  /**
   * The Constructor
   * @param y  the y coordinate of the button.
   * @param x  the x coordinate of the button.
   * @param hasBeenChecked true or false if the button has been checked.
   */
  public SameGameButton(int y,int x, boolean hasBeenChecked){
    this.y=y;
    this.x=x;
    this.hasBeenChecked=hasBeenChecked;
  }
  
  /**
   * Returns the x coordinate of the button.
   * @return the x coordinate of the button.
   */
  public int getX(){
    return x;
  }
  
  /**
   * Changes the x coordinate of the button.
   * @param x the new x coordinate of the button.
   */
  public void setX(int x){
    this.x=x;
  }
  
  /**
   * Returns the y coordinate of the button.
   * @return the y coordinate of the button.
   */
  public int getY(){
    return y;
  }
  
  /**
   * Changes the y coordinate of the button.
   * @param y the new y coordinate of the button.
   */ 
  public void setY(int y){
    this.y=y;
  }
  
  /**
   * Returns the boolean which is true or false.
   * @return the boolean which is true or false.
   */
  public boolean getHasBeenChecked(){
    return hasBeenChecked;
  }
  
  /**
   * Changes the boolean to true or false.
   * @param hasBeenChecked  the new boolean.
   */
  public void setHasBeenChecked(boolean hasBeenChecked){
    this.hasBeenChecked=hasBeenChecked;
  }
}
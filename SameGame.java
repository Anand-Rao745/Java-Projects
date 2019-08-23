import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.lang.NumberFormatException;
import java.lang.NullPointerException;

/**
 * A class to represent the Same Game.
 * @author  Anand Rao
 */ 
public class SameGame extends Application {
  /*Stores the number of rows.*/
  private int rows=12;
  /*Stores the number of columns.*/
  private int columns=12;
  /*Stores the number of colors.*/
  private int colors=3;
  
  /**
   * Changes the number of rows.
   * @param rows  the new number of rows.
   */
  public void setRows(int rows){
    this.rows=rows;
  }
  
  /**
   * Returns the number of rows.
   * @return the number of rows.
   */
  public int getRows(){
    return rows;
  }
  
  /**
   * Changes the number of columns.
   * @param columns  the new number of columns.
   */
  public void setColumns(int columns){
    this.columns=columns;
  }
  
  /**
   * Returns the number of columns.
   * @return the number of columns.
   */
  public int getColumns(){
    return columns;
  }
  
  /**
   * Changes the number of colors.
   * @param colors  the new number of colors.
   */
  public void setColors(int colors){
    this.colors=colors;
  }
  
  /**
   * Returns the number of colors.
   * @return the number of colors.
   */
  public int getColors(){
    return colors;
  }
  
  /** Create the GUI
    * @param primaryStage  the main display
    * @throws NumberFormatException  if any of the command line arguments are invalid.
    */
  public void start(Stage primaryStage)throws NumberFormatException{
    try{
      if(this.getParameters().getRaw().size()!=0){
        if(this.getParameters().getRaw().size()!=3){
          throw new NumberFormatException();
        }
        //This loop checks if any of the command line arguments are less than  or equal to 0, and throws an exception if they are.
        for(int i=0;i<3;i++){
          if(Integer.parseInt(this.getParameters().getRaw().get(i))<=0){
            throw new NumberFormatException();
          }
        }
        this.setColumns(Integer.parseInt(this.getParameters().getRaw().get(0)));
        this.setRows(Integer.parseInt(this.getParameters().getRaw().get(1)));
        this.setColors(Integer.parseInt(this.getParameters().getRaw().get(2)));
      }
      else{
        this.setColumns(12);
        this.setRows(12);
        this.setColors(3);
      }
    }
    catch(NumberFormatException e){
      System.out.println("Bad Arguments!");
      System.exit(0);
    }
    primaryStage.setTitle("Same Game");
    SameGameButton[][]board= new SameGameButton[this.getRows()][this.getColumns()];
    GridPane gridpane= new GridPane();
    Color[]boardColors=new Color[this.getColors()];
    //This loop assigns a random color to each index of the color array.
    for(int i=0;i<this.getColors();i++){
      boardColors[i]=new Color(Math.random(),Math.random(),Math.random(),1);
    }
    //This loop assigns a button to each index in the button array, then adds the button array to the gridpane.
    for(int i=0;i<this.getRows();i++){
      for(int j=0;j<this.getColumns();j++){
        board[i][j]= new SameGameButton(i,j,new Boolean(false));
        int k=(int)(Math.random()*this.getColors());
        board[i][j].setGraphic(new Circle(10,boardColors[k]));
        gridpane.add(board[i][j],j,i);
        board[i][j].setOnAction(new EventHandler<ActionEvent>() {
          //Handles button clicks.
          @Override
          public void handle(ActionEvent e){
            SameGameButton b = (SameGameButton)e.getSource();
            SameGame s= new SameGame();
            s.checkWin(board, s);
            LinkedList<SameGameButton>similar=s.checkAll(board,s,b);
            if(similar.size()>4){
              //This loop changes the colors of the buttons in the LinkedList to gray.
              for(SameGameButton c:similar){
                ((Circle)(board[c.getY()][c.getX()].getGraphic())).setFill(Color.TRANSPARENT);
              }
              drop(board);
              //This loop calls the shiftLeft method for each row of the board.
              for(int i=0;i<board[0].length;i++){
                shiftLeft(board);
              }
            }
          }
        });
      }
    }
    //Adds the gridpane to the scene.
    Scene scene= new Scene(gridpane);
    //Adds the scene to the stage.
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  /**
   * Returns a LinkedList of contiguous buttons to the left of the button that was clicked.
   * @param origin  the button that was clicked.
   * @param array  the board that contains the buttons.
   * @return a LinkedList of contiguous buttons to the left of the button that was clicked.
   */
  public LinkedList<SameGameButton>checkLeft(SameGameButton origin, SameGameButton[][]array){
    LinkedList<SameGameButton>matches= new LinkedList<SameGameButton>();
    //This loop checks if the button to the left of the origin has the same color, and adds it to a LinkedList if it does.
    for(int i=origin.getX();i>=0 && ((Circle)(array[origin.getY()][i].getGraphic())).getFill().equals(((Circle)(array[origin.getY()][origin.getX()].getGraphic())).getFill());i--){
      if(!(((Circle)(array[origin.getY()][origin.getX()].getGraphic())).getFill().equals(Color.TRANSPARENT))){
        matches.add(array[origin.getY()][i]);
      }
    }
    return matches;
  }
  
  /**
   * Returns a LinkedList of contiguous buttons to the right of the button that was clicked.
   * @param origin  the button that was clicked.
   * @param array  the board that contains the buttons.
   * @return a LinkedList of contiguous buttons to the right of the button that was clicked.
   */
  public LinkedList<SameGameButton>checkRight(SameGameButton origin, SameGameButton[][]array){
    LinkedList<SameGameButton>matches= new LinkedList<SameGameButton>();
    //This loop checks if the button to the right of the origin has the same color, and adds it to a LinkedList if it does.
    for(int i=origin.getX();i<array[0].length && ((Circle)(array[origin.getY()][i].getGraphic())).getFill().equals(((Circle)(array[origin.getY()][origin.getX()].getGraphic())).getFill());i++){
      if(!(((Circle)(array[origin.getY()][origin.getX()].getGraphic())).getFill().equals(Color.TRANSPARENT))){
        matches.add(array[origin.getY()][i]);
      }
      
    }
    return matches;
  }
  
  /**
   * Returns a LinkedList of contiguous buttons below the button that was clicked.
   * @param origin  the button that was clicked.
   * @param array  the board that contains the buttons.
   * @return a LinkedList of contiguous buttons below the button that was clicked.
   */
  public LinkedList<SameGameButton>checkDown(SameGameButton origin, SameGameButton[][]array){
    LinkedList<SameGameButton>matches= new LinkedList<SameGameButton>();
    //This loop checks if the button below the origin has the same color, and adds it to a LinkedList if it does.
    for(int i=origin.getY();i>=0 && ((Circle)(array[i][origin.getX()].getGraphic())).getFill().equals(((Circle)(array[origin.getY()][origin.getX()].getGraphic())).getFill());i--){
      if(!(((Circle)(array[origin.getY()][origin.getX()].getGraphic())).getFill().equals(Color.TRANSPARENT))){
        matches.add(array[i][origin.getX()]);
      }
      
    }
    return matches;
  }
  
  /**
   * Returns a LinkedList of contiguous buttons above the button that was clicked.
   * @param origin  the button that was clicked.
   * @param array  the board that contains the buttons.
   * @return a LinkedList of contiguous buttons above the button that was clicked.
   */
  public LinkedList<SameGameButton>checkUp(SameGameButton origin, SameGameButton[][]array){
    LinkedList<SameGameButton>matches= new LinkedList<SameGameButton>();
    //This loop checks if the button above the origin has the same color, and adds it to a LinkedList if it does.
    for(int i=origin.getY();i<array.length && ((Circle)(array[i][origin.getX()].getGraphic())).getFill().equals(((Circle)(array[origin.getY()][origin.getX()].getGraphic())).getFill());i++){
      if(!(((Circle)(array[origin.getY()][origin.getX()].getGraphic())).getFill().equals(Color.TRANSPARENT))){
        matches.add(array[i][origin.getX()]);
      }
      
    }
    return matches;
  }
  
  /**
   * Moves buttons down to fill empty spaces.
   * @param array  the board that contains the buttons.
   */ 
  public static void drop(SameGameButton[][]array){
    //This loop checks if each colored button has an empty button below it, and swaps colors if it does, then calls the method again.
    for(int i=0;i<array.length-1;i++){
      for(int j=0;j<array[0].length;j++){
        if(((Circle)array[i][j].getGraphic()).getFill().equals(Color.TRANSPARENT)==false && ((Circle)array[i+1][j].getGraphic()).getFill().equals(Color.TRANSPARENT)){
          ((Circle)array[i+1][j].getGraphic()).setFill(((Circle)array[i][j].getGraphic()).getFill());
          ((Circle)array[i][j].getGraphic()).setFill(Color.TRANSPARENT);
          drop(array);
        }
      }
    }
  }
  
  /**
   * Moves buttons to the left to fill empty columns.
   * @param array  the board that contains the buttons.
   */
  public static void shiftLeft(SameGameButton[][]array){
    boolean isEmpty=false;
    //This loop checks if each column is empty, and swaps colors with the next column if it is.
    for(int i=0;i<array[0].length;i++){
      int emptyCells=0;
      for(int j=0;j<array.length;j++){
        if(isEmpty==true){
          ((Circle)array[j][i-1].getGraphic()).setFill(((Circle)array[j][i].getGraphic()).getFill());
          ((Circle)(array[j][i].getGraphic())).setFill(Color.TRANSPARENT);
        }
        if(((Circle)array[j][i].getGraphic()).getFill().equals(Color.TRANSPARENT)){
          emptyCells++; 
        }
        if(((Circle)array[j][i].getGraphic()).getFill().equals(Color.TRANSPARENT)==false){
          isEmpty=false;
        }
        if(emptyCells==array.length){
          isEmpty=true;
        }
      }      
    }    
  }
  
  /**
   * Compiles a list of all buttons that need to be updated.
   * @param array  the board that contains the buttons.
   * @param s  a SameGame instance to run other methods.
   * @param b  a button that has been clicked.
   * @return a list of all buttons that need to be updated.
   */
  public LinkedList<SameGameButton>checkAll(SameGameButton[][]array, SameGame s, SameGameButton b){
    LinkedList<SameGameButton>gray= new LinkedList<SameGameButton>();
    //This loop adds each button returned by checkLeft to a LinkedList.
    for(SameGameButton c: s.checkLeft(b,array)){
      gray.add(c);
    }
    //This loop adds each button returned by checkRight to a LinkedList.
    for(SameGameButton c: s.checkRight(b,array)){
      gray.add(c);
    }
    //This loop adds each button returned by checkDown to a LinkedList.
    for(SameGameButton c: s.checkDown(b,array)){
      gray.add(c);
    }
    //This loop adds each button returned by checkUp to a LinkedList.
    for(SameGameButton c: s.checkUp(b,array)){
      gray.add(c);
    }
    return gray;
  }
  
  /**
   * Checks whether the game is over and exits if it is.
   * @param array  the board that contains the buttons.
   * @param s  a SameGame instance to run other methods.
   */
  public void checkWin(SameGameButton[][]array, SameGame s){
    //This loop checks to see if there are any remaining adjacent buttons of the same color.
    for(int i=0;i<array.length;i++){
      for(int j=0;j<array[0].length;j++){
        LinkedList<SameGameButton>similar=s.checkAll(array, s, array[i][j]);
        if(similar.size()>4){
          return;
        }  
      }
    }
    JFrame frame= new JFrame();
    JOptionPane.showMessageDialog(frame, "Game over."); 
    System.exit(0);
  }
  
  /**
   * Launches the program.
   * @param args  the dimensions of the board and the number of colors.
   */
  public static void main(String[] args){
    Application.launch(args);                   
  }
}



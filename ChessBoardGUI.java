/**
 * @author Martin Guevara
 * @version January 14th, 2019
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.JOptionPane;

public class ChessBoardGUI extends JFrame implements ActionListener{
  
  JButton[][] buttonArray = new JButton[8][8];
  private int xCorChoice = 8;
  private int yCorChoice = 8;
  private int xCorMove = 8;
  private int yCorMove = 8;
  private int clickCounter = 0;
  
  JLabel player1Wins = new JLabel("Black Wins!");
  JLabel player2Wins = new JLabel("White Wins!");
  
  JLabel errMessage = new JLabel("Invalid Move");
  
  JLabel playerMove1 = new JLabel("It is blacks's turn");
  JLabel playerMove2 = new JLabel("It is whites's turn");
  
  public ChessBoardGUI(){
    super("Chess Game"); 
    
    setLayout(null);
    
   // String highScoreMoves = "";
    
//    try {
//      File highScore = new File("WinningScores.txt");
//      FileReader in = new FileReader(highScore);
//      BufferedReader br = new BufferedReader(in);
//      highScoreMoves = br.readLine();
//    }catch(FileNotFoundException e){
//      System.out.println(e);
//    } catch (IOException e2) {
//      System.out.println(e2);
//    }
//    
//    JLabel highScoreText = new JLabel("High Score: " + highScoreMoves);
//    highScoreText.setBounds(830, 50, 250, 100);
//    highScoreText.setLayout(new BorderLayout());
//    highScoreText.setFont(new Font("Arial Black", Font.PLAIN, 30));
//    add(highScoreText);
    
    player2Wins.setBounds(845, 150, 250, 200);
    player2Wins.setLayout(new BorderLayout());
    player2Wins.setFont(new Font("Arial Black", Font.PLAIN, 30));
    add(player2Wins);
    player2Wins.setVisible(false);
    
    player1Wins.setBounds(845, 150, 250, 200);
    player1Wins.setLayout(new BorderLayout());
    player1Wins.setFont(new Font("Arial Black", Font.PLAIN, 30));
    add(player1Wins);
    player1Wins.setVisible(false);
    
    playerMove1.setBounds(845, 50, 250, 200);
    playerMove1.setLayout(new BorderLayout());
    playerMove1.setFont(new Font("Arial Black", Font.PLAIN, 20));
    add(playerMove1);
    playerMove1.setVisible(false);
    
    playerMove2.setBounds(845, 50, 250, 200);
    playerMove2.setLayout(new BorderLayout());
    playerMove2.setFont(new Font("Arial Black", Font.PLAIN, 20));
    add(playerMove2);
    playerMove2.setVisible(true);
    
    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 8; j++){
        buttonArray[i][j] = new JButton("");
        buttonArray[i][j].setFont(new Font("Arial", Font.PLAIN, 0));
        buttonArray[i][j].addActionListener(this); 
        if ((i + j) % 2 == 0){
          buttonArray[i][j].setBackground(Color.WHITE);
          buttonArray[i][j].setOpaque(true);
        }else{
          buttonArray[i][j].setBackground(Color.BLACK); 
          buttonArray[i][j].setOpaque(true);
        }
        
      }
    }
    
    int posX = 0;
    int posY = 0;
    
    for(int i = 0; i < 8; i++) {
      for(int j = 0; j < 8; j++){
        buttonArray[i][j].setBounds(posX, posY, 100, 100);
        add(buttonArray[i][j]);
        posX += 100;
      }
      posY += 100;
      posX = 0;
    }
    
    setResizable(false);
    
  }//chessBoardGUI
  
  /**
   * gives a clone of a buttonArray
   * @return clone of the JButton array buttonArray
   */
  public JButton[][] getButtonArray(){
    return buttonArray.clone();
  }//getButtonArray
  
  /**
   * gives a button an icon of a piece
   * @param JButton array to get buttons, int for x coordinate of button, int for y coordinate of button, char for the piece type, int for the team of the piece
   */
  public void giveIcon(JButton[][] c, int x, int y, char piece, int team){
    
    Icon blackPawn = new ImageIcon(getClass().getResource("/ChessPieces/PawnBlack.png"));
    Icon blackTower = new ImageIcon(getClass().getResource("/ChessPieces/TowerBlack.png"));
    Icon blackKnight = new ImageIcon(getClass().getResource("/ChessPieces/KnightBlack.png"));
    Icon blackBishop = new ImageIcon(getClass().getResource("/ChessPieces/BishopBlack.png"));
    Icon blackQueen = new ImageIcon(getClass().getResource("/ChessPieces/QueenBlack.png"));
    Icon blackKing = new ImageIcon(getClass().getResource("/ChessPieces/KingBlack.png"));
    Icon whitePawn = new ImageIcon(getClass().getResource("/ChessPieces/PawnWhite.png"));
    Icon whiteTower = new ImageIcon(getClass().getResource("/ChessPieces/TowerWhite.png"));
    Icon whiteKnight = new ImageIcon(getClass().getResource("/ChessPieces/KnightWhite.png"));
    Icon whiteBishop = new ImageIcon(getClass().getResource("/ChessPieces/BishopWhite.png"));
    Icon whiteQueen = new ImageIcon(getClass().getResource("/ChessPieces/QueenWhite.png"));
    Icon whiteKing = new ImageIcon(getClass().getResource("/ChessPieces/KingWhite.png"));
    
    if (team == 1){ //team one is black
      if (piece == 'p'){
        c[x][y].setIcon(blackPawn);
      }else if(piece == 't'){
        c[x][y].setIcon(blackTower);
      }else if(piece == 'k'){ //lowercase 'k' for knight
        c[x][y].setIcon(blackKnight);
      }else if(piece == 'b'){
        c[x][y].setIcon(blackBishop);
      }else if(piece == 'q'){
        c[x][y].setIcon(blackQueen);
      }else if(piece == 'K'){ //uppercase 'K' for king 
        c[x][y].setIcon(blackKing);
      }   
    }else if(team == 2){ //team two is white
      if (piece == 'p'){
        c[x][y].setIcon(whitePawn);
      }else if(piece == 't'){
        c[x][y].setIcon(whiteTower);
      }else if(piece == 'k'){ //lowercase 'k' for knight
        c[x][y].setIcon(whiteKnight);
      }else if(piece == 'b'){
        c[x][y].setIcon(whiteBishop);
      }else if(piece == 'q'){
        c[x][y].setIcon(whiteQueen);
      }else if(piece == 'K'){ //uppercase 'K' for king 
        c[x][y].setIcon(whiteKing);
      }
    }
  }//giveIcon
  
  /**
   * removes the icon from a button
   * @param JButton array to get buttons, int for x coordinate of button, int for y coordinate of button
   */
  public void removeIcon(JButton[][] c, int x, int y){
    c[x][y].setIcon(null);
  }//removeIcon
  
  /**
   * gets the coordinates of the button pressed an distinguishes whether it is the original button or the move button
   * @param ActionEvent the click of a button
   */
  public void actionPerformed(ActionEvent e){
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if(buttonArray[i][j] == e.getSource()){ 
          if (clickCounter % 2 == 0){
            xCorChoice = i;
            yCorChoice = j;
            clickCounter += 1;
          }else{
            xCorMove = i;
            yCorMove = j;
            clickCounter += 1;
          }
        }
      }
    }
  }//actionPerformed
  
  /**
   * gives the x coordinate of the original click
   * @return the x coordinate of the original click unless the click is for the move coordinates, in which case it will return 8
   */
  public int getButtonCoordChoiceX(){
    if (clickCounter % 2 == 1)
      return xCorChoice;
    else
      return 8;
  }//getButtonCoordChoiceX
  
  /**
   * gives the y coordinate of the original click
   * @return the y coordinate of the original click unless the click is for the move coordinates, in which case it will return 8
   */
  public int getButtonCoordChoiceY(){
    if (clickCounter % 2 == 1)
      return yCorChoice;
    else
      return 8;
  }//getButtonCoordChoiceY
  
  /**
   * gives the x coordinate of the button to move to
   * @return the x coordinate of the button to move to unless the click is for the original coordinates, in which case it will return 8
   */
  public int getButtonCoordMoveX(){
    if (clickCounter % 2 == 0)
      return xCorMove;
    else
      return 8;
  }//getButtonCoordMoveX
  
  /**
   * gives the y coordinate of the button to move to
   * @return the y coordinate of the button to move to unless the click is for the original coordinates, in which case it will return 8
   */
  public int getButtonCoordMoveY(){
    if (clickCounter % 2 == 0)
      return yCorMove;
    else
      return 8;
  }//getButtonCoordMoveY
  
  /**
   * sets the winning text for player 1 to visible if they win
   */
  public void player1Winner(){
    player1Wins.setVisible(true);
  }//player1Winner
  
  /**
   * sets the winning text for player 2 to visible if they win
   */
  public void player2Winner(){
    player2Wins.setVisible(true);
  }//player2Winner
  
  public void changeButtonBlue(int x, int y){
    
    buttonArray[x][y].setBackground(Color.CYAN);
    
  }//changeButtonBlue
  
  public void changeButtonNorm(int x, int y){
    
    if((x + y) % 2 == 0){
      
      buttonArray[x][y].setBackground(Color.WHITE);
    
    }else{
      
      buttonArray[x][y].setBackground(Color.BLACK);
      
    }
  }//changeButtonNorm
  
  public void errMessage(){
    
    JOptionPane.showMessageDialog(null, "Invalid Move");
    
  }//errMessage
  
  
  public void playerOneTurn(){
    
    playerMove2.setVisible(false);
    playerMove1.setVisible(true);
    
  }//playerOneTurn
  
   public void playerTwoTurn(){
    
    playerMove1.setVisible(false);
    playerMove2.setVisible(true);
    
  }//playerTwoTurn
   
  public void playerTurnRemove(){
    
    playerMove1.setVisible(false);
    playerMove2.setVisible(false);
    
  }//playerTurnRemove
  
}//chessBoardGUI

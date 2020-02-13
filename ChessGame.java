/**
 * @author Martin Guevara
 * @version January 14th, 2019
 */
import java.io.*;
import javax.swing.JFrame;
public class ChessGame{
  
  public static int playerTurn = 1;
  public static int gameNumMoves = 0;
  
  public static void main(String [] args){
    
//    //getting high score
//    String scoreString = "";
//    int oldScore = 0;   
//    try {
//      File highScore = new File("WinningScores.txt");
//      FileReader in = new FileReader(highScore);
//      BufferedReader br = new BufferedReader(in);
//      scoreString = br.readLine();
//      oldScore = Integer.parseInt(scoreString);
//    }catch(FileNotFoundException e){
//      System.out.println(e);
//    } catch (IOException e2) {
//      System.out.println(e2);
//    }
    
    //what piece they want to move
    int playerChoiceX;
    int playerChoiceY;
    
    //where they want to move it
    int playerMoveX;
    int playerMoveY;
    
    int spacing = 0;
    boolean checkSpaces = true;
    boolean winCondition = false;
    int player1Pieces = 0;
    int player2Pieces = 0;
    boolean king1Status = true;
    boolean king2Status = true;
    
    ChessPiece[][] chessBoard = new ChessPiece[8][8]; //text array with logic
    
    ChessBoardGUI chessBoardGui = new ChessBoardGUI(); //GUI that mirrors text array
    chessBoardGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    chessBoardGui.setSize(1100,830);
    chessBoardGui.setVisible(true);
    
    ChessPiece pawn1 = new ChessPiece(1, 1); //pawn team 1
    ChessPiece pawn2 = new ChessPiece(1, 2); //pawn team 2
    
    for(int i = 0; i < chessBoard.length; i++){
      chessBoard[1][i] = pawn1;
      chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 1, i, 'p', 1);
      chessBoard[6][i] = pawn2; 
      chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 6, i, 'p', 2);
    }
    ChessPiece tower1 = new ChessPiece(4, 1);
    ChessPiece tower2 = new ChessPiece(4, 2);
    
    chessBoard[0][0] = tower1;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 0, 0, 't', 1);
    chessBoard[0][7] = tower1;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 0, 7, 't', 1);
    chessBoard[7][0] = tower2;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 7, 0, 't', 2);
    chessBoard[7][7] = tower2;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 7, 7, 't', 2);
    
    ChessPiece knight1 = new ChessPiece(5, 1);
    ChessPiece knight2 = new ChessPiece(5, 2);
    
    chessBoard[0][1] = knight1;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 0, 1, 'k', 1);
    chessBoard[0][6] = knight1;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 0, 6, 'k', 1);
    chessBoard[7][1] = knight2;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 7, 1, 'k', 2);
    chessBoard[7][6] = knight2;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 7, 6, 'k', 2);
    
    ChessPiece bishop1 = new ChessPiece(6, 1);
    ChessPiece bishop2 = new ChessPiece(6, 2);
    
    chessBoard[0][2] = bishop1;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 0, 2, 'b', 1);
    chessBoard[0][5] = bishop1;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 0, 5, 'b', 1);
    chessBoard[7][2] = bishop2;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 7, 2, 'b', 2);
    chessBoard[7][5] = bishop2;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 7, 5, 'b', 2);
    
    ChessPiece queen1 = new ChessPiece(2, 1);
    ChessPiece queen2 = new ChessPiece(2, 2);
    
    chessBoard[0][3] = queen1;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 0, 3, 'q', 1);
    chessBoard[7][3] = queen2;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 7, 3, 'q', 2);
    
    ChessPiece king1 = new ChessPiece(3, 1);
    ChessPiece king2 = new ChessPiece(3, 2);
    
    chessBoard[0][4] = king1;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 0, 4, 'K', 1);
    chessBoard[7][4] = king2;
    chessBoardGui.giveIcon(chessBoardGui.getButtonArray(), 7, 4, 'K', 2);
    
    while(winCondition == false){
      
      //start as false, game will find their status after move
      king1Status = false;
      king2Status = false;
      
      //start as 0, game will calculate pieces after turn
      player1Pieces = 0;
      player2Pieces = 0;
      
      playerChoiceX = 8;
      playerChoiceY = 8;
      
      while(playerChoiceX == 8 || playerChoiceY == 8){       
        Thread.yield();
        playerChoiceX = chessBoardGui.getButtonCoordChoiceY();
        playerChoiceY = chessBoardGui.getButtonCoordChoiceX();
      }
      
      chessBoardGui.changeButtonBlue(playerChoiceY, playerChoiceX);
      
      playerMoveX = 8;
      playerMoveY = 8;
      while(playerMoveX == 8 || playerMoveY == 8){
        Thread.yield();
        playerMoveX = chessBoardGui.getButtonCoordMoveY();
        playerMoveY = chessBoardGui.getButtonCoordMoveX();
      }
      
      chessBoardGui.changeButtonNorm(playerChoiceY, playerChoiceX);
      
      if (playerTurn % 2 == 0){ //player 1 move
        
        if (chessBoard[playerChoiceY][playerChoiceX] != null){ //if there is a piece in selected choice spot
          if (chessBoard[playerMoveY][playerMoveX] == null || chessBoard[playerMoveY][playerMoveX].getTeam() != 1){ //if there is no piece in the selected move spot from the same team
            if (chessBoard[playerChoiceY][playerChoiceX] == pawn1){ //if the piece is a pawn
              if (playerChoiceX - playerMoveX == 0){ //if the x coordinate has not changed
                if (playerChoiceY == 1){ //if it is the first turn
                  if (playerChoiceY - playerMoveY == -1 || playerChoiceY - playerMoveY == -2){ //if the piece has moved one or two spaces
                    if (chessBoard[playerMoveY][playerMoveX] == null){
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, pawn1, chessBoardGui, 'p');
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }else{
                    chessBoardGui.errMessage();
                  }
                }else if (playerChoiceY - playerMoveY == -1){ //if the piece has moved one space
                  if (chessBoard[playerMoveY][playerMoveX] == null){
                    if (playerMoveY == 7){ //if the pawn reaches the opposite end of the board to become a queen
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                    }else{
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, pawn1, chessBoardGui, 'p');
                    }
                  }else{
                    chessBoardGui.errMessage();
                  }
                }else{
                  chessBoardGui.errMessage();
                }
              }else if (playerChoiceX - playerMoveX == 1 || playerChoiceX - playerMoveX == -1){ //if the move is diagonal
                if (playerChoiceY - playerMoveY == -1){
                  if (chessBoard[playerMoveY][playerMoveX] != null){
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, pawn1, chessBoardGui, 'p');
                  }else{
                    chessBoardGui.errMessage();
                  }
                }else{
                  chessBoardGui.errMessage();
                }
              }else{
                chessBoardGui.errMessage();
              }
            }else if (chessBoard[playerChoiceY][playerChoiceX] == tower1){ //if the piece is a tower
              if (playerChoiceY - playerMoveY == 0 && playerChoiceX - playerMoveX != 0){ //if the tower moves along x axis
                if (playerChoiceX - playerMoveX < 0){ //if it is moving left
                  if (playerMoveX - playerChoiceX - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower1 , chessBoardGui, 't');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower1 , chessBoardGui, 't');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else{ //if it is moving right 
                  if (playerChoiceX - playerMoveX - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower1 , chessBoardGui, 't');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower1 , chessBoardGui, 't');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }    
              }else if (playerChoiceY - playerMoveY != 0 && playerChoiceX - playerMoveX == 0){ //if the tower moves along the y axis
                if (playerChoiceY - playerMoveY < 0){ //if it is moving down
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower1 , chessBoardGui, 't');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower1 , chessBoardGui, 't');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else{ //if it is moving up
                  if (playerChoiceY - playerMoveY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower1 , chessBoardGui, 't');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower1 , chessBoardGui, 't');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }
              }else{
                chessBoardGui.errMessage();
              }
            }else if (chessBoard[playerChoiceY][playerChoiceX] == knight1){ //if the piece is a knight
              if (((Math.abs(playerChoiceX - playerMoveX)) == 2) && (Math.abs(playerChoiceY - playerMoveY) == 1)){ //if it moves 2 spaces on the x axis and one on the y axis
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, knight1 , chessBoardGui, 'k');
              }else if(((Math.abs(playerChoiceX - playerMoveX)) == 1) && (Math.abs(playerChoiceY - playerMoveY) == 2)){ //if it moves 2 spaces on the y axis and one on the x axis
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, knight1 , chessBoardGui, 'k');
              }else{
                chessBoardGui.errMessage();
              }
              //dont need to worry about spaces (can jump over pieces)
            }else if (chessBoard[playerChoiceY][playerChoiceX] == bishop1){ //if the piece is a bishop
              if (Math.abs(playerChoiceX - playerMoveX) == Math.abs(playerChoiceY - playerMoveY)){ //if it moves the same amount vertically and horizontally
                if (playerChoiceX - playerMoveX > 0 && playerChoiceY - playerMoveY < 0){ //if it is going down and left
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop1 , chessBoardGui, 'b');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop1 , chessBoardGui, 'b');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else if (playerChoiceX - playerMoveX < 0 && playerChoiceY - playerMoveY < 0){ //if it is going down and right
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop1 , chessBoardGui, 'b');
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop1 , chessBoardGui, 'b');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else if (playerChoiceX - playerMoveX > 0 && playerChoiceY - playerMoveY > 0){ //if it is going up and left
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop1 , chessBoardGui, 'b');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop1, chessBoardGui, 'b');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else{ //if it is going up and right
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop1 , chessBoardGui, 'b');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop1, chessBoardGui, 'b');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }
              }else{
                chessBoardGui.errMessage();
              }
            }else if (chessBoard[playerChoiceY][playerChoiceX] == queen1){ //if the piece is a queen
              if (Math.abs(playerChoiceY - playerMoveY) != Math.abs(playerChoiceX - playerMoveX)){ //if it is moving horizontally or vertically
                if (playerChoiceY - playerMoveY == 0 && playerChoiceX - playerMoveX != 0){ //if the queen moves along x axis
                  if (playerChoiceX - playerMoveX < 0){ //if it is moving left
                    if (playerMoveX - playerChoiceX - 1 == 0){ //if there are no spaces
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                      
                    }else{ //if there are spaces
                      
                      if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                        movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                      }else{
                        chessBoardGui.errMessage();
                      }
                    }
                  }else{ //if it is moving right 
                    if (playerChoiceX - playerMoveX - 1 == 0){ //if there are no spaces
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                      
                    }else{ //if there are spaces
                      
                      if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                        movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                        
                      }else{
                        chessBoardGui.errMessage();
                      }
                    }
                  }    
                }else if (playerChoiceY - playerMoveY != 0 && playerChoiceX - playerMoveX == 0){ //if the piece moves along the y axis
                  if (playerChoiceY - playerMoveY < 0){ //if it is moving down
                    if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                      
                    }else{ //if there are spaces
                      
                      if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                        movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                        
                      }else{
                        chessBoardGui.errMessage();
                      }
                    }
                  }else{ //if it is moving up
                    if (playerChoiceY - playerMoveY - 1 == 0){ //if there are no spaces
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                      
                    }else{ //if there are spaces
                      
                      if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                        movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                        
                      }else{
                        chessBoardGui.errMessage();
                      }
                    }
                  }
                }else{
                  chessBoardGui.errMessage();
                }
              }else if(Math.abs(playerChoiceX - playerMoveX) == Math.abs(playerChoiceY - playerMoveY)){ //if piece is moving diagonally
                
                if (playerChoiceX - playerMoveX > 0 && playerChoiceY - playerMoveY < 0){ //if it is going down and left
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else if (playerChoiceX - playerMoveX < 0 && playerChoiceY - playerMoveY < 0){ //if it is going down and right
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else if (playerChoiceX - playerMoveX > 0 && playerChoiceY - playerMoveY > 0){ //if it is going up and left
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else{ //if it is going up and right
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen1, chessBoardGui, 'q');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }
                
              }else{
                chessBoardGui.errMessage();
              }
              
            }else if (chessBoard[playerChoiceY][playerChoiceX] == king1){ //if the piece is a king
              if (playerChoiceX - playerMoveX == 1 && playerChoiceY - playerMoveY == 0){ //if the king is moving left
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king1, chessBoardGui, 'K'); 
              }else if (playerMoveX - playerChoiceX == 1 && playerChoiceY - playerMoveY == 0){ //if the king is moving right
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king1, chessBoardGui, 'K');
              }else if (playerChoiceY - playerMoveY == 1 && playerMoveX - playerChoiceX == 0){ //if the king is moving up
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king1, chessBoardGui, 'K');
              }else if (playerMoveY - playerChoiceY == 1 && playerMoveX - playerChoiceX == 0){ //if the king is moving down
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king1, chessBoardGui, 'K');
              }else if (playerMoveX - playerChoiceX == 1 && playerChoiceY - playerMoveY == 1){ //if the king is moving right and up
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king1, chessBoardGui, 'K');
              }else if (playerChoiceX - playerMoveX == 1 && playerChoiceY - playerMoveY == 1){ //if the king is moving left and up
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king1, chessBoardGui, 'K');
              }else if (playerMoveX - playerChoiceX == 1 && playerMoveY - playerChoiceY == 1){ //if the king is moving left and down
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king1, chessBoardGui, 'K');
              }else if (playerChoiceX - playerMoveX == 1 && playerMoveY - playerChoiceY == 1){ //if the king is moving right and down
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king1, chessBoardGui, 'K');
              }else{
                chessBoardGui.errMessage();
              }
            }
            
          }else{
            chessBoardGui.errMessage();
          }
        }else{
          chessBoardGui.errMessage();
        }
        
      }else if (playerTurn % 2 != 0){ //player 2 move
        
        if (chessBoard[playerChoiceY][playerChoiceX] != null){ //if there is a piece in selected choice spot
          if (chessBoard[playerMoveY][playerMoveX] == null || chessBoard[playerMoveY][playerMoveX].getTeam() != 2){ //if there is no piece in the selected move spot from the same team
            if (chessBoard[playerChoiceY][playerChoiceX] == pawn2){ //if the piece is a pawn
              if (playerChoiceX - playerMoveX == 0){ //if the x coordinate has not changed
                if (playerChoiceY == 6){ //if it is the first turn
                  if (playerChoiceY - playerMoveY == 1 || playerChoiceY - playerMoveY == 2){ //if the piece has moved one or two spaces
                    if (chessBoard[playerMoveY][playerMoveX] == null){
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, pawn2, chessBoardGui, 'p');
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }else{
                    chessBoardGui.errMessage();
                  }
                }else if (playerChoiceY - playerMoveY == 1){ //if the piece has moved one space
                  if (chessBoard[playerMoveY][playerMoveX] == null){
                    if (playerMoveY == 0){ //if the pawn reaches the opposite end of the board to become a queen
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                    }else{
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, pawn2, chessBoardGui, 'p');
                    }
                  }else{
                    chessBoardGui.errMessage();
                  }
                }else{
                  chessBoardGui.errMessage();
                }
              }else if (playerChoiceX - playerMoveX == 1 || playerChoiceX - playerMoveX == -1){ //if the move is diagonal
                if (playerChoiceY - playerMoveY == 1){
                  if (chessBoard[playerMoveY][playerMoveX] != null){
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, pawn2, chessBoardGui, 'p');
                  }else{
                    chessBoardGui.errMessage();
                  }
                }else{
                  chessBoardGui.errMessage();
                }
              }else{
                chessBoardGui.errMessage();
              }
            }else if (chessBoard[playerChoiceY][playerChoiceX] == tower2){ //if the piece is a tower
              if (playerChoiceY - playerMoveY == 0 && playerChoiceX - playerMoveX != 0){ //if the tower moves along x axis
                if (playerChoiceX - playerMoveX < 0){ //if it is moving left
                  if (playerMoveX - playerChoiceX - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower2, chessBoardGui, 't');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower2, chessBoardGui, 't');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else{ //if it is moving right 
                  if (playerChoiceX - playerMoveX - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower2, chessBoardGui, 't');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower2, chessBoardGui, 't');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }    
              }else if (playerChoiceY - playerMoveY != 0 && playerChoiceX - playerMoveX == 0){ //if the tower moves along the y axis
                if (playerChoiceY - playerMoveY < 0){ //if it is moving down
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower2, chessBoardGui, 't');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower2, chessBoardGui, 't');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else{ //if it is moving up
                  if (playerChoiceY - playerMoveY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower2, chessBoardGui, 't');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, tower2, chessBoardGui, 't');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }
              }else{
                chessBoardGui.errMessage();
              }
            }else if (chessBoard[playerChoiceY][playerChoiceX] == knight2){ //if the piece is a knight
              if ((Math.abs(playerChoiceX - playerMoveX) == 2) && (Math.abs(playerChoiceY - playerMoveY) == 1)){ //if it moves 2 spaces on the x axis and one on the y axis
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, knight2, chessBoardGui, 'k');
              }else if((Math.abs(playerChoiceX - playerMoveX) == 1) && (Math.abs(playerChoiceY - playerMoveY) == 2)){ //if it moves 2 spaces on the y axis and one on the x axis
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, knight2, chessBoardGui, 'k');
              }else{
                chessBoardGui.errMessage();
              }
              //dont need to worry about spaces (can jump over pieces)
            }else if (chessBoard[playerChoiceY][playerChoiceX] == bishop2){ //if the piece is a bishop
              if (Math.abs(playerChoiceX - playerMoveX) == Math.abs(playerChoiceY - playerMoveY)){ //if it moves the same amount vertically and horizontally
                if (playerChoiceX - playerMoveX > 0 && playerChoiceY - playerMoveY < 0){ //if it is going down and left
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop2, chessBoardGui, 'b');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop2, chessBoardGui, 'b');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else if (playerChoiceX - playerMoveX < 0 && playerChoiceY - playerMoveY < 0){ //if it is going down and right
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop2, chessBoardGui, 'b');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop2, chessBoardGui, 'b');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else if (playerChoiceX - playerMoveX > 0 && playerChoiceY - playerMoveY > 0){ //if it is going up and left
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop2, chessBoardGui, 'b');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop2, chessBoardGui, 'b');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else{ //if it is going up and right
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop2, chessBoardGui, 'b');
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, bishop2, chessBoardGui, 'b');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }
              }else{
                chessBoardGui.errMessage();
              }
            }else if (chessBoard[playerChoiceY][playerChoiceX] == queen2){ //if the piece is a queen
              if (Math.abs(playerChoiceY - playerMoveY) != Math.abs(playerChoiceX - playerMoveX)){ //if it is moving horizontally or vertically
                if (playerChoiceY - playerMoveY == 0 && playerChoiceX - playerMoveX != 0){ //if the queen moves along x axis
                  if (playerChoiceX - playerMoveX < 0){ //if it is moving left
                    if (playerMoveX - playerChoiceX - 1 == 0){ //if there are no spaces
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                      
                    }else{ //if there are spaces
                      
                      if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                        movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                        
                      }else{
                        chessBoardGui.errMessage();
                      }
                    }
                  }else{ //if it is moving right 
                    if (playerChoiceX - playerMoveX - 1 == 0){ //if there are no spaces
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                      
                    }else{ //if there are spaces
                      
                      if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                        movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                        
                      }else{
                        chessBoardGui.errMessage();
                      }
                    }
                  }    
                }else if (playerChoiceY - playerMoveY != 0 && playerChoiceX - playerMoveX == 0){ //if the piece moves along the y axis
                  if (playerChoiceY - playerMoveY < 0){ //if it is moving down
                    if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                      
                    }else{ //if there are spaces
                      
                      if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                        movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                        
                      }else{
                        chessBoardGui.errMessage();
                      }
                    }
                  }else{ //if it is moving up
                    if (playerChoiceY - playerMoveY - 1 == 0){ //if there are no spaces
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                      
                    }else{ //if there are spaces
                      
                      if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                        movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                        
                      }else{
                        chessBoardGui.errMessage();
                      }
                    }
                  }
                }else{
                  chessBoardGui.errMessage();
                }
              }else if(Math.abs(playerChoiceX - playerMoveX) == Math.abs(playerChoiceY - playerMoveY)){ //if piece is moving diagonally
                
                if (playerChoiceX - playerMoveX > 0 && playerChoiceY - playerMoveY < 0){ //if it is going down and left
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else if (playerChoiceX - playerMoveX < 0 && playerChoiceY - playerMoveY < 0){ //if it is going down and right
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else if (playerChoiceX - playerMoveX > 0 && playerChoiceY - playerMoveY > 0){ //if it is going up and left
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }else{ //if it is going up and right
                  if (playerMoveY - playerChoiceY - 1 == 0){ //if there are no spaces
                    movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                    
                  }else{ //if there are spaces
                    
                    if (checkSpaces(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY) == true){ //if the spaces are empty
                      movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, queen2, chessBoardGui, 'q');
                      
                    }else{
                      chessBoardGui.errMessage();
                    }
                  }
                }
                
              }else{
                chessBoardGui.errMessage();
              }
              
            }else if (chessBoard[playerChoiceY][playerChoiceX] == king2){ //if the piece is a king
              if (playerChoiceX - playerMoveX == 1 && playerChoiceY - playerMoveY == 0){ //if the king is moving left
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king2, chessBoardGui, 'K');
              }else if (playerMoveX - playerChoiceX == 1 && playerChoiceY - playerMoveY == 0){ //if the king is moving right
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king2, chessBoardGui, 'K');
              }else if (playerChoiceY - playerMoveY == 1 && playerMoveX - playerChoiceX == 0){ //if the king is moving up
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king2, chessBoardGui, 'K');
              }else if (playerMoveY - playerChoiceY == 1 && playerMoveX - playerChoiceX == 0){ //if the king is moving down
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king2, chessBoardGui, 'K');
              }else if (playerMoveX - playerChoiceX == 1 && playerChoiceY - playerMoveY == 1){ //if the king is moving right and up
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king2, chessBoardGui, 'K');
              }else if (playerChoiceX - playerMoveX == 1 && playerChoiceY - playerMoveY == 1){ //if the king is moving left and up
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king2, chessBoardGui, 'K');
              }else if (playerMoveX - playerChoiceX == 1 && playerMoveY - playerChoiceY == 1){ //if the king is moving left and down
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king2, chessBoardGui, 'K');
              }else if (playerChoiceX - playerMoveX == 1 && playerMoveY - playerChoiceY == 1){ //if the king is moving right and down
                movePiece(chessBoard, playerChoiceX, playerChoiceY, playerMoveX, playerMoveY, king2, chessBoardGui, 'K');
              }else{
                chessBoardGui.errMessage();
              }
            }
            
          }else{
            chessBoardGui.errMessage();
          }
        }else{
          chessBoardGui.errMessage();
        }
        
      }
      
      //print chess board 1 and check the amount of pieces both players have
      for(int i = 0; i < chessBoard.length; i++){
        for(int j = 0; j < chessBoard[i].length; j++){
          if (chessBoard[i][j] != null){
            if (chessBoard[i][j].getTeam() == 1)
              player1Pieces += 1;
            else
              player2Pieces += 1;
            if (chessBoard[i][j] == king1){
              king1Status = true;
            }else if(chessBoard[i][j] == king2){
              king2Status = true;
            }
          }else{ 
          }
        }
      }
      
      if (king1Status == false){ //if player 1's king is dead
        winCondition = true;
        chessBoardGui.playerTurnRemove();
        chessBoardGui.player2Winner();
      }else if (king2Status == false){ //if player 2's king is dead 
        winCondition = true;
        chessBoardGui.playerTurnRemove();
        chessBoardGui.player1Winner();
      }else{
        
      }
      
    }
    
//    try{
//      FileWriter fw = new FileWriter("WinningScores.txt"); //file called WinningScores.txt
//      BufferedWriter WriteFileBuffer = new BufferedWriter(fw);
//      
//      if (oldScore > gameNumMoves){
//        if (gameNumMoves % 2 == 0){
//          WriteFileBuffer.write(Integer.toString(gameNumMoves / 2));
//        }else{
//          WriteFileBuffer.write(Integer.toString((gameNumMoves + 1) / 2));
//        }
//      }else{
//        WriteFileBuffer.write(Integer.toString(oldScore));
//      }
//      
//      WriteFileBuffer.newLine();
//      WriteFileBuffer.close();
//    }catch(java.io.IOException e){
//      System.out.println(e);
//    }
    
  }//Main
  
  /**
   * checks if spaces in between the selected original coordinate and the coordinate you want to move to are empty when necessary
   * @param ChessPiece array to check spaces, int for x coordinate choice, int for y coordinate choice, int for x coordinate move, int for y coordinate move
   * @return boolean stating whether or not the spaces are empty
   */
  public static boolean checkSpaces(ChessPiece[][] board, int choiceX, int choiceY, int moveX, int moveY){
    
    boolean ifEmptySpaces = true;
    int spacing = 0;
    
    if (choiceY - moveY > 0 && choiceX - moveX == 0){ //if it is moving up
      
      spacing = choiceY - moveY - 1;   
      for(int i = 1; i <= spacing; i++){    
        if (board[moveY + i][moveX] == null){
        }else{
          ifEmptySpaces = false;
        }
      }
      
    }else if(choiceY - moveY < 0 && choiceX - moveX == 0){ //if it is moving down
      
      spacing = moveY - choiceY - 1;   
      for(int i = 1; i <= spacing; i++){   
        if (board[moveY - i][moveX] == null){ 
        }else{
          ifEmptySpaces = false;
        }
      }
      
    }else if(choiceY - moveY == 0 && choiceX - moveX > 0){ //if it is moving left
      
      spacing = choiceX - moveX - 1;   
      for(int i = 1; i <= spacing; i++){
        if (board[moveY][moveX + i] == null){
        }else{
          ifEmptySpaces = false;
        }
      }
      
    }else if(choiceY - moveY == 0 && choiceX - moveX < 0){ //if it is moving right
      
      spacing = moveX - choiceX - 1;   
      for(int i = 1; i <= spacing; i++){          
        if (board[moveY][moveX - i] == null){ 
        }else{
          ifEmptySpaces = false;
        }                      
      }
      
    }else if(choiceY - moveY > 0 && choiceX - moveX > 0){ //if it is moving up and left
      
      spacing = choiceX - moveX - 1;
      for(int i = 1; i <= spacing; i++){
        if (board[moveY + i][moveX + i] == null){  
        }else{
          ifEmptySpaces = false;
        }
      }
      
    }else if(choiceY - moveY > 0 && choiceX - moveX < 0){ //if it is moving up and right
      
      spacing = moveX - choiceX - 1;             
      for(int i = 1; i <= spacing; i++){ 
        if (board[moveY + i][moveX - i] == null){  
        }else{
          ifEmptySpaces = false;
        }
      }
      
    }else if(choiceY - moveY < 0 && choiceX - moveX > 0){ //if it is moving down and left
      
      spacing = choiceX - moveX - 1;       
      for(int i = 1; i <= spacing; i++){ 
        if (board[moveY - i][moveX + i] == null){
        }else{
          ifEmptySpaces = false;
        } 
      }
      
    }else if(choiceY - moveY < 0 && choiceX - moveX < 0){ //if it is moving down and right
      
      spacing = moveX - choiceX - 1; 
      for(int i = 1; i <= spacing; i++){
        if (board[moveY - i][moveX - i] == null){
        }else{
          ifEmptySpaces = false;
        }
      }
      
    }else{
      ifEmptySpaces = false; 
    }
    
    return ifEmptySpaces;
    
  }//checkSpaces
   
  /**
   * moves the selected piece to the selected position both on the array and the GUI, then updates the player turn and the amount of rounds in the game and print which player's turn it is
   * @param ChessPiece array in order to update array, int for x coordinate choice, int for y coordinate choice, int for x coordinate move, int for y coordinate move,ChessPiece object to obtain information about the chess piece, ChessBoardGUI for updating the GUI, char for the piece name for GUI  
   */
  public static void movePiece(ChessPiece[][] board, int choiceX, int choiceY, int moveX, int moveY, ChessPiece c, ChessBoardGUI g, char piece){
    board[choiceY][choiceX] = null;
    board[moveY][moveX] = c; 
    
    g.removeIcon(g.getButtonArray(), moveY, moveX);
    
    if (c.getTeam() == 1){ //black
      g.giveIcon(g.getButtonArray(), moveY, moveX, piece, 1);
    }else if(c.getTeam() == 2){ //white
      g.giveIcon(g.getButtonArray(), moveY, moveX, piece, 2);
    }
    
    g.removeIcon(g.getButtonArray(), choiceY, choiceX);
    
    playerTurn += 1;
    gameNumMoves += 1;
  
    
    if(playerTurn % 2 + 1 == 1){
      g.playerOneTurn();
    }else{
      g.playerTwoTurn();
    }
    
  }//movePiece
  
}//ChessGame

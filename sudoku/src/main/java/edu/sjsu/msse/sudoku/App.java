//File Name:App.java
//Author:Project Group#4
//Team Members:Shivani Mangal(012530362),Premal Dattatray Samale(012566333),Deepti Srinivasan(012557909)       
//Project Tile:Sudoku Solution Validator
//Description: This project aims at designing a multithreaded application that determines if the solution
//to a sudoku puzzle is valid.
//Last Changed:April 24,2018

package edu.sjsu.msse.sudoku;

import java.io.IOException;

public class App {
    public static void main( String[] args ) throws IOException, InvalidFileContentException, InterruptedException {
        InputHandler inputHandler = new InputHandler();
        int[][] sudoku = inputHandler.getSudoku();      //Reads input sudoku file and store value in 2D Array

        SingleThreadedSudoku singleThreadedSudoku = new SingleThreadedSudoku();
        singleThreadedSudoku.validateSudoku(sudoku);    //Validate sudoku with single thread

        MultiThreadedSudoku multiThreadedSudoku = new MultiThreadedSudoku();
        multiThreadedSudoku.validateSudoku(sudoku);     //Validate sudoku with multiple threads
    }
}
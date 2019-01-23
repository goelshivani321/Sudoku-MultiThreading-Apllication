//File Name:SingleThreadedSudoku.java
//Author:Project Group#4
//Team Members:Shivani Mangal(012530362),Premal Dattatray Samale(012566333),Deepti Srinivasan(012557909)       
//Project Tile:Sudoku Solution Validator
//Description: This project aims at designing a multithreaded application that determines if the solution
//to a sudoku puzzle is valid.
//Last Changed:April 24,2018

package edu.sjsu.msse.sudoku;

import java.util.HashSet;

public class SingleThreadedSudoku {
    static boolean isValidSudoku = true;
    public void validateSudoku(int[][] sudoku) {
        SingleThreadedSudoku singleThreadedSudoku = new SingleThreadedSudoku();
        long start = System.nanoTime();
        for(int i=0; i<9; i++) {
            if (singleThreadedSudoku.validateRow(sudoku, i) == false) {
                isValidSudoku = false;
            }
        }
        for(int i=0; i<9; i++) {
            if (singleThreadedSudoku.validateColumn(sudoku, i) == false) {
                isValidSudoku = false;
            }
        }
        for(int i=0; i<9; i++) {
            if (singleThreadedSudoku.validateBlock(sudoku, i) == false) {
                isValidSudoku = false;
                break;
            }
        }
        long elapsedTime = System.nanoTime() - start;
        System.out.println("\n");
        System.out.println("******Single Threaded Application******");
        System.out.println("Is Sudoku solution correct? -> " + isValidSudoku);
        System.out.println("Time required for execution in nano-seconds: " + elapsedTime);
    }

    private boolean validateRow(int[][] sudoku, int row) {
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int i=0; i<9; i++) {
            hs.add(sudoku[row][i]);
        }
        return hs.size()==9;
    }

    private boolean validateColumn(int[][] sudoku, int column) {
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int i=0; i<9; i++) {
            hs.add(sudoku[i][column]);
        }
        return hs.size()==9;
    }

    private boolean validateBlock(int[][] sudoku, int blockNumber) {
        HashSet<Integer> hs = new HashSet<Integer>();
        int row = blockNumber/3;
        int column = blockNumber%3;
        row*=3;
        column*=3;
        for(int i=row; i<row+3; i++) {
            for(int j=column; j<column+3; j++) {
                hs.add(sudoku[i][j]);
            }
        }
        return hs.size()==9;
    }
}

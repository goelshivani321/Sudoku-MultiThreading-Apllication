//File Name:MultiThreadedSudoku.java
//Author:Project Group#4
//Team Members:Shivani Mangal(012530362),Premal Dattatray Samale(012566333),Deepti Srinivasan(012557909)       
//Project Tile:Sudoku Solution Validator
//Description: This project aims at designing a multithreaded application that determines if the solution
//to a sudoku puzzle is valid.Total 27 threads are created: 9 threads for nine columns,
//9 threads for nine rows and 9 threads to check each 3x3 grid for digits 1 to 9.
//Last Changed:April 24,2018


package edu.sjsu.msse.sudoku;

import java.util.ArrayList;
import java.util.HashSet;

public class MultiThreadedSudoku implements Runnable{
    static boolean isValidSudoku = true;    //Flag to decide Sudoku validation 
    private static int[][] globalsudoku;
    public void validateSudoku(int[][] sudoku) throws InterruptedException {
        this.globalsudoku=sudoku;
        ArrayList<Thread> threadList = new ArrayList();
        for(int i=0; i<9; i++) {
            Thread thread = new Thread(this, "Row"+i);//9 threads created for nine Rows,to check each row for digits1 to 9
            threadList.add(thread);
            thread.start();
        }
        for(int i=0; i<9; i++) {
            Thread thread = new Thread(this, "Column"+i);//9 threads created for nine Columns,to check each column for digits1 to 9
            threadList.add(thread);
            thread.start();
        }
        for(int i=0; i<9; i++) {
            Thread thread = new Thread(this, "Block"+i);//9 threads created to check each 3x3 grid for digits 1 to 9
            threadList.add(thread);
            thread.start();
        }
        long start = System.nanoTime(); //start time for sudoku validation
        for (Thread thread : threadList) {
            thread.join();
        }
        long elapsedTime = System.nanoTime() - start;   //endtime
        System.out.println("\n");
        System.out.println("******Multi Threaded Application******");
        System.out.println("Is Sudoku solution correct? -> " + isValidSudoku);
        System.out.println("Time required for execution in nano-seconds: " + elapsedTime);
    }

    //Run method for total 27 threads to decide sudoku is valid or not.
    public void run() {
        Thread thread = Thread.currentThread();
        String threadName = thread.getName();
        String digit=threadName.substring(threadName.length()-1, threadName.length());
        int number=Integer.parseInt(digit);
        String section=threadName.substring(0, threadName.length()-1);
        if(section.equals("Row")) {
            boolean flag=validateRow(globalsudoku,number);
            if(flag==false) {
                isValidSudoku=false;
            }
        }
        if(section.equals("Column")) {
            boolean flag=validateColumn(globalsudoku,number);
            if(flag==false) {
                isValidSudoku=false;
            }
        }
        if(section.equals("Block")) {
            boolean flag=validateBlock(globalsudoku,number);
            if(flag==false) {
                isValidSudoku=false;
            }
        }
    }

    //Function to validate Row:checks row for digits1 to 9
    private boolean validateRow(int[][] sudoku, int row) {
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int i=0; i<9; i++) {
            hs.add(sudoku[row][i]);
        }
        return hs.size()==9;
    }
    
  //Function to validate Column: checks column for digits1 to 9
    private boolean validateColumn(int[][] sudoku, int column) {
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int i=0; i<9; i++) {
            hs.add(sudoku[i][column]);
        }
        return hs.size()==9;
    }

  //Function to validate Block:checks block of 3x3 grid for digits 1 to 9
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

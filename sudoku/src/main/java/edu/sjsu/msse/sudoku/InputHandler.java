//File Name:InputHandler.java
//Author:Project Group#4
//Team Members:Shivani Mangal(012530362),Premal Dattatray Samale(012566333),Deepti Srinivasan(012557909)       
//Project Tile:Sudoku Solution Validator
//Description: This project aims at designing a multithreaded application that determines if the solution
//to a sudoku puzzle is valid.
//Last Changed:April 24,2018

package edu.sjsu.msse.sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class InputHandler {

    public int[][] getSudoku() throws IOException, InvalidFileContentException {
        String fileName = getInputFileName();
        ArrayList<String> fileContent = readInputFile(fileName);
        int[][] result = prepareOutputFromFileContent(fileContent);
        print(result);
        return result;
    }

    // Function to read the Sudoku input file name
    private String getInputFileName() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the file name:");
        return br.readLine();
    }

    // Function to read the Sudoku file contents
    private ArrayList<String> readInputFile(String fileName) throws IOException, InvalidFileContentException {
        ArrayList<String> fileContent = new ArrayList<String>();
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            fileContent.add(currentLine);
            if (fileContent.size() > 9)
                throw new InvalidFileContentException("Input file contains more than 9 lines");
        }
        return fileContent;
    }

    // Function to validate input file contents and store values in 2D array
    private int[][] prepareOutputFromFileContent(ArrayList<String> fileContent) throws InvalidFileContentException {
        int[][] result = new int[9][9];
        int index = 0;
        for (String s : fileContent) {
            if (s.length() != 9)
                throw new InvalidFileContentException("Each line of input file should have exactly 9 numbers");
            int[] row = new int[9];
            for (int i = 0; i < 9; i++) {
                row[i] = Integer.parseInt(s.charAt(i) + "");
            }
            result[index++] = row;
        }
        return result;
    }

    // Function to display the Sudoku input
    private void print(int[][] result) {
        System.out.println("\n");
        System.out.println("******Sudoku Input******");
        for (int i = 0; i < result.length; i++) {
            if (i == 3 || i == 6) { // adding new line to understand sudoku input better
                System.out.println();
            }
            for (int j = 0; j < result[i].length; j++) {
                if (j == 3 || j == 6) { // adding space to understand sudoku input better
                    System.out.print(" ");
                }
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
    }
}

//If file contents are invalid then gets InvalidFileContentException
class InvalidFileContentException extends Exception {
    InvalidFileContentException(String s) {
        super(s);
    }
}
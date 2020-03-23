/*
Author: Saikiran Nuthakki
E-mail: sqn5261@psu.edu
Course: CMPSC 221
Assignment: Programming Assignment 4
Due date: 4/4/2019
File: Onlinetutor.java
Purpose: Java application that implements an online tutoring window
Compiler/IDE: IntelliJ IDEA
Operating system: Mac OS Sierra
Reference(s): Java 8 API - Oracle Documentation
*/

package com.company;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Onlinetutor {

    private JFrame MathTutor;
    private JTextField Answer;
    JLabel Question;
    JLabel checker;
    int ans, first, second, attempt;
    String problem;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Onlinetutor window = new Onlinetutor();
                    window.MathTutor.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String operator(int op) {
        String str = " ";
        if (op == 1) {
            str += "plus ";
        } else if (op == 2) {
            str += "minus ";
        } else if (op == 3) {
            str += "times ";
        }
        return str;
    }
    public Onlinetutor() {
        initialize();
        run(1, 3);

    }

    private void run(int low, int high) {
        checker.setText("Please enter your answer:");
        problem = (generateProblem(low, high));
        Question.setText(problem);
        attempt = 1;

    }

    public String generateProblem(int low, int high) {
        String str = "How much is ";
        first = (int) (Math.random() * high) + low;
        ans = first;
        str += first;
        int ops = (int) (Math.random() * 3) + 1;
        str += operator(ops);
        second = (int) (Math.random() * high) + low;
        str += second + "?";
        return str;
    }


    private int getAnswer() {
        int solution = 0 ;
        if (problem.contains("plus")) {
            solution = first + second;
        }
        if (problem.contains("minus")) {
            solution = first - second;
        }
        if (problem.contains("times")) {
            solution = first * second;
        }
        return solution;
    }


    private void checkAnswer(String user) {
        String input = user;
        try {
            int num = Integer.parseInt(input);
            if (getAnswer() == num) {
                checker.setText("Very good! it only took you " + attempt + " try.");
                attempt = 1;
            } else if (getAnswer() != num) {
                checker.setText("I'm sorry, but no. Please try again.");
                attempt++;
            }
        } catch (Exception e) {
            checker.setText("I'm sorry, but no. Please try again.");
            attempt++;
        }
    }



    private void initialize() {
        MathTutor = new JFrame();
        MathTutor.setTitle("Math Tutor");
        MathTutor.getContentPane().setBackground(Color.YELLOW);
        MathTutor.getContentPane().setForeground(Color.YELLOW);
        MathTutor.setBounds(120, 120, 450, 300);
        MathTutor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MathTutor.getContentPane().setLayout(null);

        Question = new JLabel("Question");
        Question.setBounds(130, 30, 315, 20);
        MathTutor.getContentPane().add(Question);

        checker = new JLabel("Please enter your answer:");
        checker.setBounds(6, 131, 252, 16);
        MathTutor.getContentPane().add(checker);

        Answer = new JTextField();
        Answer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkAnswer(Answer.getText());
                }
            }
        });
        Answer.setBounds(265, 126, 130, 26);
        MathTutor.getContentPane().add(Answer);
        Answer.setColumns(10);

        JButton btnNewProblem = new JButton("New Problem");
        btnNewProblem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                run(1, 10);
            }
        });
        btnNewProblem.setBounds(159, 219, 117, 29);
        MathTutor.getContentPane().add(btnNewProblem);
    }
}
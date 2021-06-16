package isu;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;


public class GameMain extends Frame implements ActionListener{
	//Grant Nadon
	//Creates a math quiz game that accepts user input
	//15-06-2021
	JLabel jl, score, time;//Declares all the public variables
	JTextField userIn;
	JButton start, check;
	JFrame f;
	Timer timer;
	static int lives = 3, points, answer, interval;
	
	GameMain(){
		//Declaring the frame and its components
		f = new JFrame("Math Quiz");
		jl = new JLabel("Welcome to the math quiz!");
	    jl.setBounds(75,50,200,40);
	    userIn = new JTextField();
	    userIn.setBounds(90,200,100,20);
	    score = new JLabel("Score: 0 Lives: 3");
	    score.setBounds(20, 220, 200, 20);
	    time = new JLabel("Time: 60");
	    time.setBounds(20, 20, 100, 20);
	    start = new JButton("Start");
	    start.setBounds(100,100,100,40);
	    check = new JButton("Check");
	    check.setBounds(200,200,80,20);
	    start.addActionListener(this);
	    check.addActionListener(this);
	    
	    f.add(jl); f.add(start);f.add(score);f.add(userIn);f.add(check);f.add(time);
	    score.setVisible(false); userIn.setVisible(false); time.setVisible(false); check.setVisible(false);
	    f.setSize(300,300);  
        f.setLayout(null);  
        f.setResizable(false);
        f.setVisible(true);
	    
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==start) {//starts the game
			score.setVisible(true); userIn.setVisible(true); time.setVisible(true); check.setVisible(true);
			start.setVisible(false);
			start.invalidate();
			
			countdown();
			answer = generateQ();
		}else if (e.getSource()==check) {//checks values and continues the game
			String st = userIn.getText();
			int a = Integer.parseInt(st);
			
			if (a == answer) {
				points += 100;
				score.setText("Score: " + points + " Lives: " + lives);
				userIn.setText("");
				answer = generateQ();
			}else {
				lives --;
				score.setText("Score: " + points + " Lives: " + lives);
				userIn.setText("");
				answer = generateQ();
			}
			
			if (lives == 0) {//declares that the game is over
				gameOver();
			}
		}
		
	}
	public int generateQ() {
		int num1, num2, operation, answer; //generates the random questions
		num1 = (int) (Math.random()*50)+1;
		num2 = (int) (Math.random()*50)+1;
		operation = (int) (Math.random()*2)+1;
		if (operation == 1) {
			answer = num1 + num2;
			jl.setText(num1 + " + " + num2 + " = ?");
			return answer;
		}else {
			answer = num1 - num2;
			jl.setText(num1 + " - " + num2 + " = ?");
			return answer;
		}
	}
	public void countdown() {
		timer = new Timer();//declares the timer
		interval = 60;//Sets how long the timer will run for
	    timer.scheduleAtFixedRate(new TimerTask() {


			public void run() {//updates the timer on the UI and checks to see if the user ran out of time
				if (interval == 1) {
					gameOver();
				}
				interval--;
				time.setText("Time: " + interval);
			}


	    }, 1000, 1000);//sets the delay and period

	}
	public void gameOver() {//let's the user know the game is done and removes unnecessary elements
		timer.cancel();
		userIn.setVisible(false);
		userIn.invalidate();
		check.setVisible(false);
		check.invalidate();
		score.setVisible(false);
		score.invalidate();
		time.setVisible(false);
		time.invalidate();
		jl.setText("Game Over! Final Score: " + points);
	}

	public static void main(String[] args) {
		new GameMain();
		
	}


}

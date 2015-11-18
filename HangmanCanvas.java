/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Font;

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;

public class HangmanCanvas extends GCanvas {

	/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		getReady();
		incorrectGuessCount = 0;
		xCoordinateLabel = 50;
	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		if (gl == null) {
			drawWord(word);
		} else {
			remove(gl);
			drawWord(word);
		}
	}

	/* displays the current condition of the word */
	private void drawWord(String word) {
		getReady();
		gl = new GLabel(word, 50, 420);
		gl.setFont(new Font(word, Font.ROMAN_BASELINE, 18));
		add(gl);
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user.
	 * Calling this method causes the next body part to appear on the scaffold
	 * and adds the letter to the list of incorrect guesses that appears at the
	 * bottom of the window.
	 */
	public void noteIncorrectGuess(char letter) {
		incorrectGuessCount++;
		drawLetters(letter);
		switch (incorrectGuessCount) {
		case 1:
			drawHead();
			break;
		case 2:
			drawBody();
			break;
		case 3:
			drawLeftArm();
			break;
		case 4:
			drawRightArm();
			break;
		case 5:
			drawLeftLeg();
			break;
		case 6:
			drawRightLeg();
			break;
		case 7:
			drawLeftFoot();
			break;
		case 8:
			drawRightFoot();
			break;
		default:
			break;
		}

	}

	/*
	 * this method draw the incorrect letter, +25 it means that the distance
	 * between every incorrect letter is 25
	 */
	private void drawLetters(char letter) {
		GLabel gl = new GLabel(letter + "", xCoordinateLabel, 450);
		gl.setFont(new Font(letter + "", Font.ROMAN_BASELINE, 18));
		add(gl);
		xCoordinateLabel += 25;
	}

	/* draws the scaffold,beam and rope */
	private void getReady() {
		drawScaffold();
		drawBeam();
		drawRope();
	}

	/* this method takes coordinates of the line and draws it */
	private void drawPart(double x0, double y0, double x1, double y1) {
		GLine bodyPart = new GLine(x0, y0, x1, y1);
		add(bodyPart);
	}

	/* draws scaffold */
	private void drawScaffold() {
		double x = xCoordinate - BEAM_LENGTH;
		drawPart(x, yCoodrinate + SCAFFOLD_HEIGHT, x, yCoodrinate - ROPE_LENGTH);
	}

	/* draws beam */
	private void drawBeam() {
		double y = yCoodrinate - ROPE_LENGTH;
		drawPart(xCoordinate - BEAM_LENGTH, y, xCoordinate, y);
	}

	/* draws rope */
	private void drawRope() {
		drawPart(xCoordinate, yCoodrinate, xCoordinate, yCoodrinate
				- ROPE_LENGTH);
	}

	/* draw head */
	private void drawHead() {
		GOval head = new GOval(xCoordinate - HEAD_RADIUS, yCoodrinate,
				2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
		add(head);
	}

	/* draws body */
	private void drawBody() {
		double y = yCoodrinate + 2 * HEAD_RADIUS;
		drawPart(xCoordinate, y, xCoordinate, y + BODY_LENGTH);
	}

	/* draws left arm */
	private void drawLeftArm() {
		double y1 = yCoodrinate + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		drawPart(xCoordinate, y1, xCoordinate - UPPER_ARM_LENGTH, y1);

		double x2 = xCoordinate - UPPER_ARM_LENGTH;
		double y2 = yCoodrinate + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		drawPart(x2, y2, x2, y2 + LOWER_ARM_LENGTH);
	}

	/* draw right arm */
	private void drawRightArm() {
		double y1 = yCoodrinate + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		drawPart(xCoordinate, y1, xCoordinate + UPPER_ARM_LENGTH, y1);

		double x2 = xCoordinate + UPPER_ARM_LENGTH;
		double y2 = yCoodrinate + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		drawPart(x2, y2, x2, y2 + LOWER_ARM_LENGTH);
	}

	/* draw left leg */
	private void drawLeftLeg() {
		double y1 = yCoodrinate + 2 * HEAD_RADIUS + BODY_LENGTH;
		drawPart(xCoordinate, y1, xCoordinate - HIP_WIDTH, y1);

		double x2 = xCoordinate - HIP_WIDTH;
		double y2 = yCoodrinate + 2 * HEAD_RADIUS + BODY_LENGTH;
		drawPart(x2, y2, x2, y2 + LEG_LENGTH);
	}

	/* draws left foot */
	private void drawLeftFoot() {
		double x = xCoordinate - HIP_WIDTH;
		double y = yCoodrinate + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		drawPart(x, y, x - FOOT_LENGTH, y);
	}

	/* draws right leg */
	private void drawRightLeg() {
		double y1 = yCoodrinate + 2 * HEAD_RADIUS + BODY_LENGTH;
		drawPart(xCoordinate, y1, xCoordinate + HIP_WIDTH, y1);

		double x2 = xCoordinate + HIP_WIDTH;
		double y2 = yCoodrinate + 2 * HEAD_RADIUS + BODY_LENGTH;
		drawPart(x2, y2, x2, y2 + LEG_LENGTH);
	}

	/* draws right foot */
	private void drawRightFoot() {
		double x = xCoordinate + HIP_WIDTH;
		double y = yCoodrinate + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		drawPart(x, y, x + FOOT_LENGTH, y);
	}

	/*
	 * this public method gets the x coordinate from the Hangman class. with the
	 * help of this method we know the value of getWidth()
	 */
	public void setCoordinate(double x) {
		xCoordinate = x / 2;
	}

	private double xCoordinate = 0;// x and y coordinate for drawing the
									// "კაცუნა"
	private double yCoodrinate = 30;

	private GLabel gl;
	private int incorrectGuessCount = 0;
	private double xCoordinateLabel = 50;

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
}

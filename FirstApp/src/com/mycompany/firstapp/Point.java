package com.mycompany.firstapp;

import android.graphics.Color;

/**
 * Created by nathan on 8/12/13.
 */
public class Point {
	float x, y, radius;
	int color;
	@Override
	public String toString( ){
		return x + ", " + y + ", " + radius + " c (" + color + ")";
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
/*
 * Copyright (c) 2003, jMonkeyEngine - Mojo Monkey Coding
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this 
 * list of conditions and the following disclaimer. 
 * 
 * Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution. 
 * 
 * Neither the name of the Mojo Monkey Coding, jME, jMonkey Engine, nor the 
 * names of its contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */

package jme.lighting;

import jme.exception.MonkeyRuntimeException;
import jme.math.Vector;


/**
 * <code>AbstractLightMap</code> manages a data structure that contains
 * light values. The structure is set up as a two dimensional array of 
 * floats, where the particular value of (x,y) is a float from 0 to 1. 
 * Where 1 is full brightness and 0 is complete darkness. It is intended
 * to be used with the glColor*f method. 
 * 
 * As such, the class maintains a color that will define what color the 
 * light is. This allows for interesting effects, where an earth terrain
 * may be (1,1,1), but a Mars terrain would be something like (1,0.4,0.7).
 * 
 * @author Mark Powell
 * @version $Id: AbstractLightMap.java,v 1.2 2003-09-03 18:05:36 mojomonkey Exp $
 */
public abstract class AbstractLightMap {

	/**
	 * the array of values corresponding to brightness values.
	 */
	protected float[][] lightMap;

	/**
	 * the vector that defines the color the light is casting.
	 */
	protected Vector color;

	/**
	 * <code>getShade</code> returns the shade value for the
	 * given coordinates of the lightmap. The shade is a value
	 * between 0 and 1. 
	 * @param x the x coordinate of the light map.
	 * @param z the z coordinate of the light map.
	 * @return the shade value of the coordinate (between 0 and 1).
	 */
	public float getShade(int x, int z) {
		return lightMap[x][z];
	}

	/**
	 * <code>getColor</code> returns the color vector of the lightmap.
	 * @return the color vector of the lightmap.
	 */
	public Vector getColor() {
		return color;
	}

	/**
	 * <code>setColor</code> sets the color vector of the lightmap.
	 * @param color the new color for the lightmap.
	 * @throws MonkeyRuntimeException if color is null.
	 */
	public void setColor(Vector color) {
		if(null == color) {
			throw new MonkeyRuntimeException("Color cannot be null");
		}
		this.color = color;
	}
}

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
package com.jme.math;

import java.util.logging.Level;

import com.jme.util.LoggingSystem;

/**
 * <code>Vector3f</code> defines a Vector for a three float value tuple. 
 * @author Mark Powell
 * @version $Id: Vector3f.java,v 1.5 2004-02-19 23:12:03 mojomonkey Exp $
 */
public class Vector3f {
    /**
     * the x value of the vector.
     */
    public float x;
    /**
     * the y value of the vector.
     */
    public float y;
    /**
     * the z value of the vector.
     */
    public float z;
    
    /**
     * Constructor instantiates a new <code>Vector3f</code> with
     * default values of (0,0,0).
     *
     */
    public Vector3f() {
        x = y = z = 0;
    }
    
    /**
     * Constructor instantiates a new <code>Vector3f</code> with provides
     * values.
     * @param x the x value of the vector.
     * @param y the y value of the vector.
     * @param z the z value of the vector.
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * 
     * <code>add</code> adds a provided vector to this vector creating a 
     * resultant vector which is returned. If the provided vector is null,
     * null is returned.
     * @param vec the vector to add to this.
     * @return the resultant vector.
     */
    public Vector3f add(Vector3f vec) {
        if(null == vec) {
            LoggingSystem.getLogger().log(Level.WARNING, "Provided vector is " +                "null, null returned.");
            return null;
        }
        return new Vector3f(x + vec.x, y + vec.y, z + vec.z);
    }
    
    /**
     * 
     * <code>add</code>
     * @param vec
     * @param result
     */
    public void add(Vector3f vec, Vector3f result) {
    	if(null == vec) {
    		LoggingSystem.getLogger().log(Level.WARNING, "Provided vector is " +
    		"null, null returned.");
    		return;
    	}
    	
    	
    	result.x = x + vec.x;
		result.y = y + vec.y;
		result.z = z + vec.z;
    }
    
    /**
     * 
     * <code>dot</code> calculates the dot product of this vector with a 
     * provided vector. If the provided vector is null, 0 is returned.
     * @param vec the vector to dot with this vector.
     * @return the resultant dot product of this vector and a given vector.
     */
    public float dot(Vector3f vec) {
        if(null == vec) {
            LoggingSystem.getLogger().log(Level.WARNING, "Provided vector is " +
                "null, 0 returned.");
            return 0;
        }
        return x*vec.x + y*vec.y + z*vec.z;
    }
    
    /**
     * <code>cross</code> calculates the cross product of this vector
     * with a parameter vector v.
     * @param v the vector to take the cross product of with this.
     * @return the cross product vector.
     */
    public Vector3f cross(Vector3f v) {
        return new Vector3f(
            ((y * v.z) - (z * v.y)),
            ((z * v.x) - (x * v.z)),
            ((x * v.y) - (y * v.x)));
    }
    
    /**
     * <code>length</code> calculates the magnitude of this vector.
     * @return the length or magnitude of the vector.
     */
    public float length() {
        return (float) Math.sqrt(lengthSquared());
    }

    /**
     * <code>lengthSquared</code> calculates the squared value of
     * the magnitude of the vector.
     * @return the magnitude squared of the vector.
     */
    public float lengthSquared() {
        return x * x + y * y + z * z;
    }
    
    /**
     * 
     * <code>mult</code> multiplies this vector by a scalar. The resultant
     * vector is returned.
     * @param scalar the value to multiply this vector by.
     * @return the new vector.
     */
    public Vector3f mult(float scalar) {
        return new Vector3f(x*scalar, y*scalar, z*scalar);
    }
    
    public void mult(float scalar, Vector3f product) {
    	if(null == product) {
    		product = new Vector3f();
    	}
    	
    	product.x = x * scalar;
    	product.y = y * scalar;
    	product.z = z * scalar;
    }
    
    /**
     * <code>divide</code> divides the values of this vector by a 
     * scalar and returns the result. The values of this vector 
     * remain untouched.
     * @param scalar the value to divide this vectors attributes by.
     * @return the result <code>Vector</code>.
     */
    public Vector3f divide(float scalar) {
        return new Vector3f(x / scalar, y / scalar, z / scalar);
    }
    
    /**
     * 
     * <code>negate</code> returns the negative of this vector. All values are
     * negated and set to a new vector.
     * @return the negated vector.
     */
    public Vector3f negate() {
        return new Vector3f(-x,-y,-z);
    }
    
    /**
     * 
     * <code>subtract</code> subtracts the values of a given vector from those
     * of this vector creating a new vector object. If the provided vector
     * is null, null is returned.
     * @param vec the vector to subtract from this vector.
     * @return the result vector.
     */
    public Vector3f subtract(Vector3f vec) {
        return new Vector3f(x - vec.x, y - vec.y, z - vec.z);
    }
    
    /**
     * <code>normalize</code> returns the unit vector of this vector.
     * @return unit vector of this vector.
     */
    public Vector3f normalize() {
        float length = length();
        if(length != 0) {
            return divide(length);
        } else {
            return divide(1);
        }
    }
    
    /**
     * <code>toString</code> returns the string representation of this 
     * vector. The format is:
     * 
     * org.jme.math.Vector3f [X=XX.XXXX, Y=YY.YYYY, Z=ZZ.ZZZZ]
     * 
     * @return the string representation of this vector.
     */
    public String toString() {
        return "org.jme.math.Vector3f [X="+x+", Y="+y+", Z="+z+"]";
    }
}

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
package jme.math;

/**
 * <code>Approximation</code> is a static class that will create a
 * line that best fits a collection of points. The method that this line is
 * created by depends on the static method called.
 * <br><br>
 * <b>NOTE:</b> See 3D Game Engine Design. David H. Eberly.
 * @author Mark Powell
 *
 */
public class Approximation {

    /**
     * <code>orthogonalLineFit</code> creates a line that uses 
     * least squares and measures errors orthogonally rather than
     * linearly. 
     * @param points the points to fit a line to.
     * @return the line that best fits the points.
     */
    public static Line orthogonalLineFit(Vector[] points) {

        Vector origin = new Vector();
        Vector direction = new Vector();
        //compute average of points
        origin = points[0];
        for (int i = 1; i < points.length; i++) {
            origin = origin.add(points[i]);
        }

        float inverseQuantity = 1.0f / points.length;
        origin = origin.mult(inverseQuantity);

        // compute sums of products
        float sumXX = 0.0f, sumXY = 0.0f, sumXZ = 0.0f;
        float sumYY = 0.0f, sumYZ = 0.0f, sumZZ = 0.0f;
        for (int i = 0; i < points.length; i++) {
            Vector diff = points[i].subtract(origin);
            sumXX += diff.x * diff.x;
            sumXY += diff.x * diff.y;
            sumXZ += diff.x * diff.z;
            sumYY += diff.y * diff.y;
            sumYZ += diff.y * diff.z;
            sumZZ += diff.z * diff.z;
        }

        float[][] matrix = new float[3][3];
        // setup the eigensolver
        matrix[0][0] = sumYY + sumZZ;
        matrix[0][1] = -sumXY;
        matrix[0][2] = -sumXZ;
        matrix[1][0] = matrix[0][1];
        matrix[1][1] = sumXX + sumZZ;
        matrix[1][2] = -sumYZ;
        matrix[2][0] = matrix[0][2];
        matrix[2][1] = matrix[1][2];
        matrix[2][2] = sumXX + sumYY;
        EigenSystem eigen = new EigenSystem(matrix);

        // compute eigenstuff, smallest eigenvalue is in last position
        eigen.tridiagonalReduction();
        eigen.tridiagonalQL();
        eigen.decreasingSort();

        // unit-length direction for best-fit line
        direction.x = eigen.getEigenvector(0, 2);
        direction.y = eigen.getEigenvector(1, 2);
        direction.z = eigen.getEigenvector(2, 2);

        return new Line(origin, direction);
    }

    public static void gaussPointsFit(
        Vector[] akPoint,
        Vector rkCenter,
        Vector[] akAxis,
        float[] afExtent) {
            
        // compute mean of points
        rkCenter = akPoint[0];
        int i;
        for (i = 1; i < akPoint.length; i++) {
            rkCenter = rkCenter.add(akPoint[i]);
        }
        float fInvQuantity = 1.0f / akPoint.length;
        rkCenter = rkCenter.mult(fInvQuantity);

        // compute covariances of points
        float fSumXX = 0.0f, fSumXY = 0.0f, fSumXZ = 0.0f;
        float fSumYY = 0.0f, fSumYZ = 0.0f, fSumZZ = 0.0f;
        for (i = 0; i < akPoint.length; i++) {
            Vector kDiff = akPoint[i].subtract(rkCenter);
            fSumXX += kDiff.x * kDiff.x;
            fSumXY += kDiff.x * kDiff.y;
            fSumXZ += kDiff.x * kDiff.z;
            fSumYY += kDiff.y * kDiff.y;
            fSumYZ += kDiff.y * kDiff.z;
            fSumZZ += kDiff.z * kDiff.z;
        }
        fSumXX *= fInvQuantity;
        fSumXY *= fInvQuantity;
        fSumXZ *= fInvQuantity;
        fSumYY *= fInvQuantity;
        fSumYZ *= fInvQuantity;
        fSumZZ *= fInvQuantity;
        float[][] matrix = new float[3][3];
        // compute eigenvectors for covariance matrix
       
        matrix[0][0] = fSumXX;
        matrix[0][1] = fSumXY;
        matrix[0][2] = fSumXZ;
        matrix[1][0] = fSumXY;
        matrix[1][1] = fSumYY;
        matrix[1][2] = fSumYZ;
        matrix[2][0] = fSumXZ;
        matrix[2][1] = fSumYZ;
        matrix[2][2] = fSumZZ;
        
        EigenSystem kES = new EigenSystem(matrix);
        kES.tridiagonalReduction();
        kES.tridiagonalQL();
        kES.increasingSort();
        
        akAxis[0].x = kES.getEigenvector(0, 0);
        akAxis[0].y = kES.getEigenvector(1, 0);
        akAxis[0].z = kES.getEigenvector(2, 0);
        akAxis[1].x = kES.getEigenvector(0, 1);
        akAxis[1].y = kES.getEigenvector(1, 1);
        akAxis[1].z = kES.getEigenvector(2, 1);
        akAxis[2].x = kES.getEigenvector(0, 2);
        akAxis[2].y = kES.getEigenvector(1, 2);
        akAxis[2].z = kES.getEigenvector(2, 2);

        afExtent[0] = kES.getRealEigenvalue(0);
        afExtent[1] = kES.getRealEigenvalue(1);
        afExtent[2] = kES.getRealEigenvalue(2);
    }
}
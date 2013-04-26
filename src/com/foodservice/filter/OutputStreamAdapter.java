package com.foodservice.filter;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 3/21/13
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class OutputStreamAdapter extends ServletOutputStream {

    /** The wrapped {@code OutputStream}. */
    protected final OutputStream mOut;

    /**
     * Creates an {@code OutputStreamAdapter}.
     *
     * @param pOut the wrapped {@code OutputStream}
     *
     * @throws IllegalArgumentException if {@code pOut} is {@code null}.
     */
    public OutputStreamAdapter(OutputStream pOut) {
        if (pOut == null) {
            throw new IllegalArgumentException("out == null");
        }
        mOut = pOut;
    }

    /**
     * Returns the wrapped {@code OutputStream}.
     *
     * @return the wrapped {@code OutputStream}.
     */
    public OutputStream getOutputStream() {
        return mOut;
    }

    public String toString() {
        return "ServletOutputStream adapted from " + mOut.toString();
    }

    /**
     * Writes a byte to the underlying stream.
     *
     * @param pByte the byte to write.
     *
     * @throws java.io.IOException if an error occurs during writing
     */
    public void write(int pByte)
            throws IOException {
        mOut.write(pByte);
    }

    // Overide for efficiency
    public void write(byte pBytes[])
            throws IOException {
        mOut.write(pBytes);
    }

    // Overide for efficiency
    public void write(byte pBytes[], int pOff, int pLen)
            throws IOException {
        mOut.write(pBytes, pOff, pLen);
    }
}
package com.foodservice.filter;

import com.foodservice.filter.OutputStreamAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

/**
 * GZIPResponseWrapper class description.
 * <p/>
 * Based on ideas and code found in the ONJava article
 * <a href="http://www.onjava.com/pub/a/onjava/2003/11/19/filters.html">Two Servlet Filters Every Web Application Should Have</a>
 * by Jayson Falkner.
 *
 * @author Jayson Falkner
 * @author <a href="mailto:harald.kuhr@gmail.com">Harald Kuhr</a>
 * @author last modified by $Author: haku $
 * @version $Id: //depot/branches/personal/haraldk/twelvemonkeys/release-2/twelvemonkeys-servlet/src/main/java/com/twelvemonkeys/servlet/gzip/GZIPResponseWrapper.java#1 $
 */
public class CompressionResponseWrapper extends HttpServletResponseWrapper {
    protected ServletOutputStream mOut = null;
    protected PrintWriter mWriter = null;
    protected GZIPOutputStream mGZIPOut = null;
    protected int mContentLength = -1;

    public CompressionResponseWrapper(HttpServletResponse response) {
        super(response);
        response.addHeader("Content-Encoding", "gzip");
    }

    public ServletOutputStream createOutputStream() throws IOException {
        // FIX: Write directly to servlet output stream, for faster responses.
        // Relies on chunked streams, or buffering in the servlet engine.
        if (mContentLength >= 0) {
            mGZIPOut = new GZIPOutputStream(getResponse().getOutputStream(), mContentLength);
        }
        else {
            mGZIPOut = new GZIPOutputStream(getResponse().getOutputStream());
        }

        // Wrap in ServletOutputStream and return
        return new OutputStreamAdapter(mGZIPOut);
    }

    // TODO: Move this to flushbuffer or something? Hmmm..
    public void flushResponse() {
        try {
            try {
                // Finish GZIP encodig
                if (mGZIPOut != null) {
                    mGZIPOut.finish();
                }

                flushBuffer();
            }
            finally {
                // Close stream
                if (mWriter != null) {
                    mWriter.close();
                }
                else {
                    if (mOut != null) {
                        mOut.close();
                    }
                }
            }
        }
        catch (IOException e) {
            // TODO: Fix this one...
            e.printStackTrace();
        }
    }

    public void flushBuffer() throws IOException {
        if (mWriter != null) {
            mWriter.flush();
        }
        else if (mOut != null) {
            mOut.flush();
        }
    }

    public ServletOutputStream getOutputStream() throws IOException {
        if (mWriter != null) {
            throw new IllegalStateException("getWriter() has already been called!");
        }

        if (mOut == null) {
            mOut = createOutputStream();
        }
        return (mOut);
    }

    public PrintWriter getWriter() throws IOException {
        if (mWriter != null) {
            return (mWriter);
        }

        if (mOut != null) {
            throw new IllegalStateException("getOutputStream() has already been called!");
        }

        mOut = createOutputStream();
        // TODO: This is wrong. Should use getCharacterEncoding() or "ISO-8859-1" if gCE returns null.
        mWriter = new PrintWriter(new OutputStreamWriter(mOut, "UTF-8"));
        return (mWriter);
    }

    public void setContentLength(int pLength) {
        // NOTE: Do not call super, as we will shrink the size.
        mContentLength = pLength;
    }
}
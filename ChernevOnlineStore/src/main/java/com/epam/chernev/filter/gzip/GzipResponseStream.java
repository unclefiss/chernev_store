package com.epam.chernev.filter.gzip;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;


public class GzipResponseStream extends ServletOutputStream {

    public GzipResponseStream(HttpServletResponse response) throws IOException {
        super();
        closed = false;
        this.response = response;
        this.output = response.getOutputStream();
    }

    private static final String CONTENT_ENCODING = "Content-Encoding";

    protected int compressionThreshold;

    protected byte[] buffer;

    protected int bufferCount;

    protected GZIPOutputStream gzipstream;

    protected boolean closed;

    protected int length = -1;

    protected HttpServletResponse response;

    protected ServletOutputStream output;

    protected void setBuffer(int threshold) {
        compressionThreshold = threshold;
        buffer = new byte[compressionThreshold];
    }

    @Override
    public void close() throws IOException {
        if (closed) {
            return;
        }
        if (gzipstream != null) {
            flushToGZip();
            gzipstream.close();
            gzipstream = null;
        } else {
            if (bufferCount > 0) {
                output.write(buffer, 0, bufferCount);
                bufferCount = 0;
            }
        }
        output.close();
        closed = true;
    }


    @Override
    public void flush() throws IOException {

        if (closed) {
            return;
        }
        if (gzipstream != null) {
            gzipstream.flush();
        }

    }

    public void flushToGZip() throws IOException {
        if (bufferCount > 0) {
            writeToGZip(buffer, 0, bufferCount);
            bufferCount = 0;
        }
    }

    @Override
    public void write(int b) throws IOException {

        if (closed) {
            throw new IOException("Cannot write to a closed output stream");
        }
        if (bufferCount >= buffer.length) {
            flushToGZip();
        }
        buffer[bufferCount++] = (byte) b;
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {

        if (closed) {
            throw new IOException("Cannot write to a closed output stream");
        }

        if (len == 0) {
            return;
        }

        if (len <= (buffer.length - bufferCount)) {
            System.arraycopy(b, off, buffer, bufferCount, len);
            bufferCount += len;
            return;
        }

        flushToGZip();

        if (len <= (buffer.length - bufferCount)) {
            System.arraycopy(b, off, buffer, bufferCount, len);
            bufferCount += len;
            return;
        }

        writeToGZip(b, off, len);
    }

    public void writeToGZip(byte[] b, int off, int len) throws IOException {

        if (gzipstream == null) {
            gzipstream = new GZIPOutputStream(output);
            if (response.getHeader(CONTENT_ENCODING) == null || response.getHeader(CONTENT_ENCODING).isEmpty()) {
                response.addHeader(CONTENT_ENCODING, "gzip");
            }
        }
        gzipstream.write(b, off, len);

    }

    public boolean closed() {
        return (this.closed);
    }

    @Override
    public boolean isReady() {
        return output.isReady();
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}
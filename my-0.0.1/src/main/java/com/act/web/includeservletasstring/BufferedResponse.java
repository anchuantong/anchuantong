/*
 * Copyright 2006, XpertNet SARL, and individual contributors as indicated by
 * the contributors.txt. This is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of the License,
 * or (at your option) any later version. This software is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Lesser General Public License for more details. You should have
 * received a copy of the GNU Lesser General Public License along with this
 * software; if not, write to the Free Software Foundation, Inc., 51 Franklin
 * St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF site:
 * http://www.fsf.org. @author jeremi @author sdumitriu
 */

package com.act.web.includeservletasstring;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author LBlaze
 */
public class BufferedResponse extends HttpServletResponseWrapper {

	protected HttpServletResponse internalResponse;

	protected BufferOutputStream outputStream;

	protected PrintWriter writer;

	/** Creates a new instance of BufferedResponse */
	public BufferedResponse(HttpServletResponse internalResponse) {

		super(internalResponse);
		outputStream=new BufferOutputStream();
	}

	public ServletOutputStream getOutputStream() throws IOException {
		if (outputStream == null) {
			outputStream = new BufferOutputStream();
		}
		return outputStream;
	}

	public PrintWriter getWriter() throws IOException {
		if (writer == null) {
			writer = new PrintWriter(new OutputStreamWriter(getOutputStream(), getCharacterEncoding()));
		}
		return writer;
	}

	public byte[] getBufferAsByteArray() throws IOException {
		if (writer != null) {
			writer.flush();
		}
		outputStream.flush();

		return outputStream.getContentsAsByteArray();
	}

}

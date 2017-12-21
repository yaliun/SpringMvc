package net.yaliun.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ModifyResponseWrapper extends HttpServletResponseWrapper {

	private StringWriter sw = null;
	private ServletOutputStreamForString sosfs = null;
	private HttpServletResponse owner;
	
	public ModifyResponseWrapper(HttpServletResponse response){
		super(response);
		this.owner = response;
	}
	
	@Override
	public ServletOutputStream getOutputStream() throws IOException{
		if( this.sosfs == null){
			this.sosfs = new ServletOutputStreamForString(this.owner);
		}
		return this.sosfs;
	}
	
	@Override
	public PrintWriter getWriter() throws IOException{
		if(this.sw == null){
			sw = new StringWriter();
		}
		return new PrintWriter(this.sw);
	}

	@Override
	public String toString(){
		final String result;
		
		if(this.sw != null){
			result = this.sw.toString();
		}else if(this.sosfs != null){
			result = this.sosfs.toString();
		}else{
			result = "";
		}
		
		return result;
	}
	
	private class ServletOutputStreamForString extends ServletOutputStream{
		private final ServletOutputStream sos;
		private String charset;
		
		private final StringBuilder sb;
		
		public ServletOutputStreamForString(HttpServletResponse owner) throws IOException{
			this.sos = owner.getOutputStream();
			this.charset = owner.getCharacterEncoding();
			this.sb = new StringBuilder();
		}
		
		@Override
		public void write(int paramInt) throws IOException{
			sb.append(paramInt);
			this.sos.write(paramInt);
		}
		
		@Override
		public void write(byte[] paramArrayOfByte, int paramint1, int paramInt2) throws IOException{
			sb.append(new String(paramArrayOfByte, paramint1, paramInt2, this.charset));
			this.sos.write(paramArrayOfByte, paramint1, paramInt2);
		}
		
		@Override
		public String toString(){
			return sb.toString().trim();
		}
	}
}

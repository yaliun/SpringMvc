package net.yaliun.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

public class ModifyRequestWrapper extends HttpServletRequestWrapper {

	private byte[] rawData;
	
	public ModifyRequestWrapper(HttpServletRequest request) throws IOException{
		super(request);
		
		InputStream inputStream = request.getInputStream();
		this.rawData = IOUtils.toByteArray(inputStream);
	}

	public ServletInputStream getInputStream() throws IOException{
		return new ServletInputStreamImpl(new ByteArrayInputStream(this.rawData));
	}
	
	class ServletInputStreamImpl extends ServletInputStream{
		private InputStream is;
		
		public ServletInputStreamImpl(InputStream bis){
			is = bis;
		}

		@Override		
		public int read() throws IOException{
			return is.read();
		}
	}
}

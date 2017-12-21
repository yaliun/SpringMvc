package net.yaliun.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ModifyFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModifyFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
	
		LOGGER.info("##### doFilter() Start #####\n");
		
		ModifyRequestWrapper requestWrapper = new ModifyRequestWrapper((HttpServletRequest)request);
		ServletInputStream localServletInputStream = requestWrapper.getInputStream();
		
		Enumeration<String> requestEnumeration = requestWrapper.getHeaderNames();
		String reqHeaderName = "";
		String reqHeaderValue = "";
		
		LOGGER.info("++++++++++[RequestHeader Start]+++++++++++++");
		
		while(requestEnumeration.hasMoreElements()){
			reqHeaderName = requestEnumeration.nextElement();
			reqHeaderValue = requestWrapper.getHeader(reqHeaderName);
			LOGGER.debug("[{}] : {}", reqHeaderName, reqHeaderValue);
		}
		
		LOGGER.info("++++++++++[RequestHeader End]+++++++++++++\n");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(localServletInputStream, "UTF-8"));
		String requestBody = "";
		
		LOGGER.info("++++++++++[RequestBody Start]+++++++++++++");
		while((requestBody = br.readLine()) != null){
			LOGGER.debug(requestBody);
		}
		LOGGER.info("++++++++++[RequestBody End]+++++++++++++\n");
		

		ModifyResponseWrapper responseWrapper = new ModifyResponseWrapper((HttpServletResponse)response);
		filter.doFilter(requestWrapper,responseWrapper);
		
		
		LOGGER.info("\n++++++++++[ResponseHeader&Body Start]+++++++++++++"
				+ "\n[Content-Type]: "+ responseWrapper.getContentType()
				+ "\n[X-SignedResponse] : "+ responseWrapper.getHeader("X-SignedResponse")
				+ "\n[Body] : "+ responseWrapper.getOutputStream().toString()
				+ "\n++++++++++[ResponseHeader&Body End]+++++++++++++\n");
		
		LOGGER.info("##### doFilter() End #####");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.info("+++++++++++++++++++++++");
		LOGGER.info("Init Called!");
		LOGGER.info("+++++++++++++++++++++++");
	}

	@Override
	public void destroy() {
		LOGGER.info("+++++++++++++++++++++++");
		LOGGER.info("Destory Called!");
		LOGGER.info("+++++++++++++++++++++++");
	}
}

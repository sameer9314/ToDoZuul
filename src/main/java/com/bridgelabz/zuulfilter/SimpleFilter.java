package com.bridgelabz.zuulfilter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.tokenutility.TokenUtility;
import com.bridgelabz.utility.utilservice.RedisRepositoryImplementation;
import com.google.common.base.Preconditions;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * Purpose : To acknowledge the functionality of Zuul filter.	
 * @author   Sameer Saurabh
 * @version  1.0
 * @Since    13/08/2018
 */
public class SimpleFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(SimpleFilter.class);

	@Autowired
	private TokenUtility tokenUtility;
	@Autowired
	private RedisRepositoryImplementation redisRepository;

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterType()
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {
		return 1;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	@Override
	public Object run() {
		log.info("Zuul Filter Run Method Starts");
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		if(request.getRequestURI().startsWith("/note/")) {
		String tokenFromHeader = request.getHeader("JWTtoken");
		
		log.info("Token Found ="+tokenFromHeader);
		String userId = tokenUtility.parseJWT(tokenFromHeader).getId();
		
		log.info("User Found With Id = "+userId);
		String tokenFromRedis = redisRepository.getToken(userId);
		
		log.info("Token Found From Redis = "+tokenFromRedis);
		
		Preconditions.checkNotNull(tokenFromRedis, "User Not Found Or Not Registered");
		
		ctx.addZuulRequestHeader("userId", userId);
		
	}
		log.info("Zuul Filter Run Method Ends");
		return "";
	}
	
}

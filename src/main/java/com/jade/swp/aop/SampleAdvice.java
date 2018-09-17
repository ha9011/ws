package com.jade.swp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SampleAdvice {
	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
//	@Before("execution(* com.jade.swp.service.MessageService*.*(..))")
//	public void startLog(JoinPoint jp) {
//		System.out.println("++++++++++++++++++++++++++++++++++++++++ startLog::" + jp.getSignature().getName());
//		logger.info("-------------------- startLog --------");
//	}
	
//	@Resource(name = "messageService") MessageService demoService;
//	
//	@Pointcut("execution(* com.jade.swp.service.*.*(..))")
//	private void demoServiceAOPPointcut() {} 
//	
//	@After("demoServiceAOPPointcut()")
//	public void getTest(JoinPoint thisJoinPoint) { demoService.ttt(); }

//	@Before("demoServiceAOPPointcut()")
	@Before("execution(* com.jade.swp.service.*.*(..))")
	public void startLog2(JoinPoint jp) {
		System.out.println("---------------------------------------- startLog::" + jp.getSignature().getName());
	}
	
	@Before("execution(* com.jade.swp.service.MessageServiceImpl.ttt(..) )") // joinpoint 지정
	public void beforeAdvice() {
	    System.out.println("beforeAdvice() called");
	}
	
//	@After("execution(* com.jade.swp.service.MessageServiceImpl.ttt())")
//	public void ttt(JoinPoint jp) {
//		System.out.println("++++++++++++++++++++++++++++++++++++++++ ttt::" + jp.getSignature().getName());
//		logger.info("-------------------- ttt --------");
//	}
	
	@Around("execution(* com.jade.swp.service.MessageServiceImpl.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("---------------------------------------- timeLog");
		
		long stime = System.currentTimeMillis();
		Object result = pjp.proceed();
		System.out.println(pjp.getSignature().getName() + "::" + (System.currentTimeMillis() - stime));
		logger.info("-------------------- timeLog --------");
		return result;
	}
}

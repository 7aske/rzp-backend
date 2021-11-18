package rs.digitize.backend.bean.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Profile("perf")
public class PerfLogger {
	private final Logger logger = LoggerFactory.getLogger(PerfLogger.class);

	@Around("execution(* rs.digitize.backend.service.*.*(..))")
	public Object logBeforeAllMethods(ProceedingJoinPoint point) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object object = point.proceed();
		long endtime = System.currentTimeMillis();
		logger.info(point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName() + ": " + (endtime - startTime) + "ms");
		return object;
	}
}
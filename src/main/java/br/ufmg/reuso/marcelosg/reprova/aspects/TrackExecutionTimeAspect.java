package br.ufmg.reuso.marcelosg.reprova.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Slf4j
@Configuration
public class TrackExecutionTimeAspect {

    // Measures execution time of any method annotated with @TrackExecutionTime
    @Around("@annotation(br.ufmg.reuso.marcelosg.reprova.TrackExecutionTime)")
    public Object trackExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var start = Instant.now();

        var returnObject = proceedingJoinPoint.proceed();

        var end = Instant.now();
        var executionTime = Duration.between(start, end).toMillis();
        log.info("{}.{} executed in {} milliseconds.", proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(),
                executionTime);

        return returnObject;
    }

}

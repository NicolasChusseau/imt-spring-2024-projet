package org.imt.tournamentmaster.configAOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut pour toutes les méthodes des contrôleurs
    @Pointcut("execution(public * org.imt.tournamentmaster.controller..*(..))")
    public void controllerMethods() {}

    // Log avant l'exécution de la méthode
    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entrée dans la méthode: {} avec les arguments: {}",
                joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    // Log après le retour de la méthode
    @AfterReturning(value = "controllerMethods()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        logger.info("Sortie de la méthode: {} avec le résultat: {}",
                joinPoint.getSignature().getName(), result);
    }

    // Log s'il y a une exception
    @AfterThrowing(value = "controllerMethods()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        logger.error("Exception dans la méthode: {} avec l'exception: {}",
                joinPoint.getSignature().getName(), exception.getMessage());
    }
}

package edu.psu.sweng894.group7.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.psu.sweng894.group7.datastore.service.SecurityServices;
import edu.psu.sweng894.group7.service.exception.UnAuthorizedUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Aspect
@Component
public class SecureAPIAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SecurityServices securityService;
    @Around("@annotation(SecureAPI)")
    public Object validateToken(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        logger.info("Executing method: " + method.getName());

        String jsonInString= new ObjectMapper()
                .writer()
                .withDefaultPrettyPrinter()
                .writeValueAsString(args[0]);

        logger.info("Request to method: "+ jsonInString);
        long executionTime = System.currentTimeMillis();

        String authToken="";

        if(Objects.nonNull(args))
          authToken = (String) args[args.length-1]; //token is the last parameter

       if (!authToken.equals("") && securityService.validate(authToken)) {
            return joinPoint.proceed();
        } else {
            throw new UnAuthorizedUser("Un-Authorized");
        }

    }
}

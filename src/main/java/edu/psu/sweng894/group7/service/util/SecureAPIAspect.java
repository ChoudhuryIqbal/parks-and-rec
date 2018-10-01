package edu.psu.sweng894.group7.service.util;

import edu.psu.sweng894.group7.datastore.service.SecurityServices;
import edu.psu.sweng894.group7.service.exception.UnAuthorizedUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.stream.Collectors;

@Aspect
@Component
public class SecureAPIAspect {

    @Autowired
    SecurityServices securityService;
    @Around("@annotation(SecureAPI)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long executionTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
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

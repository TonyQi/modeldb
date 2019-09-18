package com.bcc.security.auth.client.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.bcc.security.auth.common.util.jwt.JWTInfo;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * Created by ace on 2017/9/15.
 */
@FeignClient(value = "${auth.serviceId}",configuration = {})
public interface ServiceAuthFeign {

    @RequestMapping(value = "/jwt/verify",method = RequestMethod.GET)
    public ResponseEntity<Boolean> isValid(@RequestParam("token") String token) throws ExpiredJwtException,SignatureException;
    
    @RequestMapping(value = "/jwt/info",method = RequestMethod.GET)
    public ResponseEntity<JWTInfo> getTokenInfo(@RequestParam("token") String token) throws ExpiredJwtException,SignatureException;

}

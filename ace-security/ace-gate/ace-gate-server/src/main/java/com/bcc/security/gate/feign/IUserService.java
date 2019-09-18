package com.bcc.security.gate.feign;

import feign.Param;
import feign.RequestLine;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.bcc.security.api.vo.authority.PermissionInfo;


/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-21 8:11
 */
@FeignClient("bccres")
public interface IUserService {
  @RequestLine(value = "GET /api/user/un/{username}/permissions")
  public List<PermissionInfo> getPermissionByUsername(@Param("username") String username);
  @RequestLine(value = "GET /api/permissions")
  List<PermissionInfo> getAllPermissionInfo();
}

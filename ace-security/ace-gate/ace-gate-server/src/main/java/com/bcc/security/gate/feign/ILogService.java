package com.bcc.security.gate.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bcc.security.api.vo.log.LogInfo;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-07-01 15:16
 */
@FeignClient("ace-admin")
public interface ILogService {
  @RequestMapping(value="/api/log/save",method = RequestMethod.POST)
  public void saveLog(LogInfo info);
}

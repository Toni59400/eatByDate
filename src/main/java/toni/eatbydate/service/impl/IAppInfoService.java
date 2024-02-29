package toni.eatbydate.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import toni.eatbydate.service.AppInfoService;

@Service
public class IAppInfoService implements AppInfoService {

    @Value("${spring.application.name}")
    private String applicationName;

    public String getAppInfo(){
        return applicationName + " - 1.0.0";
    }
}

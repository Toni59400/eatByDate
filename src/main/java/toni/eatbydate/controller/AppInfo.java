package toni.eatbydate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import toni.eatbydate.service.AppInfoService;

@Controller
public class AppInfo {

    @Autowired
    AppInfoService appInfo;

    @CrossOrigin("*")
    @GetMapping("/version")
    public ResponseEntity<String> getVersion(){
        return ResponseEntity.ok(appInfo.getAppInfo());
    }
}

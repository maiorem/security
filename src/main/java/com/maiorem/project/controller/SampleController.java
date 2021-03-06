package com.maiorem.project.controller;

import com.maiorem.project.security.dto.ProjectAuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/all")
    public void exAll(){
        log.info("exAll.................");
    }

    @GetMapping("/member")
    public void exMember(@AuthenticationPrincipal ProjectAuthMemberDTO projectAuthMember){
        log.info("exMember.................");
        log.info("----------------------------------------");
        log.info(projectAuthMember);
    }

    @GetMapping("/admin")
    public void exAdmin() {
        log.info("exAdmin....................");
    }

}

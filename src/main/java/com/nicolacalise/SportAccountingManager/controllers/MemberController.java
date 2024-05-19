package com.nicolacalise.SportAccountingManager.controllers;

import com.nicolacalise.SportAccountingManager.models.entities.Member;
import com.nicolacalise.SportAccountingManager.services.entityServices.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("member/list")
    public ResponseEntity<List<Member>> getMembers(){
        return ResponseEntity.ok().body(this.memberService.getMembers());
    }

}

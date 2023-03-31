package com.indo.perpustakaanonline.controller;

import com.indo.perpustakaanonline.entity.Member;
import com.indo.perpustakaanonline.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {

    MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMember();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable String id) {
        return memberService.getMemberById(id);
    }

    @PostMapping
    public Member saveMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable String id) {
        memberService.deleteMember(id);
    }
}

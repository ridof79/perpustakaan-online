package com.indo.perpustakaanonline.service.impl;

import com.indo.perpustakaanonline.entity.Member;
import com.indo.perpustakaanonline.repository.MemberRepository;
import com.indo.perpustakaanonline.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    MemberRepository memberRepository;

    @Override
    public List<Member> getAllMember() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(String id) {
        return memberRepository.getReferenceById(id);
    }

    @Override
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public void deleteMember(String id) {
        memberRepository.deleteById(id);
    }
}

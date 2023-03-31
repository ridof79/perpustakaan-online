package com.indo.perpustakaanonline.service;

import com.indo.perpustakaanonline.entity.Member;

import java.util.List;

public interface MemberService {
    List<Member> getAllMember();
    Member getMemberById(String id);
    Member saveMember(Member member);
    void deleteMember(String id);
}

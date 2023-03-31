package com.indo.perpustakaanonline.dto;

import com.indo.perpustakaanonline.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String id;
    private String name;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.name = member.getName();
    }
}

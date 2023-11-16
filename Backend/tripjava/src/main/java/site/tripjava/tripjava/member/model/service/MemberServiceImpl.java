package site.tripjava.tripjava.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.tripjava.tripjava.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
    private MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


}

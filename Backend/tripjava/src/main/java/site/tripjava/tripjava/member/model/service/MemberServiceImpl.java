package site.tripjava.tripjava.member.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.tripjava.tripjava.member.model.MemberDto;
import site.tripjava.tripjava.member.model.mapper.MemberMapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService{
    private MemberMapper memberMapper;

    MemberServiceImpl(MemberMapper memberMapper){
        super();
        this.memberMapper = memberMapper;
    }
    @Override
    public MemberDto login(MemberDto memberDto) throws Exception {
        return memberMapper.login(memberDto);
    }

    @Override
    public MemberDto userInfo(String userId) throws Exception {
        return memberMapper.userInfo(userId);
    }

    @Override
    public void saveRefreshToken(String id, String token) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("token", token);
        memberMapper.saveRefreshToken(map);
    }

    @Override
    public Object getRefreshToken(String userId) throws Exception {
        return memberMapper.getRefreshToken(userId);
    }

    @Override
    public void deleteRefreshToken(String userId) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("id", userId);
        map.put("token", null);
        memberMapper.deleteRefreshToken(map);
    }

    @Override
    public void join(MemberDto memberDto) throws Exception {
        memberMapper.join(memberDto);
    }

    @Override
    public void deleteMember(String id) throws Exception {
        memberMapper.deleteMember(id);
    }

    @Override
    public void updateMember(MemberDto memberDto) throws Exception {
        memberMapper.updateMember(memberDto);
    }

    @Override
    public int checkId(String id) throws Exception {
        return memberMapper.checkId(id);
    }


}

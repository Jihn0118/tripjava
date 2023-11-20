package site.tripjava.tripjava.member.model.service;

import site.tripjava.tripjava.member.model.MemberDto;

public interface MemberService {
    MemberDto login(MemberDto memberDto) throws Exception;
    MemberDto userInfo(String userId) throws Exception;
    void saveRefreshToken(String id, String token) throws Exception;
    Object getRefreshToken(String userId) throws Exception;
    void deleteRefreshToken(String userId) throws Exception;
    void join(MemberDto memberDto) throws Exception;
    void deleteMember(String id) throws Exception;
    void updateMember(MemberDto memberDto) throws Exception;

    int checkId(String id) throws Exception;
}

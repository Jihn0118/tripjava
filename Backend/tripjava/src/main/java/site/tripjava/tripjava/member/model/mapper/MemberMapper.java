package site.tripjava.tripjava.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import site.tripjava.tripjava.member.model.MemberDto;

import java.sql.SQLException;
import java.util.Map;


@Mapper
public interface MemberMapper {
    MemberDto login(MemberDto memberDto) throws SQLException;
    MemberDto userInfo(String userId) throws SQLException;
    void saveRefreshToken(Map<String, String> map) throws SQLException;
    Object getRefreshToken(String userid) throws SQLException;
    void deleteRefreshToken(Map<String, String> map) throws SQLException;
    void join(MemberDto memberDto) throws Exception;
    void deleteMember(String id) throws Exception;
    void updateMember(MemberDto memberDto) throws Exception;
}

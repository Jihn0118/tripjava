package site.tripjava.tripjava.heart.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;
import site.tripjava.tripjava.heart.model.BoardAndMember;
import site.tripjava.tripjava.heart.model.HeartRequest;

import java.sql.SQLException;

@Mapper
public interface HeartMapper {

    int getHeartCount(int contentId) throws SQLException;

    int getHeartState(BoardAndMember boardAndMember) throws SQLException;

    void doHeart(HeartRequest heartRequest) throws SQLException;

    void cancelHeart(HeartRequest heartRequest) throws SQLException;

    void upHeart(int contentId) throws SQLException;

    void downHeart(int contentId) throws SQLException;


}

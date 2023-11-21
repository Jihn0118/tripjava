package site.tripjava.tripjava.heart.model.service;

import site.tripjava.tripjava.heart.model.BoardAndMember;
import site.tripjava.tripjava.heart.model.HeartRequest;

import java.sql.SQLException;

public interface HeartService {

    int getHeartCount(int contentId) throws SQLException;

    boolean getHeartState(BoardAndMember boardAndMember) throws SQLException;

    void doHeart(HeartRequest heartRequest) throws SQLException;

    void cancelHeart(HeartRequest heartRequest) throws SQLException;

}

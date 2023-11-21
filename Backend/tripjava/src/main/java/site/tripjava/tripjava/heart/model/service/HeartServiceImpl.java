package site.tripjava.tripjava.heart.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.tripjava.tripjava.heart.model.BoardAndMember;
import site.tripjava.tripjava.heart.model.HeartRequest;
import site.tripjava.tripjava.heart.model.mapper.HeartMapper;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class HeartServiceImpl implements HeartService {

    private final HeartMapper heartMapper;

    @Override
    public int getHeartCount(int contentId) throws SQLException {
        return heartMapper.getHeartCount(contentId);
    }

    @Override
    public boolean getHeartState(BoardAndMember boardAndMember) throws SQLException {
        int isHeart = heartMapper.getHeartState(boardAndMember);
        if (isHeart > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void doHeart(HeartRequest heartRequest) throws SQLException {
        heartMapper.doHeart(heartRequest);
    }

    @Override
    public void cancelHeart(HeartRequest heartRequest) throws SQLException {
        heartMapper.cancelHeart(heartRequest);
    }
}
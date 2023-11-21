package site.tripjava.tripjava.attraction.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.tripjava.tripjava.attraction.model.AttractionInfo;
import site.tripjava.tripjava.attraction.model.SearchCondition;
import site.tripjava.tripjava.attraction.model.Sido;
import site.tripjava.tripjava.attraction.model.mapper.AttractionMapper;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionMapper attractionMapper;
    @Override
    public List<AttractionInfo> searchAttraction(SearchCondition searchCondition) throws SQLException {
        return attractionMapper.searchAttraction(searchCondition);
    }

    @Override
    public Sido [] getSidoInfo() throws SQLException {
        return attractionMapper.getSidoInfo();
    }

    @Override
    public AttractionInfo getDetailInfo(int contendId) throws SQLException {
        return attractionMapper.getDetailInfo(contendId);
    }
}

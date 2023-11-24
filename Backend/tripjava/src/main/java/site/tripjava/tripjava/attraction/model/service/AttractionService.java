package site.tripjava.tripjava.attraction.model.service;

import site.tripjava.tripjava.attraction.model.AttractionInfo;
import site.tripjava.tripjava.attraction.model.SearchCondition;
import site.tripjava.tripjava.attraction.model.Sido;

import java.sql.SQLException;
import java.util.List;

public interface AttractionService {

    List<AttractionInfo> searchAttraction (SearchCondition searchCondition) throws SQLException;
    Sido[] getSidoInfo() throws SQLException;

    AttractionInfo getDetailInfo(int contendId) throws SQLException;

    List<AttractionInfo> getAllInfosFindById (List<Long> contentIdList) throws Exception;
}

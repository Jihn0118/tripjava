package site.tripjava.tripjava.attraction.model.service;

import site.tripjava.tripjava.attraction.model.AttractionInfo;
import site.tripjava.tripjava.attraction.model.SearchCondition;

import java.sql.SQLException;
import java.util.List;

public interface AttractionService {

    List<AttractionInfo> searchAttraction (SearchCondition searchCondition) throws SQLException;

}

package site.tripjava.tripjava.attraction.model.mapper;

import site.tripjava.tripjava.attraction.model.AttractionInfo;
import site.tripjava.tripjava.attraction.model.SearchCondition;

import java.sql.SQLException;
import java.util.List;

public interface AttractionMapper {
    List<AttractionInfo> searchAttraction(SearchCondition searchCondition) throws SQLException;

}

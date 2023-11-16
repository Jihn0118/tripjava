package site.tripjava.tripjava.attraction.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.tripjava.tripjava.attraction.model.mapper.AttractionMapper;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

    private final AttractionMapper attractionMapper;

}

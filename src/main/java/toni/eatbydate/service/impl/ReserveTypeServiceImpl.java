package toni.eatbydate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toni.eatbydate.entity.ReserveType;
import toni.eatbydate.repository.ReserveTypeRepo;
import toni.eatbydate.service.ReserveTypeService;

import java.util.List;

@Service
public class ReserveTypeServiceImpl implements ReserveTypeService {

    @Autowired
    private ReserveTypeRepo reserveTypeRepository;

    @Override
    public List<ReserveType> getAllReserveTypes() {
        return reserveTypeRepository.findAll();
    }
}


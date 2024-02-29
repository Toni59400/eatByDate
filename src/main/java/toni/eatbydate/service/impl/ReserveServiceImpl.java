package toni.eatbydate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toni.eatbydate.entity.Reserve;
import toni.eatbydate.repository.ReserveRepo;
import toni.eatbydate.service.ReserveService;

import java.util.List;

@Service
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveRepo reserveRepository;

    @Override
    public List<Reserve> getAllReserves() {
        return reserveRepository.findAll();
    }

    @Override
    public List<Reserve> getReservesByUserId(Long userId) {

        return reserveRepository.findByUserId(userId);
    }

    @Override
    public Reserve saveReserve(Reserve reserve) {
        return reserveRepository.save(reserve);
    }
}


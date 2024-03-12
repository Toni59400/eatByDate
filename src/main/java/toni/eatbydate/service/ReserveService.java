package toni.eatbydate.service;

import toni.eatbydate.entity.Reserve;

import java.util.List;

public interface ReserveService {
    List<Reserve> getAllReserves();
    List<Reserve> getReservesByUserId(Long userId);
    Reserve saveReserve(Reserve reserve);

    Reserve getReserve(Long reserveId);
}


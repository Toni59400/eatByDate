package toni.eatbydate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toni.eatbydate.entity.Reserve;
import toni.eatbydate.service.ReserveService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/reserves")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @GetMapping
    public List<Reserve> getAllReserves() {
        return reserveService.getAllReserves();
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Reserve>> getReserveByUserId(@PathVariable Long userId){
        List<Reserve> reserves = reserveService.getReservesByUserId(userId);
        if (reserves.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reserves, HttpStatus.OK);
    }
}


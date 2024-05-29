package toni.eatbydate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/{Id}")
    public ResponseEntity<Reserve>getReserveById(@PathVariable Long Id){
        Reserve reserve = reserveService.getReserve(Id);
        if (reserve.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reserve, HttpStatus.OK);
    }
}
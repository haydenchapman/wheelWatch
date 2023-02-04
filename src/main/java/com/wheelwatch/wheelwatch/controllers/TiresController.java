package com.wheelwatch.wheelwatch.controllers;

import com.wheelwatch.wheelwatch.dtos.TiresDto;
import com.wheelwatch.wheelwatch.services.TiresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tires")
public class TiresController {
    @Autowired
    TiresService tiresService;

    @GetMapping("/users/userId")
    public Optional<TiresDto> getTiresByUserId(@PathVariable Long userId){
        return tiresService.getTiresById(userId);
    }

    @GetMapping("/tires/tiresId")
    public List<TiresDto> getAllTires(@PathVariable Long tiresId){
        return tiresService.getAllTires();
    }

}
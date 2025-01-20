package com.starwars.app.controller;

import com.starwars.app.model.People.PeopleListResponseDTO;
import com.starwars.app.model.People.PeopleResponseDTO;
import com.starwars.app.model.Vehicle.VehicleListResponseDTO;
import com.starwars.app.model.Vehicle.VehicleResponseDTO;
import com.starwars.app.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling requests related to vehicles.
 * This controller provides endpoints to retrieve a list of vehicles and a single vehicle by its ID.
 * It communicates with the {@link VehicleService} to fetch the required data and returns it in
 * the form of {@link VehicleListResponseDTO} and {@link VehicleResponseDTO} respectively.
 */
@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/all")
    public ResponseEntity<VehicleListResponseDTO> getVehicles(@RequestParam(defaultValue = "1") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        VehicleListResponseDTO vehicleListResponseDTO =  vehicleService.getVehicles(page, size);
        return ResponseEntity.ok(vehicleListResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> getVehicleById(@PathVariable int id) {
        VehicleResponseDTO response = vehicleService.getVehicleById(id);
        return ResponseEntity.ok(response);
    }

}

package com.nrapendra.garage.controllers;

import com.nrapendra.garage.models.FreeParkingGarageSpace;
import com.nrapendra.garage.models.Vehicle;
import com.nrapendra.garage.models.VehicleMovementStatus;
import com.nrapendra.garage.models.VehicleParkingLocation;
import com.nrapendra.garage.utils.AppUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.text.ParseException;

@Tag(name = "GarageController", description = "GarageController enables customer to park vehicle in the garage")
public abstract class OpenAPIController {

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful", content = { @Content( mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content(schema = @Schema(),mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema(),mediaType = "application/json") })
    })
    @Operation(summary = "ENTER VEHICLE")
    public abstract ResponseEntity<VehicleMovementStatus> enterVehicle(@RequestBody Vehicle vehicle) throws IOException, ParseException;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful", content = { @Content( mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content(schema = @Schema(),mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema(),mediaType = "application/json") })
    })
    @Operation(summary = "EXIT VEHICLE")
    public abstract ResponseEntity<VehicleMovementStatus> exitVehicle(@PathVariable(AppUtil.VEHICLE_NUMBER) String vehicleNumber) throws IOException, ParseException;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful",content = { @Content( mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content(schema = @Schema(),mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema(),mediaType = "application/json") })
    })
    @Operation(summary = "GET VEHICLE LOCATION")
    public abstract ResponseEntity<VehicleParkingLocation> getVehicleLocation(@PathVariable(AppUtil.VEHICLE_NUMBER) String vehicleNumber) throws IOException, ParseException;

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful", content = { @Content( mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content(schema = @Schema(),mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema(),mediaType = "application/json") })
    })
    @Operation(summary ="GET ALL FREE SPACES")
    public abstract ResponseEntity<FreeParkingGarageSpace> getAllFreeSpaces() throws IOException, ParseException;
}

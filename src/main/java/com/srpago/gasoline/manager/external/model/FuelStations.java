package com.srpago.gasoline.manager.external.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.srpago.gasoline.manager.model.FuelStation;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pagination",
    "results"
})
public class FuelStations {

    @JsonProperty("results")
    private List<FuelStation> fuelStations = null;


    @JsonProperty("results")
    public List<FuelStation> getFuelStations() {
        return fuelStations;
    }

    @JsonProperty("results")
    public void setFuelStations(List<FuelStation> fuelStations) {
        this.fuelStations = fuelStations;
    }

}

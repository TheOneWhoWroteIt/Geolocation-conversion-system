package com.senla.Geolocationconversionsystem.controller;

import com.senla.Geolocationconversionsystem.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoController {

  GeoService geoService;

  @Autowired
  public GeoController(GeoService geoService) {
    this.geoService = geoService;
  }

  @GetMapping("/position/{address}")
  public String getPosition(@PathVariable String address) {
    return geoService.getPosition(address);
  }

  @GetMapping("/address/{position}")
  public String getAddress(@PathVariable String position) {
    return geoService.getAddress(position);
  }
}

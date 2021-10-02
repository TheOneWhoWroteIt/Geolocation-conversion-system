package com.senla.Geolocationconversionsystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Position {

  @JsonProperty("lat")
  private String lat;

  @JsonProperty("lng")
  private String lng;

  public Position() {}

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public String getLng() {
    return lng;
  }

  public void setLng(String lng) {
    this.lng = lng;
  }

  @Override
  public String toString() {
    return "Longitude: " + lng + "; " + "Latitude: " + lat;
  }
}

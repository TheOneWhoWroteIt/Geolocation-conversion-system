package com.senla.Geolocationconversionsystem.service;

import com.senla.Geolocationconversionsystem.util.GeoUtil;
import com.squareup.okhttp.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class GeoService {

  private final String POSITION_PREF_URL = "https://geocode.search.hereapi.com/v1/geocode?q=";
  private final String ADDRESS_PREF_URL = "https://revgeocode.search.hereapi.com/v1/revgeocode?at=";
  private final String PART_URL_FOR_KEY = "&apiKey=";
  private final String KEY_API = "aMMQjymQ3US33cQeVSsix0hmHYUFT59z0yXRZWcpD2w";

  private GeoUtil geoUtil;

  @Autowired
  public GeoService(GeoUtil geoUtil) {
    this.geoUtil = geoUtil;
  }

  @Cacheable(cacheNames = "position")
  public String getPosition(String address) {

    Request request =
        new Request.Builder()
            .url(POSITION_PREF_URL + address + PART_URL_FOR_KEY + KEY_API)
            .get()
            .build();

    try {
      return geoUtil.getResultObject(request).getItems().stream().findAny().get().getPosition().toString();
    } catch (NullPointerException | NoSuchElementException ex) {
      ex.printStackTrace();
      return "Неверные данные. Проверьте правильность введенного адреса.";
    }
  }

  @Cacheable(cacheNames = "address")
  public String getAddress(String position) {

    Request request =
        new Request.Builder()
            .url(ADDRESS_PREF_URL + position + PART_URL_FOR_KEY + KEY_API)
            .get()
            .build();

    try {
      return geoUtil.getResultObject(request).getItems().stream().findAny().get().getAddress().getLabel();
    } catch (NullPointerException | NoSuchElementException ex) {
      ex.printStackTrace();
      return "Неверные данные. Проверьте правильность введенных координат.";
    }
  }
}

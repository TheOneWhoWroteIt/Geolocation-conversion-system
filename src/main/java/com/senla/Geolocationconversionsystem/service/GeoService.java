package com.senla.Geolocationconversionsystem.service;

import com.senla.Geolocationconversionsystem.util.GeoUtil;
import com.squareup.okhttp.Request;
import org.springframework.stereotype.Component;

@Component
public class GeoService {

  private final String POSITION_PREF_URL = "https://geocode.search.hereapi.com/v1/geocode?q=";
  private final String ADDRESS_PREF_URL = "https://revgeocode.search.hereapi.com/v1/revgeocode?at=";
  private final String PART_URL_FOR_KEY = "&apiKey=";
  private final String KEY_API = "aMMQjymQ3US33cQeVSsix0hmHYUFT59z0yXRZWcpD2w";

  public String getPosition(String address) {

    Request request =
        new Request.Builder()
            .url(POSITION_PREF_URL + address + PART_URL_FOR_KEY + KEY_API)
            .get()
            .build();

    return GeoUtil.getItem(request).getPosition().toString();
  }
}

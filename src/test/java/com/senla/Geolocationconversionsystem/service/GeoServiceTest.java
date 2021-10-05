package com.senla.Geolocationconversionsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@RunWith(SpringRunner.class)
@SpringBootTest
class GeoServiceTest {

  @Autowired private GeoService geoService;
  @Autowired private CacheManager cacheManager;

  @BeforeEach
  void setDataToCache() {
    geoService.getPosition("гродно");
    geoService.getAddress("53.93133,27.64601");
  }

  private Optional<String> getAddressFromCache(String position){
      return ofNullable(cacheManager.getCache("address")).map(c -> c.get(position, String.class));
  }

  private Optional<String> getPositionFromCache(String address){
      return ofNullable(cacheManager.getCache("position")).map(c -> c.get(address, String.class));
  }


}

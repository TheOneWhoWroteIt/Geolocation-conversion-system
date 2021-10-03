package com.senla.Geolocationconversionsystem.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.Geolocationconversionsystem.entity.Item;
import com.senla.Geolocationconversionsystem.entity.ResultObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;
import org.springframework.stereotype.Component;

@Component
public class GeoUtil {

  public ResultObject getResultObject(Request request) {

    try {
      OkHttpClient client = new OkHttpClient();

      ResponseBody responseBody = client.newCall(request).execute().body();

      ObjectMapper objectMapper = new ObjectMapper();

      ResultObject resultObject = objectMapper.readValue(responseBody.string(), ResultObject.class);

      return resultObject;

    } catch (Exception ex) {

      ex.printStackTrace();

      throw new NullPointerException();
    }
  }
}

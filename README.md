# Система конвертации геоположения

Необходимо разработать приложение для конвертации координат в адрес и из адреса в координаты. Нужно использовать сторонние API (Google, Яндекс, другое). По сути приложение будет прокси с кешированием. Кеширование заключается в том, что если мы отправляли такой же запрос раньше, то запрос к  стороннему API отправлен не будет.

**Как это работает:**

По запросу «Москва, ул. Льва Толстого, 16» геокодер выдаст координаты этого дома — [37.587874, 55.73367]. А если в запросе указать географические координаты нужной точки — скажем, [27.525773, 53.89079], то геокодер вернёт адрес: Минск, Проспект Дзержинского, 5. Если мы снова запросим «Москва, ул. Льва Толстого, 16», то запроса к стороннему API отправлен не будет.
Взаимодействие пользователя с приложением происходит через REST API(предпочтительно).

**Требования:**

* приложение должно быть реализовано на языке Java версии 8+ ;
* код должен соответствовать принципам Low Coupling и принципам ООП;
* код должен соответствовать Java code style (именование переменных, структура класса и др.);
* приложение должно содержать качественную обработку ошибок;
* использовать Spring Framework(Spring Boot).
* покрытие unit тестами(опционально, но будет плюсом).

----------------------------------------------------------------------------------------

Для решения задачи используется сервис "HERE Geocoding and Search".

Пример ответа сервиса для получения координат адреса "5 Rue Daunou, 75000 Paris, France", в формате JSON имеет следующий вид:

```json
{
  "items": [
    {
      "title": "5 Rue Daunou, 75002 Paris, France",
      "id": "here:af:streetsection:bI4Le6cyA.1mlQyLunYpjC:CggIBCCi-9SPARABGgE1KGQ",
      "resultType": "houseNumber",
      "houseNumberType": "PA",
      "address": {
        "label": "5 Rue Daunou, 75002 Paris, France",
        "countryCode": "FRA",
        "countryName": "France",
        "stateCode": "IDF",
        "state": "Île-de-France",
        "county": "Paris",
        "city": "Paris",
        "district": "2e Arrondissement",
        "street": "Rue Daunou",
        "postalCode": "75002",
        "houseNumber": "5"
      },
      "position": {
        "lat": 48.86926,
        "lng": 2.3321
      },
      "access": [
        {
          "lat": 48.86931,
          "lng": 2.33215
        }
      ],
      "mapView": {
        "west": 2.33073,
        "south": 48.86836,
        "east": 2.33347,
        "north": 48.87016
      },
      "scoring": {
        "queryScore": 0.97,
        "fieldScore": {
          "country": 1,
          "city": 1,
          "streets": [
            1
          ],
          "houseNumber": 1,
          "postalCode": 0.82
        }
      }
    }
  ]
} 
```

Пример ответа сервиса для получения адреса по введенным координатам "48.2181679,16.3899064", в формате JSON имеет следующий вид:

```json
{
  "items": [
    {
      "title": "Heinestraße 42, 1020 Vienna, Austria",
      "id": "here:af:streetsection:2VFm4oq5Zq8utAoSB90pmA:CgcIBCD6iaNNEAEaAjQy",
      "resultType": "houseNumber",
      "houseNumberType": "PA",
      "address": {
        "label": "Heinestraße 42, 1020 Vienna, Austria",
        "countryCode": "AUT",
        "countryName": "Austria",
        "state": "Vienna",
        "county": "Vienna",
        "city": "Vienna",
        "district": "2. Bezirk-Leopoldstadt",
        "street": "Heinestraße",
        "postalCode": "1020",
        "houseNumber": "42"
      },
      "position": {
        "lat": 48.21809,
        "lng": 16.38988
      },
      "access": [
        {
          "lat": 48.21815,
          "lng": 16.38995
        }
      ],
      "distance": 4,
      "mapView": {
        "west": 16.39157,
        "south": 48.21697,
        "east": 16.38819,
        "north": 48.21921
      }
    }
  ]
} 
```

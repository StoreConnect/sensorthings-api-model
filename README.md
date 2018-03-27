# OGC SensorThings API's entity model

Java version of the [OGC SensorThings API](https://github.com/opengeospatial/sensorthings)'s entity model.

Covers the [Sensing](http://docs.opengeospatial.org/is/15-078r6/15-078r6.html) part and want to cover the Tasking part when it will be available.

Within the Sensing part, this model is compatible with:
- The [Sensing entities](http://docs.opengeospatial.org/is/15-078r6/15-078r6.html#24)
- The [SensorThings MultiDatastream extension](http://docs.opengeospatial.org/is/15-078r6/15-078r6.html#77)
- ~~The [SensorThings Data Array extension](http://docs.opengeospatial.org/is/15-078r6/15-078r6.html#78)~~ (in a future release)

## How to use it

### Available on Maven Central

Under submission to Maven Central.

### Builder construction pattern

All entities can be constructed via its associated `builder`. For instance, a HistoricalLocation can be built like this:

```java
final HistoricalLocation historicalLocation = HistoricalLocation.builder()
    .id(new Integer(1))
    .selfLink(new URL("http://example.org/v1.0/HistoricalLocations(1)"))
    .locationsNavigationLink("HistoricalLocations(1)/Locations")
    .thingNavigationLink("HistoricalLocations(1)/Thing")
    .time(TMInstant.builder()
        .instant(OffsetDateTime.parse("2018-03-28T12:00:00+01:00"))
        .build()
    )
    .build();
```

More example of entities construction can be found in the [unit tests](./src/test/java).

### JSON serialization/deserialization support

All entities supports the use of the [Jackson library](https://github.com/FasterXML/jackson) to allow JSON serialization/deserialization.

In addition, all entities integrates its own Jackson configuration (e.g., specific serializers or deserializers). Hence, only a default Jackson's [ObjectMapper](https://github.com/FasterXML/jackson-databind#1-minute-tutorial-pojos-to-json-and-back) instance can be used to manipulate them.

For instance, the basic JSON serialization process of our previous `historicalLocation` would be:

```java 
final String jsonHistoricalLocation = new ObjectMapper().writeValueAsString(historicalLocation);
```

That will produce the following JSON file:

```json
{
  "@iot.id": 1,
  "@iot.selfLink": "http://example.org/v1.0/HistoricalLocations(1)",
  "Locations@iot.navigationLink": "HistoricalLocations(1)/Locations",
  "Thing@iot.navigationLink": "HistoricalLocations(1)/Thing",
  "time": "2015-01-25T12:00:00-07:00"
}
```

In the same way, the basic JSON deserialization process would be:

```java 
final HistoricalLocation deserializedHistoricalLocation = new ObjectMapper().readValue(jsonHistoricalLocation, HistoricalLocation.class);
```

And, as we expect, the `deserializedHistoricalLocation` will be the same as our previous `historicalLocation`.

More examples of serialization/deserialization can be found in the [unit tests](./src/test/java).

## How to contribute

Feel free to contribute by making a `pull request` following the [contributing](./CONTRIBUTING.md) instructions.
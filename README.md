# UP42 Feature Service

### API

```
GET /features

200 OK
{
  "features": [
    {
      "id": "39c2f29e-c0f8-4a39-a98b-deed547d6aea",
      "timestamp": 1554831167697, // epoch-ms
      "beginViewingDate": 1554831167697, // epoch-ms
      "endViewingDate": 1554831202043, // epoch-ms
      "missionName": "Sentinel-1B"
    },
    {
      "id": "39c2f29e-c0f8-4a39-a98b-deed547d6aea",
      "timestamp": 1554831167697, // epoch-ms
      "beginViewingDate": 1554831167697, // epoch-ms
      "endViewingDate": 1554831202043, // epoch-ms
      "missionName": "Sentinel-1B"
    }
  ]
}

GET /features/{id}

200 OK
{
  "id": "39c2f29e-c0f8-4a39-a98b-deed547d6aea",
  "timestamp": 1554831167697, // epoch-ms
  "beginViewingDate": 1554831167697, // epoch-ms
  "endViewingDate": 1554831202043, // epoch-ms
  "missionName": "Sentinel-1B"
}

GET /features/{id}/quicklook

200 OK
<bytes of the image/png>
```

### Notes

1. The code doesn't do any validation/sanitization against on the JSON file, assuming its data blindly.
2. The JSON file contains entries with duplicate `timestamp` property. We assume they are the same.
3. The JSON file contains entries with both `id` and `uid` properties. We use `id` property as feature ID.
4. We assume that all list of features in the JSON file can be merged into one list.
5. The specification doesn't say anything about the `timestamp` property (and other datetime properties),
whether it's epoch seconds, milliseconds or nanoseconds. We assume it is milliseconds.
6. We assume all properties are non-null, except quicklook.
7. To run the service, either use Maven or Docker. Docker image is a multi-stage type,
including running tests, building artifact (fat-jar), and building docker image.

### Improvement

1. Make the `GET /features` endpoint pageable.

### Run

```
# build and run the docker image
docker build . t feature-service
docker run --publish 8080:8080

# or using maven
mvn clean verify    # to package the artifact
mvn springboot:run  # only to run the service

# and call the endpoint
curl localhost:8080/features | jq
curl localhost:8080/features/{id} | jq
curl localhost:8080/features/{id}/quicklook
```

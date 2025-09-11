# The Worstest Films
This is the API for listing the worst films of the Golden Raspberry Awards.

## Technologies

- Java: 21 
- Spring Boot (Web, Data): 3.5.5
- Apache commons csv: 1.14.1
- H2

## Building / Running the application

This app using maven as build tool. 

All contents is in app folder, to build or run the application, first navigate to the app folder:
```bash
cd app
```

To build the application, use the following command (To run this command you need java 21+ installed):
```bash
./mvnw clean package
```

To run the application, use the following command:
```bash
./mvnw spring-boot:run
```

## Testing

To run the tests, use the following command:
```bash
./mvnw test
```

## Data Initialization

The application initializes its data from a CSV file located at `src/main/resources/movielist.csv`. This file contains the list of films and their details.

## API Endpoints

The application exposes the following endpoints:

### [GET] /worstest-films/v1/producers

Retrieve the worstest films producers with their interval details.

This endpoint returns a list of producers who have won multiple awards, along with the interval between their wins.

#### Response 
| Field | Type                   | Description |
|-------|------------------------|-------------|
|min | List<ProducerInterval> | List of producers with the minimum interval between awards |
|max | List<ProducerInterval> | List of producers with the maximum interval between awards |

#### ProducerInterval

| Field | Type | Description |
|-------|------|-------------|
| producer | String | Name of the producer |
| interval | Integer | Interval between two awards |
| previousWin | Integer | Year of the previous win |
| followingWin | Integer | Year of the following win |

#### Example of calling the endpoint

```bash
curl -X GET "http://localhost:8080/worstest-films/v1/producers" -H "accept: application/json"
```
#### Example Response

```json
{
  "min": [
    {
      "producer": "Producer A",
      "interval": 1,
      "previousWin": 2000,
      "followingWin": 2001
    }
  ],
  "max": [
    {
      "producer": "Producer B",
      "interval": 10,
      "previousWin": 1990,
      "followingWin": 2000
    }
  ]
}
```


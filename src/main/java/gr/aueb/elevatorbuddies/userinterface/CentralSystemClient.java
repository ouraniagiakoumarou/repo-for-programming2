package gr.aueb.elevatorbuddies.userinterface;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import java.io.IOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class CentralSystemClient {
    private final int minFloor;
    private final int maxFloor;
    private int originFloor;
    private int destinationFloor;
    private final HttpClient httpClient;
    private final String centralSystemUrl; 
    // sends an elevator request to the Central System
    public String requestElevator(int originFloor, int destinationFloor) {
        if (originFloor == destinationFloor) {
            System.out.println("Origin and destination floors cannot be the same.");
            return null;
        }
        if (!isValidFloor(originFloor) || !isValidFloor(destinationFloor)) {
        System.out.println("Error: Floors must be between " + minFloor + " and " + maxFloor);
        return null;
        }
        String jsonBody = "{\"fromFloor\":" + originFloor + ",\"toFloor\":" + destinationFloor + "}";
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(centralSystemUrl))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                
            .build();
            try {
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 200) {
                    System.out.println("Error: Received non-OK response from Central System: " + response.statusCode());
                    return null;
                }
                String responseBody = response.body();
                JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();
                String elevatorId = jsonResponse.get("elevatorId").getAsString();
                System.out.println("Response from Central System: " + responseBody);
                if (responseBody == null || responseBody.isBlank()) {
                    System.out.println("Error: Received empty response from Central System.");
                    return null;
                }
                return elevatorId;
             
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        System.out.println("Request sent to Central System");
        System.out.println("Origin Floor: " + originFloor);
        System.out.println("Destination Floor: " + destinationFloor);
        System.out.println("Elevator request processed successfully.");
        return "A";
    }
    private boolean isValidFloor(int floor) {
        return floor >= minFloor && floor <= maxFloor;
    }
    public CentralSystemClient(int minFloor, int maxFloor) {
        if (minFloor>= maxFloor) {
            throw new IllegalArgumentException("Minimum floor must be less than maximum floor.");
        }
        this.minFloor = minFloor;
        this.maxFloor = maxFloor; 
        this.centralSystemUrl = "http://localhost:8080/api/central/request";
        this.httpClient = HttpClient.newHttpClient();  
    }
}


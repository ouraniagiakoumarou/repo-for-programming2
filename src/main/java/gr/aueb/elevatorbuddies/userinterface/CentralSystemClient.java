package gr.aueb.elevatorbuddies.userinterface;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import java.io.IOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonParseException;
public class CentralSystemClient {
    private final int minFloor;
    private final int maxFloor;
    private int originFloor;
    private int destinationFloor;
    private final HttpClient httpClient;
    private final String centralSystemUrl; 
    public CentralSystemClient(int minFloor, int maxFloor) {
        if (minFloor>= maxFloor) {
            throw new IllegalArgumentException("Minimum floor must be less than maximum floor.");
        }
        this.minFloor = minFloor;
        this.maxFloor = maxFloor; 
        this.centralSystemUrl = "http://localhost:8080/api/central/request";
        this.httpClient = HttpClient.newHttpClient();  
    }
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
                if (responseBody == null || responseBody.isBlank()) {
                    System.out.println("Error: Received empty response from Central System.");
                    return null;
                }
                System.out.println("Response from Central System: " + responseBody);
                JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();
                if (!jsonResponse.has("elevatorId") || jsonResponse.get("elevatorId").isJsonNull()) {
                    System.out.println("Error: Response from Central System does not contain 'elevatorId'.");
                    return null;
                }
                String elevatorId = jsonResponse.get("elevatorId").getAsString();
                
                
                return elevatorId;
             
            } catch (IOException e) {
                System.out.println("Communication error: Unable to send request to Central System.");
                e.printStackTrace();
                return null;
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                System.out.println("Communication error: Request to Central System was interrupted.");
                e.printStackTrace();
                return null;
            } catch (JsonParseException e) {
                System.out.println("Error: Failed to parse JSON response from Central System.");
                e.printStackTrace();
                return null;
            }
    }
    private boolean isValidFloor(int floor) {
        return floor >= minFloor && floor <= maxFloor;
    }
}


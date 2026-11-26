package gr.aueb.elevatorbuddies.centralsystem;
import gr.aueb.elevatorbuddies.elevatorcontrol.Elevator;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;

public class CentralSystemMain {
    public static void main(String[] args) {
        // 1. Set default values or read from arguments
        int numberOfElevators = 4;
        int totalFloors = 10;
        
        if (args.length >= 2) {
            try {
                numberOfElevators = Integer.parseInt(args[0]);
                totalFloors = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Error: Parameters must be integer numbers.");
                return;
            }
        }
        // 2. Validate bounds according to specifications
        if (numberOfElevators < 2 || numberOfElevators > 20) {
            System.err.println("Error: The number of elevators must be between 2 and 20.");
            return;
        }
        if (totalFloors < 3 || totalFloors > 200) {
            System.err.println("Error: The total number of floors must be between 3 and 200.");
            return;
        }
        try {
            // lines 33-46 generated with AI
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/api/central/request", exchange -> {
                if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                    exchange.sendResponseHeaders(405,-1);
                    exchange.close();
                    return;
                }
                System.out.println("Received request: ");
            });
            System.out.println("HTTP server started on port 8080");
        } catch (IOException e) {
            System.err.println("Error: Unable to start the HTTP server on port 8080.");
            e.printStackTrace();
        }
        System.out.println("Starting Central System with " + numberOfElevators + " elevators and " + totalFloors + "floors...");
        // 3. Initialize core Central System classes
        ElevatorRegistry registry = new ElevatorRegistry(numberOfElevators);
        ElevatorAssigner assigner = new ElevatorAssigner(registry);
        ElevatorClient client = new ElevatorClient();
        RequestController controller = new RequestController(assigner, client, totalFloors);

        // 4. Start the REST server / request handling mechanism
        // controller.startServer();
        System.out.println("Central System started successfully and is awaiting requests.");
    }
}
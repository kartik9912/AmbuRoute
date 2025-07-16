package amburoute;

import java.util.Scanner;

public class DispatchService {
    Scanner sc = new Scanner(System.in);
    Graph graph;

    public DispatchService(Graph graph) {
        this.graph = graph;
    }

    public void dispatch() {
        System.out.print("Enter Emergency Location: ");
        String location = sc.nextLine();

        Ambulance amb = AmbulanceService.findNearestAmbulance(location, graph);
        if (amb != null) {
            amb.isAvailable = false;
            System.out.println("🚑 Ambulance " + amb.id + " dispatched from " + amb.currentLocation + " to " + location);
        } else {
            System.out.println("❌ No available ambulance found.");
        }
    }
}

package amburoute;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AmbulanceService {
    private List<Ambulance> ambulances = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void addAmbulance() {
        System.out.print("Enter Ambulance ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Current Location: ");
        String location = sc.nextLine();
        ambulances.add(new Ambulance(id, location));
        System.out.println("✅ Ambulance added successfully.");
    }

    public void viewAmbulances() {
        if (ambulances.isEmpty()) {
            System.out.println("⚠️ No ambulances found.");
            return;
        }
        System.out.println("🚑 List of Ambulances:");
        for (Ambulance a : ambulances) {
            System.out.println("- ID: " + a.id + ", Location: " + a.currentLocation + ", Status: " + (a.isAvailable ? "Available" : "Busy"));
        }
    }

    public List<Ambulance> getAmbulances() {
        return ambulances;
    }

    public Ambulance findNearest(String location, Graph graph) {
        double minDist = Double.MAX_VALUE;
        Ambulance nearest = null;
        for (Ambulance a : ambulances) {
            if (a.isAvailable) {
                double dist = graph.getShortestDistance(a.currentLocation, location);
                if (dist < minDist) {
                    minDist = dist;
                    nearest = a;
                }
            }
        }
        return nearest;
    }
}

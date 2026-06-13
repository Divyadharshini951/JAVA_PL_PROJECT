import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Package {
    private String id;
    private String sender;
    private String receiver;
    private List<String> history = new ArrayList<>();

    public Package(String id, String sender, String receiver) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        updateStatus("Ordered"); 
    }

    public String getId() {
        return id;
    }

    public void updateStatus(String newStatus) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        history.add(newStatus + " at " + now.format(formatter));
    }

    public void displayInfo() {
        System.out.println("Package ID: " + id);
        System.out.println("Sender: " + sender);
        System.out.println("Receiver: " + receiver);
        System.out.println("Current Status: " + (history.isEmpty() ? "None" : history.get(history.size()-1)));
        System.out.println("---------------------------");
    }

    public void displayHistory() {
        System.out.println("Package ID: " + id);
        System.out.println("Status History:");
        if (history.isEmpty()) {
            System.out.println("  No history available.");
        } else {
            for (String h : history) {
                System.out.println("  " + h);
            }
        }
        System.out.println("---------------------------");
    }
}

public class PLproject_44 {  
    private static Map<String, Package> packageMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Package Delivery Tracking System ---");
            System.out.println("1. Add New Package");
            System.out.println("2. View Package Info");
            System.out.println("3. Update Package Status");
            System.out.println("4. List All Packages");
            System.out.println("5. View Status History");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Package ID: ");
                    String newId = sc.nextLine();
                    if (packageMap.containsKey(newId)) {
                        System.out.println("Package ID already exists!");
                        break;
                    }
                    System.out.print("Enter Sender Name: ");
                    String sender = sc.nextLine();
                    System.out.print("Enter Receiver Name: ");
                    String receiver = sc.nextLine();
                    packageMap.put(newId, new Package(newId, sender, receiver));
                    System.out.println("New package added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Package ID: ");
                    String id = sc.nextLine();
                    Package p = packageMap.get(id);
                    if (p != null) {
                        p.displayInfo();
                    } else {
                        System.out.println("Package not found!");
               
                    }
                    break;

                case 3:
                    System.out.print("Enter Package ID: ");
                    id = sc.nextLine();
                    p = packageMap.get(id);
                    if (p != null) {
                        System.out.print("Enter new status (Ordered/Dispatched/In Transit/Delivered): ");
                        String status = sc.nextLine();
                        p.updateStatus(status);
                        System.out.println("Status updated successfully!");
                    } else {
                        System.out.println("Package not found!");
                    }
                    break;

                case 4:
                    if (packageMap.isEmpty()) {
                        System.out.println("No packages available!");
                    } else {
                        for (Package pkg : packageMap.values()) {
                            pkg.displayInfo();
                        }
                    }
                    break;

                case 5:
                    System.out.print("Enter Package ID: ");
                    id = sc.nextLine();
                    p = packageMap.get(id);
                    if (p != null) {
                        p.displayHistory();
                    } else {
                        System.out.println("Package not found!");
                    }
                    break;

                case 6:
                    System.out.println("Danke fürs Nutzen vom Paketzustellsystem. Tschüss, machts gut!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

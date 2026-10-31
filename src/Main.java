import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1. Create at least 3 technicians, including two technicians with the same name to demonstrate
            // HashSet<Technician> behavior.
        Technician technician1 = new Technician("Jake");
        Technician technician2 = new Technician("Alex");
        Technician technician3 = new Technician("Laura");
        Technician technician4 = new Technician("Jake");
        
        // Check hashcode() behavior for "Jake"
        System.out.println("Check equals() and hashCode() behavior:");
        System.out.println("equals (should be true): " + technician1.equals(technician4));
        System.out.println("hashCode (should be equal): t1: " + technician1.hashCode() + ", t4: " + technician4.hashCode());
        System.out.println();

        // 2. Create at least 6 support requests total, including at least 2 of each required request type.
        DeviceSetupRequest r1 = new DeviceSetupRequest(1, "Install WSL for 1050 students", 6, 10);
        DeviceSetupRequest r2 = new DeviceSetupRequest(2, "Set up dorm lab computers", 6, 15);
        PasswordResetRequest r3 = new PasswordResetRequest(3, "Reset account password", 10, "alice");
        PasswordResetRequest r4 = new PasswordResetRequest(4, "Reset administrator password", 15, "jake");
        // will recieve milestone bonus since affectedUsers >= 20
        NetworkIssueRequest r5 = new NetworkIssueRequest(5, "Network outage in Strickland Hall lab", 20, 25);
        NetworkIssueRequest r6 = new NetworkIssueRequest(6, "Slow network connection in Strickland room 212", 15, 8);

        // Create helpDesk
        HelpDesk helpDesk = HelpDesk.getInstance(); // call HelpDesk singleton method getInstance()

        List<SupportRequest> requests = List.of(r1, r2, r3, r4, r5, r6);

        System.out.println("Test assignRequest prior to adding request to HelpDesk requestsById: (Expect exception)");
        try {
            helpDesk.assignRequest(technician1, r1.getId());
        } catch(Exception e) {
            // test IllegalArgumentException("requestId invalid, request is null, must be added first");
            System.out.println(e.getMessage());
        }
        System.out.println();

        // 3. Add requests to the help desk.
        for(SupportRequest r : requests) {
            helpDesk.addRequest(r);
        }

        // 4. Assign at least 4 requests across at least 2 technicians.
        helpDesk.assignRequest(technician1, r1.getId());
        helpDesk.assignRequest(technician1, r2.getId());
        helpDesk.assignRequest(technician2, r3.getId());
        helpDesk.assignRequest(technician2, r4.getId());

        System.out.println("Show each request can only be assigned to one technician. (Will print false)");
        System.out.println(helpDesk.assignRequest(technician2, r1.getId())); // has already been assigned to t1
        System.out.println();

        // 5a. (Extra) Test helpDesk exception handling
        System.out.println("Test null technician and request: (Expect exceptions)");
        try { // expect request not found (not added)
            NetworkIssueRequest not_added_request = new NetworkIssueRequest(6, "foo", 15, 8);
            helpDesk.resolveRequest(technician1, not_added_request.getId());
        } catch(Exception e) {
            // test IllegalArgumentException("requestId invalid, request is null, must be added first");
            System.out.println(e.getMessage());
        }
        try { // expect "technician must not be null"
            helpDesk.resolveRequest(null, r1.getId());
        } catch(Exception e) {
            // test IllegalArgumentException("requestId invalid, request is null, must be added first");
            System.out.println(e.getMessage());
        }
        System.out.println();

        // 5. Resolve at least 3 requests across different request types.
        helpDesk.resolveRequest(technician1, r1.getId()); // DeviceSetupRequest
        helpDesk.resolveRequest(technician2, r3.getId()); // PasswordResetRequest
        helpDesk.resolveRequest(technician2, r5.getId()); // NetworkIssueRequest

        // 6. Print all requests
        System.out.println("6. Print all requests");
        helpDesk.printAllRequests();
        System.out.println();

        // 7. Print all assignments for technician Jake
        System.out.println("7. Print all assignments for technician Jake");
        helpDesk.printAssignmentsFor(technician1);
        System.out.println();

        // For 8 and 9, create list of technicians
        List<Technician> technicians = List.of(technician1, technician2, technician3);

        List<Integer> servicePointsPerTechnician = new ArrayList<>();
        // 8. Print final technician service point totals.
        for(Technician t : technicians) {
            servicePointsPerTechnician.add(t.getServicePoints());
        }

        System.out.println("8. Call HelpDeskUtil.printall() to print all service points for t1, t2, t3.");
        HelpDeskUtil.printAll(servicePointsPerTechnician);
        System.out.println();

        // 9. Use HelpDeskUtil.sumPoints to print the total service points across technicians.
        System.out.println("9. Use HelpDeskUtil.sumPoints to print the total service points across technicians.");
        System.out.println("Sum: " + HelpDeskUtil.sumPoints(technicians));
    }
}

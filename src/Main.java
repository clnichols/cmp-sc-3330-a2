import java.util.List;

public class Main {
    public static void main(String[] args) {

        Technician technician1 = new Technician("Jake");
        Technician technician2 = new Technician("Alex");
        Technician technician3 = new Technician("Laura");
        Technician technician4 = new Technician("Jake");
        
        // Check hashcode() behavior for "Jake"
        System.out.println("Check equals() and hashCode() behavior:");
        System.out.println("equals (should be true): " + technician1.equals(technician4));
        System.out.println("hashCode (should be equal): t1: " + technician1.hashCode() + ", t4: " + technician4.hashCode());
        System.out.println();

        DeviceSetupRequest r1 = new DeviceSetupRequest(1, "Install WSL for 1050 students", 6, 10);
        DeviceSetupRequest r2 = new DeviceSetupRequest(2, "Set up dorm lab computers", 6, 15);
        PasswordResetRequest r3 = new PasswordResetRequest(3, "Reset account password", 10, "alice");
        PasswordResetRequest r4 = new PasswordResetRequest(4, "Reset administrator password", 15, "bob");
        // will recieve milestone bonus since affectedUsers >= 20
        NetworkIssueRequest r5 = new NetworkIssueRequest(5, "Network outage in Strickland Hall lab", 20, 25);
        NetworkIssueRequest r6 = new NetworkIssueRequest(6, "Slow network connection in Strickland room 212", 15, 8);

        HelpDesk helpDesk = HelpDesk.getInstance(); // call HelpDesk singleton method getInstance()

        List<SupportRequest> requests = List.of(r1, r2, r3, r4, r5, r6);

        for(SupportRequest r : requests) {
            helpDesk.addRequest(r);
        }

        helpDesk.assignRequest(technician1, r1.getId());
        helpDesk.assignRequest(technician1, r2.getId());

        helpDesk.assignRequest(technician2, r3.getId());
        helpDesk.assignRequest(technician2, r4.getId());


    


    }
}

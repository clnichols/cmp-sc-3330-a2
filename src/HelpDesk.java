import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class HelpDesk {
    // Open Design rule 1: Requests cannot be resolved more than once.
    // Open Design rule 2: The same SupportRequest cannot be assigned to the same technician more than once
    // Open Design rule 3: mulitple technicians cannot be assigned to the same request.
    /* reasoning: this would require substantial change to each SupportRequest subclass, including
        adding maxAssignableTechnicians for each subclass, depending on their respective fields,
        better to restrict to one. */

    // Use eager singleton design
    private static final HelpDesk INSTANCE = new HelpDesk();

    private Map<Integer, SupportRequest> requestsById;
    private Map<Technician, List<SupportRequest>> assignments;

    private HelpDesk() { // eager singleton design pattern
        requestsById = new HashMap<>();
        assignments = new HashMap<>();
    }

    public static HelpDesk getInstance() {
        return INSTANCE;
    }
    
    
    public boolean addRequest(SupportRequest request) {
        if(request == null) {
            return false; // return fail if request object is null
        }

        // Use
        if (requestsById.containsKey(request.getId())) {
            throw new IllegalArgumentException("requestId already added");
        }

        requestsById.put(request.getId(), request); // request ids are handled by AbstractSupportRequest class

        return true; // return success
    }

    public SupportRequest findRequest(int id) {
        return requestsById.get(id); // if key not found, will return null, else will return request
    }

    public boolean assignRequest(Technician technician, int requestId) {
        if(technician == null) {
            throw new IllegalArgumentException("technician must not be null");
        }

        SupportRequest request = findRequest(requestId); // find request object

        // requests must be added to requestsById before it can be assigned
        if(request == null) {
            throw new IllegalArgumentException("requestId invalid, request is null, must be added first");
        }

        // Open Design Choice 3: Check how many technicians have been assigned this request
        for (Technician assignedTechnician : assignments.keySet()) {
            if (assignments.get(assignedTechnician).contains(request)) {
                return false; // only one technician may be assigned to each request
            }
        }
        

        // Check if technician already has a list
        if(!assignments.containsKey(technician)) {
            assignments.put(technician, new ArrayList<>()); // create a SupportRequest list object
        }

        // redundant if and only if maxAssignableTechnicians per request is 1
        // Open Design Choice 2: Check if technician has already been assigned request
        if(assignments.get(technician).contains(request)) {
            // Rule: double assignments are not permitted
            // If deadlines were created in Support Request for example, 
            // double assignment might signify a deadline extension. Otherwise:

            return false; // return false to signify no request was assigned
        }

        // Otherwise, retrieve SupportRequest list for technician, and add request
        assignments.get(technician).add(request);
        
        return true;
    }

    public int resolveRequest(Technician technician, int requestId) {
        if(technician == null) {
            throw new IllegalArgumentException("technician must not be null");
        }

        SupportRequest request = findRequest(requestId); // find request object
        
        if(request == null) {
            throw new IllegalArgumentException("requestId invalid, request not found");
        }

        // Check if request belongs to technician
        List<SupportRequest> requests = assignments.get(technician);

        // safety checks
        if(requests == null || !requests.contains(request)) { 
            return 0;
        }

        try {
            return request.resolveFor(technician); // will return servicePoints
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return 0; // if resolveFor fails, rturn 0 servicePoints, as no points were added
        }
    }

    public void printAllRequests() {
        for(SupportRequest request : requestsById.values()) { // Query map for all requests
            System.out.println(request.toString()); // print each request
        }
    }

    public void printAssignmentsFor(Technician technician) {
        if(technician == null) {
            throw new IllegalArgumentException("technician must not be null");
        }

        // Print technician info, then enumerate requests in List<SupportRequest>
        System.out.println(technician.toString()); // call overridden toString() method

        List<SupportRequest> requests = assignments.get(technician);

        if(requests == null) { // must check if List<SupportRequest> is null
            return; // No assignments exist for this technician
        }

        for(SupportRequest request : assignments.get(technician)) {
            if(request == null) {
                continue; // request does not exist
            }

            System.out.println(request.toString());
        }
    }
}
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class HelpDesk {
    private Map<Integer, SupportRequest> requestsById;
    private Map<Technician, List<SupportRequest>> assignments;

    public HelpDesk() { // eager singleton design pattern
        requestsById = new HashMap<>();
        assignments = new HashMap<>();
    }
    
    public boolean addRequest(SupportRequest request) {
        if(request == null) {
            return false; // return fail if request object is null
        }
        if(requestsById.containsKey(request.id)) {
            throw new IllegalArgumentException("request already added");
        }
        requestsById.put(request.id, request); // request ids are handled by AbstractSupportRequest

        return true; // return success
    }

    public SupportRequest findRequest(int id) {
        return requestsById.get(id); // if key not found, will return null, else will return request
    }

    public boolean assignRequest(Technician technician, int requestId) {
        if(technician == null) {
            throw new IllegalArgumentException("technician doesn't exist");
        }

        Request request = findRequest(request.id); // find request object

        if(request == null) {
            throw new IllegalArgumentException("requestId invalid, request not found");
        }

        // Check if technician already has a list
        if(!assignments.containsKey(technician)) {
            assignments.put(technician, new ArrayList<>()); // create a SupportRequest list object
        }

        // Check if technician has already been assigned request
        if(assignments.get(technician).contains(request)) {
            // If deadlines were created in Support Request for example, 
            // doubly adding might signify a deadline extension. Otherwise:

            return true; // return true without adding
        }

        // Otherwise, retrieve SupportRequest list for technician, and add request
        assignments.get(technician).add(request);
        
        return true;
    }

    public int resolveRequest(Technician technician, int requestId) {
        if(technician == null) {
            throw new IllegalArgumentException("technician doesn't exist");
        }

        Request request = findRequest(request.id); // find request object
        
        if(request == null) {
            throw new IllegalArgumentException("requestId invalid, request not found");
        }

        return request.resolveFor(technician); // will return servicePoints
    }

    public void printAllRequests() {
        for(SupportRequest request : requestsById.values()) { // Query map for all requests
            System.out.println(request.toString()); // print each request
        }
    }

    public void printAssignmentsFor(Technician technician) {
        // Print technician info, then enumerate requests in List<SupportRequest>
        System.out.println(technician.toString()); // call overridden toString() method

        for(SupportRequest request : assignments.get(technician)) {
            System.out.println(request.toString());
        }
    }
}
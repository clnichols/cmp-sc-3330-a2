class NetworkIssueRequest extends AbstractSupportRequest implements SupportRequest {
	private int affectedUsers;    

    public NetworkIssueRequest(int id, String title, int basePoints, int affectedUsers) {
        if (affectedUsers <= 0) {
            throw new IllegalArgumentException("affectedUsers must be > 0");
        }

        super(id, title, basePoints); // call super to assign non-unique fields
        this.affectedUsers = affectedUsers;
    }

    @Override
    public int resolveFor(Technician technician) {
        // Open Design choice 4: Formula defined as follows
        int bonus = 0;

        if(affectedUsers >= 20) {
            bonus = 30; // extra bonus if milestone is met, else 0
        }

        // Step 1: calculate servicePoints  
        int servicePoints = basePoints + (affectedUsers * 2) + bonus;
        
        // Step 2: addPoints to technician

        // Open Design rule 1: Requests cannot be resolved more than once.
        // enforce rule 1 inside resolveFor method
        // HelpDesk will catch the potential exception
        if(isResolved()) {
            throw new IllegalStateException("Invalid: request is already resolved.");
        }

        try {
            technician.addPoints(servicePoints);
        } catch(Exception e) {
            // servicePoints added must be positive
            System.out.println(e.getMessage());
            return 0; // if fail, return 0 servicePoints, as no points were added
        }

        super.setResolved(); // if addPoints succeeds, attempt to set resolved to true


        return servicePoints;
    }
}
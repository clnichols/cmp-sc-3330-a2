class PasswordResetRequest extends AbstractSupportRequest implements SupportRequest {
	public String accountName;

    public PasswordResetRequest(int id, String title, int basePoints, String accountName) {
        if (accountName == null || accountName.isBlank()) {
            throw new IllegalArgumentException("title must not be null or blank");
        }

        super(id, title, basePoints); // call super to assign non-unique fields
        this.accountName = accountName;
    }

    @Override
    public int resolveFor(Technician technician) {    
        // Step 1: calculate servicePoints    
        int servicePoints = basePoints;

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
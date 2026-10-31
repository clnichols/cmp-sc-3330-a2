class PasswordResetRequest extends AbstractSupportRequest implements SupportRequest {
	public String accountName;

    public PasswordResetRequest(String accountName) {
        if (accountName == null || accountName.isBlank()) {
            throw new IllegalArgumentException("title must not be null or blank");
        }

        this.accountName = accountName;
    }

    @Override
    int resolveFor(Technician technician) {        
        int servicePoints = basePoints;

        try {
            technician.addPoints(servicePoints);
        } catch(Exception e) {
            // servicePoints added must be positive
            System.out.println(e.getMessage());
            resolved = false;
        }

        resolved = true; // if addPoints succeeds, set resolved to true

        return servicePoints;
    }
}
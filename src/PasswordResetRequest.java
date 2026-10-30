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
        resolved = true; // 1. set to true
        
        int servicePoints = basePoints;

        technician.addPoints(servicePoints);

        return servicePoints;
    }
}
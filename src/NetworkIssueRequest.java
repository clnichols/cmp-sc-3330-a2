class NetworkIssueRequest extends AbstractSupportRequest implements SupportRequest {
	public int affectedUsers;

    public NetworkIssueRequest(int affectedUsers) {
        if (affectedUsers <= 0) {
            throw new IllegalArgumentException("affectedUsers must be > 0");
        }

        this.affectedUsers = affectedUsers;
    }

    @Override
    int resolveFor(Technician technician) {
        int bonus = 0;

        if(affectedUsers >= 20) {
            bonus = 30; // extra bonus if milestone is met, else 0
        }

        int servicePoints = basePoints + (affectedUsers * 2) + bonus;
        
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
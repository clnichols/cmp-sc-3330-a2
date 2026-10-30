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
        resolved = true; // 1. set to true
        
        int bonus = 0;

        if(affectedUsers >= 20) {
            bonus = 30; // extra bonus if milestone is met, else 0
        }

        int servicePoints = basePoints + (affectedUsers * 2) + bonus;
        
        technician.recievePts(basePoints);

        return basePoints; // awards exactly basePoints
    }
}
class DeviceSetupRequest extends AbstractSupportRequest implements SupportRequest {
	public int deviceCount;

    public DeviceSetupRequest(int deviceCount) {
        if (deviceCount <= 0) {
            throw new IllegalArgumentException("deviceCount must be > 0");
        }

        this.deviceCount = deviceCount;
    }

    @Override
    int resolveFor(Technician technician) {
        int servicePoints = basePoints * deviceCount;
        
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




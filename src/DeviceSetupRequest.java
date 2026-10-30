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
        resolved = true; // 1. set to true
        
        int servicePoints = basePoints * deviceCount;
        
        // technician.recievePts(basePoints);

        // return basePoints;
    }
}




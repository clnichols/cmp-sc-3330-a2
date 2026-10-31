import java.util.Objects;

class Technician {
    private final String name;
    private int servicePoints; 

    public Technician(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be null or blank");
        }

        this.servicePoints = 0;
        this.name = name;
    }

    public int getServicePoints() {
        return servicePoints;
    }


    public int addPoints(int amount) {
        if (amount < 0) { // forcing amount to be positive enforces servicePoints invariant
            throw new IllegalArgumentException("amount must be >= 0");
        }

        servicePoints += amount;

        return servicePoints;
    }

    @Override
	public int hashCode() { // generated with Eclipse
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) { // generated with Eclipse
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Technician other = (Technician) obj;
		return Objects.equals(name, other.name);
	}

	@Override
    public String toString() { // returns a string
        return "Technician: name = " + name + ", servicePoints = " + servicePoints;
    }    
}
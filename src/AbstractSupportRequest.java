public abstract class AbstractSupportRequest implements SupportRequest {
    int id;
    String title;
    int basePoints;
    boolean resolved;

    public AbstractSupportRequest(int id, String title, boolean basePoints) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must be > 0");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title must not be null or blank");
        }
        if (basePoints <= 0) {
            throw new IllegalArgumentException("basePoints must be > 0");
        }
        this.id = id;
        this.title = title;
        this.basePoints = basePoints;
        this.resolved = false; // initially set resolved to false
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getBasePoints() {
        return basePoints;
    }
    
    // Common isResolved method for all subclasses
    public boolean isResolved() {
        // Returning resolved value is sufficient, no conditionals
        return resolved;
    }

    // abstract method resolveFor() not required, implementation enforced by interface.

    @Override
    public String toString() { // returns a string
        return "AbstractSupportRequest: id = " + id + ", title = " + title + ", basePoints = " + basePoints;
    }
}

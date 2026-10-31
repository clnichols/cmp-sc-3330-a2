class HelpDeskUtil {
    public static <T> void printAll(List<T> items) {
        for(T item : items) {
            System.out.println(item.toString()); // call generic-typed item toString()
        }
    }

    public static int sumPoints(List<? extends Technician> technicians) {
        int sum = 0; // initialize sum variable

        // read <? extends Technician> polymorphically as technician
        for(Technician technician : technicians) { 
            if(technician == null) {
                continue;
            }
            
            sum += technician.getServicePoints();
        }

        return sum;
    }
}
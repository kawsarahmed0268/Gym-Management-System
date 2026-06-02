public class Trainer {
    private int trainerId;
    private String name, specialty, phone;

    public Trainer(int trainerId, String name, String specialty, String phone) {
        this.trainerId = trainerId;
        this.name = name;
        this.specialty = specialty;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }
    public int getTrainerId() {
        return trainerId;
    }
    public String getSpecialty() {
        return specialty;
    }

    public void showTrainer() {
        System.out.println("\n--- Trainer Profile ---");
        System.out.println("ID: " + trainerId + " \nName: " + name + " \nSpecialty: " + specialty);
        System.out.println("----------------------");
    }
}

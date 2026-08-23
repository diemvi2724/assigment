import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pet {

    private String petId;
    private String name;
    private String type;
    private int age;
    private String breed;
    private String gender;

    private String ownerName;
    private String ownerContact;
    private LocalDateTime registrationDate;

    private List<Appointment> appointments;

    public Pet(String petId, String name, String type,
               int age, String breed, String gender,
               String ownerName, String ownerContact) {

        this.petId = petId;
        this.name = name;
        this.type = type;
        this.age = age;
        this.breed = breed;
        this.gender = gender;
        this.ownerName = ownerName;
        this.ownerContact = ownerContact;
        this.registrationDate = LocalDateTime.now();
        this.appointments = new ArrayList<>();
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    @Override
    public String toString() {
        return "Pet ID: " + petId
                + ", Name: " + name
                + ", Type: " + type
                + ", Age: " + age
                + ", Breed: " + breed
                + ", Gender: " + gender
                + ", Owner: " + ownerName
                + ", Contact: " + ownerContact;
    }
}

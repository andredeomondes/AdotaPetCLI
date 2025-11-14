package pet;

import pet.enums.PetSex;
import pet.enums.PetType;

import java.util.Objects;

public class Pet {
    private String name;
    private PetType petType;
    private PetSex petSex;
    private PetAddress address;
    private Double age;
    private Double weight;
    private String breed;

    public Pet(String name, PetType petType, PetSex petSex, PetAddress address, Double age, Double weight, String breed) {
        this.name = name;
        this.petType = petType;
        this.petSex = petSex;
        this.address = address;
        this.age = age;
        this.weight = weight;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public PetSex getPetSex() {
        return petSex;
    }

    public void setPetSex(PetSex petSex) {
        this.petSex = petSex;
    }

    public PetAddress getAddress() {
        return address;
    }

    public void setAddress(PetAddress address) {
        this.address = address;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(name, pet.name) && petType == pet.petType && petSex == pet.petSex && Objects.equals(address, pet.address) && Objects.equals(age, pet.age) && Objects.equals(weight, pet.weight) && Objects.equals(breed, pet.breed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, petType, petSex, address, age, weight, breed);
    }

    @Override
    public String toString() {
        String ageStr = age != null ? String.format("%.1f", age) : "N/I";
        String weightStr = weight != null ? String.format("%.1f", weight) : "N/I";
        String breedStr = (breed == null || breed.isEmpty()) ? "N/I" : breed;

        return String.format("%s (%s/%s) | Raça: %s | Idade: %s anos | Peso: %s kg",
                name,
                petType,
                petSex,
                breedStr,
                ageStr,
                weightStr
        );
    }
}
package pet;

import java.util.Objects;

public class PetAddress {
    private String street;
    private Integer number;
    private String city;

    public PetAddress() {
    }

    public PetAddress(String street, Integer number, String city) {
        this.street = street;
        this.number = number;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PetAddress petAdress = (PetAddress) o;
        return Objects.equals(street, petAdress.street) && Objects.equals(number, petAdress.number) && Objects.equals(city, petAdress.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, city);
    }

    @Override
    public String toString() {
        String numberStr = number != null ? String.valueOf(number) : "s/n";

        return String.format("%s, %s - %s",
                (street != null ? street : "N/I"),
                numberStr,
                (city != null ? city : "N/I")
        );
    }
}
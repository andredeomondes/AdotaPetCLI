package fileProcess;

import pet.Pet;
import pet.PetAddress;
import utils.LoadingAnimation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileWriterService {

    public static final String NA = "NOT INFORMED";

    private final String directoryPath;

    public FileWriterService(String directoryPath) {
        this.directoryPath = directoryPath;
        createDirectoryIfNotExists();
    }

    private void createDirectoryIfNotExists() {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private String buildFileName(String petName) {
        LocalDateTime now = LocalDateTime.now();
        LoadingAnimation.dots(300);
        String datePart = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String timePart = now.format(DateTimeFormatter.ofPattern("HHmm"));

        String formattedName = petName.replace(" ", "").toUpperCase();

        return datePart + "T" + timePart + "-" + formattedName + ".TXT";
    }

    public void savePet(Pet pet) throws IOException {

        // --- File preparation -------------------------------------------------------
        String fileName = buildFileName(pet.getName());
        File file = new File(directoryPath + File.separator + fileName);

        // --- File writing -----------------------------------------------------------
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            // 1. PET NAME -------------------------------------------------------------
            writer.write(pet.getName());
            writer.newLine();

            // 2. PET TYPE -------------------------------------------------------------
            writer.write(pet.getPetType() != null ? pet.getPetType().toString() : NA);
            writer.newLine();

            // 3. PET SEX --------------------------------------------------------------
            writer.write(pet.getPetSex() != null ? pet.getPetSex().toString() : NA);
            writer.newLine();

            // 4. ADDRESS --------------------------------------------------------------
            PetAddress address = pet.getAddress();

            String addressLine = address.getStreet() + ", "
                    + (address.getNumber() == null ? NA : address.getNumber()) + ", "
                    + address.getCity();

            writer.write(addressLine);
            writer.newLine();

            // 5. AGE ------------------------------------------------------------------
            String ageLine;

            if (pet.getAge() == null) {
                ageLine = NA;
            } else {
                ageLine = String.format("%.1f year's", pet.getAge());
            }

            writer.write(ageLine);
            writer.newLine();
            // 6. WEIGHT ---------------------------------------------------------------
            String weightLine;
            if (pet.getWeight() == null) {
                weightLine = NA;
            } else {
                // Formats the number to one decimal place, with "kg"
                weightLine = String.format("%.1f kg", pet.getWeight());
            }

            writer.write(weightLine);
            writer.newLine();

            // 7. BREED ----------------------------------------------------------------
            String breedLine =
                    (pet.getBreed() == null || pet.getBreed().isEmpty())
                            ? NA
                            : pet.getBreed();

            writer.write(breedLine);
            writer.newLine();
            LoadingAnimation.dots(300);

        }
    }
}

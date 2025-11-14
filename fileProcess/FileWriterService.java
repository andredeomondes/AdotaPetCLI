package fileProcess;

import pet.Pet;
import pet.PetAddress;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * -----------------------------------------------------------------------------
 * FILE WRITER SERVICE
 * -----------------------------------------------------------------------------
 * Responsible for exporting Pet data into structured text files following the
 * project's formatting rules. Ensures directory creation, filename generation,
 * data sanitization, and line-by-line file writing in a predictable format.
 *
 * Pattern Inspiration: "Single Responsibility" + "Utility Service Pattern"
 * -----------------------------------------------------------------------------
 */
public class FileWriterService {

    /** Constant used whenever user-provided data is missing or invalid */
    public static final String NA = "NOT INFORMED";

    /** Directory path where all exported files will be stored */
    private final String directoryPath;

    /**
     * Constructor.
     *
     * @param directoryPath Directory where files will be saved.
     */
    public FileWriterService(String directoryPath) {
        this.directoryPath = directoryPath;
        createDirectoryIfNotExists();
    }

    /**
     * Ensures the storage directory exists. Creates it if missing.
     */
    private void createDirectoryIfNotExists() {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Builds a filename in the format:
     *     YYYYMMDDTHHMM-NAME.TXT
     *
     * @param petName Raw pet name provided by user.
     * @return Formatted filename string.
     */
    private String buildFileName(String petName) {
        LocalDateTime now = LocalDateTime.now();

        String datePart = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String timePart = now.format(DateTimeFormatter.ofPattern("HHmm"));

        // Removes whitespace and forces uppercase.
        String formattedName = petName.replace(" ", "").toUpperCase();

        return datePart + "T" + timePart + "-" + formattedName + ".TXT";
    }

    /**
     * Saves all Pet data into a file, line by line.
     * Questions are never included — only responses.
     *
     * @param pet The Pet object containing all collected information.
     * @throws IOException If the file cannot be created or written.
     */
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
            } else if (pet.getAge() < 1) {
                // Age in months if less than 1 year
                int months = (int) Math.round(pet.getAge() * 12);
                ageLine = months + " " + (months == 1 ? "month" : "months");
            } else {
                // Age in years
                int years = (int) Math.floor(pet.getAge());
                ageLine = years + " " + (years == 1 ? "year" : "years");
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
        }
    }
}

package fileProcess;

import pet.Pet;
import pet.PetAddress;
import pet.enums.PetSex;
import pet.enums.PetType;
import utils.LoadingAnimation;
import utils.Style;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static pet.StartAdotaPetCLI.directoryPath;

public class FileReaderService {

    public List<String> questionsList = new ArrayList<>();

    public List<String> readAllQuestionsFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String question;
            while ((question = br.readLine()) != null) {
                LoadingAnimation.smoothSpinner(300);
                questionsList.add(question);
            }

            LoadingAnimation.smoothSpinner(300);
            System.out.println(
            Style.msgOk("File read successfully. Total questions: " + questionsList.size())
            );

        } catch (IOException e) {

            LoadingAnimation.smoothSpinner(300);
            throw new RuntimeException(
            Style.msgError("Error reading file: " + e.getMessage())
            );
        }

        return questionsList;
    }

    public void showIndexQuestion(List<String> questionsList, int index) {

        if (questionsList == null || questionsList.isEmpty()) {
            LoadingAnimation.smoothSpinner(300);
            throw new RuntimeException(
            Style.msgError("The list of questions is empty or null.")
            );
        }

        if (index < 0 || index >= questionsList.size()) {
            LoadingAnimation.smoothSpinner(300);
            throw new RuntimeException(
            Style.msgError(
            "Invalid index: " + (index + 1) +
            ". The list size is " + questionsList.size() + "."
                    )
            );
        }

        LoadingAnimation.smoothSpinner(300);
        System.out.println(
        Style.msgQuestion("[" + (index + 1) + "]: " + " " + questionsList.get(index))
        );
    }


    public List<Pet> readAllPets() throws IOException {
        List<Pet> pets = new ArrayList<>();
        File directory = new File(directoryPath);

        File[] petFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (petFiles == null) {
            throw new IOException("Could not access directory: " + directoryPath);
        }

        if (petFiles.length == 0) {
            return pets;
        }

        for (File file : petFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                String nameLine = reader.readLine();
                String typeLine = reader.readLine();
                String sexLine = reader.readLine();
                String addressLine = reader.readLine();
                String ageLine = reader.readLine();
                String weightLine = reader.readLine();
                String breedLine = reader.readLine();

                // 1. Endereço (Address)
                String[] addressParts = addressLine.split(", ");
                String street = addressParts[0];
                String city = addressParts[2];

                Integer number = null;
                if (!addressParts[1].equals(FileWriterService.NA)) {
                    try {
                        number = Integer.parseInt(addressParts[1]);
                    } catch (NumberFormatException ignored) {
                    }
                }
                PetAddress petAddress = new PetAddress(street, number, city);

                // 2. Idade (Age)
                Double age = null;
                if (!ageLine.equals(FileWriterService.NA)) {
                    try {
                        age = Double.parseDouble(ageLine.replace(" year's", "").replace(',', '.'));
                    } catch (NumberFormatException ignored) {
                    }
                }

                // 3. Peso (Weight)
                Double weight = null;
                if (!weightLine.equals(FileWriterService.NA)) {
                    try {
                        weight = Double.parseDouble(weightLine.replace(" kg", "").replace(',', '.'));
                    } catch (NumberFormatException ignored) {
                    }
                }

                // 4. Criação final do Pet
                Pet pet = new Pet(
                        nameLine,
                        typeLine.equals(FileWriterService.NA) ? null : PetType.valueOf(typeLine),
                        sexLine.equals(FileWriterService.NA) ? null : PetSex.valueOf(sexLine),
                        petAddress,
                        age,
                        weight,
                        breedLine.equals(FileWriterService.NA) ? null : breedLine
                );
                pets.add(pet);

            } catch (FileNotFoundException e) {
                System.err.println(Style.msgError("File not found: " + file.getName()));
            } catch (Exception e) {
                System.err.println(Style.msgError("Error processing pet file " + file.getName() + ": " + e.getMessage()));
            }
        }
        return pets;
    }


}

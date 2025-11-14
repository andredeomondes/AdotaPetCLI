package pet;

import fileProcess.FileReaderService;
import pet.enums.PetSex;
import pet.enums.PetType;
import utils.Style;

import java.io.IOException;
import java.util.Scanner;
import java.util.List; // Necessário para acessar fileReaderService.questionsList

public class PetController {

    public static final String NA = "NOT INFORMED";

    private final PetService petService;
    private final FileReaderService fileReaderService;
    private final Scanner sc;

    public PetController(PetService petService, FileReaderService fileReaderService, Scanner sc) {
        this.petService = petService;
        this.fileReaderService = fileReaderService;
        this.sc = sc;
    }

    public void handleRegisterPet() {
        System.out.println("\n" + Style.msgOk("Register new pet  [ Selected ]"));
        String name = "", breed = "", response;
        PetType petType = null;
        PetSex petSex = null;
        PetAddress petAddress = new PetAddress(null, null, null);
        Double weight = null, age = null;

        List<String> questionsList = fileReaderService.questionsList;

        try {

            for (int indexQuestion = 0;
                 indexQuestion < questionsList.size();
                 indexQuestion++) {

                fileReaderService.showIndexQuestion(questionsList, indexQuestion);

                switch (indexQuestion) {

                    /* ------------------------ QUESTION 01 - PET NAME ------------------------ */
                    case 0:
                        do {
                            System.out.print(Style.msgInfo(" // Type here >>> "));
                            response = sc.nextLine().trim();
                            String fields[] = response.split(" ");

                            if (response.isEmpty()) {
                                name = NA;
                                break;
                            } else if (fields.length < 2) {
                                System.out.println(Style.msgError(" Type NAME AND LASTNAME (ex.: Michael Jackson)"));
                            } else if (!response.matches("^[A-Za-z]+(\\s[A-Za-z]+)*$")) {
                                System.out.println(Style.msgError(" Use only letters (a–z, A–Z) and spaces."));
                            } else {
                                name = response;
                                System.out.println(Style.msgOk("Name: OK"));
                                break;
                            }
                        } while (true);
                        break;

                    /* ------------------------ QUESTION 02 - PET TYPE ------------------------ */
                    case 1:
                        do {
                            System.out.print(Style.msgInfo(" // Type here [1-DOG / 2-CAT] >>> "));
                            response = sc.nextLine().trim();

                            if (!response.matches("[0-9]+")) {
                                System.out.println(Style.msgError("Invalid digit [1 or 2]"));
                                continue;
                            }

                            int type = Integer.parseInt(response);

                            if (type == 1) {
                                petType = PetType.DOG;
                                break;
                            } else if (type == 2) {
                                petType = PetType.CAT;
                                break;
                            } else {
                                System.out.println(Style.msgError("Invalid digit [1 or 2]"));
                            }

                        } while (true);

                        System.out.println();
                        System.out.println(Style.msgOk("Pet type OK!"));
                        break;

                    /* ------------------------ QUESTION 03 - PET SEX ------------------------ */
                    case 2:
                        do {
                            System.out.print(Style.msgInfo(" // Type here [1-MALE / 2-FEMALE] >>> "));
                            response = sc.nextLine().trim();

                            if (!response.matches("[0-9]+")) {
                                System.out.println(Style.msgError("Invalid digit [1 or 2]"));
                                continue;
                            }

                            int type = Integer.parseInt(response);

                            if (type == 1) {
                                petSex = PetSex.MALE;
                                break;
                            } else if (type == 2) {
                                petSex = PetSex.FEMALE;
                                break;
                            } else {
                                System.out.println(Style.msgError("Invalid digit [1 or 2]"));
                            }

                        } while (true);

                        System.out.println();
                        System.out.println(Style.msgOk("Pet sex OK!"));
                        break;

                    /* ------------------------ QUESTION 04 - ADDRESS ------------------------ */
                    case 3:
                        // City ---------------------------------------------------------------
                        do {
                            System.out.print(Style.msgInfo(" // Type here [ CITY ] >>> "));
                            response = sc.nextLine().trim();

                            if (response.isEmpty()) {
                                System.out.println(Style.msgError("[CITY] Cannot be empty..."));
                            } else {
                                petAddress.setCity(response);
                                System.out.println(Style.msgOk("CITY OK!"));
                                break;
                            }
                        } while (true);

                        // Street ---------------------------------------------------------------
                        do {
                            System.out.print(Style.msgInfo(" // Type here [ STREET ] >>> "));
                            response = sc.nextLine();

                            if (response.isEmpty()) {
                                System.out.println(Style.msgError("[STREET] Cannot be empty..."));
                            } else {
                                petAddress.setStreet(response);
                                System.out.println(Style.msgOk("Street OK!"));
                                break;
                            }
                        } while (true);

                        // Number ---------------------------------------------------------------
                        do {
                            System.out.print(Style.msgInfo(" // Type here [ NUMBER ] >>> "));
                            response = sc.nextLine().trim();

                            if (response.isEmpty()) {
                                petAddress.setNumber(null);
                                System.out.println("\n" + Style.msgOk("NUMBER OK! (Not Informed)"));
                                break;
                            }

                            if (!response.matches("[0-9]+")) {
                                System.out.println("\n" + Style.msgError("Invalid input, just numbers is accepted..."));
                            } else {
                                int number = Integer.parseInt(response);
                                petAddress.setNumber(number);
                                System.out.println("\n" + Style.msgOk(" NUMBER OK! "));
                                break;
                            }
                        } while (true);

                        break;

                    /* ------------------------ QUESTION 05 - AGE ------------------------ */
                    case 4:
                        do {
                            System.out.print(Style.msgInfo(" // Type here >>> "));
                            response = sc.nextLine().trim();

                            if (response.isEmpty()) {
                                age = null;
                                System.out.println(Style.msgOk("AGE OK! (Not Informed)"));
                                break;
                            }

                            if (!response.matches("[0-9]+")) {
                                System.out.println(Style.msgError("Invalid input, just numbers are accepted..."));
                                continue;
                            }

                            int input = Integer.parseInt(response);

                            if (input > 20) {
                                // Lançar exceção dentro do loop de input para que seja capturada
                                throw new IllegalArgumentException("Age cannot be more than 20 years!");
                            } else if (input < 1) {
                                // Convert months to years
                                age = input / 12.0;
                                System.out.println(Style.msgOk("AGE OK! Converted from months to years: " + String.format("%.2f", age)));
                            } else {
                                age = (double) input;
                                System.out.println(Style.msgOk("AGE OK!"));
                            }

                        } while (true);
                        break;

                    /* ------------------------ QUESTION 06 - WEIGHT ------------------------ */
                    case 5:
                        do {
                            System.out.print(Style.msgInfo(" // Type here >>> "));
                            response = sc.nextLine().trim();

                            if (response.isEmpty()) {
                                weight = null;
                                System.out.println(Style.msgOk("WEIGHT OK! (Not Informed)"));
                                break;
                            }

                            // A regex original estava incorreta no final, corrigida para permitir ponto ou vírgula.
                            if (!response.matches("^[0-9]+([.,][0-9]+)?$")) {
                                System.out.println("\n" + Style.msgError("Invalid input, just numbers is accepted..."));
                                continue;
                            }

                            String normalized = response.replace(",", ".");

                            double w = Double.parseDouble(normalized);

                            if (w < 0.5 || w > 60.0) {
                                System.out.println(Style.msgError("Invalid weight. Must be between 0.5kg and 60kg."));
                                continue;
                            }
                            weight = w;
                            System.out.println(Style.msgOk("WEIGHT OK!"));
                        } while (true);
                        break;

                    /* ------------------------ QUESTION 07 - BREED ------------------------ */
                    case 6:
                        System.out.print(Style.msgInfo(" // Type here >>> "));
                        response = sc.nextLine().trim();

                        if (response.isEmpty()) {
                            breed = NA;
                            System.out.println("\n" + Style.msgOk("BREED OK (Not Informed)"));
                        } else {
                            breed = response;
                            System.out.println("\n" + Style.msgOk("BREED OK"));
                        }
                        break;

                    default:
                        System.out.println(Style.msgError("Internal Error: Unhandled form question."));
                        break;
                }
            }

            petService.registerPet(name, petType, petSex, petAddress, age, weight, breed);

            System.out.println(Style.msgOk("\n*** Pet registration completed successfully! ***"));

        } catch (IllegalArgumentException e) {
            System.out.println(Style.msgError("\nRegistration failed: " + e.getMessage()));
        } catch (IOException e) {
            System.out.println(Style.msgError("\nSystem error during file saving: " + e.getMessage()));
        } catch (Exception e) {
            System.out.println(Style.msgError("\nAn unexpected error occurred during registration: " + e.getMessage()));
        }
    }
}
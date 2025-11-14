package pet;

import fileProcess.FileWriterService;
import pet.enums.PetSex;
import pet.enums.PetType;
import utils.Style;

import java.io.IOException;

public class PetService {

    public static final String NA = "NOT INFORMED";

    private final FileWriterService fileWriterService;

    public PetService(FileWriterService fileWriterService) {
        this.fileWriterService = fileWriterService;
    }


    public void registerPet(
            String name, PetType petType, PetSex petSex, PetAddress address,
            Double age, Double weight, String breed) throws IOException {


        if (address == null || address.getCity() == null) {
            throw new IllegalArgumentException(Style.msgError("Erro de Serviço: O endereço do Pet não foi fornecido corretamente."));
        }

        if (petType == null) {
            throw new IllegalArgumentException(Style.msgError("Erro de Serviço: O tipo do Pet é obrigatório."));
        }

        Pet pet = new Pet(
                name.isEmpty() ? NA : name,
                petType,
                petSex,
                address,
                age,
                weight,
                breed.isEmpty() ? NA : breed
        );

        this.fileWriterService.savePet(pet);

    }
}
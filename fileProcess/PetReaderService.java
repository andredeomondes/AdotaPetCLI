package fileProcess;

import pet.Pet;

import java.io.IOException;
import java.util.List;

public class PetReaderService {

    public List<Pet> loadAllPets(String directoryPath) throws IOException {
        // 1. Abre a pasta 'registeredPets'
        // 2. Lista todos os arquivos .TXT
        // 3. Para cada arquivo:
        //    a. Lê linha por linha (nome, tipo, sexo, endereço, etc.)
        //    b. Converte (parse) as Strings de volta para PetType, PetSex, Double, etc.
        //    c. Cria um novo objeto Pet(dados...)
        // 4. Retorna a List<Pet>
        return null;
    }
}
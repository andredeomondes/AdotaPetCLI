package fileProcess;

import utils.LoadingAnimation;
import utils.Style;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderService {
    public List<String> questionsList = new ArrayList<>();

    public List<String> readAllQuestionsFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String question;
            while ((question = br.readLine()) != null) {
                LoadingAnimation.smoothSpinner(500);
                questionsList.add(question);
            }
            LoadingAnimation.smoothSpinner(500);
            System.out.println(Style.msgOk("File read successfully. Total questions: " + questionsList.size()));

        } catch (IOException e) {
            LoadingAnimation.smoothSpinner(500);
            throw new RuntimeException(Style.msgError("Error reading file: " + e.getMessage()));
        }

        return questionsList;
    }

    public void showIndexQuestion(List<String> questionsList, int index) {
        if (questionsList == null || questionsList.isEmpty()) {
            LoadingAnimation.smoothSpinner(500);
            throw new RuntimeException(Style.msgError("The list of questions is empty or null."));
        }

        if (index < 0 || index >= questionsList.size()) {
            LoadingAnimation.smoothSpinner(500);
            throw new RuntimeException(Style.msgError(
                    "Invalid index: " + (index+1) + ". The list size is " + questionsList.size() + "."
            ));
        }
        LoadingAnimation.smoothSpinner(500);
        System.out.println(Style.msgQuestion("[" + (index+1) + "]: " + " " + questionsList.get(index)));
    }
}

package fileProcess;

import utils.LoadingAnimation;
import utils.Style;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * -----------------------------------------------------------------------------
 * FILE READER SERVICE
 * -----------------------------------------------------------------------------
 * Responsible for loading text files containing questions and providing
 * controlled access to individual lines. This class handles input validation,
 * animation feedback, formatted console messages, and runtime exception wrapping.
 *
 * Pattern Inspiration: "Single Responsibility" + "I/O Utility Service Pattern"
 * -----------------------------------------------------------------------------
 */
public class FileReaderService {

    /** Internal storage for all loaded questions */
    public List<String> questionsList = new ArrayList<>();

    /**
     * Reads all lines from a text file and stores them into the internal list.
     * Each line is considered a question.
     *
     * @param filePath Absolute or relative path to the target file.
     * @return A list containing every question in the file, in order.
     */
    public List<String> readAllQuestionsFile(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String question;

            // ---------------------------------------------------------------------
            // Reads file line-by-line until reaching EOF.
            // Each read triggers a loading animation for UX consistency.
            // ---------------------------------------------------------------------
            while ((question = br.readLine()) != null) {
                LoadingAnimation.smoothSpinner(300);
                questionsList.add(question);
            }

            LoadingAnimation.smoothSpinner(300);
            System.out.println(
                    Style.msgOk("File read successfully. Total questions: " + questionsList.size())
            );

        } catch (IOException e) {

            // ---------------------------------------------------------------------
            // If any I/O error occurs, a runtime exception is thrown with a
            // formatted error message. This keeps the CLI experience consistent.
            // ---------------------------------------------------------------------
            LoadingAnimation.smoothSpinner(300);
            throw new RuntimeException(
                    Style.msgError("Error reading file: " + e.getMessage())
            );
        }

        return questionsList;
    }

    /**
     * Displays a single question by index, applying validation and formatting.
     *
     * @param questionsList List containing all loaded questions.
     * @param index         The question index (0-based internal indexing).
     */
    public void showIndexQuestion(List<String> questionsList, int index) {

        // ---------------------------------------------------------------------
        // Validation: Ensure list was loaded correctly.
        // ---------------------------------------------------------------------
        if (questionsList == null || questionsList.isEmpty()) {
            LoadingAnimation.smoothSpinner(300);
            throw new RuntimeException(
                    Style.msgError("The list of questions is empty or null.")
            );
        }

        // ---------------------------------------------------------------------
        // Validation: Index bounds.
        // The user sees indices starting at 1, so messages reflect that.
        // ---------------------------------------------------------------------
        if (index < 0 || index >= questionsList.size()) {
            LoadingAnimation.smoothSpinner(300);
            throw new RuntimeException(
                    Style.msgError(
                            "Invalid index: " + (index + 1) +
                                    ". The list size is " + questionsList.size() + "."
                    )
            );
        }

        // ---------------------------------------------------------------------
        // Display formatted question
        // ---------------------------------------------------------------------
        LoadingAnimation.smoothSpinner(300);
        System.out.println(
                Style.msgQuestion("[" + (index + 1) + "]: " + " " + questionsList.get(index))
        );
    }
}

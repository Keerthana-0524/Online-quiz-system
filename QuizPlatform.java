import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract class representing a question
abstract class Question {
    protected String prompt;

    public Question(String prompt) {
        this.prompt = prompt;
    }

    public abstract void display();

    public abstract boolean checkAnswer(String answer);
}

// Concrete class representing a multiple choice question
class MultipleChoiceQuestion extends Question {
    private List<String> options;
    private int correctOption;

    public MultipleChoiceQuestion(String prompt, List<String> options, int correctOption) {
        super(prompt);
        this.options = options;
        this.correctOption = correctOption;
    }

    @Override
    public void display() {
        System.out.println(prompt);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    @Override
    public boolean checkAnswer(String answer) {
        int selectedOption;
        try {
            selectedOption = Integer.parseInt(answer);
        } catch (NumberFormatException e) {
            return false;
        }
        return selectedOption == correctOption;
    }
}

// Concrete class representing a true/false question
class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(String prompt, boolean correctAnswer) {
        super(prompt);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public void display() {
        System.out.println(prompt);
        System.out.println("1. True");
        System.out.println("2. False");
    }

    @Override
    public boolean checkAnswer(String answer) {
        boolean selectedAnswer = answer.equalsIgnoreCase("true") || answer.equalsIgnoreCase("1");
        return selectedAnswer == correctAnswer;
    }
}

// Concrete class representing a fill-in-the-blank question
class FillInTheBlankQuestion extends Question {
    private String correctAnswer;

    public FillInTheBlankQuestion(String prompt, String correctAnswer) {
        super(prompt);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public void display() {
        System.out.println(prompt);
        System.out.println("Enter your answer:");
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }
}

// Quiz class responsible for managing questions and conducting the quiz
class Quiz {
    private List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void conduct() {
        int score = 0;
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            question.display();
            System.out.print("Your answer: ");
            String answer = scanner.nextLine();
            if (question.checkAnswer(answer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect.");
            }
            System.out.println();
        }

        System.out.println("Quiz completed. Your score: " + score + "/" + questions.size());
    }
}

// Login class to handle user authentication
class Login {
    private static final String USERNAME = "keerthana";
    private static final String PASSWORD = "1234";

    public static boolean authenticate(String username, String password) {
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }
}

public class QuizPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt for login credentials
        System.out.println("Welcome to the Quiz Platform!");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Authenticate user
        if (Login.authenticate(username, password)) {
            System.out.println("Login successful!");
            System.out.println("Starting the quiz...\n");

            // Create and conduct the quiz
            Quiz quiz = new Quiz();
            quiz.addQuestion(new MultipleChoiceQuestion("What is the capital of France?",
                    List.of("London", "Paris", "Berlin", "Rome"), 2));
            quiz.addQuestion(new TrueFalseQuestion("Java is a programming language.", true));
            quiz.addQuestion(new FillInTheBlankQuestion("The largest planet in our solar system is ______.",
                    "Jupiter"));
            quiz.addQuestion(new MultipleChoiceQuestion("What is the capital of Japan?",
                    List.of("Beijing", "Tokyo", "Seoul", "Bangkok"), 2));
            quiz.addQuestion(new TrueFalseQuestion("Python is a statically typed language.", false));
            quiz.addQuestion(new FillInTheBlankQuestion("The force of gravity depends on the ________ of the masses involved.",
                    "product"));
            quiz.addQuestion(new MultipleChoiceQuestion("What is the largest mammal?",
                    List.of("Elephant", "Whale", "Giraffe", "Horse"), 2));
            quiz.addQuestion(new TrueFalseQuestion("Mount Everest is the highest mountain on Earth.", true));
            quiz.addQuestion(new FillInTheBlankQuestion("The chemical symbol for water is _____.",
                    "H2O"));
            quiz.addQuestion(new MultipleChoiceQuestion("Who wrote 'To Kill a Mockingbird'?",
                    List.of("Mark Twain", "Harper Lee", "Ernest Hemingway", "J.K. Rowling"), 2));
            quiz.conduct();
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }

        scanner.close(); // Close the scanner
    }
}


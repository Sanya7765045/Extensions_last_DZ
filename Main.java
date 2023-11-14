import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class UserData {
    private String surname;
    private String firstName;
    private String patronymic;
    private String dateOfBirth;
    private String phoneNumber;
    private char gender;

    public UserData(String surname, String firstName, String patronymic, String dateOfBirth, String phoneNumber, char gender) {
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getFormattedData() {
        return String.format("%s%s%s %s %s%s", surname, firstName, patronymic, dateOfBirth, phoneNumber, gender);
    }

    public String getSurname() {
        return surname;
    }
}

class UserDataParser {
    public static UserData parseUserData(String input) throws IllegalArgumentException {
        String[] data = input.split(" ");

        if (data.length != 6) {
            throw new IllegalArgumentException("Неверное количество данных");
        }

        String surname = data[0];
        String firstName = data[1];
        String patronymic = data[2];
        String dateOfBirth = data[3];
        String phoneNumber = data[4];
        char gender = data[5].charAt(0);

        // Проверка формата данных
        // Можно добавить дополнительные проверки для каждого поля

        return new UserData(surname, firstName, patronymic, dateOfBirth, phoneNumber, gender);
    }
}

class App {
    public static void main(String[] args) {
        String userDataInput = "Фамилия Имя Отчество датарождения номертелефона пол";
        try {
            UserData userData = UserDataParser.parseUserData(userDataInput);
            writeUserDataToFile(userData);
            System.out.println("Данные успешно записаны в файл.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл:");
            e.printStackTrace();
        }
    }

    public static void writeUserDataToFile(UserData userData) throws IOException {
        BufferedWriter writer = null;
        try {
            String directoryPath = "C:\\Users\\tsand\\OneDrive\\Рабочий стол\\Lekciyi DZ\\Isklu4eniya\\App\\src\\";
            String fileName = directoryPath + userData.getSurname() + ".txt";
            writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(userData.getFormattedData());
            writer.newLine();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
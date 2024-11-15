package Controllers.ProcessOptions;

import Models.User;

import java.io.IOException;

public interface ProcessUserChoice<T extends User> {
    public void processUserChoice(T user, String choice) throws IOException, ClassNotFoundException;
}

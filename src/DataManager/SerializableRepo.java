package DataManager;

import java.io.IOException;

public interface SerializableRepo<T> {
    void saveData() throws IOException;
    void loadData() throws IOException, ClassNotFoundException;
    T getData();

}

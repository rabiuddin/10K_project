package DataManager;

import Models.Inventory;

import java.io.*;

public class InventoryRepo implements SerializableRepo<Inventory> {
    private static final String INVENTORY_FILE = "C:\\Users\\Rabiuddin\\IdeaProjects\\untitled\\src\\DataManager\\inventory.txt";
    private Inventory inventory = new Inventory();

    @Override
    public void saveData() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(INVENTORY_FILE))) {
            oos.writeObject(inventory);
            System.out.println("Inventory data saved successfully.");
        }
    }

    @Override
    public void loadData() throws IOException, ClassNotFoundException {
        if (new File(INVENTORY_FILE).exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(INVENTORY_FILE))) {
                inventory = (Inventory) ois.readObject();
                System.out.println("Inventory data loaded successfully.");
            }
        }
    }

    @Override
    public Inventory getData() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}

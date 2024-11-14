package Models;

import java.io.Serializable;

public class Medication implements Serializable {
    private String name;
    private int stockLevel;
    private int lowStockAlert;

    public Medication(String name, int stockLevel, int lowStockAlert) {
        this.name = name;
        this.stockLevel = stockLevel;
        this.lowStockAlert = lowStockAlert;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public int getLowStockAlert() {
        return lowStockAlert;
    }

    public void setLowStockAlert(int lowStockAlert) {
        this.lowStockAlert = lowStockAlert;
    }

    @Override
    public String toString() {
        return name + ", " + stockLevel + ", " + lowStockAlert;
    }
}

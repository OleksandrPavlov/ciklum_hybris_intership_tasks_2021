package lesson2;

public class Vehicle {
    private String name;
    private String model;
    private int price;
    private boolean fourWDrive;



    public Vehicle(String name, String model, int price, boolean fourWDrive) {
        this.name = name;
        this.model = model;
        this.price = price;
        this.fourWDrive = fourWDrive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isFourWDrive() {
        return fourWDrive;
    }

    public void setFourWDrive(boolean fourWDrive) {
        this.fourWDrive = fourWDrive;
    }
    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", fourWDrive=" + fourWDrive +
                '}';
    }
}

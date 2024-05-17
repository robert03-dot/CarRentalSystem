import java.util.ArrayList;
import java.util.List;

public class RentedCars {
    private List<String> cars;

    public void addCar(String Car) {
        cars.add(Car);
    }

    public int sizeList() {
        return cars.size();
    }

    public RentedCars() {
        this.cars = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Masinile inchiriate de catre proprietar sunt:" + cars;
    }
}
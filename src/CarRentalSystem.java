import java.util.HashMap;
import java.util.Scanner;

public class CarRentalSystem {
    private static final Scanner sc = new Scanner(System.in);
    private HashMap<String, String> rentedCars =
            new HashMap<>(100, 0.5f);
    private HashMap<String, RentedCars> whoRented =
            new HashMap<>(100, 0.5f);//acest map stocheaza cheia client si valoarea lista de masini inchiriata de catre client

    private static String getPlateNo() {
        System.out.println("Introduceti numarul de inmatriculare:");
        return sc.nextLine();
    }

    private static String getOwnerName() {
        System.out.println("Introduceti numele proprietarului:");
        return sc.nextLine();
        //scot scanner-ul ca parametru si il folosesc in corpul metodei.il declar ca o variabila static final in cadrul clasei(camp al clasei)
    }

    // search for a key in hashtable
    private boolean isCarRent(String plateNo) {
        return rentedCars.containsKey(plateNo);
    }

    // get the value associated to a key
    private String getCarRent(String plateNo) {
        return rentedCars.get(plateNo);
    }

    // add a new (key, value) pair
    private void rentCar(String plateNo, String ownerName) {
        if (isCarRent(plateNo)) {
            System.out.println("Masina" + plateNo + " a fost deja inchiriata de:" + ownerName);
        } else {
            rentedCars.put(plateNo, ownerName);
        }
    }

    private boolean isOwnerExisting(String ownerName) {
        return whoRented.containsKey(ownerName);
    }

    private void addInRentedCarsList(String plateNo, String ownerName) {
        if (!isCarRent(plateNo)) {
            System.out.printf("Adaugare autoturism..." + "%nPlateNo:%s | Owner name:%s%n", plateNo, ownerName);
            rentedCars.put(plateNo, ownerName);
            if (!isOwnerExisting(ownerName)) {
                RentedCars rentedcars = new RentedCars();
                rentedcars.addCar(plateNo);
                whoRented.put(ownerName, rentedcars);
            } else {
                whoRented.get(ownerName).addCar(plateNo);//adaug unui proprietar deja existent in sistem o noua masina la lista sa de masini
            }
        } else {
            System.out.println("Adaugarea autoturismului a esuat!");
        }
    }

    // remove an existing (key, value) pair
    private void returnCar(String plateNo) {
        if (isCarRent(plateNo)) {
            rentedCars.remove(plateNo);
            System.out.println("Masina a fost eliminata din sistem cu succes");
        } else {
            throw new RuntimeException("Masina pe care doriti sa o stergeti nu exista in sistem");
        }
    }

    private int totalCarsRented() {
        return rentedCars.size();//returnam numarul de elemente pe care le contine map-ul
    }

    private RentedCars getCarsList(String ownerName) {
        if (!whoRented.containsKey(ownerName)) {
            throw new RuntimeException("Proprietarul nu exista in sistem");
        }
        return whoRented.get(ownerName);
    }

    private static void printCommandsList() {
        System.out.println("help         - Afiseaza aceasta lista de comenzi");
        System.out.println("add          - Adauga o noua pereche (masina, sofer)");
        System.out.println("check        - Verifica daca o masina este deja luata");
        System.out.println("remove       - Sterge o masina existenta din hashtable");
        System.out.println("getOwner     - Afiseaza proprietarul curent al masinii");
        System.out.println("getCarsNo    - Afiseaza numarul de masini");
        System.out.println("getCarsList  - Afiseaza lista masinilor");
        System.out.println("addCarInList - Adauga o masina in lista");
        System.out.println("totalRented  - Totalul masinilor inchiriate");
        System.out.println("quit         - Inchide aplicatia");
    }

    private int getCarsNo(String ownerName) {
        if (whoRented.containsKey(ownerName)) {
            return whoRented.get(ownerName).sizeList();
        } else {
            throw new RuntimeException("Proprietarul nu exista in sistem");
        }
    }

    public void run(Scanner sc) {
        boolean quit = false;
        while (!quit) {
            System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
            String command = sc.nextLine();
            switch (command) {
                case "help":
                    printCommandsList();
                    break;
                case "add":
                    rentCar(getPlateNo(), getOwnerName());
                    break;
                case "check":
                    System.out.println(isCarRent(getPlateNo()));
                    break;
                case "remove":
                    returnCar(getPlateNo());
                    break;
                case "getOwner":
                    System.out.println(getCarRent(getPlateNo()));
                    break;
                case "totalRented":
                    System.out.println("Numarul total de masini inchiriate este:" + totalCarsRented());
                    break;
                case "getCarsNo":
                    System.out.println(getCarsNo("Numarul de masini este:" + getOwnerName()));
                    break;
                case "getCarsList":
                    System.out.println(getCarsList(getOwnerName()));
                    break;
                case "addCarInList":
                    addInRentedCarsList(getOwnerName(), getPlateNo());
                    break;
                case "quit":
                    System.out.println("Aplicatia se inchide...");
                    quit = true;
                    break;
                default:
                    System.out.println("Unknown command. Choose from:");
                    printCommandsList();
            }
        }
    }

    public static void main(String[] args) {

        new CarRentalSystem().run(new Scanner(System.in));
    }
}
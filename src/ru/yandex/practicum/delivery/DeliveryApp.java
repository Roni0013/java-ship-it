package ru.yandex.practicum.delivery;

import ru.yandex.practicum.parcel.FragileParcel;
import ru.yandex.practicum.parcel.Parcel;
import ru.yandex.practicum.parcel.PerishableParcel;
import ru.yandex.practicum.parcel.StandardParcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final int STANDARD_BOX_MAX_WEIGHT = 50;
    private static final int PERISHABLE_BOX_MAX_WEIGHT = 35;
    private static final int FRAGILE_BOX_MAX_WEIGHT = 16;

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Trackable> allTrackableParcels = new ArrayList<>();
    private static final ParcelBox<StandardParcel> standardParcelBox = new ParcelBox<>(STANDARD_BOX_MAX_WEIGHT);
    private static final ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(PERISHABLE_BOX_MAX_WEIGHT);
    private static final ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(FRAGILE_BOX_MAX_WEIGHT);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    trackAll();
                    break;
                case 5:
                    showParcelBox();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Проверить статус доставки всех посылок");
        System.out.println("5 — Просмотреть коробку с отправлениями");
        System.out.println("0 — Завершить");
    }

    private static void showParcelTypeMenu() {
        System.out.println("Выберите тип посылки:");
        System.out.println("1 - Стандартная");
        System.out.println("2 - Скоропортящаяся");
        System.out.println("3 - Хрупкая");
    }

    private static void showBoxTypeMenu() {
        System.out.println("Введите номер коробки:");
        System.out.println("1 - Коробка со станадртными отправлениями");
        System.out.println("2 - Коробка со скоропортящимися отправлениями");
        System.out.println("3 - Коробка с хрупкими отправлениями");
    }

    private static void addParcel() {
        showParcelTypeMenu();
        int parcelType = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите описание посылки: ");
        String description = scanner.nextLine();

        System.out.print("Введите вес посылки: ");
        int weight = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите адрес доставки: ");
        String deliveryAddress = scanner.nextLine();

        System.out.print("Введите день отправки: ");
        int sendDay = scanner.nextInt();
        scanner.nextLine();

        Parcel parcel;
        switch (parcelType) {
            case 1:
                parcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                standardParcelBox.addParcel((StandardParcel) parcel);
                break;
            case 2:
                System.out.print("Введите допустимый срок хранения посылки: ");
                int timeToLive = scanner.nextInt();
                scanner.nextLine();
                parcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                perishableParcelBox.addParcel((PerishableParcel) parcel);
                break;
            case 3:
                parcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                fragileParcelBox.addParcel((FragileParcel) parcel);
                break;
            default:
                System.out.println("Такие посылки не отправляем.");
                return;
        }

        allParcels.add(parcel);
        if (parcel instanceof Trackable) {
            allTrackableParcels.add((Trackable) parcel);
        }
    }

    private static void sendParcels() {
        if (allParcels.isEmpty()) {
            System.out.println("Список отправки пуст.");
            return;
        }
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
        System.out.println("Все посылки отправлены.");
    }

    private static void calculateCosts() {
        int totalCost = 0;
        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Стоимость доставки составит: " + totalCost);
    }

    private static void trackAll() {
        if (allTrackableParcels.isEmpty()) {
            System.out.println("Нечего отслеживать.");
            return;
        }
        for (Trackable trackable : allTrackableParcels) {
            System.out.print("Введите локацию: ");
            String location = scanner.nextLine();
            trackable.reportStatus(location);
        }
    }

    private static void showParcelBox() {
        showBoxTypeMenu();
        int boxType = scanner.nextInt();
        scanner.nextLine();

        ParcelBox<?> parcelBox;
        switch (boxType) {
            case 1:
                parcelBox = standardParcelBox;
                break;
            case 2:
                parcelBox = perishableParcelBox;
                break;
            case 3:
                parcelBox = fragileParcelBox;
                break;
            default:
                System.out.println("Неправильная коробка.");
                return;
        }

        if (parcelBox.isEmpty()) {
            System.out.println("Коробка пустая.");
            return;
        }
        for (Parcel parcel : parcelBox.getAllParcels()) {
            System.out.println(parcel);
        }
    }
}


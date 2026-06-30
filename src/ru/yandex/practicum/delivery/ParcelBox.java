package ru.yandex.practicum.delivery;

import ru.yandex.practicum.parcel.Parcel;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final List<T> parcels;
    private final int maxWight;
    private int currentWeight;

    public ParcelBox(int maxWight) {
        parcels = new ArrayList<>();
        this.maxWight = maxWight;
        currentWeight = 0;
    }

    public void addParcel(T parcel) {
        if (currentWeight + parcel.getWeight() > maxWight) {
            System.out.println("Превышен максимальный вес коробки");
            return;
        }
        parcels.add(parcel);
        currentWeight += parcel.getWeight();
    }

    public List<T> getAllParcels() {
        return parcels;
    }

    public Boolean isEmpty() {
        return parcels.isEmpty();
    }
}

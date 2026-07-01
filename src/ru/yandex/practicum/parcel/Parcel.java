package ru.yandex.practicum.parcel;

import java.util.Objects;

public abstract class Parcel {
    protected final String description;
    protected final int weight;
    protected final String deliveryAddress;
    protected final int sendDay;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public void packageItem() {
        System.out.println("Посылка <<" + description + ">> упакована");
    }

    public void deliver() {
        System.out.println("Посылка <<" + description + ">> доставлена по адресу " + deliveryAddress);
    }

    public int calculateDeliveryCost() {
        return weight * getTariff();
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return weight == parcel.weight && sendDay == parcel.sendDay && Objects.equals(description, parcel.description)
                && Objects.equals(deliveryAddress, parcel.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public String toString() {
        return "Посылка " +
                "'" + description + '\'' +
                ", вес=" + weight +
                ", адрес доставки='" + deliveryAddress + '\'' +
                ", день отправки=" + sendDay;
    }

    protected abstract int getTariff();
}

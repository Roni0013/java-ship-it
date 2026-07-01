package ru.yandex.practicum.parcel;

/**
 * Стандартная посылка
 */
public class StandardParcel extends Parcel {
    private static final int TARIFF = 2;

    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    protected int getTariff() {
        return TARIFF;
    }
}

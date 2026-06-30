package ru.yandex.practicum.parcel;

/**
 * Скоропортящаяся посылка
 */
public class PerishableParcel extends Parcel {
    private static final int TARIFF = 3;

    private final int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {
        return (sendDay + timeToLive) < currentDay;
    }

    @Override
    public String toString() {
        return super.toString() + "время хранения=" + timeToLive;
    }

    @Override
    protected int getTariff() {
        return TARIFF;
    }
}

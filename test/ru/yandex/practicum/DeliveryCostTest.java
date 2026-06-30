package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.*;
import ru.yandex.practicum.parcel.FragileParcel;
import ru.yandex.practicum.parcel.Parcel;
import ru.yandex.practicum.parcel.PerishableParcel;
import ru.yandex.practicum.parcel.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {

    @Test
    public void calcCostStandardParcel() {
        StandardParcel parcel = new StandardParcel("Стандарт", 2, "Адрес", 10);

        assertEquals(4, parcel.calculateDeliveryCost());
    }

    @Test
    public void calcCostPerishableParcel() {
        PerishableParcel parcel = new PerishableParcel("Скоропортящаяся", 2, "Адрес", 10, 2);

        assertEquals(6, parcel.calculateDeliveryCost());
    }

    @Test
    public void calcCostFragileParcel() {
        FragileParcel parcel = new FragileParcel("Скоропортящаяся", 2, "Адрес", 10);

        assertEquals(8, parcel.calculateDeliveryCost());
    }

    @Test
    public void isNotExpiredWhenMoreCurrent() {
        PerishableParcel parcel = new PerishableParcel("Скоропортящаяся", 2, "Адрес", 1, 4);

        assertFalse(parcel.isExpired(2));
    }

    @Test
    public void isNotExpiredWhenEqualsCurrent() {
        PerishableParcel parcel = new PerishableParcel("Скоропортящаяся", 2, "Адрес", 1, 4);

        assertFalse(parcel.isExpired(5));
    }

    @Test
    public void isExpiredWhenLessCurrent() {
        PerishableParcel parcel = new PerishableParcel("Скоропортящаяся", 2, "Адрес", 1, 4);

        assertTrue(parcel.isExpired(10));
    }

    @Test
    public void addNewParcelToBoxIfLessThanMax() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(2);
        StandardParcel parcel1 = new StandardParcel("Стандарт1", 1, "Адрес1", 10);
        StandardParcel parcel2 = new StandardParcel("Стандарт2", 1, "Адрес2", 5);

        box.addParcel(parcel1);
        box.addParcel(parcel2);

        assertArrayEquals(new Parcel[]{parcel1, parcel2}, box.getAllParcels().toArray());
    }

    @Test
    public void addNewParcelToBoxIfMoreThanMax() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(2);
        StandardParcel parcel1 = new StandardParcel("Стандарт1", 2, "Адрес1", 10);
        StandardParcel parcel2 = new StandardParcel("Стандарт2", 2, "Адрес2", 5);

        box.addParcel(parcel1);
        box.addParcel(parcel2);

        assertArrayEquals(new Parcel[]{parcel1}, box.getAllParcels().toArray());
    }
}

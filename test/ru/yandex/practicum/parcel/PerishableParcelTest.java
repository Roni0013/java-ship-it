package ru.yandex.practicum.parcel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PerishableParcelTest {

    @Test
    public void calcCostPerishableParcel() {
        PerishableParcel parcel = new PerishableParcel("Скоропортящаяся", 2, "Адрес", 10, 2);

        assertEquals(6, parcel.calculateDeliveryCost());
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
}

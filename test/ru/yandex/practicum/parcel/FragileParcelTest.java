package ru.yandex.practicum.parcel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FragileParcelTest {

    @Test
    public void calcCostFragileParcel() {
        FragileParcel parcel = new FragileParcel("Скоропортящаяся", 2, "Адрес", 10);

        assertEquals(8, parcel.calculateDeliveryCost());
    }
}

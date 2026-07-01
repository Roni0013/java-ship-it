package ru.yandex.practicum.parcel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardParcelTest {

    @Test
    public void calcCostStandardParcel() {
        StandardParcel parcel = new StandardParcel("Стандарт", 2, "Адрес", 10);

        assertEquals(4, parcel.calculateDeliveryCost());
    }
}

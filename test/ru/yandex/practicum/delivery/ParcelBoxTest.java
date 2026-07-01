package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.parcel.Parcel;
import ru.yandex.practicum.parcel.StandardParcel;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ParcelBoxTest {

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

package lab5.comands;

import lab5.CollectionManager;
import lab5.entities.MusicBand;
import lab5.exceptions.ArgumentException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 * класс {@link FilterLessThanBestAlbum} отвечает за выполнение команды, которая выводит элементы , значение поля bestAlbum которых меньше заданного
 */

public class FilterLessThanBestAlbum {
    public static void run(CollectionManager manager, String length) {
        Long len = 0L;
        try {
            len = Long.parseLong(length);

        } catch (NumberFormatException e){
            throw new ArgumentException("Проверьте правильность введенных аргументов!");
        }
        Long finalLen = len;
        manager.getBands().forEach((key, value) -> {
            if(value.getBestAlbum().getLength() < finalLen) {
                System.out.println("ID: " + value.getId());
                System.out.println("Название: " + value.getName());
                System.out.println("Дата: " + value.getCreationDate());
                System.out.println("Количество участников: " + value.getNumberOfParticipants());
                System.out.println("Жанр: " + value.getGenre().name());
                System.out.println("Название лучшего альбома: " + value.getBestAlbum().getName() + "\n");
            }
        });


    }
}

package lab5.comands;

import lab5.CollectionManager;
import lab5.exceptions.ArgumentException;

/**
 * класс {@link FilterGreaterThanNumberOfParticipants} отвечает за выполнение команды, которая выводит элементы, значение поля numberOfParticipants, которых больше заданного
 */

public class FilterGreaterThanNumberOfParticipants {
    public static void run(CollectionManager manager, String number) {
        int num = 0;
        try {
            num = Integer.parseInt(number);

        } catch (NumberFormatException e){
            throw new ArgumentException("Проверьте правильность введенных аргументов!");
        }
        int finalNum = num;
        manager.getBands().forEach((key, value) -> {
            if(value.getNumberOfParticipants() > finalNum) {
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

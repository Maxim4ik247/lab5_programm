package lab5.comands;

import lab5.CollectionManager;

/**
 * класс {@link ShowCommand} отвечает за выполнение команды, которая выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */

public class ShowCommand implements Runable {
    public static void run(CollectionManager manager){
        manager.getBands().forEach((key, value) -> {
            System.out.println("ID: " + value.getId());
            System.out.println("Название: " + value.getName());
            System.out.println("Дата: " + value.getCreationDate());
            System.out.println("Количество участников: " + value.getNumberOfParticipants());
            System.out.println("Жанр: " + value.getGenre().name() + "\n");
        });
    }

}

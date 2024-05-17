package lab5.comands;

import lab5.CollectionManager;

/**
 * класс {@link InfoCommand} отвечает за выполнение команды, которая выводит в стандартный поток вывода (консоль) информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
 */

public class InfoCommand implements Runable {
    public static void run(CollectionManager manager) {
        System.out.println("Тип коллекции: " + manager.getBands().getClass() + "(MusicBand)");
        System.out.println("Кол-во элементов: " + manager.getBands().size());
        System.out.println("Дата инициализации: " + manager.getCreationTime());
        StringBuilder sb = new StringBuilder();



    }
}

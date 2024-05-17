package lab5.comands;

import lab5.CollectionManager;
import lab5.entities.Album;
import lab5.entities.Coordinates;
import lab5.entities.MusicBand;
import lab5.entities.MusicGenre;
import lab5.exceptions.ArgumentException;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * класс {@link InsertCommand} отвечает за выполнение команды, которая добавляет новый элемент с заданным ключом
 */

public class InsertCommand implements Runable{
    public static void run(CollectionManager manager, InputStream in, String id_str, String entityName) throws ArgumentException{
        int id = -1;
        if(!id_str.equals("null")) {
            try {
                id = Integer.parseInt(id_str);
            } catch (NumberFormatException e){
                throw new ArgumentException("Проверьте правильность введенных аргументов!");
            }
        }

        if(id > 0) {
            if(manager.getBands().get(id) != null){
                printError();
                return;
            }
        } else {
            id = manager.getNewId();
        }

        if(entityName.equalsIgnoreCase("musicband")) {
            System.out.println("Создание сущности: " + entityName);
            Scanner scanner = new Scanner(in);
            MusicBand band = InsertCommand.getMusicBand(scanner, id);
            Album bestAlbom = InsertCommand.getAlbum(scanner);
            Coordinates coords = InsertCommand.getCoordinates(scanner);
            band.setCoordinates(coords);
            band.setBestAlbum(bestAlbom);
            manager.addMusicBand(band);
        } else {
            throw new ArgumentException("Проверьте правильность введенных аргументов!");
        }
    }

    public static void printError(){
        System.out.println("Что-то пошло не так, попробуйте еще раз.");
    }


    public static Album getAlbum(Scanner scanner){
        System.out.print("Введите название лучшего альбома группы: ");
        String bestAlbumName = scanner.nextLine();
        System.out.print("Введите продолжительность лучшего альбома группы: ");
        String bestAlbumLengthStr = scanner.nextLine();

        if (bestAlbumName.isEmpty() || bestAlbumLengthStr.isEmpty()) {
            printError();
            return null;
        }

        long bestAlbumLength = 0;
        try {
            bestAlbumLength = Long.parseLong(bestAlbumLengthStr);
        } catch (NumberFormatException e){
            printError();
            return null;
        }
        return new Album(bestAlbumName, bestAlbumLength);
    }


    public static Coordinates getCoordinates(Scanner scanner){
        System.out.print("Введите координату X: ");
        String coordsXStr = scanner.nextLine();
        System.out.print("Введите координату Y: ");
        String coordsYStr = scanner.nextLine();
        if (coordsXStr.isEmpty() || coordsYStr.isEmpty()) {
            printError();
            return null;
        }
        int coordsX = 0;
        float coordsY = 0;
        try {
            coordsX = Integer.parseInt(coordsXStr);
            coordsY = Float.parseFloat(coordsYStr);
        } catch (NumberFormatException e){
            printError();
            return null;
        }
        return new Coordinates(coordsX, coordsY);
    }

    public static MusicBand getMusicBand(Scanner scanner, int id){
        System.out.print("Введите название группы: ");
        String name = scanner.nextLine();
        System.out.print("Введите дату создания группы в формате YYYY-MM-DD: ");
        String date = scanner.nextLine();
        System.out.print("Введите количество участников группы: ");
        String number = scanner.nextLine();
        System.out.println("Введите номер жанра группы из данного списка: ");
        MusicGenre[] genres = MusicGenre.values();
        for (MusicGenre genre : genres) {
            System.out.println(genre.ordinal()+": "+genre.name());
        }
        String genre_num = scanner.nextLine();
        if (name.isEmpty() || date.isEmpty() || number.isEmpty() || genre_num.isEmpty()) {
            printError();
            return null;
        }
        LocalDate creatDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            creatDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            printError();
            return null;
        }
        int numberOfParticipants = 0;
        int genreNumber = 0;
        try {
            numberOfParticipants = Integer.parseInt(number);
            genreNumber = Integer.parseInt(genre_num);
        } catch (NumberFormatException e){
            printError();
            return null;
        }
        return new MusicBand(
                id,
                name,
                creatDate,
                numberOfParticipants,
                MusicGenre.values()[genreNumber]
        );
    }
}

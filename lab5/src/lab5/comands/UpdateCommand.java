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
 * класс {@link UpdateCommand} отвечает за выполнение команды, которая обновляет значение элемента коллекции, id которого равен заданному
 */

public class UpdateCommand implements Runable{
    public static void run(CollectionManager manager, InputStream in, String id_str, String entityName){
        int id;
        try {
            id = Integer.parseInt(id_str);
        } catch (NumberFormatException e){
            throw new ArgumentException("Проверьте правильность введенных аргументов!");
        }
        MusicBand band =  manager.getBands().get(id);
        if(entityName.equalsIgnoreCase("musicband")) {
            System.out.println("Обновление сущности (чтобы не изменять поле, оставьте его пустым): " + entityName);
            Scanner scanner = new Scanner(in);

            band = UpdateCommand.updateMusicBand(scanner, band);
            UpdateCommand.updateAlbum(scanner, band);
            UpdateCommand.updateCoordinates(scanner, band);
            manager.getBands().put(id, band);
        } else {
            throw new ArgumentException("Проверьте правильность введенных аргументов!");
        }

    }

    public static void printError(){
        System.out.println("Что-то пошло не так, попробуйте еще раз.");
    }

    public static void updateAlbum(Scanner scanner, MusicBand band){
        Album newAlbum = new Album();
        System.out.print("Введите название лучшего альбома группы: ");
        String bestAlbumName = scanner.nextLine();
        System.out.print("Введите продолжительность лучшего альбома группы: ");
        String bestAlbumLengthStr = scanner.nextLine();
        if(!bestAlbumName.isEmpty()){
            if (band.getBestAlbum() == null){
                newAlbum.setName(bestAlbumName);
            } else {
                band.getBestAlbum().setName(bestAlbumName);
            }
        }
        if(!bestAlbumLengthStr.isEmpty()){
            if (band.getBestAlbum() == null){
                newAlbum.setLength(Long.parseLong(bestAlbumLengthStr));
            } else {
                band.getBestAlbum().setLength(Long.parseLong(bestAlbumLengthStr));
            }
        }
        if (band.getBestAlbum() == null){
            band.setBestAlbum(newAlbum);
        }
    }

    public static void updateCoordinates(Scanner scanner, MusicBand band){
        System.out.print("Введите координату X: ");
        String coordsXStr = scanner.nextLine();
        System.out.print("Введите координату Y: ");
        String coordsYStr = scanner.nextLine();

        Coordinates coordinates = new Coordinates();



        if(!coordsXStr.isEmpty()){
            if (band.getCoordinates() == null){
                coordinates.setX(Long.parseLong(coordsXStr));
            } else {
                band.getCoordinates().setX(Long.parseLong(coordsXStr));
            }
        }

        if(!coordsYStr.isEmpty()){
            if (band.getCoordinates() == null){
                coordinates.setY(Float.parseFloat(coordsYStr));
            } else {
                band.getCoordinates().setY(Float.parseFloat(coordsYStr));
            }
        }
        if (band.getCoordinates() == null){
            band.setCoordinates(coordinates);
        }
    }

    public static MusicBand updateMusicBand(Scanner scanner, MusicBand band){
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
        if(!name.isEmpty())
            band.setName(name);
        if(!date.isEmpty()) {
            LocalDate creatDate;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
                creatDate = LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                printError();
                return null;
            }
            band.setCreationDate(creatDate);
        }
        if(!number.isEmpty()){
            int numberOfParticipants = 0;
            try {
                numberOfParticipants = Integer.parseInt(number);
            } catch (NumberFormatException e){
                printError();
                return null;
            }
            band.setNumberOfParticipants(numberOfParticipants);
        }
        if(!genre_num.isEmpty()){
            int genreNumber = 0;
            try {
                genreNumber = Integer.parseInt(genre_num);
            } catch (NumberFormatException e){
                printError();
                return null;
            }
            band.setGenre(MusicGenre.values()[genreNumber]);
        }
        return band;
    }

}

package lab5.comands;

import lab5.CollectionManager;
import lab5.entities.Coordinates;
import lab5.entities.MusicBand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 * класс {@link GroupCountingByCoordinatesCommand} отвечает за выполнение команды, которая группирует коллекции по значению поля coordinates, выводит количество элементов в каждой группе
 */

public class GroupCountingByCoordinatesCommand {
    public static void run(CollectionManager manager) {
        ArrayList<Map.Entry<?, MusicBand>> band_list = new ArrayList(manager.getBands().entrySet());
        Collections.sort(band_list, new Comparator<Map.Entry<?, MusicBand>>() {
            @Override
            public int compare(Map.Entry<?, MusicBand> o1, Map.Entry<?, MusicBand> o2) {
                return o1.getValue().getCoordinates().compareTo(o2.getValue().getCoordinates());
            }
        });
        Coordinates curr_coords = null;
        curr_coords = null;
        int count = 0;
        for(Map.Entry<?, MusicBand> band : band_list){
            if (band.getValue().getCoordinates().compareTo(curr_coords) == 0){
                count++;

            } else {
                if(count > 0)
                    System.out.println(count);
                System.out.print("Coordinates group \"X="+band.getValue().getCoordinates().getX() + "\" : ");
                count = 1;
            }
            curr_coords = band.getValue().getCoordinates();
        }
        System.out.println(count);

    }
}

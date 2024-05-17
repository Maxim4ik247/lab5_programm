package lab5;

import lab5.entities.Album;
import lab5.entities.Coordinates;
import lab5.entities.MusicBand;
import lab5.entities.MusicGenre;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

/**
 * класс {@link MusicBandReader} создает коллекцию музыкальных групп, используя класс {@link XMLReader}
 */
public class MusicBandReader {

    private String inputFileName;

    public MusicBandReader(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public Hashtable<Integer, MusicBand> readMusicBands(){
        Hashtable<Integer, MusicBand> bands = new Hashtable<>();
        XMLReader r = new XMLReader(inputFileName);
        Tag root = r.read();

        if(!root.getName().equals("bands")){
            return null;
        }

        for (Tag band:  root.getChildren()) {
            MusicBand musicBand = this.readBand(band);
            bands.put(musicBand.getId(), musicBand);
        }

        return bands;
    }


    private MusicBand readBand(Tag band){
        MusicBand musicBand = new MusicBand();

        if(!band.getName().equals("band")){
            return null;
        }

        for (Tag option:  band.getChildren()) {
            if(option.getName().equals("id")){
                musicBand.setId(Integer.valueOf(option.getContent()));
            }
            if(option.getName().equals("name")){
                musicBand.setName(option.getContent());
            }
            if(option.getName().equals("creationDate")){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd" );
                LocalDate date = LocalDate.parse( option.getContent() , formatter);
                musicBand.setCreationDate(date);
            }
            if(option.getName().equals("numberOfParticipants")){
                musicBand.setNumberOfParticipants(Integer.valueOf(option.getContent()));
            }
            if(option.getName().equals("genre")){
                musicBand.setGenre(MusicGenre.values()[Integer.valueOf(option.getContent())]);
            }
            if(option.getName().equals("album")){
                Album album = readAlbum(option);
                musicBand.setBestAlbum(album);
            }
            if(option.getName().equals("coordinates")){
                Coordinates coordinates = readCoords(option);
                musicBand.setCoordinates(coordinates);
            }

        }

        return musicBand;
    }

    private Album readAlbum(Tag album_tag){
        Album album = new Album();

        for (Tag option: album_tag.getChildren()) {
            if(option.getName().equals("name")){
                album.setName(option.getContent());
            }
            if(option.getName().equals("length")){
                album.setLength(Long.valueOf(option.getContent()));
            }
        }

        return album;
    }

    private Coordinates readCoords(Tag coords_tag){
        Coordinates coords = new Coordinates();
        for (Tag option: coords_tag.getChildren()) {
            if(option.getName().equals("x")){
                coords.setX(Long.parseLong(option.getContent()));
            }
            if(option.getName().equals("y")){
                coords.setY(Float.valueOf(option.getContent()));
            }
        }

        return coords;
    }
}

package lab5;

import lab5.entities.MusicBand;

import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * класс {@link CollectionManager} позволяет управлять коллекцией музыкальных групп: {@link #bands}
 */

public class CollectionManager {
    private Hashtable<Integer, MusicBand> bands;
    private String creationTime;

    private String filename;

    public CollectionManager(String filename) {
        this.filename = filename;
    }

    public void read(){
        MusicBandReader musicBandReader = new MusicBandReader(filename);
        this.bands = musicBandReader.readMusicBands();
        this.creationTime = (new Date()).toString();
    }
    public void save(){
        MusicBandWriter musicBandWriter = new MusicBandWriter(filename);
        musicBandWriter.writeMusicBands(bands);
    }

    public void addMusicBand(MusicBand band){
        bands.put(band.getId(), band);
    }
    public void clear(){
        bands.clear();
    }

    public Integer getNewId(){
        Integer max = 0;
        for(Integer i : Collections.list(bands.keys())){
            max = (i > max) ? i : max;
        }
        return max + 1;
    }

    public Hashtable<Integer, MusicBand> getBands(){
        return bands;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void removeById(int id){
        this.bands.remove(id);
    }
    public void removeGreater(int id){
        List<Integer> tmp = Collections.list(bands.keys());
        for (Integer key : tmp) {
            if(key > id)
                bands.remove(key);
        }
    }
    public void removeLower(int id){
        List<Integer> tmp = Collections.list(bands.keys());
        for (Integer key : tmp) {
            if(key < id)
                bands.remove(key);
        }
    }
}

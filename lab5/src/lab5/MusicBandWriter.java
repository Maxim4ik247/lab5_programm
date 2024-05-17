package lab5;

import lab5.entities.MusicBand;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MusicBandWriter {
    private String outputFileName;

    public MusicBandWriter(String inputFileName) {
        this.outputFileName = inputFileName;
    }

    public void writeMusicBands(Hashtable<Integer, MusicBand> bandList){
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName))) {
            writter.write("<bands>");
            List<Integer> tmp = Collections.list(bandList.keys());
            Collections.sort(tmp);
            for (Integer integer : tmp) {
                MusicBand value = bandList.get(integer);
                writter.write(String.valueOf(value));
            }
            writter.write("\n</bands>");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package lab5;

import lab5.comands.ExecuteScript;
import lab5.entities.MusicBand;
import lab5.exceptions.ArgumentException;
import lab5.exceptions.ScriptFileNotFoundException;

import java.io.*;
import java.util.Scanner;

/**
 * класс {@link App} служит для запуска приложения
 * он содержит методы: {@link #start()}(запускает приложение), {@link #executeScript(String, CollectionManager)}(позволяет запустить команды из файла)
 */
public class App {

    /**
     * <p>метод start() запускает приложение</p>
     * <p>Сначала он создает {@link CollectionManager} обьект</p>
     * <p>Затем использует {@link ComandExecuter}, чтобы выполнять введенные команды</p>
     */
    public void start() {
        String filename = System.getenv("BANDS_FILE");
//        if(filename == null){
//            filename = "bands.xml";
//        }
        CollectionManager manager = new CollectionManager(filename);
        manager.read();
        Scanner scanner = new Scanner(System.in);
        ComandExecuter comandExecuter = new ComandExecuter(manager);
        while (true){
            String str = scanner.nextLine().trim();
            if(str.equals("exit"))
                break;
            if(str.startsWith("execute_script")){
                try {
                    this.executeScript(str, manager);
                } catch (ScriptFileNotFoundException e){
                    System.out.println(e.getMessage());
                }
                continue;
            }
            try {
                comandExecuter.execute(str, System.in);
            } catch (ArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private void executeScript(String str, CollectionManager manager) throws ScriptFileNotFoundException{
        if (str.length() < 15){
            return;
        }
        String scriptFilename = str.substring(15).trim();
        FileInputStream fileReader = null;
        try {
            fileReader = new FileInputStream(scriptFilename);
            ExecuteScript.run(fileReader, manager);
        } catch (FileNotFoundException e) {
            throw new ScriptFileNotFoundException("Файл "+scriptFilename+" не найден, убедитесь, что путь к файлу корректен и попробуйте снова.");
        }
    }
}

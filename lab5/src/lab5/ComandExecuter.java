package lab5;

import lab5.comands.*;
import lab5.exceptions.ArgumentException;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * класс {@link ComandExecuter} выполняет команды пользователя, хранит историю команд
 */
public class ComandExecuter {

    CollectionManager manager;

    ArrayList<String> commandHistory;

    public ComandExecuter(CollectionManager manager) {

        this.manager = manager;
        this.commandHistory = new ArrayList<>();
    }

    public void execute(String command, InputStream in) throws ArgumentException {

        if(command.equals("history")){
            this.showHistory();
        }
        this.addCommandToHistory(command);

        if(command.equals("show")){
            ShowCommand.run(manager);
            return;
        }
        if (command.equals("info")){
            InfoCommand.run(manager);
            return;
        }
        if(command.equals("save")){
            manager.save();
            return;
        }
        if(command.trim().startsWith("insert")){
            String commandRightSide = command.substring(6).trim();
            String[] id_entityName = commandRightSide.split(" ");
            if(id_entityName.length == 2)
                InsertCommand.run(manager, in, id_entityName[0], id_entityName[1]);
            return;
        }

        if(command.startsWith("update")){
            String commandRightSide = command.substring(7);
            String[] id_entityName = commandRightSide.split(" ");
            if(id_entityName.length == 2)
                UpdateCommand.run(manager, in, id_entityName[0], id_entityName[1]);
            return;
        }
        if(command.equals("clear")){
            ClearCommand.run(manager);
            return;
        }
        if(command.equals("help")){
            HelpCommand.run();
            return;
        }
        if(command.startsWith("remove_key")){
            String commandRightSide = command.substring(10).trim();
            RemoveCommand.run(manager, commandRightSide.trim());
            return;
        }
        if(command.startsWith("remove_greater")){
            String commandRightSide = command.substring(14).trim();
            RemoveGreaterCommand.run(manager, commandRightSide.trim());
        }
        if(command.startsWith("remove_lower")){
            String commandRightSide = command.substring(12).trim();
            RemoveLowerCommand.run(manager, commandRightSide.trim());
        }
        if(command.equals("group_counting_by_coordinates")){
            GroupCountingByCoordinatesCommand.run(manager);
        }
        if(command.startsWith("filter_less_than_best_album")) {
            String commandRightSide = command.substring(27).trim();
            FilterLessThanBestAlbum.run(manager, commandRightSide);
        }
        if(command.startsWith("filter_greater_than_number_of_participants")){
            String commandRightSide = command.substring(42).trim();
            FilterGreaterThanNumberOfParticipants.run(manager, commandRightSide);
        }


    }

    private void showHistory(){
        for (String command : this.commandHistory) {
            System.out.println(command);
        }
    }

    private void addCommandToHistory(String command){
        String commandName = command.split(" ")[0];
        this.commandHistory.add(commandName);
        if(this.commandHistory.size() > 14){
            this.commandHistory.remove(0);
        }
    }
}

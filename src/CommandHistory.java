import java.util.ArrayList;
import java.util.List;

public interface CommandHistory {
    public String getHistory(int index){
        List<String> history = new ArrayList<String>();
        return history.get(index);
    }

    public void addHistory(String newCommand){
        List<String> history = new ArrayList<String>();
        history.add(newCommand);
    }
}

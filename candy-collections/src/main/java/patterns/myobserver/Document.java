package patterns.myobserver;

import java.util.ArrayList;
import java.util.List;

public class Document {

    private List<EventListener> listeners;
    private String documentName;


    public Document(String documentName){
        this.documentName = documentName;
        listeners = new ArrayList<>();
    }

    public void registerView(EventListener eventListener) {
        listeners.add(eventListener);
    }

    private void notifyAllViews() {
        for(EventListener eventListener : listeners){
            eventListener.onUpdate();
        }
    }

    public void removeView(EventListener eventListener){
        listeners.remove(eventListener);
    }

    //необходимо при изменении имени оповещать наблюдателей
    public void setName(String name){
        this.documentName = name;
        notifyAllViews();

    }

    public String getDocumentName(){
        return this.documentName;
    }
}

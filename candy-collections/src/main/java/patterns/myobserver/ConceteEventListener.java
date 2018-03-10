package patterns.myobserver;

public class ConceteEventListener implements EventListener {
    private Document observableDocument;

    public ConceteEventListener(Document observableDocument){
        this.observableDocument = observableDocument;
        observableDocument.registerView(this);
    }
    @Override
    public void onUpdate() {
        observableDocument.getDocumentName();
    }
}

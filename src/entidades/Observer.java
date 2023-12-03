package entidades;
public abstract class Observer {
    protected Observable observavel;

    public Observer(Observable observavel) {
       setObservavel(observavel);
    }
    
    public void setObservavel(Observable observavel) {
        this.observavel = observavel;
    }

    public abstract void update();
}

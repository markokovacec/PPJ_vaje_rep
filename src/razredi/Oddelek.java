package razredi;

public enum Oddelek{
    sadje(211), zelenjava (222), meso(210), pekovski_izdelki(232)
    ;
    private final int koda;
    Oddelek(int koda){
        this.koda=koda;
    }
    public  int getCode(){
        return this.koda;
    }
}

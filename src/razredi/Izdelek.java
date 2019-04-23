package razredi;

public enum Izdelek{
    Banana(6789), Korenje(5566), Krompir(9876), Kruh(6464)
    ;
    private  final int koda;
    Izdelek(int koda){
        this.koda=koda;
    }

    public int getCode(){
        return this.koda;
    }
}

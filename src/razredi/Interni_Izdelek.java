package razredi;


enum Oddelek{
    sadje(211), zelenjava (222), meso(210), mlecni_izdelki(232)
    ;
    private final int koda;
    Oddelek(int koda){
        this.koda=koda;
    }
    public  int getCode(){
        return this.koda;
    }
}

enum Izdelek{
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

public class Interni_Izdelek {
    Izdelek izdelek;
    Oddelek oddelek;
    int teza;
    double cena_gram;
    double cena;
    String ean;

    public Interni_Izdelek (Izdelek izdelek, Oddelek oddelek, int teza, double cena){
        this.izdelek=izdelek;
        this.oddelek=oddelek;
        this.cena_gram=cena;
        this.teza=teza;
        this.cena=makeCena(this.teza, this.cena_gram);
        makeEAN();
    }

    void makeEAN(){
        ean= ""+oddelek.getCode()+""+izdelek.getCode()+""+teza;
        ean=ean+""+checkDig(ean);
    }

    double makeCena(int teza, double cena_gram){
        return teza*cena_gram;
    }

    int checkDig(String ean){
        Boolean validation=false;
        char[] c= ean.toCharArray();
        int[] test=new int[ean.length()+1];
        for (int i = 0; i < ean.length(); i++) {
            test[i] = Character.digit(c[i], 10);
        }
        int[] num = new int[ean.length()+1];

        for (int i = 0; i < ean.length(); i++) {
            num[i] = Character.digit(c[i], 10);
        }
        Boolean t=true;
        int sum=0, s=0;
        int checkdigi=0;
        int zaokrozi=0;
        int x=c.length;
        //mnozi z 3
        for(int i=c.length-2; i>=0;i--){
            if(t==true) {
                num[i] = num[i]*3;
                t=false;
            } else if(t==false){
                t=true;
            }

        }

        //sum
        for(int i=0;i<ean.length()-1;i++){
            sum=sum+num[i];
        }

        zaokrozi=sum/10;//odstranimo enico
        zaokrozi=(zaokrozi*10)+10;//zakrozimo navzgor
        checkdigi=zaokrozi-sum;

        return checkdigi;
    }


}

package CP;
import java.util.*;

class Bus{
    int st_awal, st_akhir, wk_awal, wk_akhir;
    double p_lancar, p_gagal;

    public Bus(int a, int b, int c, int d, double e){
        this.st_awal = a;
        this.st_akhir = b;
        this.wk_awal = c;
        this.wk_akhir = d;
        this.p_lancar = e;
        this.p_gagal = (1 - this.p_lancar);
    }

    public int getStAwal(){
        return st_awal;
    }

    public int getStAkhir(){
        return st_akhir;
    }

    public int getWkAwal(){
        return wk_awal;
    }

    public int getWkAkhir(){
        return wk_akhir;
    }

    public double getLancar(){
        return p_lancar;
    }

    public double getGagal(){
        return p_gagal;
    }
}

public class CTP {
    static ArrayList<Bus> bus = new ArrayList<Bus>();
    static ArrayList<Double> probabilitas = new ArrayList<Double>();
    static int a,b,c,d;
    static int awal;
    static double e;

    static int m,n,k;

//    public static Comparator<Bus> StuNameComparator = new Comparator<Bus>() {
//
//    public int compare(Bus b1, Bus b2) {
//        int Waktu1 = b1.getWkAwal();
//        int Waktu2 = b2.getWkAwal();
//
//        //urutan turun
//        return Waktu1.compareTo(Waktu2);
//
//        //urutan naik
//        //return Waktu2.compareTo(Waktu1);
//    }};

    public static Comparator<Bus> WkBus = new Comparator<Bus>() {

    public int compare(Bus b1, Bus b2) {

        int wk1 = b1.getWkAwal();
        int wk2 = b2.getWkAwal();

        //
//        return wk1-wk2;

        return wk2-wk1;
    }};

    static Double busMacet(int i){
        ArrayList<Double> probs = new ArrayList<Double>();

        double prob = 0;
        int st_awal = bus.get(i).st_awal;
        int st_akhir = bus.get(i).st_akhir;
        int wk_awal = bus.get(i).wk_awal;
        int wk_akhir = bus.get(i).wk_akhir;
        double lancar = bus.get(i).p_lancar;
        double macet = bus.get(i).p_gagal;

//        if(lancar == 1.0 && st_akhir == 1 && awal == 0){
//            return lancar;
//        }
//
//        else{
            for(int j = 0 ; j < m ; j++){

                double t1 = bus.get(i).wk_akhir;
                double t2 = bus.get(j).wk_awal;

//            assert((j!=i) & (t2 >= t1));

//                if((j!=i) && (t2 >= t1) && (bus.get(j).st_akhir == 1) && (bus.get(j).p_lancar == 1.0)){
//                    prob = (lancar + (macet*bus.get(j).p_lancar));
//                    probs.add(prob);
//                }

                if((j!=i) && (t2 >= t1) && (bus.get(i).st_awal == bus.get(j).st_awal)){
                    prob = (lancar + (macet*busMacet(j)));
                    probs.add(prob);
                }

                if((j!=i) && (t2 > t1) && (bus.get(i).st_akhir == bus.get(j).st_awal)){
                    prob = (macet + (lancar*busMacet(j)));
                    probs.add(prob);
                }
            }
//        }

        System.out.println(probs);

        if(bus.get(i).st_akhir == 1){
            if(probs.isEmpty()) return lancar;
            else
            return Collections.max(probs);
        } else return 0.0;
    }

    public static void main(String[] Args){

        Scanner scan = new Scanner(System.in);

        m = scan.nextInt();
        n = scan.nextInt();
        k = scan.nextInt();

        for(int i=0 ; i<m ; i++){
            a = scan.nextInt();
            b = scan.nextInt();
            c = scan.nextInt();
            d = scan.nextInt();
            e = scan.nextDouble();
            Bus bb = new Bus(a, b, c, d, e);
            bus.add(bb);
        }

        Collections.sort(bus, CTP.WkBus);

        for(int l=0 ; l<m ; l++){
            if(bus.get(l).st_awal == 0){
                awal = bus.get(l).st_awal;
                probabilitas.add(busMacet(l));
            }
        }

        System.out.println("Hasil: " + Collections.max(probabilitas));

    }

}

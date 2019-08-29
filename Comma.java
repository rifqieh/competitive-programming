package CP;

import java.util.*;

public class Comma {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] Args){
        ArrayList<String> kumpulanKataPrev = new ArrayList<>();
        ArrayList<String> kumpulanKataNext = new ArrayList<>();
        String str = scan.nextLine();
//        System.out.println(str);

        boolean benar = true;
        String kata = "";

        while(benar){

            int jumlah = 0;

            for(int i=0 ; i<str.length() ; i++){
                //nyari koma
                if(str.charAt(i) == ','){
                    int index_prev = i-1;
                    int index_next = i+2;
                    kata = "";

                    //nyari kata
                    while(str.charAt(index_prev) != ' ' && str.charAt(index_prev) != '.'){
                        kata = str.charAt(index_prev) + kata;
                        index_prev--;
                        if(index_prev < 0) break;
                    }

                    if(kata != ""){
                        kumpulanKataPrev.add(kata);
                    }

                    kata = "";

                    while(str.charAt(index_next) != ' ' && str.charAt(index_next) != '.'){
                        kata = kata + str.charAt(index_next);
                        index_next++;
                        if(index_next == str.length()) break;
                    }

                    if(kata != ""){
                        kumpulanKataNext.add(kata);
                    }
                }
            }

            for(int j=0 ; j<kumpulanKataPrev.size() ; j++){
                for(int i=0 ; i<str.length() ; i++){
                    if(str.charAt(i) == ' ' || str.charAt(i) == '.'){

                        if(kata.equals(kumpulanKataPrev.get(j))){
                            int prev = i - kata.length() - 1;
                            int next = i + 1;

                            if(next < str.length()){
                                if(str.charAt(next) != ',' && str.charAt(next) != '.' && str.charAt(next-1) != '.'){
                                    str = str.substring(0, next-1) + ',' + str.substring(next-1, str.length());
                                    jumlah++;
                                }
                            }
                        }
                        kata = "";
                    } else kata = kata + str.charAt(i);
                }
            }

            for(int j=0 ; j<kumpulanKataNext.size() ; j++){
                for(int i=0 ; i<str.length() ; i++){
                    if(str.charAt(i) == ' ' || str.charAt(i) == '.'){

                        if(kata.equals(kumpulanKataNext.get(j))){

                            int prev = i - kata.length() - 1;
                            int next = i + 1;

                            if(prev >= 0){
                                if(str.charAt(prev-1) != ',' && str.charAt(prev-1) != '.'){
                                    str = str.substring(0, prev) + ',' + str.substring(prev, str.length());
                                    jumlah++;
                                }
                            }

                        }
                        kata = "";
                    } else kata = kata + str.charAt(i);
                }
            }
            if(jumlah == 0) benar = false;
//            System.out.println(str);
        }
        System.out.println(str);

    }
}

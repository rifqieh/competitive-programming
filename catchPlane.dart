import 'dart:io';
import 'dart:math';

class Bus{
  var st_awal;
  var st_tujuan;
  var wk_awal;
  var wk_tujuan;
  double prob;
  
  Bus(this.st_awal, this.st_tujuan, this.wk_awal, this.wk_tujuan, this.prob);
  
  void printBus(){
    print('st_awal: $st_awal');
    print('st_tujuan: $st_tujuan');
    print('wk_awal: $wk_awal');
    print('wk_tujuan: $wk_tujuan');
    print('prob: $prob');
  }
}

// List<Bus> bus = [
//   Bus(0, 1, 0, 900, 0.2),
//   Bus(0, 2, 100, 500, 1.0),
//   Bus(2, 1, 500, 700, 1.0),
//   Bus(2, 1, 501, 701, 0.1),
//   Bus(0, 3, 200, 400, 0.5),
//   Bus(3, 1, 500, 800, 0.1),
//   Bus(3, 0, 550, 650, 0.9),
//   Bus(0, 1, 700, 900, 0.1),
// ];

List<Bus> bus = [
  Bus(0, 1, 0, 1, 0.5),
  Bus(0, 1, 0, 1, 0.5),
  Bus(0, 1, 1, 2, 0.4),
  Bus(0, 1, 1, 2, 0.2)
];

List<double> probabilitas = List();

// var batasWaktu = 1000;
// var banyakBus = 8;
// var banyakStasiun = 4;

var batasWaktu = 2;
var banyakBus = 4;
var banyakStasiun = 2;

double busProbMacet(var i){
  
  List<double> probs = List();
  
  double prob = 0;
  double lancar = (1 - bus[i].prob);
  double macet = bus[i].prob;
  
  for(int j = 0 ; j < banyakBus ; j++){
    
    double t1 = bus[i].wk_tujuan;
    double t2 = bus[j].wk_awal;
    
    assert((j!=i) & (t2 >= t1));
    
    if((j!=i) & (t2 >= t1)){
      prob = (lancar + (macet*busProbMacet(j)));
      probs.add(prob);
      // probs.add(busProbLancar(j));
    }
    
  }
  
//   if(probs.isEmpty){
//     for(int k = 0 ; k<probs.length ; k++){
//       if(probs[k] > 0.3) assert('ada');
//     }
//   }
  
  if(bus[i].st_tujuan == 1){
    if(probs.isEmpty) return macet;
    else return probs.reduce(max); 
  } else return 0.0;
}

double busProbLancar(var i){
  
  List<double> probs = List();
  
  double prob = 0;
  double lancar = (1 - bus[i].prob);
  double macet = bus[i].prob;
  
  for(int j = 0 ; j < banyakBus ; j++){
    
    double t1 = bus[i].wk_tujuan;
    double t2 = bus[j].wk_awal;
    
    assert((j!=i) & (t2 >= t1));
    
    if((j!=i) & (t2 >= t1)){
      prob = (lancar + (macet*busProbLancar(j)));
      probs.add(prob);
      // probs.add(busProbMacet(j));
    }
    
  }
  
  
  if(bus[i].st_tujuan == 1){
    if(probs.isEmpty) return lancar;
    else return probs.reduce(max); 
  } else return 0.0;
}

void main(){

//   List<double> coba = [1.0, 0.5, 200.290, 200.81, 90, 2];
//   print(coba.reduce(max));
  
//   bus[0].printBus();
  
//   print('===============');
  
  for(int i = 0 ; i<banyakBus ; i++){
    probabilitas.add(busProbMacet(i));
  }
  
//   print('hello');
  
  print(probabilitas.reduce(max));
}
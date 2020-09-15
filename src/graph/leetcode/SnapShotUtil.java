package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SnapShotUtil{
 int[] arr;
 int sequenceId;
Map<Integer, List<Integer[]>> snapShots;
List<Integer[]> updateTracker;
 
public SnapShotUtil(int[] input){
     this.arr= input;
     this.sequenceId=0;
     snapShots= new HashMap<>();
     updateTracker= new ArrayList<>();
     
   }

public int get(int index){
     if(index>=this.arr.length) return -1;
     return this.arr[index];
   }

public void put(int index, int value){
     if(index>=this.arr.length) return ;
     this.updateTracker.add(new Integer[]{index,value});
    this.arr[index]=value;
   }

public int createSnapShot(){
     snapShots.put(++sequenceId, this.updateTracker);
     this.updateTracker= new ArrayList<>();
     return sequenceId;
   }

public int get(int snapShotId, int index){
     if(!snapShots.containsKey(snapShotId)) return -1;
     
    List<Integer[]> updates= snapShots.get(snapShotId);
     int[] tempArr= this.arr;
     
     for(Integer[] update: updates){
       tempArr[update[0]]=update[1];
     }
     
     return tempArr[index];
   }
}


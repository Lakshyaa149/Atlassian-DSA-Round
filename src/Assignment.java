
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;

/**
 *
 *
 * file1.txt (size: 100)
 * file2.txt (size: 200) in collection "collection1"
 * file3.txt (size: 200) in collection "collection1"
 * file4.txt (size: 300) in collection "collection2"
 * file5.txt (size: 10)
 *
 * name size
 *
 */
class Pair{
    String fileName;
    Integer totalSizeOfFile;

    Pair(String fileName, Integer totalSizeOfFile){
        this.fileName=fileName;
        this.totalSizeOfFile=totalSizeOfFile;
    }
}
public class Assignment {
    HashMap<String,List<Integer>> map = new HashMap<>();
    PriorityQueue<Pair> priorityQueue =new PriorityQueue<>(new Comparator<Pair>() {
        @Override
        public int compare(Pair o1, Pair o2) {
            return o2.totalSizeOfFile-o1.totalSizeOfFile;
        }
    });
    public long getRecords(int N) {
        Integer totalSizeOfAllCollections =0;
      for(String nameOfCollection:map.keySet()){
          List<Integer> fileSizes= map.get(nameOfCollection);
          Optional<Integer> totalSize = fileSizes.stream().reduce((a, b)->a+b);
         if(totalSize.isPresent()){
             totalSizeOfAllCollections+=totalSize.get();
         }
         else{
             continue;
         }
         if(!nameOfCollection.equals("noCollection")) {
             priorityQueue.add(new Pair(nameOfCollection, totalSize.get()));
         }
      }
        return  totalSizeOfAllCollections;
    }

    public void saveDataToCollection(String collection, Integer fileSize){
        if(Objects.isNull(collection)){
            collection="noCollection";
        }
        List<Integer> sizeOfCollectionFiles =null;
        if(map.containsKey(collection)){
            sizeOfCollectionFiles = map.get(collection);
        } else{
            sizeOfCollectionFiles = new ArrayList<>();
        }
        sizeOfCollectionFiles.add(fileSize);
        map.put(collection,sizeOfCollectionFiles);
    }
    public static void main(String[] args) {

        Assignment assignment = new Assignment();
        String fileName="file1.text";
        Integer fileSize=100;
        String nameOfCollection=null;
        assignment.saveDataToCollection(nameOfCollection,fileSize);
        String fileName1="file2.text";
        Integer fileSize1=200;
        String nameOfCollection1="collection1";
        assignment.saveDataToCollection(nameOfCollection1,fileSize1);
        String fileName2="file3.text";
        Integer fileSize2=200;
        String nameOfCollection2="collection1";
        assignment.saveDataToCollection(nameOfCollection2,fileSize2);
        String fileName3="file4.text";
        Integer fileSize3=300;
        String nameOfCollection3="collection2";
        assignment.saveDataToCollection(nameOfCollection3,fileSize3);
        String fileName4="file5.text";
        Integer fileSize4=10;
        String nameOfCollection4=null;
        assignment.saveDataToCollection(nameOfCollection4,fileSize4);
        System.out.println(assignment.getRecords(3));
        while(!assignment.priorityQueue.isEmpty()){
            Pair data =assignment.priorityQueue.poll();
            System.out.println(data.fileName+"    "+data.totalSizeOfFile);
        }





    }
}

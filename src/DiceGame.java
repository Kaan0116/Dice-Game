import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FileIO{

    public void Read(String FileName,List list,List list1){
        try{
            FileReader File=new FileReader(FileName);
            BufferedReader Reader=new BufferedReader(File);
            String Line;
            int counter=0;
            while((Line=Reader.readLine())!=null){
                if(counter<2){
                    list1.add(Line);// Number of people and their names assigned to a list
                }else{
                    list.add(Line); //Dice numbers assigned to this list
                }
                counter++;
            }
            Reader.close();
            File.close();
        }catch(IOException e){
            System.out.println("FILE READER ERROR");
        }
    }

}


abstract class Player{
    abstract void Score();
    abstract void ScoreCalculator(int point,int point2);
    abstract int GetScore();
}
class ScoreKeeper {
    Map<String, Integer> scores;

    public ScoreKeeper() {
        scores = new HashMap<>(); //A hashmap was created to keep each person's score.
    }

    public void addScore(String element, int score) {
        scores.put(element, scores.getOrDefault(element, 0) + score); //Scores ??starting with 0 are assigned and scores ??are added with each dice
    }
    public int getScore(String element) {
        return scores.getOrDefault(element, 0); //Desired scores ??are fetched
    }
    public void resetScore(String element) {
        scores.put(element, 0); //Scores reset
    }

}

class GameTools extends Player{
    ScoreKeeper obj=new ScoreKeeper();
    int score;
    String name;
    void Score(){
        this.score=0;
    }


    void ScoreCalculator(int point,int point2){
        this.score=point+point2;


    }
    int GetScore(){
        return this.score;
    }
    public void ErrorMessage(List list){
        if (Integer.parseInt((String) list.get(0))<2){
            System.out.println("Imposible game");
        }
    }
    public void InfoMessage(List<List<String>> list,List<String> names,String outputfile){
        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter(outputfile));
            for(int i=0;i<list.size();i++){
                String name=names.get(i%names.size()); //Index numbers of the names were determined
                int First=Integer.parseInt((list.get(i)).get(0)); //First dice value
                int Second=Integer.parseInt((list.get(i)).get(1)); //Second dice value



                if((First==0)&&(Second==0)){
                    ScoreCalculator(First,Second);
                    obj.addScore(name,GetScore());
                    writer.write(name+" skipped the turn and "+name+"'s"+" score is "+obj.getScore(name)+"\n");
                }
                if(((First)==1)&&(Second!=1)){
                    writer.write(name+" threw "+(list.get(i)).get(0)+"-"+(list.get(i)).get(1) +" and "+name+"'s"+" score is "+obj.getScore(name)+"\n");
                }
                if(((First)!=1)&&(Second==1)){

                    writer.write(name+" threw "+(list.get(i)).get(0)+"-"+(list.get(i)).get(1) +" and "+name+"'s"+" score is "+obj.getScore(name)+"\n");
                }
                if(((First)==1)&&(Second==1)){
                    writer.write(name+" threw 1-1. Game over "+name+"\n");
                    names.remove(name); //In this case, this index is removed from the list.

                }
                if(((First)!=1)&&((First)!=0)&&(Second!=0)&&(Second!=1)){
                    ScoreCalculator(First,Second);
                    obj.addScore(name,GetScore());
                    writer.write(name+" threw "+(list.get(i)).get(0)+"-"+(list.get(i)).get(1) +" and "+name+"'s"+" score is "+obj.getScore(name)+"\n");
                }

            }
            if(names.size()==1){
                writer.write(names.get(0)+" is the winner of the game with the score of "+obj.getScore(names.get(0))+". Congratulations "+names.get(0)+ "!");
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}








public class DiceGame {
    public static void main(String[] args) {
        List<String>NameInfo=new ArrayList<>();
        List<String>NameInfo1=new ArrayList<>();
        List<String>UserInput =new ArrayList<>();
        List<String>MainList =new ArrayList<>();
        List<List<String>>MainList1 =new ArrayList<>();
        FileIO obj=new FileIO();
        GameTools obj1=new GameTools();

        String InputFile=args[0];
        String OutputFile=args[1];
        obj.Read(InputFile, MainList,UserInput);

        NameInfo.add(UserInput.get(1));
        UserInput.remove(1);
        for(String item:NameInfo){
            String[] splitList=item.split(",");
            for(String item1:splitList){
                NameInfo1.add(item1); //Names separated by commas added to the new list
            }
        }
        for(String item:MainList){
            String[] splitList=item.split("-");
            List<String> subList = new ArrayList<>();
            subList.add(splitList[0]);
            subList.add(splitList[1]);
            MainList1.add(subList); //Dice added to the list
        }
        obj1.InfoMessage(MainList1, NameInfo1,OutputFile);
    }
}


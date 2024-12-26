import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.datahandler.JSONDataHandler;
import com.recipeapp.ui.RecipeUI;
import java.io.*;

public class App {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Choose the file format:");
            System.out.println("1. CSV");
            System.out.println("2. JSON");
            System.out.print("Select (1/2): ");//1か2を選んでもらう
            String choice = reader.readLine();//それを文字列として受け取る
            
            //1の時のCSV modeの時の出力
            if(choice.equals("1")) {
                CSVDataHandler csvDataHandler = new CSVDataHandler();
                System.out.println("Current mode: CSV");
                System.out.println("Main Menu: ");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.println("Please choose an option:");
                String choise = reader.readLine();

                //2の時の出力
            } else if(choice.equals("2")) {
                JSONDataHandler jsonDataHandler = new JSONDataHandler();
                System.out.println("Current mode: JSON");
                System.out.println("Main Menu: ");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.println("Please choose an option:");
                String choise = reader.readLine();

                //それ以外の時
            } else {
                CSVDataHandler csvDataHandler = new CSVDataHandler();
                System.out.println("Current mode: CSV");
                System.out.println("Main Menu: ");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.println("Please choose an option:");
                String choise = reader.readLine();
            }
            

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
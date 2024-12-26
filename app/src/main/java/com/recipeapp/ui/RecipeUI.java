package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Recipe;
import com.recipeapp.model.Ingredient;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                    displayRecipes();
                        break;
                    case "2":
                    addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    //Display Recipesの実装
    private void displayRecipes() {
        try {
            ArrayList<Recipe> recipes = dataHandler.readData();

            //レシピが存在しない場合
            if (recipes.isEmpty()) {
                System.out.println("No recipes available.");
                return;
            }

            //レシピ名の出力
            System.out.println("Recipes:");
            for (Recipe recipe : recipes) {
                System.out.println("-----------------------------------");
                System.out.println("Recipe Name: " + recipe.getName());
                System.out.print("Main Ingredients: ");
                
                //材料をカンマで区切って出力
                ArrayList<Ingredient> ingredients = recipe.getIngredients();
                for (int i = 0; i < ingredients.size(); i++) {
                    System.out.print(ingredients.get(i).getName());
                    //最後以外にカンマつける
                    if (i < ingredients.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }

            


        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
    }
    }

    //addNewRecipeの実装
    private void addNewRecipe() {
        try {
            System.out.println("Adding a new recipe.");
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();

            System.out.println("Enter ingredients (type 'done' when finished):");
            ArrayList<Ingredient> ingredients = new ArrayList<>();

            //宣言したArrayListに格納していく
            while (true) {
                System.out.print("Ingredients: ");
                String input = reader.readLine();
                if (input.equals("done")) {
                    break;//doneの時に終了する
                }
                ingredients.add(new Ingredient(input));
            }

            //ファイルに入力したデータを書き込む
            //recipeオブジェクトを作成
            Recipe recipe = new Recipe(recipeName, ingredients);
            dataHandler.writeData(recipe);

            System.out.println("Recipe add successfully");

        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage());
        }
    }
}


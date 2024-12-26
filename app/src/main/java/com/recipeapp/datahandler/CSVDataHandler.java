package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath;

    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMode() {
        return "CSV";
    }

    @Override
    public ArrayList<Recipe> readData() throws IOException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            //1行ずつ読み込む
            while ((line = reader.readLine()) != null) {
                //レシピ名と材料名を分ける
                String[] parts = line.split(",", 2);
                String recipeName = parts[0];//これがレシピ名
                //次に材料をカンマで区切る
                String[] ingredientParts = parts[1].split(",");
                //材料を格納するArrayListを宣言
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                //拡張for文を使って格納
                for (String intgredient : ingredientParts) {
                    ingredients.add(new Ingredient(intgredient));
                }
                //recipesにレシピ名と材料を格納する
                recipes.add(new Recipe(recipeName, ingredients));

            }
        }
        return recipes;
    }
    

    @Override
    public void writeData(Recipe recipe) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            //レシピ名を文字列にする
            String recipeLine = recipe.getName();

            //材料をカンマ区切りで追加
            for (Ingredient ingredient : recipe.getIngredients()) {
                recipeLine += "," + ingredient.getName();
            }

            //ファイルに書き込む
            writer.write(recipeLine);
            writer.newLine();
        }
    }

    @Override
    public ArrayList<Recipe> serchData(String keybord) throws IOException {
        return null;
    }
    
}

package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            Sandwich sandwich = new Sandwich();

            JSONObject root = new JSONObject(json);

            // parse names
            JSONObject nameObj = root.getJSONObject("name");
            sandwich.setMainName(nameObj.getString("mainName"));

            List<String> alsoKnownAsList = new ArrayList<>();
            JSONArray alsoKnownAsArr = nameObj.getJSONArray("alsoKnownAs");
            for (int i = 0; i < alsoKnownAsArr.length(); i++) {
                alsoKnownAsList.add(alsoKnownAsArr.getString(i));
            }
            sandwich.setAlsoKnownAs(alsoKnownAsList);

            // parse place of origin
            sandwich.setPlaceOfOrigin(root.getString("placeOfOrigin"));

            // parse description
            sandwich.setDescription(root.getString("description"));

            // parse image URL
            sandwich.setImage(root.getString("image"));

            // parse ingredients
            List<String> ingredientsList = new ArrayList<>();
            JSONArray ingredientsArr = root.getJSONArray("ingredients");
            for (int i = 0; i < ingredientsArr.length(); i++) {
                ingredientsList.add(ingredientsArr.getString(i));
            }
            sandwich.setIngredients(ingredientsList);

            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}

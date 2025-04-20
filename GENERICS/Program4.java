import java.util.*;

interface MealPlan {
    String getPlanName();
    List<String> getMeals();
}

class VegetarianMeal implements MealPlan {
    public String getPlanName() {
        return "Vegetarian";
    }
    public List<String> getMeals() {
        return Arrays.asList("Paneer Curry", "Mixed Veg", "Dal Tadka");
    }
}

class VeganMeal implements MealPlan {
    public String getPlanName() {
        return "Vegan";
    }
    public List<String> getMeals() {
        return Arrays.asList("Vegan Salad", "Tofu Stir Fry", "Lentil Soup");
    }
}

class KetoMeal implements MealPlan {
    public String getPlanName() {
        return "Keto";
    }
    public List<String> getMeals() {
        return Arrays.asList("Grilled Chicken", "Zucchini Noodles", "Avocado Salad");
    }
}

class HighProteinMeal implements MealPlan {
    public String getPlanName() {
        return "High-Protein";
    }
    public List<String> getMeals() {
        return Arrays.asList("Protein Shake", "Egg Omelette", "Grilled Fish");
    }
}

class Meal<T extends MealPlan> {
    private T mealPlan;
    public Meal(T mealPlan) {
        this.mealPlan = mealPlan;
    }
    public T getMealPlan() {
        return mealPlan;
    }
}

public class Program4 {
    public static <T extends MealPlan> void generateMealPlan(Meal<T> meal) {
        System.out.println("Meal Plan: " + meal.getMealPlan().getPlanName());
        for (String dish : meal.getMealPlan().getMeals()) {
            System.out.println("- " + dish);
        }
    }

    public static void main(String[] args) {
        Meal<VegetarianMeal> veg = new Meal<>(new VegetarianMeal());
        Meal<VeganMeal> vegan = new Meal<>(new VeganMeal());
        Meal<KetoMeal> keto = new Meal<>(new KetoMeal());
        Meal<HighProteinMeal> protein = new Meal<>(new HighProteinMeal());

        generateMealPlan(veg);
        generateMealPlan(vegan);
        generateMealPlan(keto);
        generateMealPlan(protein);
    }
}

package practiceproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies
 *
 */
public class FindAllPossibleRecipes {


    public List<String> findAllRecipesBruteForce(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> supply= new HashSet<>(List.of(supplies));
        List<String> result= new ArrayList<>();


        for(int i=0;i<recipes.length;i++){
            boolean contains = true;
            for(int j=0;j<ingredients.get(i).size();j++){

                if(!supply.contains(ingredients.get(i).get(j))){
                    contains = false;
                    break;
                }

            }
            if(contains && supply.add(recipes[i])) {
                result.add(recipes[i]);
                i=-1;
                //previous recipe which was short of ingredients may be created now. Therefore, we need to scan from the beginning to search again.
            }
        }
        return result;
    }

    /**
     * tricky Topological
     *
     *  Modified Topological sort:
     *          if all the ingredients of a recipe is found in supplies then it's indegree is 0
     *
     *          if one the ingredients is not found then create a dependency list like below and increment it's indegree to number of missing supplies
     *              ex if burger is in short of 2 recipes then it's indegree would be 2
     *
     *            // [bread    -> [sandwich,burger]
     *            //  sandwich -> [burger] ]
     *
     *          enqueue the indegree 0 and start processing
     *
     *          if the indegree 0's reciepe is found in the dependency list we got the missing reciepe, start processing it
     */
    public List<String> findAllRecipesTopological(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        // Map for storing the recipes indexes
        HashMap<String, Integer> recMap = new HashMap<>();

        // Graph for vertices as the recipes and all the dependent recipes on them
        HashMap<String, ArrayList<String>> graph = new HashMap<>();

        // Number of recipes
        int n = recipes.length;

        // Set of all the supplies we have
        HashSet<String> sup = new HashSet<>();

        // Maintain the list of all the available supplies with us
        Collections.addAll(sup, supplies);

        // Maintain the index for all the recipes
        for (int i = 0; i < n; i++) {
            recMap.put(recipes[i], i);
        }

        // indegree for all receipes
        int[] indegree = new int[n];

        // Traverse through all the receipes and the ingredients required by them
        for (int i = 0; i < n; i++) {
            //[["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]]
            //supplies = ["yeast","flour","meat"]
            for (String str : ingredients.get(i)) {

                // If one of the ingredient is not present in supply then it is dependent on a recipes
                if (!sup.contains(str)) {
                    // Add this particular receipe as a dependent recipe
                    //recipes = ["bread","sandwich","burger"]
                    graph.computeIfAbsent(str, x->new ArrayList<>()).add(recipes[i]);
                    // [bread    -> [sandwich,burger]
                    //  sandwich -> [burger] ]

                    // Increment the indegree of the dependent recipe
                    indegree[i]++;
                }
            }
        }

        // Queue for doing Topological Sorting
        Queue<String> q = new LinkedList<>();

        // All the recipes that have indegree as 0
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(recipes[i]);
            }
        }

        // List of all the recipes that can be created
        List<String> ans = new ArrayList<>();

        // Until there are nodes traverse it
        while (!q.isEmpty()) {
            // Peek a node from the queue
            String node = q.poll();
            // Add it to the list of recipes that can be created
            ans.add(node);

            // Traverse through all the dependent recipes and add them to queue if indegree is 0
            if (graph.containsKey(node)) {
                // [bread    -> [sandwich,burger]
                //  sandwich -> [burger] ]
                for (String str : graph.get(node)) {

                    indegree[recMap.get(str)]--;
                    if (indegree[recMap.get(str)] == 0)
                        q.add(str);
                }
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        System.out.println(new FindAllPossibleRecipes().findAllRecipesTopological(new String[]{"bread","sandwich","burger"},
                List.of(List.of("yeast","flour"),List.of("bread","meat"),List.of("sandwich","meat","bread")),new String[]{"yeast","flour","meat"}));
    }
}

import { Component, OnInit } from '@angular/core';
import {Recipe} from "../../models/recipe";
import {RecipeService} from "../../service/recipe.service";

@Component({
  selector: 'app-planned-recipes',
  templateUrl: './planned-recipes.component.html',
  styleUrls: ['./planned-recipes.component.scss']
})
export class PlannedRecipesComponent implements OnInit {
  plannedRecipeList: Array<Recipe>;

  constructor(private recipeService: RecipeService) { }

  ngOnInit(): void {
    this.recipeService.getRefreshNeeded
      .subscribe(() => this.getAllPlannedRecipes());
  }

  getAllPlannedRecipes() {
    this.recipeService.getPlannedRecipes()
      .subscribe(recipes => this.plannedRecipeList = recipes);
  }
}

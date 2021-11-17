import {Component, Input, OnInit} from '@angular/core';
import {RecipeService} from "../../service/recipe.service";
import {Recipe} from "../../models/recipe";

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.scss']
})
export class RecipesComponent implements OnInit {

  recipesList: Array<Recipe>;

  constructor(private recipeService: RecipeService) { }

  ngOnInit(): void {
    this.recipeService.getAllRecipes().subscribe(recipes => {
      this.recipesList = recipes;
    })
  }

  showDetails(): void {

  }

  hideDetails(recipe: Recipe) {
    console.log(recipe.detailsVisible.valueOf());
  }
}

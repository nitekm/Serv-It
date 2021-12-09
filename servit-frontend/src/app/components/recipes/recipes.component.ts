import {Component, OnInit} from '@angular/core';
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
    this.recipeService.getRefreshNeeded
      .subscribe(() => this.getAllRecipes());
    this.getAllRecipes();
  }

  handlePlannedClick() {
    window.scroll(0,0);
  }

  getAllRecipes() {
    this.recipeService.getAllRecipes()
      .subscribe(recipes => this.recipesList = recipes);
  }
}

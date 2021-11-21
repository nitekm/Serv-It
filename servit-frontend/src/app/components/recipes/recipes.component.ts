import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {RecipeService} from "../../service/recipe.service";
import {Recipe} from "../../models/recipe";
import {Router} from "@angular/router";

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.scss']
})
export class RecipesComponent implements OnInit {

  recipesList: Array<Recipe>;

  constructor(private recipeService: RecipeService, private router: Router) { }

  ngOnInit(): void {
    this.recipeService.getRefreshNeeded
      .subscribe(() => this.getAllRecipes());
    this.getAllRecipes();
  }

  getAllRecipes() {
    this.recipeService.getAllRecipes()
      .subscribe(recipes => this.recipesList = recipes);
  }

  onAddNewRecipeClick() {
    this.router.navigate(['add-recipe']);
  }
}

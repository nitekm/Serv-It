import {Component, OnInit} from '@angular/core';
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
  display = 'none';

  constructor(private recipeService: RecipeService) { }


  ngOnInit(): void {
    this.recipeService.getRefreshNeeded
      .subscribe(() => this.getAllRecipes());
    this.getAllRecipes();
  }

  openModal() {
    this.display = 'block';
  }

  onCloseHandled() {
    this.display = 'none';
  }

  getAllRecipes() {
    this.recipeService.getAllRecipes()
      .subscribe(recipes => this.recipesList = recipes);
  }
}

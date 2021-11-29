import { Component, OnInit } from '@angular/core';
import {Recipe} from "../../models/recipe";
import {RecipeService} from "../../service/recipe.service";
import {IngredientListComponent} from "../ingredient-list/ingredient-list.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-planned-recipes',
  templateUrl: './planned-recipes.component.html',
  styleUrls: ['./planned-recipes.component.scss']
})
export class PlannedRecipesComponent implements OnInit {
  plannedRecipeList: Array<Recipe>;

  constructor(private recipeService: RecipeService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.recipeService.getRefreshNeeded
      .subscribe(() => this.getAllPlannedRecipes());
    this.getAllPlannedRecipes();
  }

  open() {
    const modalRef = this.modalService.open(IngredientListComponent);
    modalRef.componentInstance.name = 'ingredients';
  }

  getAllPlannedRecipes() {
    this.recipeService.getPlannedRecipes()
      .subscribe(recipes => this.plannedRecipeList = recipes);
  }
}

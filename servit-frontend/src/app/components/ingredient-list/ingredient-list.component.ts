import { Component, OnInit } from '@angular/core';
import {Ingredient} from "../../models/ingredient";
import {IngredientService} from "../../service/ingredient.service";

@Component({
  selector: 'app-ingredient-list',
  templateUrl: './ingredient-list.component.html',
  styleUrls: ['./ingredient-list.component.scss']
})
export class IngredientListComponent implements OnInit {
  ingredientList: Array<Ingredient>;

  constructor(private ingredientService: IngredientService) {}

  ngOnInit(): void {
    this.getPlannedRecipes();
  }

  getPlannedRecipes() {
    this.ingredientService.getPlannedIngredients()
      .subscribe(ingredients => this.ingredientList = ingredients);
    console.log(this.ingredientList);
  }
}

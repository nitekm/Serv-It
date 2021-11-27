import {Component, OnInit} from '@angular/core';
import {Ingredient} from "../../models/ingredient";
import {IngredientService} from "../../service/ingredient.service";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-ingredient-list',
  templateUrl: './ingredient-list.component.html',
  styleUrls: ['./ingredient-list.component.scss']
})
export class IngredientListComponent implements OnInit {
  ingredientList: Array<Ingredient>;

  constructor(private ingredientService: IngredientService, public activeModal: NgbActiveModal) {}

  ngOnInit(): void {
    this.getPlannedIngredients();
  }

  getPlannedIngredients() {
    this.ingredientService.getPlannedIngredients()
      .subscribe(ingredients => this.ingredientList = ingredients);
    console.log(this.ingredientList);
  }
}

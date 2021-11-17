import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Recipe} from "../../../models/recipe";

@Component({
  selector: 'app-single-recipe',
  templateUrl: './single-recipe.component.html',
  styleUrls: ['./single-recipe.component.scss']
})
export class SingleRecipeComponent implements OnInit {

  @Input()
  recipe: Recipe;

  @Output()
  detailsEvent = new EventEmitter<Recipe>();

  constructor() { }

  ngOnInit(): void {
    this.recipe.detailsVisible = false;
  }

  hideDetails(): void {
    this.recipe.detailsVisible = false;
    this.detailsEvent.emit(this.recipe);
  }

}

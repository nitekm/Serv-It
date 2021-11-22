import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Recipe} from "../../../models/recipe";
import {RecipeService} from "../../../service/recipe.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-single-recipe',
  templateUrl: './single-recipe.component.html',
  styleUrls: ['./single-recipe.component.scss']
})
export class SingleRecipeComponent implements OnInit {

  @Input()
  recipe: Recipe;

  detailsVisible: boolean = false;

  constructor(private recipeService: RecipeService, private router: Router) { }

  ngOnInit(): void {
    this.recipe.detailsVisible = false;
  }

  toggleDetailsView(): void {
    this.detailsVisible = !this.detailsVisible;
  }

  onPlanClick(id: number) {
    this.recipeService.togglePlanned(id);
  }

  onDeleteClick(id: number) {
    this.recipeService.deleteRecipe(id);
  }

  onEditClick() {
    this.recipeService.passRecipe(this.recipe);
    this.router.navigate(['/add-recipe']);
  }

}

import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {RecipeService} from "../../service/recipe.service";
import {Recipe} from "../../models/recipe";
import {Ingredient} from "../../models/ingredient";
import {Step} from "../../models/step";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.scss']
})
export class AddRecipeComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private recipeService: RecipeService, private router: Router) { }
  recipeForm: FormGroup;

  ngOnInit(): void {
    this.recipeForm = this.formBuilder.group({
      name: [''],
      timeToPrepare: [''],
      ingredients: this.formBuilder.array([]),
      steps: this.formBuilder.array([])
    });
  }

  addIngredient() {
    this.ingredients.push(this.formBuilder.control(''));
  }

  removeIngredient() {
    this.ingredients.removeAt(this.ingredients.length-1);
  }

  addStep() {
    this.steps.push(this.formBuilder.control(''));
  }

  removeStep() {
    this.steps.removeAt(this.steps.length-1);
  }

  get name() {
    return this.recipeForm.get('name');
  }

  get timeToPrepare() {
    return this.recipeForm.get('timeToPrepare');
  }

  get ingredients(): FormArray {
    return this.recipeForm.get('ingredients') as FormArray;
  }

  get steps(): FormArray {
    return this.recipeForm.get('steps') as FormArray;
  }

  createRecipe(): Recipe {
    // create Array<Ingredient> from formGroup ingredients to create recipe object with those later on
    let ingredients = new Array<Ingredient>();
    this.recipeForm.value.ingredients
      .forEach((ingredient: string) => {
        ingredients.push(new Ingredient(ingredient));
      })

    let steps = new Array<Step>();
    this.recipeForm.value.steps
      .forEach((step: string) => {
        steps.push(new Step(step));
      })

    let recipe = new Recipe(
      this.recipeForm.value.name,
      this.recipeForm.value.timeToPrepare,
      ingredients,
      steps
    );

    return recipe;
  }

  onSubmit() {
    this.recipeService.addRecipe(this.createRecipe());
    this.router.navigate(['/recipes']);
  }
}

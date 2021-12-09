import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RecipeService} from "../../service/recipe.service";
import {Recipe} from "../../models/recipe";
import {Ingredient} from "../../models/ingredient";
import {Step} from "../../models/step";
import {Router} from "@angular/router";
import {CustomValidatorService} from "src/app/service/custom-validator.service";

@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.scss']
})
export class AddRecipeComponent implements OnInit, OnDestroy {

  constructor(private formBuilder: FormBuilder,
              private recipeService: RecipeService,
              private router: Router,
              private customValidator: CustomValidatorService) { }
  recipeForm: FormGroup;
  recipe: Recipe;
  submitted = false;
  edited: boolean = false;

  ngOnInit(): void {
    this.recipe = this.recipeService.recipe;
    if (this.recipe != undefined) {
      this.edited = true;

      let ingredientArray = new Array<string>();
      this.recipe.ingredients.forEach(ingredient =>
      ingredientArray.push(ingredient.name));

      let stepArray = new Array<string>();
      this.recipe.steps.forEach(step =>
        stepArray.push(step.description));

      this.recipeForm = this.formBuilder.group({
        name: [this.recipe.name, Validators.required],
        timeToPrepare: [this.recipe.timeToPrepare, [Validators.required, this.customValidator.timeToPrepareValidator()]],
        ingredients: this.formBuilder.array(ingredientArray),
        steps: this.formBuilder.array(stepArray),
      });
    } else {
      this.recipeForm = this.formBuilder.group({
        name: ['', Validators.required],
        timeToPrepare: ['', [Validators.required, this.customValidator.timeToPrepareValidator()]],
        ingredients: this.formBuilder.array([]),
        steps: this.formBuilder.array([])
      });
    }
  }

  get recipeFormControl() {
    return this.recipeForm.controls;
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
    return new Recipe(
      this.recipeForm.value.name,
      this.recipeForm.value.timeToPrepare,
      ingredients,
      steps
    );
  }

  onSubmit() {
    this.submitted = true;
    if (this.edited) {
      this.recipeService.editRecipe(this.recipe.id, this.createRecipe())
    }
     else {
      this.recipeService.addRecipe(this.createRecipe());
    }
    this.router.navigate(['/recipes']);
  }

  ngOnDestroy(): void {
    this.recipeService.recipeUnassign();
  }
}

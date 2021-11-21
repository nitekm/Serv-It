import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { AddRecipeComponent } from './components/add-recipe/add-recipe.component';
import {HttpClientModule} from "@angular/common/http";
import { SingleRecipeComponent } from './components/recipes/single-recipe/single-recipe.component';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {ReactiveFormsModule} from "@angular/forms";
import { IngredientListComponent } from './components/ingredient-list/ingredient-list.component';

@NgModule({
  declarations: [
    AppComponent,
    RecipesComponent,
    AddRecipeComponent,
    SingleRecipeComponent,
    IngredientListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FontAwesomeModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

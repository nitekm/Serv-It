import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AddRecipeComponent} from "./components/add-recipe/add-recipe.component";
import {AppComponent} from "./app.component";
import {RecipesComponent} from "./components/recipes/recipes.component";

const routes: Routes = [
  {path: '', redirectTo: '/recipes', pathMatch: 'full'},
  {path: 'recipes', component: RecipesComponent},
  {path: 'add-recipe', component: AddRecipeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  bootstrap: [AppComponent]
})
export class AppRoutingModule { }

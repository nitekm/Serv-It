import { Injectable } from '@angular/core';
import {Recipe} from "../models/recipe";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Endpoints} from "../shared/endpoints";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RecipeService {
  url: string = environment.baseUrl + Endpoints.RECIPES;

  constructor(private http: HttpClient) { }

  getAllRecipes(): Observable<Array<Recipe>> {
    return this.http.get<Array<Recipe>>(this.url);
  }

  getSingleRecipe(id: number): Observable<Recipe> {
    return this.http.get<Recipe>(this.url + 'id')
  }
}

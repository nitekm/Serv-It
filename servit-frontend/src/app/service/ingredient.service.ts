import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Recipe} from "../models/recipe";
import {environment} from "../../environments/environment";
import {Endpoints} from "../shared/endpoints";

@Injectable({
  providedIn: 'root'
})
export class IngredientService {
  url: string = environment.baseUrl + Endpoints.INGREDIENTS;

  constructor(private httpClient: HttpClient) { }

  getPlannedIngredients(): Observable<Array<Recipe>> {
    return this.httpClient.get<Array<Recipe>>(this.url+'planned');
  }
}

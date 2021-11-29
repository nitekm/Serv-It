import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {Recipe} from "../models/recipe";
import {environment} from "../../environments/environment";
import {Endpoints} from "../shared/endpoints";

@Injectable({
  providedIn: 'root'
})
export class IngredientService {
  url: string = environment.baseUrl + Endpoints.INGREDIENTS;
  private refreshNeeded = new Subject<void>();

  constructor(private httpClient: HttpClient) { }

  get getRefreshNeeded() {
    return this.refreshNeeded;
  }

  getPlannedIngredients(): Observable<Array<Recipe>> {
    return this.httpClient.get<Array<Recipe>>(this.url+'planned');
  }

  createAndSendTasks() {
    return this.httpClient.post<any>(this.url + 'toList', {});
  }

  toggleIngredientPlanned(id: number) {
    return this.httpClient.patch(this.url + 'planned/' + id, {})
      .subscribe(() => this.getRefreshNeeded.next());
  }
}

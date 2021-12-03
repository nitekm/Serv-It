import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, Subject, throwError} from "rxjs";
import {Recipe} from "../models/recipe";
import {environment} from "../../environments/environment";
import {Endpoints} from "../shared/endpoints";
import {ToastService} from "src/app/service/toast.service";

@Injectable({
  providedIn: 'root'
})
export class IngredientService {
  url: string = environment.baseUrl + Endpoints.INGREDIENTS;
  private refreshNeeded = new Subject<void>();

  constructor(private httpClient: HttpClient, private toast: ToastService) { }

  get getRefreshNeeded() {
    return this.refreshNeeded;
  }

  getPlannedIngredients() {
    return this.httpClient.get<Array<Recipe>>(this.url + Endpoints.PLANNED)
      .pipe(
        catchError((err => {
            this.toast.toastError()
            return throwError(err);
          })
        )
      );
  }

  createAndSendTasks() {
    return this.httpClient.post<any>(this.url + Endpoints.TOLIST, {})
      .pipe(
        catchError((err => {
          this.toast.toastError()
          return throwError(err);
        })
        )
      );
  }

  toggleIngredientPlanned(id: number) {
    return this.httpClient.patch(this.url + Endpoints.PLANNED + id, {})
      .subscribe(() => this.getRefreshNeeded.next(),
        error => this.toast.toastError());
  }
}

import {Injectable} from '@angular/core';
import {Recipe} from "../models/recipe";
import {HttpClient} from "@angular/common/http";
import {environment} from "src/environments/environment";
import {Endpoints} from "../shared/endpoints";
import {catchError, Observable, Subject, throwError} from "rxjs";
import {ToastService} from "src/app/service/toast.service";

@Injectable({
  providedIn: 'root'
})
export class RecipeService {
  url: string = environment.baseUrl + Endpoints.RECIPES;
  private recipeToEdit: Recipe;
  private refreshNeeded = new Subject<void>();
  errorOccurred = new Subject<void>();

  constructor(private http: HttpClient, private toast: ToastService) { }

  get getRefreshNeeded() {
    return this.refreshNeeded;
  }

  get recipe(): Recipe {
    return this.recipeToEdit;
  }

  passRecipe(recipe: Recipe): Recipe {
    this.recipeToEdit = recipe;
    return this.recipeToEdit;
  }

  recipeUnassign(): void {
    this.recipeToEdit = undefined;
  }

  getAllRecipes(): Observable<Array<Recipe>> {
    return this.http.get<Array<Recipe>>(this.url)
      .pipe(
        catchError((err => {
            this.toast.toastError()
            return throwError(err);
          })
        )
      );
  }

  getPlannedRecipes() {
    return this.http.get<Array<Recipe>>(this.url + Endpoints.PLANNED)
      .pipe(
        catchError((err => {
            this.toast.toastError()
            return throwError(err);
          })
        )
      );
  }

  getSingleRecipe(id: number): Observable<Recipe> {
    return this.http.get<Recipe>(this.url + 'id')
  }

  addRecipe(recipe: Recipe) {
    return this.http.post<Recipe>(this.url, recipe, {})
      .subscribe(() => {
        this.refreshNeeded.next();
        this.toast.toastSuccess();
      },
      error => this.toast.toastError()
      );
  }

  editRecipe(id: number, recipe: Recipe): Observable<Recipe> {
    return this.http.put<any>(this.url + id, recipe);
  }

  deleteRecipe(id: number): void {
    this.http.delete(this.url + id, {})
      .subscribe(() => {
        this.refreshNeeded.next();
        this.toast.toastSuccess();
      },
        error => this.toast.toastError()
      );
  }

  togglePlanned(id: number) {
    this.http.patch(this.url + id, {}, {})
      .subscribe(() => {
        this.refreshNeeded.next()
      },
        error => this.toast.toastError()
      );
  }
}

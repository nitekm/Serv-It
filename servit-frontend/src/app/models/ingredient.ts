import {Recipe} from "./recipe";

export class Ingredient {
  id: number;
  name: string;
  planned: boolean;
  recipe?: Recipe;

  constructor(name: string) {
    this.name = name;
  }
}

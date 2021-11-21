import {Recipe} from "./recipe";

export class Step {
  id: number;
  description: string;
  recipe?: Recipe;

  constructor(description: string) {
    this.description = description;
  }
}

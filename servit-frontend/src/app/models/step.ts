import {Recipe} from "./recipe";

export interface Step {
  id: number;
  description: string;
  recipe: Recipe;
}

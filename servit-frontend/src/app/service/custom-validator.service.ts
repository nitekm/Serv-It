import {Injectable} from '@angular/core';
import {AbstractControl, ValidatorFn} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class CustomValidatorService {

  constructor() {}

  timeToPrepareValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } => {
      if (!control.value) {
        return null;
      }
      const regex = new RegExp('^\\d:[0-5]\\d$');
      const valid = regex.test(control.value)
      return valid ? null : {invalidTime: true};
    }
  }
}

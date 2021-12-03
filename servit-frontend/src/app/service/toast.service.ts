import { Injectable } from '@angular/core';
import {ToastrService} from "ngx-toastr";

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(private toast: ToastrService) { }

  toastSuccess() {
    this.toast.success('Operacja zakończona powodzeniem!', 'Suckes!')
  }

  toastError() {
    this.toast.error('Operacja zakończona niepowodzeniem', 'Błąd!')
  }
}

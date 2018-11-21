import { Injectable } from '@angular/core';

import { Http } from '@angular/http';

import { Transaction } from '../shared/transaction';
import { error } from 'util';
import { Payment } from '../shared/Payment';

@Injectable()
export class PaymentService {

  constructor(private http: Http) {}

  postTransaction(transaction: Transaction) {
     return this.http.post('http://localhost:8300/api/payment/transaction/', transaction).toPromise();
  }

  checkPaymentValidity(paymentUrl: string, merchantId: string, merchantOrderId: string): Promise<Payment> {
    return this.http.get('http://localhost:8300/api/payment/check/url?paymentUrl=' + paymentUrl +
     '&merchantId=' + merchantId + '&merchantOrderId=' + merchantOrderId).
    toPromise().then(response => { console.log(response.json()); return response.json() as Payment; }).catch(this.handleCheckLinkError);
  }

  private handleCheckLinkError(error: any): Promise<any> {
    return Promise.apply(window.location.href = 'https://localhost:4200/invalid-link');
  }
}

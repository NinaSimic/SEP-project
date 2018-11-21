import { Component, OnInit, Directive, forwardRef, Attribute,
  OnChanges, SimpleChanges, Input } from '@angular/core';
import {Router, ActivatedRoute, Params} from '@angular/router';
import { PaymentService } from '../service/payment.service';
import { Transaction } from '../shared/Transaction';
import { Payment } from '../shared/Payment';

@Component({
selector: 'app-payment',
templateUrl: './payment.component.html',
styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

constructor(private paymentService: PaymentService, private activatedRoute: ActivatedRoute, private router: Router) { }

holder: string;
expiryMonth: string;
expiryYear: string;
securityCode: string;
pan: string;

payment: Payment;

successfulTransaction: boolean;
transactionNotification: boolean;

transaction: Transaction = {
pan: '',
securityCode: '',
holder: '',
validityDate: '',
amount: '',
paymentId: 0
};

submitted = false;

ngOnInit() {
this.activatedRoute.params.subscribe((params: Params) => {
const paymentUrl = params['payment_url'];
const merchantId = params['merchant_id'];
const merchantOrderId = params['merchant_order_id'];
this.paymentService.checkPaymentValidity(paymentUrl, merchantId, merchantOrderId)
  .then(payment =>  {this.payment = payment; } );
});
}

postTransaction() {
this.transaction.validityDate = this.expiryYear + '-' + this.expiryMonth;

this.transaction.amount = this.payment.amount.toString();
this.transaction.paymentId = this.payment.id;

this.paymentService.postTransaction(this.transaction).then();
}

onSubmit() { this.submitted = true; }

}


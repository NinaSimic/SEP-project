export class Transaction {
    constructor(pan: string, securityCode: string, holder: string,
        validityDate: string, amount: string, paymentId: number) {
           this.pan = pan;
           this.securityCode = securityCode;
           this.amount = amount;
           this.validityDate = validityDate;
           this.holder = holder;
           this.paymentId = paymentId;
   }

   pan: string;
   securityCode: string;
   holder: string;
   validityDate: string;
   amount: string;
   paymentId: number;
}

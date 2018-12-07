import { PaymentStatus } from './payment-status';
import { PaymentType } from './payment-type';

export class Payment {
    id: number;
    merchantId: string;
    merchantPassword: string;
    merchantOrderId: string;
    amount: number;
    date: Date;
    status: PaymentStatus;
    type: PaymentType;
    paymentLink: string;
    active: boolean;
}

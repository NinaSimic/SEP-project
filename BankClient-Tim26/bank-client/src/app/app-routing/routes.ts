import { Routes } from '@angular/router';

import { PaymentComponent} from '../payment/payment.component';
import { InvalidLinkComponent } from '../invalid-link/invalid-link.component';


export const routes: Routes = [
    { path: 'payment/:payment_url/:merchant_id/:merchant_order_id', component: PaymentComponent},
    { path: 'invalid-link', component: InvalidLinkComponent}
];

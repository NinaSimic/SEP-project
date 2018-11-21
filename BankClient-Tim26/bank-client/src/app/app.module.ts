import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal';


import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { PaymentComponent } from './payment/payment.component';

import { AppRoutingModule} from './app-routing/app-routing.module';
import { PaymentService } from './service/payment.service';
import { BankService } from './service/bank.service';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { InvalidLinkComponent } from './invalid-link/invalid-link.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    PaymentComponent,
    InvalidLinkComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    AppRoutingModule,
    FormsModule,
    ModalModule.forRoot(),
    NgbModule.forRoot()
  ],
  providers: [
    PaymentService,
    BankService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

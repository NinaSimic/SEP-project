import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { BehaviorSubject, Observable } from 'rxjs';

import { Bank } from '../shared/Bank';

import 'rxjs/add/operator/toPromise';

import { Output, EventEmitter } from '@angular/core';

@Injectable()
export class BankService {

  constructor(private http: Http) { }

  private currentBank = new BehaviorSubject<boolean>(false);
  currentState = this.currentBank.asObservable;

  private getIdOfBank = new BehaviorSubject<number>(0);
  currentId = this.getIdOfBank.asObservable;

  @Output() getCurrentId: EventEmitter<any> = new EventEmitter;

  changeState(specific: boolean) {
    this.currentBank.next(specific);
  }

  updateId(id: number) {
    this.getIdOfBank.next(id);
    this.getCurrentId.emit(id);
  }

  getBanka(id: number): Promise<Bank> {
    return this.http.get('/api/bank/' + id)
      .toPromise().then(response => response.json() as Bank)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
      console.error('An error occured: ', error);
      return Promise.reject(error.message || error);
  }

}

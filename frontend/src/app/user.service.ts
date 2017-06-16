import { Injectable } from '@angular/core';
import { Http, RequestOptions } from '@angular/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

import { UserModel } from './models/user.model';

@Injectable()
export class UserService {

  userEvents = new BehaviorSubject<UserModel | null>(null);

  constructor(private http: Http, private requestOptions: RequestOptions) {
    this.retrieveUser();
  }

  authenticate(credentials: { login: string; password: string }) {
    return this.http.post('/api/authentication', credentials)
      .map(response => response.json())
      .do(user => this.storeLoggedInUser(user));
  }

  storeLoggedInUser(user: UserModel) {
    window.localStorage.setItem('rememberMe', JSON.stringify(user));
    this.requestOptions.headers.set('Authorization', `Bearer ${user.token}`);
    this.userEvents.next(user);
  }

  retrieveUser() {
    const value = window.localStorage.getItem('rememberMe');
    if (value) {
      const user: UserModel = JSON.parse(value);
      this.requestOptions.headers.set('Authorization', `Bearer ${user.token}`);
      this.userEvents.next(user);
    }
  }

  logout() {
    this.userEvents.next(null);
    window.localStorage.removeItem('rememberMe');
    this.requestOptions.headers.delete('Authorization');
  }

  isLoggedIn(): boolean {
    return !!window.localStorage.getItem('rememberMe');
  }
}

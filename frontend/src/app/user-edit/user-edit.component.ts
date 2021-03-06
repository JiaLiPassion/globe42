import { Component, OnInit } from '@angular/core';
import { UserCommand } from '../models/user.command';
import { UserWithPasswordModel } from '../models/user-with-password.model';
import { ActivatedRoute, Router } from '@angular/router';
import { UserModel } from '../models/user.model';
import { ErrorService } from '../error.service';
import { UserService } from '../user.service';

@Component({
  selector: 'gl-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.scss']
})
export class UserEditComponent implements OnInit {

  editedUser: UserModel;
  createdUser: UserWithPasswordModel;

  user: UserCommand;

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private router: Router,
              private errorService: ErrorService) { }

  ngOnInit() {
    this.editedUser = this.route.snapshot.data['user'];
    this.user = this.editedUser ? { login: this.editedUser.login, admin: this.editedUser.admin } : {
      login: '',
      admin: false
    };
  }

  save() {
    if (this.editedUser) {
      this.userService.update(this.editedUser.id, this.user).subscribe(
        () => this.router.navigate(['/users']),
        this.errorService.functionalErrorHandler());
    } else {
      this.userService.create(this.user).subscribe(
        createdUser => this.createdUser = createdUser,
        this.errorService.functionalErrorHandler());
    }
  }
}

import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UsersComponent } from './users/users.component';
import { HomeComponent } from './users/home/home.component';

export const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'users',
    component: UsersComponent,
    children: [
      {
        path: 'home',
        component: HomeComponent
      },
    ]
  },
  {
    path: '**',
    pathMatch: 'full',
    redirectTo: '/login'
  }
];

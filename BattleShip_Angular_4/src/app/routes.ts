import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UsersComponent } from './users/users.component';
import { HomeComponent } from './users/home/home.component';
import { NewGameComponent } from './games/newgame/newgame.component';
import { TestPannelComponent } from './games/battleship/testpannel/testpannel.component';

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
    path: 'game',
    children: [
      {
        path: 'new',
        component: NewGameComponent
      },
      {
        path: 'my',
        component: TestPannelComponent
      },
    ]
  },
  {
    path: '**',
    pathMatch: 'full',
    redirectTo: '/login'
  }
];

import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UsersComponent } from './users/users.component';
import { HomeComponent } from './users/home/home.component';
import { NewGameComponent } from './games/newgame/newgame.component';
import { TestPannelComponent } from './games/battleship/testpannel/testpannel.component';
import { LeaderboardsComponent } from './leaderboards/leaderboards.component';
import { MygamesComponent } from './mygames/mygames.component';
import { LobbyComponent } from './lobby/lobby.component';
import { MyspaceComponent } from './myspace/myspace.component';
import { GridComponent } from './games/battleship/grid/grid.component';
import { AdminHomeComponent } from './users/adminHome/adminhome.component';

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
      {
        path: 'myspace',
        component: MyspaceComponent
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
        component: MygamesComponent
      },
    ]
  },
  {
    path: 'leaderboard',
    component: LeaderboardsComponent
  },
  {
    path: 'test',
    component: TestPannelComponent
  },
  {
    path: 'grid',
    component: GridComponent
  },
  {
    path: 'admin',
    component: AdminHomeComponent
  },
  {
    path: 'lobby',
    component: LobbyComponent
  },
  {
    path: '**',
    pathMatch: 'full',
    redirectTo: '/login'
  }
];

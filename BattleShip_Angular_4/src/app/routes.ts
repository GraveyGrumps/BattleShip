import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UsersComponent } from './users/users.component';
import { HomeComponent } from './users/home/home.component';
import { NewGameComponent } from './games/newgame/newgame.component';
import { LeaderboardsComponent } from './leaderboards/leaderboards.component';
import { MygamesComponent } from './mygames/mygames.component';
import { LobbyComponent } from './lobby/lobby.component';
import { MyspaceComponent } from './myspace/myspace.component';
import { GridComponent } from './games/battleship/grid/grid.component';
import { SettingsComponent } from './settings/settings.component';

import { AdminHomeComponent } from './users/adminHome/adminhome.component';

import { GameComponent } from './games/battleship/game/game.component';
import { GamescreenComponent } from './games/battleship/gamescreen/gamescreen.component';


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
    component: GameComponent
  },
  {
    path: 'admin',
    component: AdminHomeComponent
  },
  {
    path: 'gamer',
    component: GamescreenComponent
  },
  {
    path: 'lobby',
    component: LobbyComponent
  },
  {
    path: 'settings',
    component: SettingsComponent
  },
  {
    path: '**',
    pathMatch: 'full',
    redirectTo: '/login'
  }
];

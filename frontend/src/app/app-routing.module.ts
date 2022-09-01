import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CanActivateViaLoggingAdministrador } from './canActivateViaLoggingAdministrador';
import { CanActivateViaLoggingEmpleado } from './canActivateViaLoggingEmpleado';
import { NotFoundComponent } from './core/not-found/not-found.component'

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./home/home.module').then(m => m.HomeModule)
  },
  {
    path: 'clientes',
    loadChildren: () => import('./clientes/clientes.module').then(m => m.ClientesModule),
    canActivateChild: [CanActivateViaLoggingEmpleado]
  },
  {
    path: 'mascotas',
    loadChildren: () => import('./mascotas/mascotas.module').then(m => m.MascotasModule),
    canActivateChild: [CanActivateViaLoggingEmpleado]
  },
  {
    path: 'prestaciones',
    loadChildren: () => import('./prestaciones/prestaciones.module').then(m => m.PrestacionesModule),
    canActivateChild: [CanActivateViaLoggingEmpleado]
  },
  {
    path: 'administracion',
    loadChildren: () => import('./administracion/administracion.module').then(m => m.AdministracionModule),
    canActivateChild: [CanActivateViaLoggingAdministrador]
  }, 
  {
    path: 'not-found',
    component: NotFoundComponent
  },
  {
    path: '**',
    redirectTo: 'not-found',
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

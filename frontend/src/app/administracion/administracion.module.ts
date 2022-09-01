import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdministracionRoutingModule } from './administracion-routing.module';
import { FormsModule } from '@angular/forms';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { UsuarioFichaComponent } from './usuarios/usuario-ficha/usuario-ficha.component';
import { UsuarioFormComponent } from './usuario-form/usuario-form.component';
import { UsuarioComponent } from './usuario/usuario.component';


@NgModule({
  declarations: [UsuariosComponent, UsuarioFichaComponent, UsuarioFormComponent, UsuarioComponent],
  imports: [
    CommonModule,
    AdministracionRoutingModule,
    FormsModule
  ]
})
export class AdministracionModule { }

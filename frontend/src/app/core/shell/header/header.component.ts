import { Component, OnInit } from '@angular/core';
import { faDotCircle } from '@fortawesome/free-regular-svg-icons';
import { faBed, faBone, faDog, faUser, faUsersCog } from '@fortawesome/free-solid-svg-icons';
import { Usuario } from 'src/app/administracion/models/usuario';
import { UsuarioService } from 'src/app/administracion/service/usuario.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  faUser =faUser;
  faDog = faDog;
  faBed = faBed;
  faBone = faBone;
  faUsersCog = faUsersCog;
  faDotCircle = faDotCircle;
  usuarios: Usuario [] = [];
  nombreUsuario: string ='';
  password: string = '';
  tipoUsuario: string = '';
  static logged: boolean = false;
  static   admin: boolean = false;

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.usuarioService.getUsuarios().subscribe((response) => this.usuarios = this.usuarioService.extraerUsuarios(response));
  }

  comprobarLogging(): void {
    this.ngOnInit();
    for (let usuario of this.usuarios) {
      if (usuario.nombre === this.nombreUsuario) {
        if (usuario.password === this.password) {
          HeaderComponent.logged = true;
          this.tipoUsuario = (usuario.tipo === 'administrador') ? 'administrador' : 'empleado';
          HeaderComponent.admin = (usuario.tipo === 'administrador');
        }
      }
    }
  }

  cerrarSesion(): void {
    this.ngOnInit();
    HeaderComponent.logged = false;
    HeaderComponent.admin = false;
    this.nombreUsuario ='';
    this.password = '';
    this.tipoUsuario = '';
  }

  getLogged(): boolean {
    return HeaderComponent.logged;
  }
}

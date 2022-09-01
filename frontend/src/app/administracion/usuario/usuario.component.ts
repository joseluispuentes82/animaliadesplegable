import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Usuario } from '../models/usuario';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styles: [
  ]
})
export class UsuarioComponent implements OnInit {
  @Input() usuario: Usuario;
  @Output() usuarioSeleccionado = new EventEmitter<Usuario>();

  constructor() { }

  ngOnInit(): void {

  }

}

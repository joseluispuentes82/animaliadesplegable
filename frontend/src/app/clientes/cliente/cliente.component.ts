import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Cliente } from '../models/cliente';
import { ClienteService } from '../service/cliente.service';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styles: [
  ]
})
export class ClienteComponent implements OnInit {
  @Input() cliente: Cliente;
  @Output() clienteSeleccionado = new EventEmitter<Cliente>();

  constructor() { }

  ngOnInit(): void {

  }

}

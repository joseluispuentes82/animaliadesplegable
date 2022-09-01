import { Usuario } from "./usuario";

export class UsuarioImpl implements Usuario {

  id: string;
  nombre: string;
  password: string;
  tipo: string;
  url?: string;

  constructor(){}
  getId(url: string): string {
    return url.slice(url.lastIndexOf('/') + 1, url.length);
  }
}

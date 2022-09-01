import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Usuario } from '../models/usuario';
import { UsuarioImpl } from '../models/usuario-impl';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {


  private host: string = environment.hostAnimalia;
  private urlEndPoint: string = `${this.host}usuarios/`;

  constructor(
    private http: HttpClient) { }

  getUsuarios(): Observable<any> {
    return this.http.get<any>(`${this.urlEndPoint}?page=0&size=1000`);
  }

  extraerUsuarios(respuestaApi: any): Usuario[] {
    const usuarios: Usuario[] = [];
    respuestaApi._embedded.usuarios.forEach(u => {
      usuarios.push(this.mapearUsuario(u));

    });
    return usuarios;
  }

  mapearUsuario(usuarioApi: any): UsuarioImpl {
    const usuario = new UsuarioImpl();
    usuario.nombre = usuarioApi.nombre;
    usuario.password = usuarioApi.password;
    usuario.tipo = usuarioApi.tipo;
    usuario.url = usuarioApi._links.self.href;
    usuario.id = usuario.getId(usuario.url);

    return usuario;
  }

  create(usuario: Usuario): Observable<any> {
    return this.http.post(`${this.urlEndPoint}`, usuario).pipe(
      catchError((e) => {
        if (e.status === 400) {
          return throwError(e);
        }
        if (e.error.mensaje) {
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    );
  }

  delete(usuario): Observable<Usuario> {
    return this.http.delete<Usuario>(`${this.urlEndPoint}${usuario.id}`)
      .pipe(
        catchError((e) => {
          if (e.status === 405) {
            console.error('El metodo está bien hecho');
          }
          return throwError(e);
        })
      );
  }

  update(usuario: Usuario): Observable<any> {
    return this.http
      .put<any>(`${this.urlEndPoint}${usuario.id}`, usuario)
      .pipe(
        catchError((e) => {
          if (e.status === 400) {
            return throwError(e);
          }
          if (e.error.mensaje) {
            console.error(e.error.mensaje);
          }
          return throwError(e);
        })
      );
  }

  getUsuario(id): Observable<any> {
    return this.http.get<Usuario>(`${this.urlEndPoint}${id}`).pipe(
      catchError((e) => {
        if (e.status !== 401 && e.error.mensaje) {
          console.error(e.error.mensaje);
        }
        return throwError(e);
      })
    );
  }
}

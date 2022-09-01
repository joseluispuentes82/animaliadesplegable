import { Injectable } from '@angular/core';
import { CanActivateChild, Router } from '@angular/router';
import { HeaderComponent } from './core/shell/header/header.component';

@Injectable()
export class CanActivateViaLoggingEmpleado implements CanActivateChild {

    constructor(private router: Router) { }

    canActivateChild() {
        // si el usuario no está loggeado le dará un alert y le llevará a "/"
        if (!HeaderComponent.logged) {
            this.router.navigate(['/']);
            alert('Debes identificarte para continuar');
            return false;
        }

        return true;
    }
}